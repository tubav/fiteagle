package de.fhg.fokus.ptm;

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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Identifier
	implements Comparable<Identifier>
{
	public static final String DELIMITER = "/";
	public static final String WILDCARD = "*";
	public static final String TYPE_SEPARATOR = "-";
	public static final String CURRENT = ".";
	
	private String uuid;
	
	public Identifier(Resource resource) 
		throws IdentifierException
	{
		this(resource.getIdentifier().getIdentifier());
	}
	
	public Identifier(String uuid)
		throws IdentifierException
	{
		if (uuid == null)
			uuid = DELIMITER;
		if (!uuid.startsWith(DELIMITER))
			throw new IdentifierException("Illegal uuid: " + uuid);
			
		this.uuid = trim(uuid);
	}
	
	public Identifier(Identifier parent, String typename, String name)
		throws IdentifierException
	{
		this(name == null ? new Identifier(parent).adapterFor(typename) : new Identifier(parent).slash(typename + "-" + name));
	}
	
	public Identifier(String uuid, boolean need_full)
		throws IdentifierException
	{
		this(uuid);
		if (need_full && (isAdapter() || !isAbsolute()))
			throw new IdentifierException("Need a full Identifier here, not: " + uuid);
	}
	
	public Identifier(Identifier uuid) 
	{
		if (uuid == null)
			this.uuid = DELIMITER;
		else 
			this.uuid = uuid.getIdentifier();
	}

	private static String trim(String uuid)
		throws IdentifierException
	{
		if (uuid == null || uuid.length() == 0)
			return DELIMITER;
		
		if (uuid == CURRENT)
			return uuid;
		
		String[] parts = uuid.split(DELIMITER, -1);
		
		assert (parts.length > 0);
		
		int i = 0;
		String result = CURRENT;
	
		//id begins with "/"
		if (parts[0].length() == 0)
		{
			result = "";
			i = 1;
		}
		
		for (;i < parts.length - 1; ++i)
		{
			String part = parts[i];
			
			if (part.length() == 0 || part.equals(CURRENT))
				continue;
			
			int p = part.indexOf(TYPE_SEPARATOR);
			if (p < 0 || p >= part.length() - 1 || part.contains(WILDCARD))
				throw new IdentifierException("Illegal element '" + part + "' in id '" + uuid + "'");
			
			result += DELIMITER + part;
		}
		
		String last = parts[parts.length - 1];
		
		int p = last.indexOf(WILDCARD);
		if (p > 0 && p < last.length() - 1 || last.endsWith(TYPE_SEPARATOR) || last.endsWith(TYPE_SEPARATOR + WILDCARD))
			throw new IdentifierException("Illegal element '" + last + "' in id '" + uuid + "'");
		
		result += DELIMITER;
		
		if (last != CURRENT)
			result += last;
		
		return result;
	}
	
	
	private static void check(String s, String label) throws IdentifierException
	{
		if (s != null && (s.equals("") || s.contains(DELIMITER) || s.contains(WILDCARD)))
			throw new IdentifierException("Illegal value for " + label + ": " + s);
	}
	
	@Override
	public int hashCode() 
	{
		return uuid.hashCode();
	}
	
	public Identifier getManagerName()
	{
		try
		{
			//if (isWildcard())
			//	return new UUID(uuid.substring(0, uuid.length() - WILDCARD.length()));
			if (isAdapter())
				return this;
			//return new UUID(uuid.substring(0, uuid.length() - getBasename().length()));
			return new Identifier(uuid.substring(0, uuid.lastIndexOf(TYPE_SEPARATOR)));
		}
		catch (IdentifierException e)
		{
			e.printStackTrace();
			throw new PTMInternalError(e);
		}
	}
	
	public Identifier append(String type, String name) throws IdentifierException
	{
		return slash(type + TYPE_SEPARATOR + name);
	}
	
	public Identifier append(String type) throws IdentifierException
	{
		return slash(type);
	}
	
	//TODO: find a better name
	public Identifier slash(String part) throws IdentifierException
	{
		String uuid = this.uuid;
		if (!isAdapter())
			uuid += DELIMITER;
		else uuid = this.getDirname().getIdentifier();
		
		//TODO: more sanity checks
		if (part != null && part.length() > 0)
			uuid += part;
		
		return new Identifier(uuid);
	}
	
	public Identifier slash()
	{
		try
		{
			return slash(null);
		}
		catch (IdentifierException e)
		{
			e.printStackTrace();
			throw new PTMInternalError(e);
		}
	}
	
	public Identifier adapterFor(String typename) throws IdentifierException
	{
		return slash(typename);
	}
	
	public boolean isAdapter()
	{
		return !getBasename().contains(TYPE_SEPARATOR);
	}
	
	public boolean isAbsolute()
	{
		return uuid.startsWith(DELIMITER);
	}
	
	public boolean isCurrent()
	{
		return uuid.equals(CURRENT);
	}
	
	public boolean isWildcard()
	{
		return uuid.endsWith(WILDCARD);
	}
	
	public boolean isRoot()
	{
		return uuid.equals(DELIMITER) || uuid.equals(DELIMITER + WILDCARD);
	}
	
	public boolean isResponsible(Identifier uuid) throws IdentifierException
	{
		if (!isAdapter())
			throw new IdentifierException("Only managers are responsible for stuff (I am: " + this.uuid + ")");
		String name = uuid.getManagerName().getIdentifier();
		return equals(name) || (isWildcard() && name.startsWith(getManagerName().getIdentifier()));
	}
	
	public String getIdentifier()
	{
		return uuid;
	}
	
	@Override
	public String toString()
	{
		return getIdentifier();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj instanceof Identifier)
			obj = ((Identifier)obj).getIdentifier();
		return this.uuid.equals(obj);
	}
	
	public String[] getParts()
	{
		return uuid.split(DELIMITER, -1);
	}
	
	public String getBasename()
	{
		String[] parts = getParts();
		return parts[parts.length - 1];
	}
	
	public Identifier getDirname()
	{
		if (isCurrent())
			return this;
		
		try 
		{
			return new Identifier(uuid.substring(0, uuid.length() - getBasename().length()));
		} catch (IdentifierException e) {
			e.printStackTrace();
			throw new PTMInternalError(e);
		}
		
	}
	
	public String getName()
	{
/*		String basename = getBasename();
		if (basename.equals(""))
			return "";
		
		String[] a = basename.split(TYPE_SEPARATOR, 2);
		return a[a.length - 1];*/
		
		if (isAdapter())
			return null;
		String last = getBasename();
		return last.substring(last.indexOf(TYPE_SEPARATOR) + 1);
	}
	
	public String getTypename()
	{
		String basename = getBasename();
		if (basename.equals(""))
			return null;
		
		if (isWildcard())
			basename = basename.substring(0, basename.length() - WILDCARD.length());
			
		if (basename.length() == 0)
			return null;
		
		return basename.split(TYPE_SEPARATOR, 2)[0];
	}
	
	
	
	public Identifier makeChildIdentifier(String type, String name) throws IdentifierException
	{
		check(name, "name");
		check(type, "type");
		if (type != null && type.contains(TYPE_SEPARATOR))
			throw new IdentifierException("Illegal token in type: " + type);

		if (name == null)
			throw new IdentifierException("Name must be specified");
		
		if (!isAdapter())
			if (type == null)
				throw new IdentifierException("type not specified");
			else 
				return new Identifier(uuid + DELIMITER + type + TYPE_SEPARATOR + name);
		
		String uuid = this.uuid;
		if (isWildcard())
			uuid = uuid.substring(0, uuid.length() - WILDCARD.length());
		
		if (getTypename() == null)
			if (type == null)
				throw new IdentifierException("type not specified and I ('" + this + "') am untyped");
			else 
				return new Identifier(uuid + type + TYPE_SEPARATOR + name);
		
		if (type != null && !getTypename().equals(type))
			throw new IdentifierException("Specified type ('" + type + "') does not match my type ('" + this + "')");
			
		return new Identifier(uuid + TYPE_SEPARATOR + name);		
	}
	
	public Identifier getParentId() //throws UUIDException
	{
		Identifier dirname = getDirname();
		if (dirname.isCurrent() || dirname.isRoot())
			return dirname;
		
		try
		{
			return new Identifier(dirname.getIdentifier().substring(0, dirname.getIdentifier().length() - DELIMITER.length()));
		}
		catch (IdentifierException e)
		{
			e.printStackTrace();
			throw new PTMInternalError(e);
		}
	}

	@Override
	public int compareTo(Identifier o) {
		return uuid.compareTo(o.getIdentifier());
	}
	
	public String urlEncode() throws IdentifierException
	{
		return urlEncode("utf-8");
	}
	
	public String urlEncode(String encoding) throws IdentifierException
	{
		try {
			return URLEncoder.encode(uuid, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IdentifierException("Error urlencoding id " + uuid + " with encoding " + encoding, e);
		}
	}
	
	public static Identifier urlDecode(String id) throws IdentifierException
	{
		return urlDecode(id, "utf-8");
	}
	
	public static Identifier urlDecode(String id, String encoding) throws IdentifierException
	{
		if (id != null)
			try {
				id = URLDecoder.decode(id, encoding);
			} catch (UnsupportedEncodingException e) {
				throw new IdentifierException("Error urldecoding id " + id + " with encoding " + encoding, e);
			}
			
		return new Identifier(id);
	}
}
