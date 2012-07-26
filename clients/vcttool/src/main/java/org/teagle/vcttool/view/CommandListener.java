/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.custom.CTabItem;

import teagle.vct.model.Vct;

/**
 * @author sim
 * 
 */
public interface CommandListener {

	public void onNew();

	public void onOpen();

	public boolean onSave(IVctToolView vctView, Vct data);

	public boolean onSaveAs(IVctToolView vctView, Vct data);

	public void onExit();

	public void onPreferences();

	public void onDelete(IVctToolView vctView, Vct data, VctView view,
			CTabItem tab);

	public void onRefresh();
}
