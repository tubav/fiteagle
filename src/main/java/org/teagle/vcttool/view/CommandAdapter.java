/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.teagle.vcttool.app.ValidateActions;
import org.teagle.vcttool.control.RootController;
import org.teagle.vcttool.view.dialogs.InputDialog;
import org.teagle.vcttool.view.dialogs.MessageDialog;

import de.fhg.fokus.ngni.openpe.pem1.EvaluationHandler;

import teagle.vct.model.ModelManager;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.Vct;
import teagle.vct.tssg.impl.TSSGVct;
import teagle.vct.util.Util;

/**
 * @author sim
 * 
 */
public class CommandAdapter implements CommandListener {
	RootController rootController;

	public CommandAdapter(RootController rootController) {
		this.rootController = rootController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.teagle.vcttool.view.CommandListener#onNew()
	 */
	public void onNew() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.teagle.vcttool.view.CommandListener#onSave()
	 */
	public boolean onSave(VctToolView vctView, Vct data) {
		if (data.isModified()) {
			if (!data.isPersisted())
				return onSaveAs(vctView, data);
			else
				data = (Vct) data.persist();
		} else {
			System.out.println("wasn't changed");
		}

		return true;
	}

	public void onOpen() {
		System.out.println("open");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.teagle.vcttool.view.CommandListener#onSaveAs()
	 */
	public boolean onSaveAs(VctToolView vctView, Vct data) {

		EvaluationHandler handler = new EvaluationHandler();
		if (!ValidateActions.validateVCT(data, handler)) {
			Util.showMsg(vctView.getShell(), SWT.ERROR,
					"VCT failed to be saved", handler.getMessage());
			System.out.println("Failed to save VCT");
		} else {

			InputDialog dialog = new InputDialog(vctView.getShell(),
					"Save VCT as...");
			dialog.addInputField("Name", "", "VCT-Name");
			switch (dialog.show()) {
			case SWT.OK:
				// String vctName = ((TSSGVct)data).getPerson().getUserName() +
				// "_"
				// + dialog.getName();

				String vctName = dialog.getName();
				if (dialog.getName().equals("")) {
					vctView.showError("No name given.");
					System.out.println("no name given");
				} else if (!ModelManager.getInstance().vctExists(vctName,
						((TSSGVct) data).getPerson().getUserName())) {
					((TSSGVct) data).setCommonName(dialog.getName());
					data = ((TSSGVct) data).persist();
					rootController.reloadVcts();
					return true;
				} else
					vctView.showError("VCT name already taken.");

				break;
			case SWT.CANCEL:
				break;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.teagle.vcttool.view.CommandListener#onExit()
	 */
	public void onExit() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.teagle.vcttool.view.CommandListener#onPreferences()
	 */
	public void onPreferences() {
	}

	// @Override
	public void onDelete(VctToolView vctToolView, Vct data, VctView view,
			CTabItem tab) {
		MessageDialog d = new MessageDialog(vctToolView.getShell(),
				"Are you sure? This can not be undone.", SWT.ICON_QUESTION
						| SWT.OK | SWT.CANCEL);

		if (d.getResult() != SWT.OK) {
			System.out.println("Deletion canceled");
			return;
		}

		System.out.println("deleting");
		if (data.isPersisted())
			try {
				data.delete();
			} catch (RepositoryException e) {
				e.printStackTrace();
				vctToolView.showException(e);
				return;
			}
		System.out.println("deleted");
		view.dispose();
		tab.dispose();
		if (rootController != null)
			rootController.reloadVcts();

	}

	public void setRootController(RootController c) {
		this.rootController = c;
	}

}
