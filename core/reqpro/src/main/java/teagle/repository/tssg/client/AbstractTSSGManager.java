package teagle.repository.tssg.client;

import java.io.IOException;

public abstract class AbstractTSSGManager
{
	protected TSSGClient client;
	
	/*public AbstractTSSGManager(ClassLoader loader, Object ... aliases) throws IOException
	{
		client = new TSSGClient(loader, aliases);
	}*/
	public AbstractTSSGManager(ClassLoader loader) throws IOException
	{
		client = CachingTSSGClient.getInstance();
		//client = new TSSGClient(loader);
	}
}
