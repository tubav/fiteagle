/**
 * 
 */
package org.teagle.vcttool.view;

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

	void onBook(IVctToolView vctView, Vct data);

	public void onStart(IVctToolView vctView, Vct data);

	public void onStop(IVctToolView vctToolView, Vct data);

}
