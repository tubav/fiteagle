/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teagle.vcttool.view.ConnectionView;
import org.teagle.vcttool.view.ResourceInstanceWidget;
import org.teagle.vcttool.view.VctListener;
import org.teagle.vcttool.view.VctView;

import teagle.vct.model.Configuration;
import teagle.vct.model.Connection;
import teagle.vct.model.ModelManager;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;
import teagle.vct.model.ResourceSpec;
import teagle.vct.model.Vct;
import teagle.vct.util.Util;

/**
 * @author sim
 *
 */
public class VctController implements VctListener {

	private static final Logger log = LoggerFactory.getLogger(VctController.class);
	
//	private List<ResourceInstanceControler> controlers = new ArrayList<ResourceInstanceControler>();
	
	private VctView view;

	private Vct vct;

	public VctController(Vct vct) {
		this.vct = vct;
	}

	public Vct getVct() {
		return vct;
	}

	public void save() {
		if (!vct.getResourceInstances().isEmpty() && ModelManager.getInstance().isModified(vct)) {
			ModelManager.getInstance().persist(vct);
		}
	}

	public void onResourceSpecDropped(String specName, Point position) {
		String[] comp = specName.split(":", 2);
		String provider = "";
		
		if (comp.length > 1)
		{
			specName = comp[1];
			provider = comp[0]  + ".";
		}
		else
			specName = comp[0];

		String commonName = provider + specName + "-"
		+ RandomKey.randomKey();

		for (ResourceSpec spec : ModelManager.getInstance().listResourceSpecs())
			if (spec.getCommonName().equals(specName)) {
				System.out.println("spec: " + spec.getCommonName() + "comp: "
						+ specName);
				// Validate use of Resource.
				/*EvaluationHandler handler = new EvaluationHandler();
				if (!ValidateActions.validateResourceUsage(spec, commonName, handler)) {
					Util.showMsg(view.getShell(), SWT.ERROR,
							"Resource Selection Failed", handler.getMessage());
					System.out.println("spec: " + spec.getCommonName()
							+ "comp: " + specName + " failed to be retrieved");
				} else {*/
					ResourceInstance resourceInstance = ModelManager
							.getInstance().createResourceInstance(spec);
					resourceInstance.setCommonName(commonName);
					resourceInstance.setDescription(spec.getDescription());
					resourceInstance.setState(ResourceInstanceState.State.NEW);

					addResourceInstance(resourceInstance, position);
				//}
				return;
			}
		
		//TODO: throw something
		System.out.println("Spec not found: " + specName);
	}

	private void addResourceInstance(ResourceInstance resourceInstance,
			Point position)
	{
		vct.addResourceInstance(resourceInstance);
		ResourceInstanceController controller = new ResourceInstanceController(resourceInstance);
		
		ResourceInstanceWidget widget = controller.getView(this);
		widget.setLocation(position);
		
		view.updateContent();
	}
	
	public void onResourceInstanceDropped(String instanceName, Point position)
	{
		try
		{
			//TODO: need to pull in dependencies here
			ResourceInstance instance = ModelManager.getInstance().findResourceInstanceByName(instanceName);
			addResourceInstance(instance, position);
		}
		catch (RepositoryException e)
		{
			e.printStackTrace();
		}
		
	}

	public VctView getView(Composite parent) {
		if (view == null && parent != null) {

			view = new VctView(parent);
			view.addVctListener(this);

			for (ResourceInstance instance : vct.getResourceInstances()) {
				log.debug("init resourceInstance {}", instance.getCommonName());
				
				ResourceInstanceController controler = ResourceInstanceSelectionController.findResourceInstanceController(instance.getCommonName());
				if (controler == null) {
					log.warn("vct references a non existing resource instance! ignoring");
					continue;
				}
				ResourceInstanceWidget widget = controler.getView(this);
				
				List<Configuration> referenceConfigs = controler.getReferenceConfigurations();
				for (Configuration config : referenceConfigs) {
					String[] values;
					values = config.isReferenceArray() ? config.getValue().split(",") : new String[] {config.getValue()};
					for (String value : values) {
						ConnectionView connectionView = createReferenceConnectionView(widget, config.getCommonName(), value);
						if (connectionView != null) {
							view.addConnectionView(connectionView);														
						}
					}
				}
				// controlers.add(controler);
			}

			// adding CONTAINS connections to the vct view
			for (ResourceInstance destinationResourceInstance : vct.getResourceInstances()) 
			{							
				ResourceInstanceWidget sourceWidget = null;
				ResourceInstanceWidget destinationWidget = null;

				ResourceInstance sourceResourceInstance = destinationResourceInstance.getParentInstance();
				
				if (sourceResourceInstance != null) {
					log.debug("source resource instance is {}", sourceResourceInstance.getCommonName());						
					ResourceInstanceController sourceController = ResourceInstanceSelectionController.findResourceInstanceController(sourceResourceInstance.getCommonName());
					if (sourceController != null)
						sourceWidget = sourceController.getView(this);
					else 
						Util.warn("Source not found");
				}

				log.debug("destination resource instance is {}", destinationResourceInstance.getCommonName());						
				ResourceInstanceController destinationControler = ResourceInstanceSelectionController.findResourceInstanceController(destinationResourceInstance.getCommonName());
				if (destinationControler != null)
					destinationWidget = destinationControler.getView(this);

				if (sourceWidget != null && destinationWidget != null) {
					ConnectionView connectionView = createContainsConnectionView(sourceWidget, destinationWidget);
					view.addConnectionView(connectionView);
				}
			}
		}
		return view;
	}

	public void addResourceInstance(ResourceInstanceController resourceInstanceControler) {
		vct.addResourceInstance(resourceInstanceControler.getResourceInstance());
//		controlers.add(resourceInstanceControler);
		resourceInstanceControler.getView(this);
		view.updateContent();
	}

	public void removeResourceInstance(ResourceInstanceController resourceInstanceController) {
		ResourceInstance resourceInstance = resourceInstanceController.getResourceInstance();
		vct.removeResourceInstance(resourceInstance);
//		controlers.remove(resourceInstanceControler);
	}
	
	public void importResourceInstance(ResourceInstanceController resourceInstanceController) {
		
	}

	private ConnectionView createReferenceConnectionView(ResourceInstanceWidget source, String configName, String targetName) {
		ConnectionView view = new ConnectionView(Connection.Type.REFERENCES.toString());

		view.setSource(source, source.getSrcPin(configName));
		ResourceInstanceController targetController = ResourceInstanceSelectionController.findResourceInstanceController(targetName);		
		if (targetController == null) {
			log.warn("resource instance references a non exisiting target: " + targetName);
			return null;
		}

		ResourceInstanceWidget targetWidget = targetController.getView(this);
		view.setTarget(targetWidget, targetWidget.getDstPin());

		return view;
	}

	private ConnectionView createContainsConnectionView(ResourceInstanceWidget source, ResourceInstanceWidget target) {
		ConnectionView view = new ConnectionView(Connection.Type.CONTAINS.toString());
		
		view.setSource(source, source.getSrcPin(""));
		view.setTarget(target, target.getDstPin());

		return view;
	}

}
