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
import teagle.vct.model.ResourceInstanceState;

/**
 * @author sim
 *
 */
@XmlRootElement(name="resourceInstanceState")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGResourceInstanceState extends TSSGEntity implements ResourceInstanceState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308637823918011319L;

	protected static TSSGCache<TSSGResourceInstanceState> cache = new TSSGCache<TSSGResourceInstanceState>("resourceInstanceState", new TSSGResourceInstanceState[]{});

	public TSSGResourceInstanceState() {
	}

	protected TSSGResourceInstanceState(ResourceInstanceState state) {
		super(state);
		flag = true;
	}
	
	public static TSSGResourceInstanceState find(String id) {
		return cache.find(id);
	}
	
	public static List<? extends ResourceInstanceState> list() {
		return cache.list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceState persist() {
		return cache.persist(this);
	}
	
	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceState resolve() {
		return id != null ? cache.find(id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceStateInstance getInstance() {
		return new TSSGResourceInstanceStateInstance(this);
	}	

	@Override
	public State get() {
		return State.valueOf(getCommonName());
	}

	@Override
	public void set(State state) {
		setCommonName(state.toString());
		flag = true;
	}

	@XmlRootElement(name="resourceInstanceStateInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public class TSSGResourceInstanceStateInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1197266542522953286L;

		private String commonName;
		
		private String description;

		protected TSSGResourceInstanceStateInstance() {
		}
				
		protected TSSGResourceInstanceStateInstance(TSSGResourceInstanceState state) {
			commonName = state.commonName;
			description = state.description;
		}
	}
}
