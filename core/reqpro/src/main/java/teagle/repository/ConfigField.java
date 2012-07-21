package teagle.repository;

/**
 * This class is returned when parsing a configuration source code, for each
 * field in that configuration.
 */
public class ConfigField {
	/** 
	 * Field name. Generally a java identifier.
	 */
	private String name;
	/**
	 * Field type. Currently restricted to int, boolean, string, double.
	 */
	private String type;
	/**
	 * String representation of the default field value. 
	 */
	private String defval; 
	/**
	 * Field description. 
	 */
	private String description;
	
	public ConfigField(String name, String type, String defval)
	{
		this(name, type, defval, "");
	}
	
	public ConfigField(String name, String type, String defval, String description) 
	{
		this.name = name;
		this.type = type.toLowerCase();
		this.defval = defval;
		this.description = description;
	}

	public String getName()
	{
		return this.name;
	}

	public String getType()
	{
		return this.type;
	}

	public String getDefval()
	{
		return this.defval;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public boolean isArray()
	{
		return type.endsWith("-array");
	}
	
	public boolean isReference()
	{
		return type.startsWith("reference");
	}
}