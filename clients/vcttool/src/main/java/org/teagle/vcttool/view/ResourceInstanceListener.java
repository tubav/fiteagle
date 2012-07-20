/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.graphics.Point;

/**
 * @author sim
 * 
 */
public interface ResourceInstanceListener {
	public void onMoved(Point position);

	public void onEditConfig(VctView vctView);

	public void onHelp(VctView vctView);

	public void onDelete(ResourceInstanceWidget widget);
}
