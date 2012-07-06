/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import teagle.vct.model.ModelManager;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;
import teagle.vct.model.ResourceInstanceState.State;


/**
 * @author sim
 *
 */
public class ResourceInstanceSelectionController {

	private static Map<String, ResourceInstanceController> controlers = new HashMap<String, ResourceInstanceController>();

	//private RootController root;
	
	private Tree tree;
	
	public ResourceInstanceSelectionController(RootController root, String username, Composite parent) {
		//this.root = root;

		tree = new Tree(parent, SWT.NONE);
		
		System.out.println("Calling modelmanager");
		
		//List<Vct> vcts = ModelManager.getInstance().findVctsByUserName(username);
		
		System.out.println("have vcts");
		
		Collection<ResourceInstance> origInstances = ModelManager.getInstance().findResourceInstancesByUserName(username);
		LinkedList<ResourceInstance> instances = new LinkedList<ResourceInstance>();
		
		for (ResourceInstance a : origInstances) {
			if (a.getState().equals(State.PROVISIONED) &&
							! a.getDescription().contains("VNode") &&
							! a.getDescription().contains("iperf")) {
				System.out.print(" * " + a.getCommonName() + " : " + a.getState());
	 			System.out.print(" : " + a.getDescription());
	 			System.out.println();
				instances.add(a);
			}
		}
		//Collections.sort(instances);
		
		System.out.println("have instances");
		
		for (ResourceInstance instance : instances) {
			controlers.put(instance.getCommonName(), new ResourceInstanceController(instance));
			if (instance.getState() == ResourceInstanceState.State.PROVISIONED) {
				TreeItem instanceItem = new TreeItem(tree, SWT.NONE);
				instanceItem.setText(instance.getCommonName());
				instanceItem.setData(instance);
/*				for (Vct vct : vcts) {
					if (vct.getResourceInstances().contains(instance)) {
						TreeItem vctItem = new TreeItem(instanceItem, SWT.NONE);
						vctItem.setText(vct.getCommonName());
						vctItem.setData(vct);
					}
				}	*/			
			}
		}
		
		DragSource dragSource = new DragSource(tree, DND.DROP_COPY|DND.DROP_MOVE);
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		dragSource.setTransfer(types);		
		dragSource.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				Object data = tree.getSelection()[0].getData();
					String transfer = "instance:" + ((ResourceInstance)tree.getSelection()[0].getData()).getCommonName();
					event.data = transfer;					
			}
		});

	}
	
	public void addResourceInstanceControler(ResourceInstanceController controler) {
		controlers.put(controler.getResourceInstance().getCommonName(), controler);
	}
	
	public ResourceInstanceController removeResourceInstanceControler(String commonName) {
		return controlers.remove(commonName);
	}
	
	public static ResourceInstanceController findResourceInstanceController(String commonName) {
		return controlers.get(commonName);
	}
	
	public Control getSelectionView() {
		return tree;
	}

}
