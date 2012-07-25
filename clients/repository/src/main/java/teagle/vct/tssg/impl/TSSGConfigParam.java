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
@XmlRootElement(name="configParam")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGConfigParam extends TSSGEntity implements ConfigParam, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631592882790421538L;

	public TSSGConfigParam() {
	}
	
	protected TSSGConfigParam(ConfigParam param) {
		super(param);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGConfigParam resolve() {
		TSSGConfigParam resolved = TSSGConfigParamComposite.find(id);
		if (resolved == null) {
			resolved = TSSGConfigParamAtomic.find(id);
		}
		return resolved;
	}
	
	@Override
	public TSSGConfigParam persist()
	{
		return this;
	}
}
