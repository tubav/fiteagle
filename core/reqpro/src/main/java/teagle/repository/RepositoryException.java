package teagle.repository;

public class RepositoryException extends Exception
{

	public RepositoryException(Throwable cause)
	{
		super(cause);
	}
	
	public RepositoryException(String string)
	{
		super(string);
	}

	public RepositoryException(String string, Throwable cause)
	{
		super(string, cause);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
