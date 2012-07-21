package teagle.repository.client;

import teagle.repository.OrchestrateReturn;
import teagle.repository.RepositoryException;
import teagle.repository.VCT;

public interface VctManager
{
	VCT getVct(String userName, String name) throws RepositoryException;

	String[][] listVcts(String userName) throws RepositoryException;

	void setVct(String userName, String name, VCT vct)
			throws RepositoryException;

	boolean testAndSetVct(String userName, String name, VCT currentVct,
			VCT originalVct) throws RepositoryException;

	OrchestrateReturn pollBooking(String userName, String name) throws RepositoryException;

	String[][] getVctsContainingResource(String userName, String id)
			throws RepositoryException;
	
	public void renameIds(String userName, String vctName, OrchestrateReturn.Result.Mapping[] mappings) throws RepositoryException;
}
