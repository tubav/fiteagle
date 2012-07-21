package teagle.repository;

import java.util.ArrayList;


/**
 * Represents a Resource object from the Resource Repository.
 * TODO: find a way to link this class (maybe through XStream) with the representation from the database.
 * @author Bogdan Harjoc and alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public class Resource {
	
	private PTM[] supportedBy;
	/**
	 * Unique identifier, similar to an SQL schema
	 */
	private Integer id;
	/**
	 * The name of the entity providing this resource type.
	 */
	private String provider;
	/**
	 * Fully qualified name for the class within the <tt>library</tt> package behind the specific resource type.
	 */
	private String name;
	/**
	 * Only the class part from within the <tt>library</tt> package.
	 */
	private String type;
	/**
	 * Cost for the resource. TODO: what for?
	 */
	private double price;
	/**
	 * Short descriptive text, in order to help identify the resource type.
	 */
	private String synopsis;
	/**
	 * Username that created this resource.
	 */
	private String owner;
	
	private String url;
	
	private ConfigField[] configFields;
	
	public Resource(Integer id, String owner, String provider, String name, String type, double price, String synopsis, String url, ConfigField[] configFields) {
		this.id = id;
		this.owner = owner;
		this.provider = provider;
		this.name = name;
		this.type = type;
		this.price = price;
		this.synopsis = synopsis;
		this.configFields = configFields;
		this.url = url;
	}
	
	
	
	public ConfigField[] getReferences()
	{
		ArrayList<ConfigField> fields = new ArrayList<ConfigField>();
		for (ConfigField f : configFields)
			if (f.getType().startsWith("reference"))
				fields.add(f);
		return fields.toArray(new ConfigField[0]);
	}
	
	public ConfigField[] getPrimitiveFields()
	{
		ArrayList<ConfigField> fields = new ArrayList<ConfigField>();
		for (ConfigField f : configFields)
			if (!f.getType().startsWith("reference"))
				fields.add(f);
		return fields.toArray(new ConfigField[0]);		
	}
	
	public ConfigField[] getConfigFields()
	{
		if (configFields == null)
			return new ConfigField[0];
		return configFields;
	}
	
	public ConfigField getConfigField(String name)
	{
		ConfigField[] fields = getConfigFields();
		for (ConfigField f : fields)
			if (f.getName().equals(name))
				return f;
		return null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return this.id + " " + this.name + " (" + this.type + ")" + this.owner + " " + this.provider + " " + this.price + " \"" + this.synopsis + "\"";
	}
}
