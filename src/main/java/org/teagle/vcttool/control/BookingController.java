/**
 * 
 */
package org.teagle.vcttool.control;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.view.BookingListener;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.VctToolView;
import org.teagle.vcttool.view.dialogs.BookingResultDialog;

import com.thoughtworks.xstream.XStream;

import teagle.vct.model.Configuration;
import teagle.vct.model.ModelManager;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;
import teagle.vct.util.Util;

/**
 * @author sim
 *
 */
public class BookingController implements BookingListener {
	private RootController controller;
	private CommandAdapter ca;
	
	//TODO: do this through jax
	private XStream xs;
	private URL reqProcUrl;
	
	public BookingController(RootController controller, CommandAdapter ca, VctToolConfig config)
	{
		this.controller = controller;
		this.ca = ca;
		this.reqProcUrl = config.getReqprocUrl();
		assert(reqProcUrl != null);
		
		xs = Util.newXstream();
		//xs.alias("testbed", Testbed.class);
		xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		xs.alias("logentry", String.class);
		xs.processAnnotations(OrchestrateReturn.class);
	}
	
	public void onBook(final VctToolView vctView, final Vct data) {
		if (data.isModified() && !ca.onSave(vctView, data))
				return;
		/*
		BookingConfirmationView confirmation = new BookingConfirmationView(data);
		
		vctView.showDialog(confirmation);
		
		if (confirmation.getResult() == VctToolApp.BookConfirmResult.CANCEL)
			return;
		*/
		System.out.println("do_book");
		
		data.setState(VctState.State.INPROGRESS_SYNC);
		data.persist();
		final String[] answer = { null };
		
		controller.handleProgressJob(new ProgressJob() {
			

			public void run() throws Exception {
				try
				{
					URLConnection conn = reqProcUrl.openConnection();
					conn.setDoOutput(true);
					conn.setRequestProperty("Content-Type", "text/plain");
			
					String req = "<string-array><string>VctRegistry</string><string>setVct</string><string>"
							+ data.getPerson().getUserName()
							+ "</string><string>"
							+ data.getCommonName()
							+ "</string></string-array>";
					conn.getOutputStream().write(req.getBytes());
					answer[0] = Util.readStream(conn.getInputStream());
					Util.debug("Answer from reqproc: " + answer[0]);
				}
				catch (IOException e)
				{
					e.printStackTrace();
					throw e;
				}
			}
		}, "booking in progress...");

		if (answer[0] != null)
			showBookingResult(answer[0], vctView);
		
	}
	
	private void showBookingResult(String answer, VctToolView view)
	{
		OrchestrateReturn or = (OrchestrateReturn) xs.fromXML(answer);
		
		System.out.println(or.message);
		
		BookingResultDialog d = new BookingResultDialog(view.getShell(), or.status, or.message, or.log, or.logbook);
		d.show();
	}

	public void onExportXml(Vct data) {
		// TODO Auto-generated method stub
		
	}

	public void onRefreshState(Vct data) {
		// TODO Auto-generated method stub
		
	}

	public void onShowState(Vct data) {
		// TODO Auto-generated method stub
		
	}

	public void onCloneUnprovisioned(Vct data) {
		System.out.println("Cloning: " + data.getCommonName());
		String name = data.getCommonName();
		if (!name.endsWith("-cloned"))
			name += "-cloned";

		VctController newVctController = controller.createEmptyVct(name);
		Vct newVct = newVctController.getVct();
		HashMap<String, String> nameMapping = new HashMap<String, String>();
		HashMap<ResourceInstance, ResourceInstance> instanceMapping = new HashMap<ResourceInstance, ResourceInstance>();
		
		for (ResourceInstance instance : data.getResourceInstances())
		{
			ResourceInstance newInstance = ModelManager.getInstance().createResourceInstance(instance.getResourceSpec());
			for (Configuration config : instance.getConfigurations())
				for (Configuration newConfig : newInstance.getConfigurations())
					if (config.getCommonName().equals(newConfig.getCommonName()))
						newConfig.setValue(config.getValue());
			
			newInstance.setCommonName(instance.getResourceSpec().getCommonName()+ "-" + RandomKey.randomKey());
			nameMapping.put(instance.getCommonName(), newInstance.getCommonName());

			instanceMapping.put(instance, newInstance);
			
//			ResourceInstanceWidget widget = controller.getView(newVct);
			//widget.setLocation(position);			
			newInstance.setGeometry(ModelManager.getInstance().createGeometry(instance.getGeometry().getX(), instance.getGeometry().getY()));
			newVct.addResourceInstance(newInstance);

			
		}
		
		for (ResourceInstance newInstance : newVct.getResourceInstances())
		{
			System.out.println("Fixing config for: " + newInstance.getCommonName());
			for (Configuration newConfig : newInstance.getConfigurations())
				if (newConfig.getConfigParamAtomic().isReference())
				{
					System.out.println("Considering: " + newConfig.getConfigParamAtomic().getCommonName() + " which is currently: " + newConfig.getValue());
					String newName = nameMapping.get(newConfig.getValue());
					if (newName != null)
					{
						System.out.println("setting: " + newConfig.getConfigParamAtomic() + " to " + newName);
						newConfig.setValue(newName);
					}
				}
				else
					System.out.println("Not considering: " + newConfig.getConfigParamAtomic().getCommonName() + " (" + newConfig.getConfigParamAtomic().getType() + ")");

			ResourceInstanceController ricontroller = new ResourceInstanceController(newInstance);
			this.controller.getResourceInstanceSelectionController().addResourceInstanceControler(ricontroller);
			//newVctController.addResourceInstance(ricontroller);
		}
	
		controller.addVct(newVctController);
	}

}
