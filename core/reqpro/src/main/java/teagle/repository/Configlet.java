package teagle.repository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Configlet {
	public Configlet(ConfigField field)
	{
		this.field = field;
		value = field.getDefval();
	}
	
	public Configlet(ConfigField field, String value)
	{
		this.field = field;
		this.value = value;
	}
	
	public String getName()
	{
		return field.getName();
	}
	
	public String getType()
	{
		return field.getType();
	}
	
	public String getValueString()
	{
		return value;
	}
	
	public Object getValue()
	{
		if (value == null)
			return null;
		if (getType().equals("boolean"))
			return (value.equals("1") || value.toLowerCase().equals("true") || value.toLowerCase().equals("t") ||
						value.toLowerCase().equals("yes") || value.toLowerCase().equals("y")); 
		if (getType().equals("integer"))
			return value.length() > 0 ? Integer.valueOf(value) : 0;
			
		if (getType().equals("float") || getType().equals("double"))
			return value.length() > 0 ? Double.valueOf(value) : 0.0;
			
		return value;
	}
	
	public String[] getArrayValueStrings() throws RepositoryException
	{
		if (!isArray())
			throw new RepositoryException("Not an array");
		
		String[] vals = getValueString().split("/");
		try {
			for (int i = 0; i < vals.length; ++i)
				vals[i] = (vals[i] == null || vals[i].length() == 0) ? null : URLDecoder.decode(vals[i], "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
		return vals;
	}
	
	public void setArrayValueStrings(String[] values) throws RepositoryException
	{
		if (!isArray())
			throw new RepositoryException("Not an array");
		
		String[] result = new String[values.length];
		try
		{
			for (int i = 0; i < values.length; ++i)
				result[i] = (values[i] == null || values[i].length() == 0) ? "" : URLEncoder.encode(values[i], "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			throw new RepositoryException(e);
		}
		
		String r = "";
		for (int i = 0; i < result.length; ++i)
		{
			r += result[i];
			if ( i < result.length - 1)
				r += "/";
		}
		setValueString(r);
	}
	
	public void setValueString(String value)
	{
		this.value = value;
	}
	
	public String[] getReferenceValues() throws RepositoryException 
	{
		if (!isReference())
			throw new RepositoryException("Not a reference");
		
		if (isArray())
			return getArrayValueStrings();
		
		String val = getValueString();
		
		if (val == null || val.length() == 0)
			return new String[0];
		
		return new String[] { val };
	}
	
	public boolean isReference()
	{
		return field.isReference();
	}
	
	public boolean isArray()
	{
		return field.isArray();
	}
	
	private ConfigField field;
	String value;
}
