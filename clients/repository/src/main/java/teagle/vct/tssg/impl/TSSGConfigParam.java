/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.ConfigParam;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "configParam")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParam extends TSSGEntity implements ConfigParam,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631592882790421538L;

	public TSSGConfigParam() {
	}

	protected TSSGConfigParam(final ConfigParam param) {
		super(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParam resolve() {
		TSSGConfigParam resolved = TSSGConfigParamComposite.find(this.id);
		if (resolved == null)
			resolved = TSSGConfigParamAtomic.find(this.id);
		return resolved;
	}

	@Override
	public TSSGConfigParam persist() {
		return this;
	}
}
