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
	public boolean onSave(VctToolView vctView, Vct data);
	public boolean onSaveAs(VctToolView vctView, Vct data);
	public void onExit();
	
	public void onPreferences();
	public void onDelete(VctToolView vctView, Vct data, VctView view, CTabItem tab);
	public void onRefresh();
}
