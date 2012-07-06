/**
 * 
 */
package org.teagle.vcttool.control;

import org.eclipse.swt.custom.CTabItem;
import org.teagle.vcttool.app.ProgressException;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.app.VctToolApp;
import org.teagle.vcttool.view.CommandAdapter;
import org.teagle.vcttool.view.VctToolView;
import org.teagle.vcttool.view.VctView;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepoClientConfig;
import teagle.vct.model.Vct;

/**
 * @author sim
 *
 */
public class RootController {

	private VctToolConfig config;
	
	private VctToolView view;
	
	private ResourceSpecSelectionController resourceSpecSelectionController;
	private VctSelectionController vctSelectionController;
	private ResourceInstanceSelectionController resourceInstanceSelectionController;

	private BookingController bookingController;
	
	//private VctToolApp app;
	private CommandAdapter ca;
	
	public RootController(VctToolApp app, VctToolView toolView, VctToolConfig config, CommandAdapter ca) {
		view = toolView;
		this.config = config;
		//this.app = app;
		this.ca = ca;
		
		this.view.addCommandListener(new CommandAdapter(this) {
			@Override
			public void onNew() {
				VctController empty = createEmptyVct();
				addVct(empty);	
			}
			
			@Override
			public void onOpen(){
				//TODO
				System.out.println("Root: onOpen");
				//vctSelectionController.getSelectionView();
//				VctController controller = (VctController)view.getVctPane().getSelection().getData();
//				controller.onOpen();
			}

			@Override
			public boolean onSave(VctToolView vctView, Vct data) {
				//VctController controller = (VctController)view.getVctPane().getSelection().getData();
				//controller.save();
				return false;
			}
			
			@Override 
			public boolean onSaveAs(VctToolView vctView, Vct data) {
				return false;
			}
			
			@Override  
			public void onDelete(VctToolView vctView, Vct data, VctView view, CTabItem tab)
			{
				
			}
		});
		
		ModelManager.getInstance().config(new RepoClientConfig(config.getRepoUrl(), config.getUsername(), config.getPassword(), config.getDoPrefetching()));
	}
	
	public void init() {
		System.out.println("creating resourceInstanceSelectionController");
		
		resourceInstanceSelectionController = new ResourceInstanceSelectionController(this, config.getUsername(), view.getSelectionPane());
		view.addSelectionControl("Resource Instances",resourceInstanceSelectionController.getSelectionView(), -1);

		System.out.println("creating vctSelectionController");
		
		vctSelectionController = new VctSelectionController(this, config.getUsername(), view.getSelectionPane());
		view.addSelectionControl("VCTs", vctSelectionController.getSelectionView(), -1);
		
		System.out.println("creating VctController");
		
		VctController empty = vctSelectionController.createEmptyVct(config.getUsername());		
		view.addVctView(empty.getView(view.getVctPane()), empty, empty.getVct().getCommonName());		

		System.out.println("creating resourceSpecSelectionController");
		
		resourceSpecSelectionController = new ResourceSpecSelectionController(this, view.getSelectionPane());
		view.addSelectionControl("Resource Specs", resourceSpecSelectionController.getSelectionView(), 0);

		System.out.println("creating bookingController");
		
		bookingController = new BookingController(this, ca, config);
		view.addBookingListener(bookingController);
	}
	
	public void reloadVcts()
	{
		vctSelectionController.reload();
	}

	public VctToolView getView() {
		return view;
	}
	
	public void runProgressJob(ProgressJob job, String text) throws ProgressException
	{
		ProgressDialogController c = new ProgressDialogController(view, text);
		c.run(job);
	}
	
	public void runProgressJob(ProgressJob job) throws ProgressException
	{
		runProgressJob(job, "Operation in progress...");
	}
	
	public void handleProgressJob(ProgressJob job, String text)
	{
		try
		{
			runProgressJob(job, text);
		}
		catch (ProgressException e)
		{
			view.showException(e);
		}
	}
	
	private VctController createEmptyVct()
	{
		return createEmptyVct(null);
	}
	
	VctController createEmptyVct(String name)
	{
		return vctSelectionController.createEmptyVct(config.getUsername(), name); 
	}
	
	void addVct(VctController vctController)
	{
		view.addVctView(vctController.getView(view.getVctPane()), vctController, vctController.getVct().getCommonName());
	}
	
	public ResourceInstanceSelectionController getResourceInstanceSelectionController()
	{
		return resourceInstanceSelectionController;
	}
}
