package teagle.repository.client;

import teagle.repository.PTM;
import teagle.repository.RepositoryException;

public interface PtmManager
{
	PTM getPtm(String id) throws RepositoryException;

	void removePtm(String id) throws RepositoryException;

	void setPtm(PTM ptm) throws RepositoryException;

	PTM[] listPtms() throws RepositoryException;
}
