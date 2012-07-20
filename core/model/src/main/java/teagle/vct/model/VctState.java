/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 *
 */
public interface VctState extends Entity {

	public enum State {
		NEW, UNPROVISIONED, PROVISIONED, UNBOOKED, BOOKED, INPROGRESS_SYNC, INPROGRESS_ASYNC, INPROGRESS_WAIT
	}
	
	public State get();
	public void set(State commonName);
	
}
