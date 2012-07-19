/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.Collection;
import java.util.HashMap;
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
import org.teagle.api.TeagleClient;

import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceInstanceState;


/**
 * @author sim
 *
 */
public class ResourceInstanceSelectionController {

	private static Map<String, ResourceInstanceController> controlers = new HashMap<String, ResourceInstanceController>();

	//private RootController root;
	
	private Tree tree;
	private TeagleClient teagleClient;

	private Composite parent;
	
	public ResourceInstanceSelectionController(RootController root, String username, Composite parent) {
		//this.root = root;
		
		this.teagleClient = new TeagleClient(root.getConfig());
		this.parent = parent;
		
		tree = new Tree(parent, SWT.NONE);
		DragSource dragSource = new DragSource(tree, DND.DROP_COPY|DND.DROP_MOVE);
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		dragSource.setTransfer(types);		
		dragSource.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
					//Object data = tree.getSelection()[0].getData();
					String transfer = "instance:" + ((ResourceInstance)tree.getSelection()[0].getData()).getCommonName();
					event.data = transfer;					
			}
		});
		init();
	}

	private void init() {
		Collection<ResourceInstance> instances = teagleClient.getResourceInstances();
		
		for (ResourceInstance instance : instances) {
			controlers.put(instance.getCommonName(), new ResourceInstanceController(instance));
			if (instance.getState() == ResourceInstanceState.State.PROVISIONED) {
				TreeItem instanceItem = new TreeItem(tree, SWT.NONE);
				instanceItem.setText(instance.getCommonName());
				instanceItem.setData(instance);			
			}
		}		

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

	public void reload() {
		controlers.clear();
		tree.removeAll();
		init();		
	}

}
