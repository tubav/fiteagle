/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import teagle.vct.model.RepositoryException;

/**
 * @author sim
 *
 */
@XmlRootElement(name="object")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8908111234885538286L;

	@XmlTransient
	protected boolean flag = false;
	
	@XmlAttribute
	public String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public <T extends TSSGObject> T resolve() { return null; };
	
	public <T extends TSSGObject> T persist() { return null; };
	
	public void delete() throws RepositoryException { };
	
	public <T extends Serializable> T getInstance() { return null; };
	
	public boolean isModified() { return flag; };
	
	public static <T extends TSSGObject> T find(String id)
		throws RepositoryException
	{
		throw new IllegalAccessError();
	}
	
}
