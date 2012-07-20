/**
 * 
 */
package org.teagle.vcttool.view;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

/**
 * @author sim
 * 
 */
public class ConnectionEvent {
	public CLabel sourceLabel;
	public ResourceInstanceWidget source;

	public ResourceInstanceWidget target;

	public Composite parent;
}
