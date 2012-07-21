package teagle.repository.tssg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.timer.Timer;

import teagle.repository.RepositoryException;
import teagle.repository.tssg.client.TSSGClient;



public class TSSGResource extends AbstractTSSGEntity
{
	public static class ReservationEntry
	{
		public ReservationEntry(long day, String[] users)
		{
			assert(users.length > 0);
			this.day = day;
			this.users = users;
		}
		
		public void addUser(String user)
		{
			String[] newUsers = new String[users.length + 1];
			for (int i = 0; i < users.length; ++i)
			{
				if (users[i].equals(user))
					return;
				newUsers[i]  = users[i];
			}
			newUsers[newUsers.length - 1] = user;
			users = newUsers;
		}
		
		public boolean contains(String user)
		{
			for (String u : users)
				if (u.equals(user))
					return true;
			return false;
		}
		
		public long day;
		public String[] users;
	}
	
	public String commonName;
	public String description;
	private Cost cost;
	private ConfigParamComposite configurationParameters;
	private String url = null;
	private String reservations = null;
	private List<ReservationEntry> parsedReservations = null;
	private String realDescription = null;
	
	public TSSGResource(TSSGClient client, String name, String description, ConfigParamComposite params, Cost price)
	{
		super(client);
		commonName = name;
		this.description = description;
		configurationParameters = params;
		this.cost = price;
	}
	
	public int getPrice() throws RepositoryException
	{
		init();
		if (cost == null)
			return 0;
		return cost.costAmount;
	}
	
	public Cost getCost() throws RepositoryException
	{
		init();
		return cost;
	}
	
	public ConfigParamAtomic[] getConfigParams() throws RepositoryException
	{
		init();
		if (configurationParameters == null)
			return new ConfigParamAtomic[0];
		return configurationParameters.getConfigParams();
	}
	
	public void setConfigurationParameters(ConfigParamComposite c)
	{
		configurationParameters = c;
	}
	
	protected void _init() throws RepositoryException
	{
		//System.out.println("Init for: " + commonName + " " + getId());
		if (configurationParameters != null)
			configurationParameters = client.getObject(configurationParameters.getClass(), configurationParameters.getId());
		
		if (cost == null)
			return;
		if (cost.getId() == null)
			cost = null;
		else
			cost = client.getObject(Cost.class, cost.getId());
	}
	
	private void splitDescription()
	{
		if (url != null)
			return;
		url = "";
		reservations = "";
		
		String[] vals = description.split("\\|");
		
		if (vals.length == 0)
		{
			realDescription = "";
			return;
		}
		
		realDescription = vals[vals.length - 1];
		if (vals.length > 1)
			url = vals[vals.length - 2];
		if (vals.length > 2)
			reservations = vals[0];
	}
	
	private void parseReservations()
	{
		if (parsedReservations != null)
			return;
		splitDescription();
		
		parsedReservations = new ArrayList<ReservationEntry>();
		String[] days = reservations.split(";");
		for (String dayString : days)
		{
			String[] vals = dayString.split(":");
			assert(vals.length == 2);
			Long day = Long.parseLong(vals[0]);
			parsedReservations.add(new ReservationEntry(day, vals[1].split(",")));
		}
	}
	
	private void writeReservation()
	{
		assert(parsedReservations != null);
		String result = "";
		
		int i = 0;
		for (ReservationEntry e : parsedReservations)
		{
			assert(e.users.length > 0);
			result += Long.toString(e.day) + ":";
			int j = 0;
			for (String user : e.users)
			{
				result += user;
				if (++j < e.users.length)
					result += ",";
			}
			if (++i < parsedReservations.size())
				result += ";";
		}
		
		reservations = result;
	}
	
	public List<ReservationEntry> getReservers(Date start, Date end)
	{
		ArrayList<ReservationEntry> result = new ArrayList<ReservationEntry>();
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		end.setHours(0);
		end.setMinutes(0);
		end.setSeconds(0);
		
		parseReservations();
		
		for (int i = 0; i < parsedReservations.size(); ++i)
		{
			ReservationEntry e = parsedReservations.get(i);
			if (e.day >= start.getTime() && e.day <= end.getTime())
			{
				do
				{
					result.add(e);
					if (++i == parsedReservations.size())
						break;
					e = parsedReservations.get(i);
				} while (e.day <= end.getTime());
				
				break;
			}
			
			if (e.day > end.getTime())
				break;
		}
		
		return result;
	}
	
	public void addReserver(String user, Date start, Date end)
	{
		assert(start.getTime() <= end.getTime());
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		end.setHours(0);
		end.setMinutes(0);
		end.setSeconds(0);
		
		List<ReservationEntry> reservers = getReservers(start, end);
		int i = 0;
		for (i = 0; i < reservers.size() && reservers.get(i).day < start.getTime(); ++i);
		
		for (;i < reservers.size() && start.getTime() <= end.getTime(); ++i)
		{
			ReservationEntry e = reservers.get(i);
			
			if (e.day == start.getTime())
				e.addUser(user);
			else
				reservers.add(i, new ReservationEntry(start.getTime(), new String[] { user } ));
			
			start.setTime(start.getTime() + Timer.ONE_DAY);
		}
		
		while (start.getTime() <= end.getTime())
		{
			reservers.add(new ReservationEntry(start.getTime(), new String[] { user } ));
			start.setTime(start.getTime() + Timer.ONE_DAY);
		}
		
		writeReservation();
	}
	
	public String getDescription()
	{
		splitDescription();
		return realDescription;
	}
	
	public String getUrl()
	{
		splitDescription();
		return url;
	}
	
	public String getReservations()
	{
		splitDescription();
		return reservations;
	}
}
