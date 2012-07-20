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

	private static final Logger log = LoggerFactory
			.getLogger(VctController.class);

	// private List<ResourceInstanceControler> controlers = new
	// ArrayList<ResourceInstanceControler>();

	private VctView view;

	private final Vct vct;

	public VctController(final Vct vct) {
		this.vct = vct;
	}

	public Vct getVct() {
		return this.vct;
	}

	public void save() {
		if (!this.vct.getResourceInstances().isEmpty()
				&& ModelManager.getInstance().isModified(this.vct))
			ModelManager.getInstance().persist(this.vct);
	}

	public void onResourceSpecDropped(String specName, final Point position) {
		final String[] comp = specName.split(":", 2);
		String provider = "";

		if (comp.length > 1) {
			specName = comp[1];
			provider = comp[0] + ".";
		} else
			specName = comp[0];

		final String commonName = provider + specName + "-"
				+ RandomKey.randomKey();

		for (final ResourceSpec spec : ModelManager.getInstance()
				.listResourceSpecs())
			if (spec.getCommonName().equals(specName)) {
				System.out.println("spec: " + spec.getCommonName() + "comp: "
						+ specName);
				// Validate use of Resource.
				/*
				 * EvaluationHandler handler = new EvaluationHandler(); if
				 * (!ValidateActions.validateResourceUsage(spec, commonName,
				 * handler)) { Util.showMsg(view.getShell(), SWT.ERROR,
				 * "Resource Selection Failed", handler.getMessage());
				 * System.out.println("spec: " + spec.getCommonName() + "comp: "
				 * + specName + " failed to be retrieved"); } else {
				 */
				final ResourceInstance resourceInstance = ModelManager
						.getInstance().createResourceInstance(spec);
				resourceInstance.setCommonName(commonName);
				resourceInstance.setDescription(spec.getDescription());
				resourceInstance.setState(ResourceInstanceState.State.NEW);

				this.addResourceInstance(resourceInstance, position);
				// }
				return;
			}

		// TODO: throw something
		System.out.println("Spec not found: " + specName);
	}

	private void addResourceInstance(final ResourceInstance resourceInstance,
			final Point position) {
		this.vct.addResourceInstance(resourceInstance);
		final ResourceInstanceController controller = new ResourceInstanceController(
				resourceInstance);

		final ResourceInstanceWidget widget = controller.getView(this);
		widget.setLocation(position);

		this.view.updateContent();
	}

	public void onResourceInstanceDropped(final String instanceName,
			final Point position) {
		try {
			// TODO: need to pull in dependencies here
			final ResourceInstance instance = ModelManager.getInstance()
					.findResourceInstanceByName(instanceName);
			this.addResourceInstance(instance, position);
		} catch (final RepositoryException e) {
			e.printStackTrace();
		}

	}

	public VctView getView(final Composite parent) {
		if ((this.view == null) && (parent != null)) {

			this.view = new VctView(parent);
			this.view.addVctListener(this);

			for (final ResourceInstance instance : this.vct
					.getResourceInstances()) {
				VctController.log.debug("init resourceInstance {}",
						instance.getCommonName());

				final ResourceInstanceController controler = ResourceInstanceSelectionController
						.findResourceInstanceController(instance
								.getCommonName());
				if (controler == null) {
					VctController.log
							.warn("vct references a non existing resource instance! ignoring");
					continue;
				}
				final ResourceInstanceWidget widget = controler.getView(this);

				final List<Configuration> referenceConfigs = controler
						.getReferenceConfigurations();
				for (final Configuration config : referenceConfigs) {
					String[] values;
					values = config.isReferenceArray() ? config.getValue()
							.split(",") : new String[] { config.getValue() };
					for (final String value : values) {
						final ConnectionView connectionView = this
								.createReferenceConnectionView(widget,
										config.getCommonName(), value);
						if (connectionView != null)
							this.view.addConnectionView(connectionView);
					}
				}
				// controlers.add(controler);
			}

			// adding CONTAINS connections to the vct view
			for (final ResourceInstance destinationResourceInstance : this.vct
					.getResourceInstances()) {
				ResourceInstanceWidget sourceWidget = null;
				ResourceInstanceWidget destinationWidget = null;

				final ResourceInstance sourceResourceInstance = destinationResourceInstance
						.getParentInstance();

				if (sourceResourceInstance != null) {
					VctController.log.debug("source resource instance is {}",
							sourceResourceInstance.getCommonName());
					final ResourceInstanceController sourceController = ResourceInstanceSelectionController
							.findResourceInstanceController(sourceResourceInstance
									.getCommonName());
					if (sourceController != null)
						sourceWidget = sourceController.getView(this);
					else
						Util.warn("Source not found");
				}

				VctController.log.debug("destination resource instance is {}",
						destinationResourceInstance.getCommonName());
				final ResourceInstanceController destinationControler = ResourceInstanceSelectionController
						.findResourceInstanceController(destinationResourceInstance
								.getCommonName());
				if (destinationControler != null)
					destinationWidget = destinationControler.getView(this);

				if ((sourceWidget != null) && (destinationWidget != null)) {
					final ConnectionView connectionView = this
							.createContainsConnectionView(sourceWidget,
									destinationWidget);
					this.view.addConnectionView(connectionView);
				}
			}
		}
		return this.view;
	}

	public void addResourceInstance(
			final ResourceInstanceController resourceInstanceControler) {
		this.vct.addResourceInstance(resourceInstanceControler
				.getResourceInstance());
		// controlers.add(resourceInstanceControler);
		resourceInstanceControler.getView(this);
		this.view.updateContent();
	}

	public void removeResourceInstance(
			final ResourceInstanceController resourceInstanceController) {
		final ResourceInstance resourceInstance = resourceInstanceController
				.getResourceInstance();
		this.vct.removeResourceInstance(resourceInstance);
		// controlers.remove(resourceInstanceControler);
	}

	public void importResourceInstance(
			final ResourceInstanceController resourceInstanceController) {

	}

	private ConnectionView createReferenceConnectionView(
			final ResourceInstanceWidget source, final String configName,
			final String targetName) {
		final ConnectionView view = new ConnectionView(
				Connection.Type.REFERENCES.toString());

		view.setSource(source, source.getSrcPin(configName));
		final ResourceInstanceController targetController = ResourceInstanceSelectionController
				.findResourceInstanceController(targetName);
		if (targetController == null) {
			VctController.log
					.warn("resource instance references a non exisiting target: "
							+ targetName);
			return null;
		}

		final ResourceInstanceWidget targetWidget = targetController
				.getView(this);
		view.setTarget(targetWidget, targetWidget.getDstPin());

		return view;
	}

	private ConnectionView createContainsConnectionView(
			final ResourceInstanceWidget source,
			final ResourceInstanceWidget target) {
		final ConnectionView view = new ConnectionView(
				Connection.Type.CONTAINS.toString());

		view.setSource(source, source.getSrcPin(""));
		view.setTarget(target, target.getDstPin());

		return view;
	}

}
