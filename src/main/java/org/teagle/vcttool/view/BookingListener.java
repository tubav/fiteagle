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
public interface BookingListener {

	public void onExportXml(Vct data);
	public void onRefreshState(Vct data);
	public void onShowState(Vct data);
	public void onCloneUnprovisioned(Vct data);
	void onBook(VctToolView vctView, Vct data);
	public void onStart(VctToolView vctView, Vct data);
	public void onStop(VctToolView vctToolView, Vct data);
	
}
