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
@XmlRootElement(name = "vctState")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGVctState extends TSSGEntity implements VctState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308637823918011319L;

	protected static TSSGCache<TSSGVctState> cache = new TSSGCache<TSSGVctState>(
			"vctState", new TSSGVctState[] {});

	public TSSGVctState() {
	}

	protected TSSGVctState(final VctState state) {
		super(state);
		this.flag = true;
	}

	public static TSSGVctState find(final String id) {
		return TSSGVctState.cache.find(id);
	}

	public static List<? extends VctState> list() {
		return TSSGVctState.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctState persist() {
		return TSSGVctState.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGVctState.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctState resolve() {
		return this.id != null ? TSSGVctState.cache.find(this.id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctStateInstance getInstance() {
		return new TSSGVctStateInstance(this);
	}

	@Override
	public State get() {
		return State.valueOf(this.getCommonName());
	}

	@Override
	public void set(final State commonName) {
		this.setCommonName(commonName.toString());
	}

	@XmlRootElement(name = "vctStateInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGVctStateInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6746657578317980907L;

		protected TSSGVctStateInstance() {
		}

		protected TSSGVctStateInstance(final TSSGVctState state) {
		}

	}
}
