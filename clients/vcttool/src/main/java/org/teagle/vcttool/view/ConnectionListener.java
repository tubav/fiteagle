/**
 * 
 */
package org.teagle.vcttool.view;

/**
 * @author sim
 * 
 */
public interface ConnectionListener {
	public void onConnectionNew(ConnectionEvent event);

	public void onConnectionDeleted(ConnectionEvent event);
}
