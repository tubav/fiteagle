package teagle.repository.tssg.client.xstream;

import teagle.repository.tssg.model.AbstractTSSGEntity;
import teagle.repository.tssg.model.TSSGResourceInstance;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class TSSGEntityConverter extends ReflectionConverter
{
	private Mapper mapper;
	
	public TSSGEntityConverter(Mapper mapper,
			ReflectionProvider reflectionProvider)
	{
		super(mapper, reflectionProvider);
		this.mapper = mapper; 
	}

	@SuppressWarnings({"rawtypes" })
	@Override
    public boolean canConvert(Class clazz) {
        return AbstractTSSGEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) 
	{
		AbstractTSSGEntity e = ((AbstractTSSGEntity)value);
		if (e.isRoot())
			super.marshal(value, writer, context);
		else
		{
			//System.out.println("Writing: " + e);
			if (e.getId() == null && e  instanceof TSSGResourceInstance && ((TSSGResourceInstance)e).commonName == null)
				return;
			//System.out.println("Writing instance: " + ((TSSGResourceInstance)e).commonName);
			writer.startNode(mapper.serializedClass(value.getClass()));
			//System.out.println("Writing id: " + e.getId());
			writer.setValue(e.getId().toString());
			writer.endNode();
		}
	}

}
