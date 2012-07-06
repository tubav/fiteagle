/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.graphics.Point;

/**
 * @author sim
 *
 */
public interface VctListener {
	public void onResourceSpecDropped(String specName, Point position);
	public void onResourceInstanceDropped(String instanceName, Point position);
}
