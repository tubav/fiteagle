package teagle.repository.tssg.client.xstream;

import teagle.repository.tssg.model.AbstractTSSGEntity;

public class RootElement
{
	private AbstractTSSGEntity element;
	
	public RootElement(AbstractTSSGEntity element)
	{
		this.element = element;
	}
	
	public AbstractTSSGEntity getElement()
	{
		return element;
	}
}
