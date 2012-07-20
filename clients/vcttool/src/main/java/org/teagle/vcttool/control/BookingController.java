/**
 * 
 */
package org.teagle.vcttool.control;

import java.net.URL;
import java.util.HashMap;

import org.teagle.clients.cli.LegacyTeagleClient;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.view.BookingListener;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.VctToolView;
import org.teagle.vcttool.view.dialogs.BookingResultDialog;

import teagle.vct.model.Configuration;
import teagle.vct.model.ModelManager;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.util.OrchestrateReturn;
import teagle.vct.util.Util;

import com.thoughtworks.xstream.XStream;

/**
 * @author sim
 * 
 */
public class BookingController implements BookingListener {
	private static final int STATUS_OK = 0;
	private final RootController controller;
	private final CommandAdapter ca;
	private XStream xs;
	private LegacyTeagleClient client;

	private void prepareVCTcommand(final VctToolView vctView, final Vct data,
			final String commandStopVCT, final String textStopVCT) {
		final String[] answer = { null };
		this.controller.handleProgressJob(new ProgressJob() {
			public void run() throws Exception {
				BookingController.this.client.execVctCommand(data, answer,
						commandStopVCT);
			}
		}, textStopVCT);
		if (answer[0] != null)
			this.showRequestProcessorResult(answer[0], vctView);
	}

	public BookingController(final RootController controller,
			final CommandAdapter ca, final VctToolConfig config) {
		this.controller = controller;
		this.ca = ca;
		this.initTeagleClient(config);
		this.initOrchestrationReturn();
	}

	private void initTeagleClient(final VctToolConfig config) {
		final URL reqProcUrl = config.getReqprocUrl();
		assert (reqProcUrl != null);
		this.client = new LegacyTeagleClient(config.getUsername(),
				config.getPassword(), config.getReqprocUrl(),
				config.getRepoUrl());
	}

	private void initOrchestrationReturn() {
		this.xs = Util.newXstream();
		// xs.alias("testbed", Testbed.class);
		this.xs.alias("idmapping", OrchestrateReturn.Result.Mapping[].class);
		this.xs.alias("logentry", String.class);
		this.xs.processAnnotations(OrchestrateReturn.class);
	}

	public void onBook(final VctToolView vctView, final Vct data) {
		System.out.println("DEBUG: Booking: " + LegacyTeagleClient.toString(data));

		if (data.isModified() && !this.ca.onSave(vctView, data))
			return;
		/*
		 * BookingConfirmationView confirmation = new
		 * BookingConfirmationView(data);
		 * 
		 * vctView.showDialog(confirmation);
		 * 
		 * if (confirmation.getResult() == VctToolApp.BookConfirmResult.CANCEL)
		 * return;
		 */
		System.out.println("do_book");

		final String[] answer = { null };

		this.controller.handleProgressJob(new ProgressJob() {
			public void run() throws Exception {
				BookingController.this.client.bookVct(data, answer);
			}
		}, "booking in progress...");

		if (answer[0] != null)
			this.showRequestProcessorResult(answer[0], vctView);
	}

	private void showRequestProcessorResult(final String answer,
			final VctToolView view) {
		final OrchestrateReturn or = (OrchestrateReturn) this.xs
				.fromXML(answer);
		System.out.println(or.message);

		new BookingResultDialog(view.getShell(), or.status, or.message, or.log,
				or.logbook).show();

		this.updateView(view, or.status);
	}

	private void updateView(final VctToolView view, final int status) {
		if (BookingController.STATUS_OK == status)
			view.setLifecycleButtonsEnabled(true);
	}

	public void onExportXml(final Vct data) {
		// TODO Auto-generated method stub

	}

	public void onRefreshState(final Vct data) {
		// TODO Auto-generated method stub

	}

	public void onShowState(final Vct data) {
		// TODO Auto-generated method stub

	}

	public void onCloneUnprovisioned(final Vct data) {
		System.out.println("Cloning: " + data.getCommonName());
		String name = data.getCommonName();
		if (!name.endsWith("-cloned"))
			name += "-cloned";

		final VctController newVctController = this.controller
				.createEmptyVct(name);
		final Vct newVct = newVctController.getVct();
		final HashMap<String, String> nameMapping = new HashMap<String, String>();
		final HashMap<ResourceInstance, ResourceInstance> instanceMapping = new HashMap<ResourceInstance, ResourceInstance>();

		for (final ResourceInstance instance : data.getResourceInstances()) {
			final ResourceInstance newInstance = ModelManager.getInstance()
					.createResourceInstance(instance.getResourceSpec());
			for (final Configuration config : instance.getConfigurations())
				for (final Configuration newConfig : newInstance
						.getConfigurations())
					if (config.getCommonName()
							.equals(newConfig.getCommonName()))
						newConfig.setValue(config.getValue());

			newInstance.setCommonName(instance.getResourceSpec()
					.getCommonName() + "-" + RandomKey.randomKey());
			nameMapping.put(instance.getCommonName(),
					newInstance.getCommonName());

			instanceMapping.put(instance, newInstance);

			// ResourceInstanceWidget widget = controller.getView(newVct);
			// widget.setLocation(position);
			newInstance.setGeometry(ModelManager.getInstance().createGeometry(
					instance.getGeometry().getX(),
					instance.getGeometry().getY()));
			newVct.addResourceInstance(newInstance);

		}

		for (final ResourceInstance newInstance : newVct.getResourceInstances()) {
			System.out.println("Fixing config for: "
					+ newInstance.getCommonName());
			for (final Configuration newConfig : newInstance
					.getConfigurations())
				if (newConfig.getConfigParamAtomic().isReference()) {
					System.out.println("Considering: "
							+ newConfig.getConfigParamAtomic().getCommonName()
							+ " which is currently: " + newConfig.getValue());
					final String newName = nameMapping
							.get(newConfig.getValue());
					if (newName != null) {
						System.out.println("setting: "
								+ newConfig.getConfigParamAtomic() + " to "
								+ newName);
						newConfig.setValue(newName);
					}
				} else
					System.out.println("Not considering: "
							+ newConfig.getConfigParamAtomic().getCommonName()
							+ " (" + newConfig.getConfigParamAtomic().getType()
							+ ")");

			final ResourceInstanceController ricontroller = new ResourceInstanceController(
					newInstance);
			this.controller.getResourceInstanceSelectionController()
					.addResourceInstanceControler(ricontroller);
			// newVctController.addResourceInstance(ricontroller);
		}

		this.controller.addVct(newVctController);
	}

	public void onStop(final VctToolView vctView, final Vct data) {
		System.out.println("do_stop");
		final String commandStopVCT = "stopVct";
		final String textStopVCT = "stopping in progress...";

		this.prepareVCTcommand(vctView, data, commandStopVCT, textStopVCT);
	}

	public void onStart(final VctToolView vctView, final Vct data) {
		System.out.println("do_start");
		final String commandStopVCT = "startVct";
		final String textStopVCT = "start in progress...";

		this.prepareVCTcommand(vctView, data, commandStopVCT, textStopVCT);
	}
}
