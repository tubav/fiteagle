package teagle.vct.app;

import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Models a time interval when a specific {@link ResourceSpec} is available for booking.
 * @author Stefan Harder
 */
public class Availability {
	private DateTime firstDay;
	private DateTime lastDay;
	private DateTime firstTime;
	private DateTime lastTime;
	private List<Integer> daysOfWeek;
		
	//days of week from 0-6 starting with monday
	//TODO: alexandru mihaiuc: replace ugly numbers with an Enum type; xml can handle it; remember to check out repository/resources to properly update the temporary xml sample data
	/**
	 * @param firstDay_day
	 * @param firstDay_month
	 * @param firstDay_year
	 * @param lastDay_day
	 * @param lastDay_month
	 * @param lastDay_year
	 * @param daysOfWeek must be a 0-based list of days, with 0 corresponding to Monday, 1 Tuesday, 2 Wednesday, ... 6 Friday
	 * @param firstTime_hour
	 * @param firstTime_minute
	 * @param lastTime_hour
	 * @param lastTime_minute
	 */
	public Availability(int firstDay_day, int firstDay_month, int firstDay_year, int lastDay_day, int lastDay_month, int lastDay_year, List<Integer> daysOfWeek,
			int firstTime_hour, int firstTime_minute, int lastTime_hour, int lastTime_minute) {
		firstDay = new DateTime(firstDay_year, firstDay_month, firstDay_day, 0, 0, 0, 0, DateTimeZone.UTC);
		lastDay = new DateTime(lastDay_year, lastDay_month, lastDay_day, 23, 59, 0, 0, DateTimeZone.UTC);
		
		firstTime = new DateTime(1970, 1, 1, firstTime_hour, firstTime_minute, 0, 0, DateTimeZone.UTC);	//don't forget to use the start of the Java epoch (1 Jan 1970)
		lastTime = new DateTime(1970, 1, 1, lastTime_hour, lastTime_minute, 0, 0, DateTimeZone.UTC);

		this.daysOfWeek = daysOfWeek;
	}
	
	public Availability() {
		firstDay = new DateTime(2009, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);	// TODO: will probably have to change these to a more appropriate value
		lastDay = new DateTime(2009, 12, 31, 0, 0, 0, 0, DateTimeZone.UTC);
		firstTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);	// don't forget to use the start of the Java epoch (1 Jan 1970)
		lastTime = new DateTime(1970, 1, 1, 23, 59, 0, 0, DateTimeZone.UTC);	// ^^
		
		Integer[] ints = {0, 1, 2, 3, 4, 5, 6}; 
		this.daysOfWeek = Arrays.asList(ints); 
	}

	public DateTime getFirstDay() {
		return firstDay;
	}
	
	public void setFirstDay(DateTime firstDay) {
		this.firstDay = firstDay.withMillisOfDay(0);
	}

	public DateTime getLastDay() {
		return lastDay;
	}

	public void setLastDay(DateTime lastDay) {
		this.lastDay = lastDay.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(0).withMillisOfSecond(0);
	}

	public DateTime getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(DateTime firstTime) {
		this.firstTime = firstTime.withYear(1970).withDayOfYear(1);
	}

	public DateTime getLastTime() {
		return lastTime;
	}

	public void setLastTime(DateTime lastTime) {
		this.lastTime = lastTime.withYear(1970).withDayOfYear(1);
	}

	/**
	 * @return the 0-based list of days within the current user's selection, 0 = Monday, ... 6 = Sunday
	 */
	public List<Integer> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(List<Integer> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	
	public String toString() {
		return firstDay.toString() + "-" + lastDay.toString() + " (" + firstTime.toString() + "-" + lastTime.toString() + ")";
	}
}
