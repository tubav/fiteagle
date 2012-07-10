/**
 * 
 */
package org.teagle.vcttool.control;

import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.teagle.client.TeagleClient;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.view.BookingListener;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.VctToolView;
import org.teagle.vcttool.view.dialogs.BookingResultDialog;

import teagle.vct.model.Configuration;
import teagle.vct.model.ModelManager;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.tssg.impl.TSSGVct;
import teagle.vct.util.Util;

import com.thoughtworks.xstream.XStream;


/**
 * @author sim
 *
 */
public class BookingController implements BookingListener {
	private static final int STATUS_OK = 0;
	private RootController controller;
	private CommandAdapter ca;
	private XStream xs;
	private TeagleClient client;
	

	private void prepareVCTcommand(final VctToolView vctView, final Vct data,
			final String commandStopVCT, final String textStopVCT) {
		final String[] answer = { null };		
		controller.handleProgressJob(new ProgressJob() {
			public void run() throws Exception {
				client.execVctCommand(data, answer, commandStopVCT);
			}
		}, textStopVCT);
		if (answer[0] != null)
			showRequestProcessorResult(answer[0], vctView);
	}
	
	public BookingController(RootController controller, CommandAdapter ca, VctToolConfig config)
	{
		this.controller = controller;
		this.ca = ca;
		initTeagleClient(config);
		initOrchestrationReturn();
	}

	private void initTeagleClient(VctToolConfig config) {
		URL reqProcUrl = config.getReqprocUrl();
		assert(reqProcUrl != null);
		this.client = new TeagleClient(config);
	}

	private void initOrchestrationReturn() {
		xs = Util.newXstream();
		//xs.alias("testbed", Testbed.class);
		xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		xs.alias("logentry", String.class);
		xs.processAnnotations(OrchestrateReturn.class);
	}
	
	public void onBook(final VctToolView vctView, final Vct data) {
		System.out.println("DEBUG: Booking: " +  TeagleClient.toString(data));

		if (data.isModified() && !ca.onSave(vctView, data))
				return;
		/*
		BookingConfirmationView confirmation = new BookingConfirmationView(data);
		
		vctView.showDialog(confirmation);
		
		if (confirmation.getResult() == VctToolApp.BookConfirmResult.CANCEL)
			return;
		*/
		System.out.println("do_book");
		
		final String[] answer = { null };
		
		controller.handleProgressJob(new ProgressJob() {
			public void run() throws Exception {
				client.bookVct(data, answer);
			}
		}, "booking in progress...");

		if (answer[0] != null)
			showRequestProcessorResult(answer[0], vctView);
	}
	
	private void showRequestProcessorResult(String answer, VctToolView view)
	{
		OrchestrateReturn or = (OrchestrateReturn) xs.fromXML(answer);
		System.out.println(or.message);
		
		new BookingResultDialog(view.getShell(), or.status, or.message, or.log, or.logbook).show();
		
		updateView(view, or.status);
	}

	private void updateView(VctToolView view, int status) {
		if (STATUS_OK == status)
			view.setLifecycleButtonsEnabled(true);
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

	public void onStop(final VctToolView vctView, final Vct data) {
		System.out.println("do_stop");
		final String commandStopVCT = "stopVct";
		final String textStopVCT = "stopping in progress...";
		
		prepareVCTcommand(vctView, data, commandStopVCT, textStopVCT);
	}

	public void onStart(final VctToolView vctView, final Vct data) {
		System.out.println("do_start");
		final String commandStopVCT = "startVct";
		final String textStopVCT = "start in progress...";
		
		prepareVCTcommand(vctView, data, commandStopVCT, textStopVCT);
	}
}
