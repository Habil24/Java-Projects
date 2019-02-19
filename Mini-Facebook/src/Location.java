
public class Location {
	
	private double latitude;
	private double longtitude;
	
	/**
	 * @param latitude
	 * Latitude
	 * @param longtitude
	 * Longtitude
	 * Post Location Constructor
	 */
	public Location(double latitude,double longtitude){
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Location: " + this.longtitude + ", " + this.latitude;
	}
}
