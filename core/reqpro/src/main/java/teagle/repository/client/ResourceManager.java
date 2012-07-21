package teagle.repository.client;

import teagle.repository.RepositoryException;
import teagle.repository.Resource;


public interface ResourceManager
{

	Resource[] getResources() throws RepositoryException;
	public Resource getResource(String name) throws RepositoryException;

	/**
	 * Add a resource to the repo. The String parameters should contain Java source
	 * code for this resource. These two source files can be generated for instance
	 * using ResourceManager.generateSources() (currently done in the portal).
	 * @return the integer id of the resource
	 */
	public Integer addResource(Resource resource, String resSource, String cfgSource) throws RepositoryException;
	
	/**
	 * Modifies an existing resource.
	 */
	public void setResource(Resource resource) throws RepositoryException;
	
	/**
	 * Removes the resource with the given name from the repository.
	 */
	public void removeResource(String name) throws RepositoryException;
	boolean isUsed(String name) throws RepositoryException;

}
