/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Email;
import teagle.vct.model.Person;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "email")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGEmail extends TSSGObject implements Email, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2684833807932872934L;

	protected static TSSGCache<TSSGEmail> cache = new TSSGCache<TSSGEmail>(
			"email", new TSSGEmail[] {});

	private String address = "";

	private TSSGPerson person = new TSSGPerson();

	public TSSGEmail() {
	}

	protected TSSGEmail(final Email email) {
		this.address = email.getAddress();
		if (email instanceof TSSGEmail)
			this.setPerson(((TSSGEmail) email).getPerson());
		this.flag = true;
	}

	public static TSSGEmail find(final String id) {
		return TSSGEmail.cache.find(id);
	}

	public static List<? extends Email> list() {
		return TSSGEmail.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGEmail persist() {
		return TSSGEmail.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGEmail.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGEmail resolve() {
		return this.id != null ? TSSGEmail.cache.find(this.id) : this;
	}

	/*
	 * @see teagle.vct.tssg.impl.TSSGObject#isModified() Mail shouldn't check
	 * Person.
	 */
	@Override
	public boolean isModified() {
		final boolean modified = super.isModified();
		// modified |= person.resolve().isModified();
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGEmailInstance getInstance() {
		return new TSSGEmailInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Email#getAddress()
	 */
	@Override
	public String getAddress() {
		return this.address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Email#setAddress(java.lang.String)
	 */
	@Override
	public void setAddress(final String address) {
		this.address = address;
		this.flag = true;
	}

	public Person getPerson() {
		if (this.person.getId() != null)
			this.person = TSSGPerson.find(this.person.getId());
		return this.person;
	}

	public void setPerson(final Person person) {
		this.person = (person instanceof TSSGPerson) ? (TSSGPerson) person
				: new TSSGPerson(person);
		this.flag = true;
	}

	@XmlRootElement(name = "emailInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	static public class TSSGEmailInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7391614921322119336L;

		private String person;

		protected TSSGEmailInstance() {
		}

		protected TSSGEmailInstance(final TSSGEmail instance) {
			this.person = instance.person.getId();
		}
	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGEmail email = new TSSGEmail();
		email.address = "test@testing.com";

		final TSSGPerson person = new TSSGPerson();
		person.setId("5");
		person.flag = false;

		email.setPerson(person);

		email = email.persist();

		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		email.delete();
	}
}
