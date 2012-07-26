/**
 * 
 */
package org.teagle.vcttool.control;

import org.eclipse.swt.custom.CTabItem;
import org.teagle.vcttool.app.ProgressException;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.app.VctToolApp;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.IVctToolView;
import org.teagle.vcttool.view.VctView;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.Vct;

/**
 * @author sim
 * 
 */
public class RootController {

	private final VctToolConfig config;

	private final IVctToolView view;

	private ResourceSpecSelectionController resourceSpecSelectionController;
	private VctSelectionController vctSelectionController;
	private ResourceInstanceSelectionController resourceInstanceSelectionController;

	private BookingController bookingController;

	// private VctToolApp app;
	private final CommandAdapter ca;

	public VctToolConfig getConfig() {
		return this.config;
	}

	public RootController(final VctToolApp app, final IVctToolView toolView,
			final VctToolConfig config, final CommandAdapter ca) {
		this.view = toolView;
		this.config = config;
		this.ca = ca;
		this.addCommandListeners();
		ModelManager.getInstance().config(
				new RepoClientConfig(config.getRepoUrl(), config.getUsername(),
						config.getPassword(), config.getDoPrefetching()));
	}

	private void addCommandListeners() {
		this.view.addCommandListener(new CommandAdapter(this) {
			@Override
			public void onNew() {
				final VctController empty = RootController.this
						.createEmptyVct();
				RootController.this.addVct(empty);
			}

			@Override
			public void onOpen() {
				// TODO
				System.out.println("Root: onOpen");
				// vctSelectionController.getSelectionView();
				// VctController controller =
				// (VctController)view.getVctPane().getSelection().getData();
				// controller.onOpen();
			}

			@Override
			public boolean onSave(final IVctToolView vctView, final Vct data) {
				// VctController controller =
				// (VctController)view.getVctPane().getSelection().getData();
				// controller.save();
				return false;
			}

			@Override
			public boolean onSaveAs(final IVctToolView vctView, final Vct data) {
				return false;
			}

			@Override
			public void onDelete(final IVctToolView vctView, final Vct data,
					final VctView view, final CTabItem tab) {

			}
		});
	}

	public void init() {
		System.out.println("creating resourceInstanceSelectionController");

		this.resourceInstanceSelectionController = new ResourceInstanceSelectionController(
				this, this.config.getUsername(), this.view.getSelectionPane());
		this.view
				.addSelectionControl("Resource Instances",
						this.resourceInstanceSelectionController
								.getSelectionView(), -1);

		System.out.println("creating vctSelectionController");

		this.vctSelectionController = new VctSelectionController(this,
				this.config.getUsername(), this.view.getSelectionPane());
		this.view.addSelectionControl("VCTs",
				this.vctSelectionController.getSelectionView(), -1);

		System.out.println("creating VctController");

		final VctController empty = this.vctSelectionController
				.createEmptyVct(this.config.getUsername());
		this.view.addVctView(empty.getView(this.view.getVctPane()), empty,
				empty.getVct().getCommonName());

		System.out.println("creating resourceSpecSelectionController");

		this.resourceSpecSelectionController = new ResourceSpecSelectionController(
				this, this.view.getSelectionPane());
		this.view.addSelectionControl("Resource Specs",
				this.resourceSpecSelectionController.getSelectionView(), 0);

		System.out.println("creating bookingController");

		this.bookingController = new BookingController(this, this.ca,
				this.config);
		this.view.addBookingListener(this.bookingController);
	}

	public void reloadAll() {
		this.reloadVcts();
		this.reloadInstances();
		this.reloadSpecs();
	}

	public void reloadVcts() {
		this.vctSelectionController.reload();
	}

	public void reloadSpecs() {
		this.resourceSpecSelectionController.reload();
	}

	public void reloadInstances() {
		this.resourceInstanceSelectionController.reload();
	}

	public IVctToolView getView() {
		return this.view;
	}

	public void runProgressJob(final ProgressJob job, final String text)
			throws ProgressException {
		final ProgressDialogController c = new ProgressDialogController(
				this.view, text);
		c.run(job);
	}

	public void runProgressJob(final ProgressJob job) throws ProgressException {
		this.runProgressJob(job, "Operation in progress...");
	}

	public void handleProgressJob(final ProgressJob job, final String text) {
		try {
			this.runProgressJob(job, text);
		} catch (final ProgressException e) {
			this.view.showException(e);
		}
	}

	private VctController createEmptyVct() {
		return this.createEmptyVct(null);
	}

	VctController createEmptyVct(final String name) {
		return this.vctSelectionController.createEmptyVct(
				this.config.getUsername(), name);
	}

	void addVct(final VctController vctController) {
		this.view.addVctView(vctController.getView(this.view.getVctPane()),
				vctController, vctController.getVct().getCommonName());
	}

	public ResourceInstanceSelectionController getResourceInstanceSelectionController() {
		return this.resourceInstanceSelectionController;
	}
}
