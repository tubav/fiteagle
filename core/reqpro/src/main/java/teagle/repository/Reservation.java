package teagle.repository;

public class Reservation {
	/**
	 * The user id (user name) of the owner of this reservation. 
	 */
	public String user;
	/**
	 * The id of the resource being reserved.
	 */
	public String id;
	/**
	 * <tt>null</tt> or the id (name) of the {@link VCT} of which this resource is part.
	 */
	public String vct;
	/**
	 * Time in milliseconds since January 1st, 1970 (start of Java epoch as of joda.DateTime) when
	 * this reservation for the resource starts.
	 */
	public long startTime;
	/**
	 * Time in milliseconds since January 1st, 1970 (start of Java epoch as of joda.DateTime) when
	 * this reservation for the resource ends.
	 */
	public long stopTime;
	
	public Reservation(String user, String id, String vct, long startTime, long stopTime) {
		this.user = user;
		this.id = id;
		this.vct = vct;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}
}
