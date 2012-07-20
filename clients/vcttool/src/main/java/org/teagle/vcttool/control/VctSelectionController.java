/**
 * 
 */
package org.teagle.vcttool.control;

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
import org.teagle.clients.cli.LegacyTeagleClient;

import teagle.vct.model.ModelManager;
import teagle.vct.model.Person;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;

/**
 * @author sim
 * 
 */
public class VctSelectionController {

	// private RootController root;

	private final Tree tree;
	// private String username;
	// private Composite parent;

	private final Map<String, VctController> controllers = new HashMap<String, VctController>();
	private final RootController root;

	public VctSelectionController(final RootController root,
			final String username, final Composite parent) {
		this.root = root;
		this.tree = new Tree(parent, SWT.NONE);
		this.initMouseListener();
		this.initViews();
	}

	private void initMouseListener() {
		this.tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(final MouseEvent event) {
				final TreeItem item = VctSelectionController.this.tree
						.getItem(new Point(event.x, event.y));
				if (item != null) {
					final VctController vctController = (VctController) item
							.getData();
					VctSelectionController.this.root.getView().addVctView(
							vctController
									.getView(VctSelectionController.this.root
											.getView().getVctPane()),
							vctController,
							vctController.getVct().getCommonName());
				}
			}
		});
	}

	public void initViews() {
		final LegacyTeagleClient client = new LegacyTeagleClient(this.root.getConfig()
				.getUsername(), this.root.getConfig().getPassword(), this.root
				.getConfig().getReqprocUrl(), this.root.getConfig()
				.getRepoUrl());
		final List<Vct> vcts = client.getVCTs();

		for (final Vct vct : vcts) {
			final TreeItem vctItem = new TreeItem(this.tree, SWT.NONE);
			vctItem.setText(vct.getCommonName());
			final VctController controller = new VctController(vct);
			this.controllers.put(vct.getCommonName(), controller);

			vctItem.setData(controller);

			this.tree.showItem(vctItem);
		}

	}

	public void reload() {
		this.controllers.clear();
		this.tree.removeAll();
		this.initViews();
	}

	public Control getSelectionView() {
		return this.tree;
	}

	public VctController createEmptyVct(final String username) {
		return this.createEmptyVct(username, null);
	}

	public VctController createEmptyVct(final String username, String vctName) {
		final Vct vct = ModelManager.getInstance().createVct();
		int i = 1;
		if (vctName == null)
			vctName = "Vct" + i;
		while (this.controllers.containsKey(vctName))
			vctName = "Vct" + i++;

		vct.setCommonName(vctName);
		Person person = ModelManager.getInstance().findPersonByUserName(
				username);
		if (person == null) {
			person = ModelManager.getInstance().createPerson();
			person.setUserName(username);
			person.setFullName(username);
		}
		vct.setPerson(person);
		vct.setState(VctState.State.NEW);
		final VctController controller = new VctController(vct);
		this.controllers.put(vctName, controller);
		return controller;
	}
}
