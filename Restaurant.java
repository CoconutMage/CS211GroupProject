// The Restaurant class is a MeetingPlace with an additional dollarRating attribute.
public class Restaurant extends MeetingPlace {
	// Declares dollarRating attribute.
	private String dollarRating;
	
	// Declares constructors assuming an object with a dollarRating will also have all other information.
	public Restaurant(String lat, String lng, String name, String address, String placeType, String starRating, String dollarRating) {
		super(lat, lng, name, address, placeType, starRating);
		this.dollarRating = dollarRating;
	}
	
	public Restaurant(String name, String address, String placeType, String starRating, String dollarRating) {
		super(name, address, placeType, starRating);
		this.dollarRating = dollarRating;
	}
	
	// Declares setter and getter for dollarRating.
	public String getDollarRating() {
		return dollarRating;
	}
	public void setDollarRating(String dollarRating) {
		this.dollarRating = dollarRating;
	}
	
	// Overrides displayDetails in MeetingPlace to add the dollarRating.
	@Override
	public String displayDetails() {
		String details = "";
		details += this.getName() + "/";
		details += this.getAddress() + "/";
		if (this.getPlaceType() != "") {
			details += this.getPlaceType() + "/";
		}
		if (this.getStarRating() != "") {
			details += "Rated " + this.getStarRating() + " out of 5" + "/";
		}
		details += dollarRating + "/";
		return details;
	}
}
