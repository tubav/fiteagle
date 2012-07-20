/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 * 
 */
public interface ResourceInstanceState extends Entity {

	public enum State {
		UNPROVISIONED, PROVISIONED, NEW
	}

	public State get();

	public void set(State state);

}
