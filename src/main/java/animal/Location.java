package animal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Location model 
 * @author Yuanlong Zhang
 * @date 11 Aug 2022
 */
public class Location {

	public Location(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	// constraint: -90 to 90 for latitude
	@NotNull
	@Range(min = -90, max = 90)
	private double lat;

	// constraint: -180 to 180 for longitude
	@NotNull
	@Range(min = -180, max = 180)
	private double lon;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{\"lat\":%.5f,\"lon\":%.5f}", this.getLat(), this.getLon());
	}

}
