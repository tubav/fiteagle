/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.RepositoryException;
import teagle.vct.model.VctState;

/**
 * @author sim
 *
 */
@XmlRootElement(name="vctState")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGVctState extends TSSGEntity implements VctState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308637823918011319L;

	protected static TSSGCache<TSSGVctState> cache = new TSSGCache<TSSGVctState>("vctState", new TSSGVctState[]{});
	
	public TSSGVctState() {
	}
	
	protected TSSGVctState(VctState state) {
		super(state);
		flag = true;
	}
	
	public static TSSGVctState find(String id) {
		return cache.find(id);
	}

	public static List<? extends VctState> list() {
		return cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctState persist() {
		return cache.persist(this);
	}
	
	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctState resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctStateInstance getInstance() {
		return new TSSGVctStateInstance(this);
	}	
	
	@Override
	public State get() {
		return State.valueOf(getCommonName());
	}

	@Override
	public void set(State commonName) {
		setCommonName(commonName.toString());
	}

	@XmlRootElement(name="vctStateInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGVctStateInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6746657578317980907L;

		private String commonName;
		private String description;
		
		protected TSSGVctStateInstance() {
		}
		
		protected TSSGVctStateInstance(TSSGVctState state) {
			commonName = state.commonName;
			description = state.description;
		}
		
	}
}
