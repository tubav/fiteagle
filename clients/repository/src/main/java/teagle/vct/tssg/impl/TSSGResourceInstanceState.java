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
@XmlRootElement(name = "resourceInstanceState")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGResourceInstanceState extends TSSGEntity implements
		ResourceInstanceState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308637823918011319L;

	protected static TSSGCache<TSSGResourceInstanceState> cache = new TSSGCache<TSSGResourceInstanceState>(
			"resourceInstanceState", new TSSGResourceInstanceState[] {});

	public TSSGResourceInstanceState() {
	}

	protected TSSGResourceInstanceState(final ResourceInstanceState state) {
		super(state);
		this.flag = true;
	}

	public static TSSGResourceInstanceState find(final String id) {
		return TSSGResourceInstanceState.cache.find(id);
	}

	public static List<? extends ResourceInstanceState> list() {
		return TSSGResourceInstanceState.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceState persist() {
		return TSSGResourceInstanceState.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGResourceInstanceState.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceState resolve() {
		return this.id != null ? TSSGResourceInstanceState.cache.find(this.id)
				: this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGResourceInstanceStateInstance getInstance() {
		return new TSSGResourceInstanceStateInstance(this);
	}

	@Override
	public State get() {
		return State.valueOf(this.getCommonName());
	}

	@Override
	public void set(final State state) {
		this.setCommonName(state.toString());
		this.flag = true;
	}

	@XmlRootElement(name = "resourceInstanceStateInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public class TSSGResourceInstanceStateInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1197266542522953286L;

		protected TSSGResourceInstanceStateInstance() {
		}

		protected TSSGResourceInstanceStateInstance(
				final TSSGResourceInstanceState state) {
		}
	}
}
