/**
 * 
 */
package teagle.vct.model;

/**
 * @author sim
 *
 */
public interface Connection {

	public enum Type {
		REFERENCES, CONTAINS
	}

	public String getIdentifier();
	public void setIdentifier(String identifier);
	
	public Source getSource();
	public void setSource(Source source);
	
	public Destination getDestination();
	public void setDestination(Destination destination);
	
	public Type getConnectionType();
	public void setConnectionType(Type type);
	
	public String getRules();
	public void setRules(String rules);
	
}
