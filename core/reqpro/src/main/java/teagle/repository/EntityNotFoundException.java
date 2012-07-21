package teagle.repository;

public class EntityNotFoundException extends RepositoryException {
	private int id;
	
	public EntityNotFoundException(String type, int id) {
		super("Entity of type " + type + " not found in repository: " + id);
		this.id = id;
	}

	public int getId()
	{
		return id;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
