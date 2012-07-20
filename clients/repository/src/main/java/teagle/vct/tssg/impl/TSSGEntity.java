/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Entity;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "entity")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TSSGEntity extends TSSGObject implements Entity,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4532521231117336048L;

	protected String commonName = "";

	protected String description = "";

	public TSSGEntity() {
	}

	protected TSSGEntity(final Entity entity) {
		this.commonName = entity.getCommonName();
		this.description = entity.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Entity#getCommonName()
	 */
	@Override
	public String getCommonName() {
		return this.commonName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Entity#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Entity#setCommonName(java.lang.String)
	 */
	@Override
	public void setCommonName(final String commonName) {
		this.commonName = commonName;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Entity#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(final String description) {
		this.description = description;
		this.flag = true;
	}

	@Override
	public boolean isPersisted() {
		return this.getId() != null;
	}
}
