package teagle.repository.tssg.model;

import teagle.repository.tssg.client.TSSGClient;

public class Cost extends AbstractTSSGEntity
{
	public Cost(TSSGClient client)
	{
		super(client);
		costAmount = 100;
		costDenominator = "Euro";
	}
	
	public int costAmount;
	public String costDenominator;
}
