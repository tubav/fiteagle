/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import teagle.vct.model.ModelManager;
import teagle.vct.model.Person;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;

/**
 * @author sim
 *
 */
public class VctSelectionController {

	//private RootController root;
	
	private Tree tree;
	private String username;
	//private Composite parent;
	
	private Map<String, VctController> controllers = new HashMap<String, VctController>();
	
	public VctSelectionController(final RootController root, String username, Composite parent) {
		//this.root = root;
		
		this.username = username;
		//this.parent = parent;
		tree = new Tree(parent, SWT.NONE);
		init();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent event) {
				TreeItem item = tree.getItem(new Point(event.x, event.y));
				if (item != null) {
					VctController vctController = (VctController)item.getData();
					root.getView().addVctView(vctController.getView(root.getView().getVctPane()), vctController, vctController.getVct().getCommonName());
				}
			}
		});

	}
	
	public void init()
	{
		
		List<Vct> vcts = ModelManager.getInstance().findVctsByUserName(username);
		Collections.sort(vcts, new Comparator<Vct>() {

			public int compare(Vct o1, Vct o2) {
				return o1.getCommonName().toLowerCase().compareTo(o2.getCommonName().toLowerCase());
			}
		});
		for (Vct vct : vcts) {
			TreeItem vctItem = new TreeItem(tree, SWT.NONE);
			vctItem.setText(vct.getCommonName());
			VctController controller = new VctController(vct);
			controllers.put(vct.getCommonName(), controller);
			
			vctItem.setData(controller);
			
			tree.showItem(vctItem);
		}
		

	}
	
	public void reload()
	{
		controllers.clear();
		tree.removeAll();
		init();
		
		
	}
	
	public Control getSelectionView() {
		return tree;
	}
	
	

	public VctController createEmptyVct(String username) {
		return createEmptyVct(username, null);
	}
	
	public VctController createEmptyVct(String username, String vctName) {
		Vct vct = ModelManager.getInstance().createVct();
		int i = 1;
		if (vctName == null)
			vctName = "Vct" + i;
		while (controllers.containsKey(vctName)) 
			vctName = "Vct" + i++;
		
		vct.setCommonName(vctName);
		Person person = ModelManager.getInstance().findPersonByUserName(username);
		if (person == null) {
			person = ModelManager.getInstance().createPerson();
			person.setUserName(username);
			person.setFullName(username);
		}
		vct.setPerson(person);
		vct.setState(VctState.State.NEW);
		VctController controller = new VctController(vct);
		controllers.put(vctName, controller);
		return controller;
	}
}
