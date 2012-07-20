package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Connection;
import teagle.vct.model.RepositoryException;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.VctState;
import teagle.vct.model.Person;
import teagle.vct.model.Vct;

@XmlRootElement(name="vct")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGVct extends TSSGEntity implements Vct, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4169085982356204855L;

	protected static TSSGCache<TSSGVct> cache = new TSSGCache<TSSGVct>("vct", new TSSGVct[]{});

	@XmlElement(name="resourceInstance")
	@XmlElementWrapper(name="providesResources")
	protected List<TSSGResourceInstance> resourceInstances = new ArrayList<TSSGResourceInstance>();

	protected TSSGVctState state;

	@XmlElement(name="user")
	protected TSSGPerson person = new TSSGPerson();

	protected boolean shared;
	
	public TSSGVct() {
		setState(VctState.State.NEW);
	}

	protected TSSGVct(Vct vct) {
		super(vct);
		shared = vct.isShared();
		setState(vct.getState());
		setPerson(vct.getPerson());
		
		for (ResourceInstance instance : vct.getResourceInstances()) {
			addResourceInstance(instance);
		}
		flag = true;
	}
	
	public static TSSGVct find(String id) {
		return cache.find(id);
	}
	
	public static List<? extends TSSGVct> list() {
		return cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVct persist() {
		state = state.resolve().persist();
		person = person.resolve().persist();

		for (ListIterator<TSSGResourceInstance> it = resourceInstances.listIterator(); it.hasNext(); ) {
			it.set(it.next().resolve().persist());
//			it.next().resolve().persist();
		}
		
		return cache.persist(this);
	}

	public boolean exist(String name) {
		Iterator it_vct = cache.getCache().entrySet().iterator();
		while(it_vct.hasNext()){
			Map.Entry pairs = (Map.Entry)it_vct.next();
			if (((TSSGVct)pairs.getValue()).getCommonName().equals(name)){
				System.out.println("Name already taken: " + name);
				return true;
			}
	    }
		return false;
	}
	
	// TODO very messy
	public TSSGVct existingVct(String name){
		Iterator it_vct = cache.getCache().entrySet().iterator();
		while(it_vct.hasNext()){
			Map.Entry pairs = (Map.Entry)it_vct.next();
			if (((TSSGVct)pairs.getValue()).getCommonName().equals(name)){
				return (TSSGVct)(pairs.getValue());
			}
	    }
		return null;
	}

	@Override
	public void delete() throws RepositoryException {		
/*		for (ListIterator<TSSGConnection> it = connections.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}*/
		/*for (ListIterator<TSSGResourceInstance> it = resourceInstances.listIterator(); it.hasNext(); ) {
			it.next().resolve().delete();
		}*/
		
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGVct resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGVctInstance getInstance() {
		return new TSSGVctInstance(this);
	}	

	@Override
	public VctState.State getState() {
		if (state == null) {
			for (VctState s : TSSGVctState.list()) {
				if (s.getCommonName().equals(VctState.State.NEW.toString())) {
					state = (TSSGVctState)s;
					flag = true;
				}
			}
		}
		VctState c = TSSGVctState.find(state.getId());
		return c != null ? VctState.State.valueOf(c.getCommonName()) : state.get();
	}

	@Override
	public boolean isShared() {
		return shared;
	}

	@Override
	public void setState(VctState.State state) {
		if (this.state != null) {
			flag = true;			
		}
		for (VctState s : TSSGVctState.list()) {
			if (s.getCommonName().equals(state.toString())) {
				this.state = (TSSGVctState)s;
			}				
		}
		if (this.state == null) {
			this.state = new TSSGVctState();
			this.state.set(state);
		}
	}

	@Override
	public Person getPerson() {
		if (person.getId() != null) {
			person = TSSGPerson.find(person.getId());
		}
		return person;
	}

	@Override
	public void setPerson(Person user) {
		person = (user instanceof TSSGPerson) ? (TSSGPerson)user : new TSSGPerson(user);
		flag = true;
	}

	@Override
	public void addResourceInstance(ResourceInstance instance) {
		TSSGResourceInstance in = (instance instanceof TSSGResourceInstance) ? (TSSGResourceInstance)instance : new TSSGResourceInstance(instance);
		resourceInstances.add(in);
		flag = true;
	}

	@Override
	public List<? extends TSSGResourceInstance> getResourceInstances() {
		List<TSSGResourceInstance> array = new ArrayList<TSSGResourceInstance>();
		for (TSSGResourceInstance instance : resourceInstances) {
			TSSGResourceInstance i = TSSGResourceInstance.find(instance.getId());
			array.add(i == null ? instance : i);
		}
		return array;
	}

	@Override
	public void removeResourceInstance(ResourceInstance instance) {
		resourceInstances.remove(instance);
		flag = true;
	}

	@Override
	public boolean isModified() {
		boolean modified = super.isModified();
		modified |= state.resolve().isModified();
		modified |= person.resolve().isModified();
		for (TSSGResourceInstance instance : resourceInstances) {
			modified |= instance.resolve().isModified();
		}
		return modified; 
	}
	
	@XmlRootElement(name="vctInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGVctInstance implements Serializable {
		
		private boolean shared;

		private String commonName;
		
		private String description;
		
		private String user;
		
		@XmlElement(name="state.id")
//		@XmlElement(name="state")
		private String state;
		
//		@XmlElementWrapper(name="providesResources")
		@XmlElement(name="providesResources")
		private List<String> resourceInstances = new ArrayList<String>();
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7543873915821949434L;

		protected TSSGVctInstance() {
		}
		
		protected TSSGVctInstance(TSSGVct vct) {
			shared = false;
			commonName = vct.commonName;
			description = vct.description;
			user = vct.person.getId();			
			state = vct.state.getId();			
			for (TSSGResourceInstance instance : vct.resourceInstances) {
				resourceInstances.add(instance.getId());
			}
		}
		
	}
	
}
