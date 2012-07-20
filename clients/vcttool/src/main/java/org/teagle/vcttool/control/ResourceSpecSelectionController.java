/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import teagle.vct.app.Availability;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Organisation;
import teagle.vct.model.Ptm;
import teagle.vct.model.ResourceSpec;
import teagle.vct.tssg.impl.TSSGPtm;
import teagle.vct.tssg.impl.TSSGResourceSpec;

/**
 * @author sim
 * 
 */
public class ResourceSpecSelectionController {

	// private RootController root;

	private final Composite content;
	private final Tree tree;

	public ResourceSpecSelectionController(final RootController root,
			final Composite parent) {
		// this.root = root;

		this.content = new Composite(parent, SWT.NONE);
		this.content.setLayout(new GridLayout(1, false));
		this.content.setBackground(this.content.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));

		final ExpandBar expandBar = new ExpandBar(this.content, SWT.NONE);
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		expandBar.setSpacing(0);

		final Composite expandContent = new Composite(expandBar, SWT.NONE);
		expandContent.setLayout(new FillLayout());
		final Group group = new Group(expandContent, SWT.NONE);
		group.setLayout(new RowLayout());

		final Button filterButton = new Button(group, SWT.PUSH);
		filterButton.setText("Apply");

		final ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setHeight(expandContent
				.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		expandItem.setText("Filter");
		expandItem.setControl(expandContent);
		expandItem.setImage(new Image(this.content.getDisplay(), this
				.getClass().getResourceAsStream("/icons/filter_ps.gif")));
		expandItem.setExpanded(false);

		expandBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(final MouseEvent arg0) {
				ResourceSpecSelectionController.this.content.layout();
			}
		});

		this.tree = new Tree(this.content, SWT.NONE);
		this.tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.initDragDrop();
		this.initViews();
	}

	private void initDragDrop() {
		final DragSource dragSource = new DragSource(this.tree, DND.DROP_COPY
				| DND.DROP_MOVE);
		final Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		dragSource.setTransfer(types);
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(final DragSourceEvent event) {
				final Object data = ResourceSpecSelectionController.this.tree
						.getSelection()[0].getData();
				if (data instanceof Ptm) {
					String transfer = ((TSSGPtm) ResourceSpecSelectionController.this.tree
							.getSelection()[0].getData()).getCommonName();
					transfer += ":"
							+ ((TSSGResourceSpec) ResourceSpecSelectionController.this.tree
									.getSelection()[0].getParentItem()
									.getData()).getCommonName();
					System.out.println("transfer: " + transfer + "\n");
					// String transfer =
					// ((ResourceSpec)tree.getSelection()[0].getData()).getCommonName();
					// transfer += ":" +
					// ((Ptm)tree.getSelection()[0].getParentItem().getData()).getCommonName();
					event.data = transfer;
				} else if (data instanceof ResourceSpec) {
					final String transfer = ((ResourceSpec) ResourceSpecSelectionController.this.tree
							.getSelection()[0].getData()).getCommonName();
					event.data = transfer;
				}
			}
		});
	}

	private void initViews() {
		final List<? extends Ptm> ptms = ModelManager.getInstance().listPtms();
		final List<Organisation> orgs = new ArrayList<Organisation>();
		final Map<ResourceSpec, List<Availability>> resourceMap = new HashMap<ResourceSpec, List<Availability>>();

		List<? extends ResourceSpec> resources = ModelManager.getInstance()
				.listResourceSpecs();
		for (final ResourceSpec resource : resources)
			resourceMap.put(resource, null);

		final java.util.Map<ResourceSpec, java.util.List<Ptm>> specifications = new HashMap<ResourceSpec, java.util.List<Ptm>>();

		for (final ResourceSpec spec : resourceMap.keySet()) {
			java.util.List<Ptm> p = null;
			if (!specifications.containsKey(spec)) {
				p = new ArrayList<Ptm>();
				specifications.put(spec, p);
			} else
				p = specifications.get(spec);
			for (final Ptm ptm : ptms)
				if (ptm.getResourceSpecs().contains(spec)) {
					orgs.add(ptm.getOrganisation());
					p.add(ptm);
				}
		}

		resources = new ArrayList<ResourceSpec>(specifications.keySet());

		Collections.sort(resources, new Comparator<ResourceSpec>() {
			public int compare(final ResourceSpec a, final ResourceSpec b) {
				return a.getCommonName().trim()
						.compareToIgnoreCase(b.getCommonName().trim());
			}
		});

		for (final ResourceSpec spec : resources) {
			final TreeItem item = new TreeItem(this.tree, SWT.NONE);
			item.setText(spec.getCommonName().trim());
			item.setData(spec);

			this.tree.showItem(item);

			final java.util.List<Ptm> p = specifications.get(spec);

			Collections.sort(p, new Comparator<Ptm>() {
				public int compare(final Ptm a, final Ptm b) {
					return a.getCommonName().trim()
							.compareToIgnoreCase(b.getCommonName().trim());
				}
			});

			for (final Ptm ptm : p) {
				final TreeItem res = new TreeItem(item, SWT.NONE);
				res.setText(ptm.getCommonName());
				res.setData(ptm);
				this.tree.showItem(res);
			}
		}
	}

	public Control getSelectionView() {
		return this.content;
	}

	public void reload() {
		this.tree.removeAll();
		this.initViews();
	}

}
