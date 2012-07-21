package teagle.repository;

public class DuplicateNameException extends RepositoryException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateNameException(Throwable cause)
	{
		super(cause);
	}

	public DuplicateNameException(String string)
	{
		super(string);
	}

	public DuplicateNameException(String string, Throwable cause)
	{
		super(string, cause);
	}

}
