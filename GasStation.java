// The GasStation class is a MeetingPlace with an additional gasPrice attribute.
public class GasStation extends MeetingPlace {
	// Declares gasPrice attribute.
	private String gasPrice;
	
	// Declares constructors assuming an object with a gasPrice will also have all other information.
	public GasStation(String lat, String lng, String name, String address, String placeType, String starRating, String gasPrice) {
		super(lat, lng, name, address, placeType, starRating);
		this.gasPrice = gasPrice;
	}
	public GasStation(String name, String address, String placeType, String starRating, String gasPrice) {
		super(name, address, placeType, starRating);
		this.gasPrice = gasPrice;
	}
	
	// Declares setter and getter for gasPrice.
	public String getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}	
	
	// Overrides displayDetails in MeetingPlace to add the gasPrice.
	@Override
	public String displayDetails() {
		String details = "";
		if (this.getStarRating() != "") {
			details += "Rated: " + this.getStarRating() + " out of 5" + "/";
		}
		details += gasPrice + "/";
		return details;
	}

}
