package teagle.repository.tssg.client.xstream;

import teagle.repository.tssg.model.AbstractTSSGEntity;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.collections.ArrayConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class ListConverter extends ArrayConverter
{
	private final Mapper mapper;
	
	public ListConverter(Mapper mapper)
	{
		super(mapper);
		this.mapper = mapper;
	}

	@Override
    public boolean canConvert(Class clazz) 
	{
		return clazz.isArray() && AbstractTSSGEntity.class.isAssignableFrom(clazz.getComponentType());
	}
	
	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) 
	{
		Object[] arr = (Object[])value;
		
		for (Object o : arr)
		{
			writer.startNode(mapper.serializedClass(value.getClass().getComponentType()));
			AbstractTSSGEntity e = (AbstractTSSGEntity)o;
			writer.setValue(e.getId().toString());
			//writer.addAttribute("id", e.getId().toString());
			writer.endNode();
		}
	}
}
