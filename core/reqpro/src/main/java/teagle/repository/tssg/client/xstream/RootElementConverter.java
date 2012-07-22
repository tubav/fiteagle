package teagle.repository.tssg.client.xstream;


import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class RootElementConverter extends ReflectionConverter
{

	public RootElementConverter(Mapper mapper,
			ReflectionProvider reflectionProvider)
	{
		super(mapper, reflectionProvider);
	}
	
	@Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(RootElement.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) 
	{
		super.marshal(((RootElement)value).getElement(), writer, context);
	}
}
