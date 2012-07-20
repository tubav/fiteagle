package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Person;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.Vct;
import teagle.vct.model.VctState;

@XmlRootElement(name = "vct")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGVct extends TSSGEntity implements Vct, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4169085982356204855L;

	protected static TSSGCache<TSSGVct> cache = new TSSGCache<TSSGVct>("vct",
			new TSSGVct[] {});

	@XmlElement(name = "resourceInstance")
	@XmlElementWrapper(name = "providesResources")
	protected List<TSSGResourceInstance> resourceInstances = new ArrayList<TSSGResourceInstance>();

	protected TSSGVctState state;

	@XmlElement(name = "user")
	protected TSSGPerson person = new TSSGPerson();

	protected boolean shared;

	public TSSGVct() {
		this.setState(VctState.State.NEW);
	}

	protected TSSGVct(final Vct vct) {
		super(vct);
		this.shared = vct.isShared();
		this.setState(vct.getState());
		this.setPerson(vct.getPerson());

		for (final ResourceInstance instance : vct.getResourceInstances())
			this.addResourceInstance(instance);
		this.flag = true;
	}

	public static TSSGVct find(final String id) {
		return TSSGVct.cache.find(id);
	}

	public static List<? extends TSSGVct> list() {
		return TSSGVct.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVct persist() {
		this.state = this.state.resolve().persist();
		this.person = this.person.resolve().persist();

		for (final ListIterator<TSSGResourceInstance> it = this.resourceInstances
				.listIterator(); it.hasNext();)
			it.set(it.next().resolve().persist());
		// it.next().resolve().persist();

		return TSSGVct.cache.persist(this);
	}

	public boolean exist(final String name) {
		final Iterator<Entry<String, TSSGVct>> it_vct = TSSGVct.cache.getCache().entrySet().iterator();
		while (it_vct.hasNext()) {
			final Entry<String, TSSGVct> pairs = it_vct.next();
			if (((TSSGVct) pairs.getValue()).getCommonName().equals(name)) {
				System.out.println("Name already taken: " + name);
				return true;
			}
		}
		return false;
	}

	// TODO very messy
	public TSSGVct existingVct(final String name) {
		final Iterator<Entry<String, TSSGVct>> it_vct = TSSGVct.cache.getCache().entrySet().iterator();
		while (it_vct.hasNext()) {
			final Entry<String, TSSGVct> pairs = it_vct.next();
			if (((TSSGVct) pairs.getValue()).getCommonName().equals(name))
				return (TSSGVct) (pairs.getValue());
		}
		return null;
	}

	@Override
	public void delete() throws RepositoryException {
		/*
		 * for (ListIterator<TSSGConnection> it = connections.listIterator();
		 * it.hasNext(); ) { it.next().resolve().delete(); }
		 */
		/*
		 * for (ListIterator<TSSGResourceInstance> it =
		 * resourceInstances.listIterator(); it.hasNext(); ) {
		 * it.next().resolve().delete(); }
		 */

		TSSGVct.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVct resolve() {
		return this.id != null ? TSSGVct.cache.find(this.id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctInstance getInstance() {
		return new TSSGVctInstance(this);
	}

	@Override
	public VctState.State getState() {
		if (this.state == null)
			for (final VctState s : TSSGVctState.list())
				if (s.getCommonName().equals(VctState.State.NEW.toString())) {
					this.state = (TSSGVctState) s;
					this.flag = true;
				}
		final VctState c = TSSGVctState.find(this.state.getId());
		return c != null ? VctState.State.valueOf(c.getCommonName())
				: this.state.get();
	}

	@Override
	public boolean isShared() {
		return this.shared;
	}

	@Override
	public void setState(final VctState.State state) {
		if (this.state != null)
			this.flag = true;
		for (final VctState s : TSSGVctState.list())
			if (s.getCommonName().equals(state.toString()))
				this.state = (TSSGVctState) s;
		if (this.state == null) {
			this.state = new TSSGVctState();
			this.state.set(state);
		}
	}

	@Override
	public Person getPerson() {
		if (this.person.getId() != null)
			this.person = TSSGPerson.find(this.person.getId());
		return this.person;
	}

	@Override
	public void setPerson(final Person user) {
		this.person = (user instanceof TSSGPerson) ? (TSSGPerson) user
				: new TSSGPerson(user);
		this.flag = true;
	}

	@Override
	public void addResourceInstance(final ResourceInstance instance) {
		final TSSGResourceInstance in = (instance instanceof TSSGResourceInstance) ? (TSSGResourceInstance) instance
				: new TSSGResourceInstance(instance);
		this.resourceInstances.add(in);
		this.flag = true;
	}

	@Override
	public List<? extends TSSGResourceInstance> getResourceInstances() {
		final List<TSSGResourceInstance> array = new ArrayList<TSSGResourceInstance>();
		for (final TSSGResourceInstance instance : this.resourceInstances) {
			final TSSGResourceInstance i = TSSGResourceInstance.find(instance
					.getId());
			array.add(i == null ? instance : i);
		}
		return array;
	}

	@Override
	public void removeResourceInstance(final ResourceInstance instance) {
		this.resourceInstances.remove(instance);
		this.flag = true;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= this.state.resolve().isModified();
		modified |= this.person.resolve().isModified();
		for (final TSSGResourceInstance instance : this.resourceInstances)
			modified |= instance.resolve().isModified();
		return modified;
	}

	@XmlRootElement(name = "vctInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGVctInstance implements Serializable {

		private String user;

		@XmlElement(name = "state.id")
		// @XmlElement(name="state")
		private String state;

		// @XmlElementWrapper(name="providesResources")
		@XmlElement(name = "providesResources")
		private final List<String> resourceInstances = new ArrayList<String>();

		/**
		 * 
		 */
		private static final long serialVersionUID = -7543873915821949434L;

		protected TSSGVctInstance() {
		}

		protected TSSGVctInstance(final TSSGVct vct) {
			this.user = vct.person.getId();
			this.state = vct.state.getId();
			for (final TSSGResourceInstance instance : vct.resourceInstances)
				this.resourceInstances.add(instance.getId());
		}

	}

}
