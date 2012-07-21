package teagle.repository.tssg.client;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import teagle.repository.EntityNotFoundException;
import teagle.repository.RepositoryException;
import teagle.repository.Util;
import teagle.repository.tssg.client.xstream.ListConverter;
import teagle.repository.tssg.client.xstream.TSSGEntityConverter;
import teagle.repository.tssg.model.AbstractTSSGEntity;
import teagle.repository.tssg.model.TSSGResource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

public class TSSGClient
{
	private Client client;
	private WebResource webresource;
	private XStream xs;
	//private ArrayList<Mapping> mappings;
	private HashMap<Class<?>, String> mapping;
	
	//private final static String repoUrl = "http://root:r00t@repos.pii.tssg.org:8080/repository/rest";	
	public TSSGClient(ClassLoader loader, URL repoUrl, Object ... aliases) throws IOException 
	{
		client = Client.create();
		
		String userinfo = repoUrl.getUserInfo();
		
		if (userinfo != null)
		{
			String[] info = userinfo.split(":");
			if (info.length > 1)
				client.addFilter(new HTTPBasicAuthFilter(info[0], info[1]));
		}
		webresource = client.resource(repoUrl.toString());
		
		xs = Util.newXstream();
		xs.useAttributeFor(AbstractTSSGEntity.class, "id");
		xs.omitField(AbstractTSSGEntity.class, "client");
		xs.omitField(AbstractTSSGEntity.class, "initialized");
		xs.omitField(AbstractTSSGEntity.class, "isRoot");
		xs.omitField(TSSGResource.class, "url");
		xs.omitField(TSSGResource.class, "reservations");
		xs.omitField(TSSGResource.class, "parsedReservations");
		xs.omitField(TSSGResource.class, "realDescription");
		xs.registerConverter(new TSSGEntityConverter(xs.getMapper(), xs.getReflectionProvider()));
		xs.registerConverter(new ListConverter(xs.getMapper()));
		//xs.registerConverter(new RootElementConverter(xs.getMapper(), xs.getReflectionProvider()));
		
		if (loader!=null) 
			xs.setClassLoader(loader);
		
		mapping = new HashMap<Class<?>, String>();
		addAliases(aliases);
	}
	
	@SuppressWarnings("unchecked")
	public void addAliases(Object... aliases)
	{
		if (aliases.length % 2 != 0)
			throw new IllegalArgumentException("aliases params should be string-class pairs");	
		for (int i=0; i<aliases.length; i += 2)
			addAlias((String)aliases[i], (Class<? extends AbstractTSSGEntity>)aliases[i+1]);
	}
	
	public void addAlias(String alias, Class<? extends AbstractTSSGEntity> cls)
	{
		xs.alias(alias, cls);
		//mappings.add(new Mapping(alias, cls));
		mapping.put(cls, alias);
	}
	
	private String getAlias(Class<?> cls)
	{
		String alias = mapping.get(cls);

		if (alias == null)
			return cls.getSimpleName();
		
		return alias;
	}
	
/*	private Class<?> getType(String alias) throws RepositoryException
	{
		for (Mapping m : mappings)
			if (m.alias.equals(alias))
				return m.type;
		throw new RepositoryException("No such alias: " + alias);
	}*/
	
	public AbstractTSSGEntity getObject(String type, int id) throws RepositoryException
	{
		String answer = null;
		Util.debug("Getting: " + type + " " + id);
		try
		{
			answer = webresource.path(type + "/" + id).type(MediaType.TEXT_XML_TYPE).get(String.class);
			//System.out.println("Got object: " + answer);
			AbstractTSSGEntity e = deserialize(answer);
			e.setClient(this);
			return e;
		}
		catch (CannotResolveClassException e)
		{
			throw new RepositoryException("the format of the answer came as a complete suprise to me: " + answer, e);
		}
		catch (UniformInterfaceException e)
		{
			ClientResponse response = e.getResponse();
			
			String msg = response.getEntity(String.class);
			System.out.println(msg);
			if (response.getStatus() == 404)
				throw new EntityNotFoundException(type, id);
			throw new RepositoryException("Error while getting an entity: " + msg, e);
		}
	}
	
	public void updateObject(String type, AbstractTSSGEntity o) throws RepositoryException
	{
		if (!o.isPersistent())
			throw new RepositoryException("Not in repo: " + o);
		
		String request = serialize(o);
		System.out.println("Sending for update: " + request);
		try
		{
			String answer = webresource.path(type + "/" + o.getId() + "/").type(MediaType.TEXT_XML_TYPE).put(String.class, request);
			System.out.println("Answer:");
			System.out.println(answer);
		}
		catch (UniformInterfaceException e)
		{
			//System.out.println(request);
			ClientResponse response = e.getResponse(); 
			String msg = response.getEntity(String.class);
			System.out.println(msg);
			throw new RepositoryException("Error while updating an entity: " + msg, e);
		}
	}
	
	public void updateObject(AbstractTSSGEntity o) throws RepositoryException
	{
		updateObject(getAlias(o.getClass()), o);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<T> cls, int id) throws RepositoryException
	{
		String type = getAlias(cls);
		Object o = getObject(type, id);
		try
		{
			return (T)o;
		}
		catch (ClassCastException e)
		{
			throw new RepositoryException("Received wrong type for " + type + ": " + o);
		}
	}
	
	public void deleteObject(String type, int id) throws RepositoryException
	{
		try
		{
			webresource.path(type + "/" + id).type(MediaType.TEXT_XML_TYPE).delete();
		}
		catch (UniformInterfaceException e)
		{
			ClientResponse response = e.getResponse(); 
			String msg = response.getEntity(String.class);
			System.out.println(msg);
			throw new RepositoryException("Error while deleting an entity: " + msg, e);
		}
	}
	
	public void deleteObject(AbstractTSSGEntity o) throws RepositoryException
	{
		if (o == null)
			throw new RepositoryException("Object is null");
		
		deleteObject(getAlias(o.getClass()), o.getId());
		o.setId(null);
	}
	
	public AbstractTSSGEntity addObject(String type, AbstractTSSGEntity data) throws RepositoryException
	{
		if (data.isPersistent())
			throw new RepositoryException("Already in repo: " + data);
		String answer = null;
		String request = serialize(data);
		try
		{
			System.out.println("Sending: " + request);
			answer = webresource.path(type).type(MediaType.TEXT_XML_TYPE).post(String.class, request);
			System.out.println("Answer:");
			System.out.println(answer);
			AbstractTSSGEntity ret = deserialize(answer);
			if (ret == null)
				throw new RepositoryException("Failed to interpret answer. Answer is null.");
			if (!data.getClass().isAssignableFrom(ret.getClass()))
				throw new RepositoryException("answer " + answer + " yielded a surprising object: " + ret);
			if (ret.getId() == null)
				throw new RepositoryException("added object is missing an id.");
			data.setId(ret.getId());
			ret.setClient(this);
			return ret;
		}
		catch (CannotResolveClassException e)
		{
			System.out.println(request);
			throw new RepositoryException("While adding '" + type + "', the format of the answer came as a complete suprise to me: " + answer);
		}
		catch (UniformInterfaceException e)
		{
			System.out.println(request);
			ClientResponse response = e.getResponse(); 
			String msg = response.getEntity(String.class);
			System.out.println(msg);
			throw new RepositoryException("Error while adding an entity: " + msg, e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AbstractTSSGEntity> T addObject(T data) throws RepositoryException
	{
		AbstractTSSGEntity newData = addObject(getAlias(data.getClass()), data);
		return (T)newData;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AbstractTSSGEntity> T[] listObjects(Class<T> cls) throws RepositoryException
	{
		String type = getAlias(cls);
		
		try 
		{
			@SuppressWarnings("rawtypes")
			Collection c = listObjects(type);
			T[] arr = (T[])Array.newInstance(cls, c.size());
			c.toArray(arr);	
			return arr;
		}
		catch (CannotResolveClassException e)
		{
			throw new RepositoryException("While listing '" + type + "' from the repository, a surprising type was found", e);
		}
		catch (Exception e)
		{
			throw new RepositoryException("Error while listing " + type + " from the repository: " + e, e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected Collection listObjects(String type) throws RepositoryException
	{		
		String answer = null;
		//Util.debug("Listing: " + type);
		try
		{
			answer = webresource.path(type).type(MediaType.TEXT_XML_TYPE).get(String.class);
			//System.out.println(answer);
			Collection c= ((Collection)deserializeRaw(answer));
			for (Object e : c)
				((AbstractTSSGEntity)e).setClient(this);
			return c;
		}
		catch (ClassCastException e)
		{
			throw new RepositoryException("While listing '" + type + "' from the repository, the format of the answer came as a complete suprise to me: " + answer, e);
		}
		catch (UniformInterfaceException e)
		{
			ClientResponse response = e.getResponse(); 
			String msg = response.getEntity(String.class);
			System.out.println(msg);
			throw new RepositoryException("Error while listing " + type + " from the repository: " + msg, e);
		}
	}
	
	private String mangleXml(String xml) throws RepositoryException
	{
		xml = basicMangle(xml);
		
		if (xml.endsWith("</organisationInstance>"))
		{
			xml = xml.replace("</resourceSpec>\n    <resourceSpec>", "</resourceSpec>\n</hasResources>\n<hasResources>\n<resourceSpec>");
			xml = xml.replace("</person>\n    <person>", "</person>\n</people>\n<people>\n<person>");
		}
		else if (xml.endsWith("</configParamCompositeInstance>"))
		{
			xml = xml.replace("</configParamAtomic>\n    <configParamAtomic>", "</configParamAtomic>\n</configParams><configParams><configParamAtomic>");
		}
		else if (xml.endsWith("</resourceInstanceInstance>"))
		{
			xml = xml.replace("</configlet>\n    <configlet>", "</configlet>\n</configurationData>\n<configurationData><configlet>");
			xml = xml.replace("<state>\n    <resourceInstanceState>", "<state.id>");
			xml = xml.replace("</resourceInstanceState>\n  </state>", "</state.id>");
		}
		else if (xml.endsWith("</connectionInstance>"))
		{
			xml = xml.replace("<source>\n    <src>", "<source.id>");
			xml = xml.replace("</src>\n  </source>", "</source.id>");
			xml = xml.replace("<destination>\n    <dst>", "<destination.id>");
			xml = xml.replace("</dst>\n  </destination>", "</destination.id>");
		}
		else if (xml.endsWith("</vctInstance>"))
		{
			//xml = xml.replace("</connection>\n    <connection>", "</connection>\n</hasConnections>\n<hasConnections><connection>");
			//xml = xml.replace("<providesResources/>", "");
			xml = xml.replace("<hasConnections>\n    <connection>", "<hasConnections>");
			xml = xml.replace("</connection>\n    <connection>", "</hasConnections>\n<hasConnections>");
			xml = xml.replace("</connection>\n  </hasConnections>", "</hasConnections>\n");
			
			xml = xml.replace("<providesResources>\n    <resourceInstance>", "<providesResources>");
			xml = xml.replace("</resourceInstance>\n    <resourceInstance>", "</providesResources>\n<providesResources>");
			xml = xml.replace("</resourceInstance>\n  </providesResources>", "</providesResources>\n");
			
			xml = xml.replace("<state>\n    <vctState>", "<state.id>");
			xml = xml.replace("</vctState>\n  </state>", "</state.id>");
		}
		else if (xml.endsWith("</ptmInstance>"))
		{
			xml = xml.replace("<describedByPtmInfo>\n    <ptmInfo>", "<describedByPtmInfo.id>");
			xml = xml.replace("</ptmInfo>\n  </describedByPtmInfo>", "</describedByPtmInfo.id>");
		}
		else if (xml.endsWith("</ptmInfoInstance>"))
		{
			xml = xml.replace("</resourceSpec>\n    <resourceSpec>", "</resourceSpec>\n</resourceSpecs>\n<resourceSpecs><resourceSpec>");
		}
		else if (xml.endsWith("</configletInstance>"))
		{
			xml = xml.replace("<configParamAtomic>\n    <configParamAtomic>", "<configParamAtomic.id>");
			xml = xml.replace("</configParamAtomic>\n  </configParamAtomic>", "</configParamAtomic.id>");
		}
		
		return xml;
	}
	
	private String basicMangle(String xml) throws RepositoryException
	{
		if (xml.equals("<ptmInfo/>"))
			return "<ptmInfoInstance/>";
		int i = xml.indexOf(">");
		int j = xml.lastIndexOf(">");
		
		if (i < 2 || j < 2 || j <= i)
			throw new RepositoryException("Cannot mangle: " + xml);
		
		if (xml.charAt(i - 1) == '"')
		{
			int k = xml.indexOf(" ");
			if (k >= i)
				throw new RepositoryException("Cannot mangle: " + xml);
			return xml.substring(0, k) + "Instance" + xml.substring(i, j) + "Instance" + xml.substring(j);
		}
		else
			return xml.substring(0, i) + "Instance" + xml.substring(i, j) + "Instance" + xml.substring(j); 
	}
	
	private String serialize(AbstractTSSGEntity o) throws RepositoryException
	{
		try
		{
			o.setRoot(true);
			return mangleXml(xs.toXML(o));
		}
		finally
		{
			o.setRoot(false);
		}
	}
	
	private AbstractTSSGEntity deserialize(String answer) throws RepositoryException
	{
		return (AbstractTSSGEntity)deserializeRaw(answer);
	}
	
	private Object deserializeRaw(String answer) throws RepositoryException
	{
		try
		{
			return xs.fromXML(answer);
		}
		catch (StreamException e)
		{
			if (e.getMessage().indexOf("XML document structures must start and end within the same entity.") >= 0)
				throw new RepositoryException("Repository returned invalid xml");
			throw e;
		}
	}
}
