/*
 * The Location class stores a latitude and longitude attribute.
 * It is used explicitly when the controller finds a midpoint.
 * Every MeetingPlace object is also a Location object.
*/

public class Location {
	// Declares latitude and longitude attributes.
	private String lat;
	private String lng;
	
	// Declares two constructors.
	public Location() {
		this.lat = "";
		this.lng = "";
	}
	
	public Location(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	// Declares setters and getters for lat and lng.
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}
