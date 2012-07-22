package de.fhg.fokus.ptm.xmlrpc.server;

/*
Copyright (C) 2010 FhG Fokus

This file is part of the open source Teagle implementation.

Licensed under the Apache License, Version 2.0 (the "License"); 

you may not use this file except in compliance with the License. 

You may obtain a copy of the License at 



http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 

distributed under the License is distributed on an "AS IS" BASIS, 

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 

See the License for the specific language governing permissions and 

limitations under the License. 

For further information please contact teagle@fokus.fraunhofer.de
*/

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcNoSuchHandlerException;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.IdentifierException;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.client.PTMClient;
import de.fhg.fokus.ptm.marshaller.Marshaller;
import de.fhg.fokus.ptm.server.Manager;
import de.fhg.fokus.ptm.server.ManagerProperties;

class MethodDispatcher implements XmlRpcHandlerMapping
{
	abstract class PTMHandler implements XmlRpcHandler
	{
		@Override
		public Object execute(XmlRpcRequest arg0) throws XmlRpcException {
			logger.debug("Executing: " + arg0.getMethodName());
			try {
				return dispatch(arg0);
			} catch (PTMException e) {
				logger.error("Error dispatching " + arg0.getMethodName(), e);
				throw new XmlRpcException("Error dispatching " + arg0.getMethodName() + ": " + e, e);
			}
		}
		
		protected Map<String, Object> getMap(Object o) throws PTMException
		{
			if (o == null)
				return new HashMap<String, Object>();
			if (o instanceof Map)
				return marshaller.unpackMap((Map<?, ?>)o);
			throw new PTMException("Illegal value for config: " + o);
		}
		
		protected Identifier getId(Object o, boolean need_full) throws PTMException
		{
			if (o == null)
				return null;
			if (o instanceof String)
				return new Identifier((String)o, need_full);
			throw new PTMException("Illegal vlaue for config: " + o);
		}
		
		protected boolean getBoolean(Object arg3) throws PTMException
		{
			boolean weak = false;
			if (arg3 != null)
				if (arg3 instanceof Boolean)
					weak = (Boolean)arg3;
				else
					throw new PTMException("Illegal value for 'weak': " + arg3);
			return weak;
		}
		
		protected abstract Object dispatch(XmlRpcRequest request) throws PTMException;
	}
	
    abstract class StringArgsHandler extends PTMHandler
	{
    	int num, allnum;
    	
    	public StringArgsHandler(int args)
    	{
    		this(args, args);
    	}
    	
    	public StringArgsHandler(int args, int allargs)
    	{
    		num = args;
    		allnum = allargs;
    	}
    	
    	public Object dispatch(XmlRpcRequest request) throws PTMException
    	{
    		if (request.getParameterCount() < allnum)
    			throw new PTMException("Not enough arguments for " + request.getMethodName());
    		
    		String[] args = new String[num];
    		for (int i = 0;i < num; ++i)
    		{
    			Object arg = request.getParameter(i);
    			if ((arg == null && allowNull(i)) || ((arg instanceof String) && !("".equals(arg))))
       				args[i] = (String)arg;
    			else
    				throw new PTMException("Need a non empty String instead of " + arg + " at position " + (i + 1) + " of " + request.getMethodName());
    		}

    		return dispatch(args, request);
    	}
    	
    	protected boolean allowNull(int pos)
    	{
    		return false;
    	}
    	
    	protected abstract Object dispatch(String[] args, XmlRpcRequest request) throws PTMException;
	}
    
    abstract class IdentifierArgsHandler extends StringArgsHandler
    {
    	private int idnum;
    	
    	public IdentifierArgsHandler(int args)
    	{
    		this(args, args, args);
    	}
    	
    	public IdentifierArgsHandler(int args, int allargs)
    	{
    		this(args, allargs, args);
    	}
    	
    	public IdentifierArgsHandler(int args, int allargs, int idargs)
    	{
    		super(args, allargs);
    		idnum = idargs;
    	}
    	
    	protected Object dispatch(String[] args, XmlRpcRequest request) throws PTMException
    	{
    		Identifier[] result = new Identifier[args.length];
    		for (int i = 0; i < idnum && i < args.length; ++i)
    				try {
    					if (!(args[i] == null && allowNull(i)))
    						result[i] = new Identifier(args[i], needFull(i));
    				} catch (IdentifierException e) {
    					throw new PTMException("Need a valid identifier instead of " + args[i] + " at position " + (i + 1) + " of " + request.getMethodName(), e);
    				}
    		return dispatch(result, args, request);
    	}
    	
    	protected boolean needFull(int pos)
    	{
    		return true;
    	}
    	
    	protected abstract Object dispatch(Identifier[] ids, String[] args, XmlRpcRequest request) throws PTMException;
    }
    
    abstract class SecondOptionalHandler extends IdentifierArgsHandler
    {
    	public SecondOptionalHandler()
    	{
    		this(2);
    	}
    	
		public SecondOptionalHandler(int allargs) 
		{
			super(2, allargs);
		}
		
		@Override
		protected boolean allowNull(int pos) 
		{
			if (pos == 1)
				return true;
			return super.allowNull(pos);
		}
    }
    
    class AcquireResourceHandler extends SecondOptionalHandler
    {

		public AcquireResourceHandler() {
			super(3);
		}
		
		@Override
		protected boolean needFull(int pos) 
		{
			if (pos == 1)
				return false;
			return super.needFull(pos);
		}
		
		@Override
		protected boolean allowNull(int pos) 
		{
			return pos == 0;
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args, XmlRpcRequest request)
				throws PTMException {
			boolean weak = getBoolean(request.getParameter(2));
			manager.acquireResource(ids[0], ids[1], weak);
			return null;
		}
    	
    }
    
    class AddResourceHandler extends IdentifierArgsHandler
    {

		public AddResourceHandler() {
			super(3, 5, 1);
		}

		@Override
		protected boolean allowNull(int pos) 
		{
			if (pos != 2)
				return true;
			return super.allowNull(pos);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args, XmlRpcRequest request)
				throws PTMException {
			Object o = request.getParameter(4);
			Identifier owner = null;
			
    		if (o != null)
    			if (!(o instanceof String) || "".equals(o))
    				throw new PTMException("Need a non empty string or null for owner instead of: " + o);
    			else 
    				owner = getId((String)o, false);

			Resource r = manager.addResource(ids[0], args[1], args[2], getMap(request.getParameter(3)), owner);
			if (r == null)
				throw new PTMException("resource adapter returned null");
			return r.getIdentifier().getIdentifier();
		}
    	
    }
    
    abstract class NotifyHandler extends IdentifierArgsHandler
    {
		public NotifyHandler() {
			super(2);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) {
			try {
				dispatch(ids[0], ids[1]);
			} catch (PTMException e) {
				logger.error("Error while notification: " + request.getMethodName() + " " + ids[0] + " " + ids[1]);
			}
			return null;
		}
		
		protected abstract void dispatch(Identifier identifier, Identifier arg1) throws PTMException;
    }
    
    class ChildDeletedHandler extends NotifyHandler
    {
		@Override
		protected void dispatch(Identifier identifier, Identifier arg1)
				throws PTMException {
			manager.childDeleted(identifier, arg1);			
		}	
    }
    
    class WeakDeletedHandler extends NotifyHandler
    {
		@Override
		protected void dispatch(Identifier identifier, Identifier arg1)
				throws PTMException {
			manager.weakDeleted(identifier, arg1);			
		}	
    }
    
    class StrongDeletedHandler extends NotifyHandler
    {
		@Override
		protected void dispatch(Identifier identifier, Identifier arg1)
				throws PTMException {
			manager.strongDeleted(identifier, arg1);			
		}	
    }
    
    class DeleteResourceHandler extends SecondOptionalHandler
    {
		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			manager.deleteResource(ids[0], ids[1], getBoolean(args[2]));
			return null;
		}
    	
    }
    
    class GetAttributeHandler extends IdentifierArgsHandler
    {

		public GetAttributeHandler() {
			super(2, 2, 1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			return marshaller.packAttribute(manager.getAttribute(ids[0], (String)args[1]));
		}
    }
    
    class GetConfigurationHandler extends IdentifierArgsHandler
    {

		public GetConfigurationHandler() {
			super(1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			Map<String, Object> config = manager.getConfiguration(ids[0]);
			Object result = null;
			if (config != null)
				result = marshaller.packMap(config);
			else
				result = new HashMap<String, Object>();
			
			//logger.debug("Returning packed config: " + result);
			
			return result;
		}	
    }
	
    class GetResourceHandler extends IdentifierArgsHandler
    {
		public GetResourceHandler() {
			super(1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			return marshaller.packResource(manager.getResource(ids[0]));
		}
    }
 
    class HaveResourceHandler extends IdentifierArgsHandler
    {
		public HaveResourceHandler() {
			super(1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			return manager.haveResource(ids[0]);
		}
    }
    
    class ReleaseResourceHandler extends SecondOptionalHandler
    {

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			manager.releaseResource(ids[0], ids[1]);
			return null; 
		}
    	
    }
    
    class SetAttributeHandler extends IdentifierArgsHandler
    {

		public SetAttributeHandler() {
			super(2, 3, 1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			manager.setAttribute(ids[0], args[1], marshaller.unpackAttribute(request.getParameter(2)));
			return null;
		}
    	
    }
    
    class SetConfigurationHandler extends IdentifierArgsHandler
    {

		public SetConfigurationHandler() {
			super(1, 2, 1);
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			manager.setConfiguration(ids[0], getMap(request.getParameter(1)));
			return null;
		}
    	
    }
    
    class ListResourcesHandler extends IdentifierArgsHandler
    {

		public ListResourcesHandler() {
			super(1, 2, 1);
		}
		
		@Override
		protected boolean allowNull(int pos) 
		{
			return true;
		}

		@Override
		protected Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			String type = null;
			Object arg1 = request.getParameter(1);
			if (arg1 != null && !"".equals(arg1))
				type = arg1.toString();
			
			Resource[] resources = manager.listResources(ids[0], type);
			String[] result = new String[resources.length];
			for (int i = 0; i < resources.length; ++i)
				result[i] = resources[i].getIdentifier().getIdentifier();
			return result;
		}
    	
    }
    
    class GetOwnersHandler extends IdentifierArgsHandler
    {
    	public GetOwnersHandler()
    	{
    		super(1);
    	}

		@Override
		public Object dispatch(Identifier[] ids, String[] args,
				XmlRpcRequest request) throws PTMException {
			return marshaller.packOwners(manager.getOwners(ids[0]));
		}

    }
    
	private Marshaller marshaller;
	private Manager manager;
	private HashMap<String, PTMHandler> map = new HashMap<String, PTMHandler>();
	private static Logger logger = Logger.getLogger(MethodDispatcher.class);

	public MethodDispatcher(Manager manager)
	{
		marshaller = new Marshaller(manager.getClient());
		this.manager = manager;
		
		map.put("acquire_resource", new AcquireResourceHandler());
		map.put("add_resource", new AddResourceHandler());
		map.put("weak_deleted", new WeakDeletedHandler());
		map.put("strong_deleted", new StrongDeletedHandler());
		map.put("child_deleted", new ChildDeletedHandler());
		map.put("delete_resource", new DeleteResourceHandler());
		map.put("get_attribute", new GetAttributeHandler());
		map.put("get_configuration", new GetConfigurationHandler());
		map.put("get_resource", new GetResourceHandler());
		map.put("release_resource", new ReleaseResourceHandler());
		map.put("set_attribute", new SetAttributeHandler());
		map.put("set_configuration", new SetConfigurationHandler());
		map.put("list_resources", new ListResourcesHandler());
		map.put("get_owners", new GetOwnersHandler());
		map.put("have_resource", new HaveResourceHandler());
	}
	
	@Override
	public XmlRpcHandler getHandler(String arg0)
			throws XmlRpcNoSuchHandlerException, XmlRpcException {
		XmlRpcHandler handler = map.get(arg0);
		if (handler == null)
			throw new XmlRpcNoSuchHandlerException(arg0);
		return handler;
	}
}

public class XMLRPCManager extends Manager
{
	private XmlRpcHandlerMapping mapping;
	
	public XMLRPCManager(ManagerProperties properties, PTMClient client)
			throws PTMException {
		super(properties, client);
		mapping = new MethodDispatcher(this);
	}

	public XMLRPCManager(URL url, URL registryUrl) throws PTMException {
		super(url, registryUrl);
		mapping = new MethodDispatcher(this);
	}
	
	public XMLRPCManager(URL url) throws PTMException {
		super(url);
		mapping = new MethodDispatcher(this);
	}
	
	public XmlRpcHandlerMapping getHandlerMapping()
	{
		return mapping;
	}
}
