
public class Controller {
	// ATTRIBUTES
	private String address1; // takes two input addresses from the UI
	private String address2;
	// TO DO: IMPLEMENT RADIUS AND RATIO
	
	// CONSTRUCTORS
	public Controller(String address1, String address2) { // Constructor to initialize the attribute variable.
		this.address1 = address1;
		this.address2 = address2;
		// TO DO: IMPLEMENT RADIUS AND RATIO
	}
	
	// METHODS BY ADAM
	// public void ParseData(String s);
	// public void URLrequest();
	
	// METHODS BY ZAIN
	public Location findMidPoint(Step[] step) {
		// Inputs array of step objects with Start/End Location objects and Distance/Time Integers.
		// Finds the total distance of step from first to last, identifies what would be the middle distance, then finds the step that reaches that point.

		// Find Total Distance
		int totDist = 0; // the total distance tracker
		for (int t = 0; t < step.length; t++) { // for loop that iterates through each step
			totDist = totDist + step[t].getDistance(); // adds each steps distance to total tracker
		}
		int midDist = totDist/2; // Finds half way distance. TO DO: IMPLEMENT RATIO
		
		// Find Mid Step
		int cumuDist = 0; // tracker for cumulative distance covered
		Step midStep = null; // Step variable to store the step object that is identified as midpoint. Initialized to null for compilation
		
		for (int t = 0; t < step.length; t++) { // Itereates through each Step Object again
			cumuDist = cumuDist + step[t].getDistance(); // adds distance to cumulative tracker
			if (cumuDist >= midDist) { // Checks if distance covered has exceeded midDist with t'th step
				// If so, assign t'th step to midStep and break the loop
				midStep = step[t];
				break;
			}
			// If cumuDist still less than midDist, continue iterating until it is.
		}
		
		// Create and Return Location Object
		Location loc = midStep.getStartLoc();
		return loc; // returns the Location Object
	}
	
	public findMeetingPlaces(Location loc, int radius) { // ADAM METHOD
		// Parses through the URL request to produce a array of locations in a radius around location loc coordinates
		// ADAM TO DO
	}
	
	public String run() {
		// Calls All Methods In Order, Returning the displayDetails of MeetingPlaceArrayList.
		// return this.findMeetingPlaces(this.FindMidPoint(ParseSteps(URLrequest(String, String, Int) (general order)
		String API = URLrequest(String arg, String arg2, int x);
		Step[] step = Parsesteps(API);
		Location loc = findMidPoint(Step[] step);
		MeetingPlaceArrayList meetList = findMeetingPlaces(loc, radius);
		return meetList.displayDetails();
	}
	
	// GETTERS & SETTERS
	public String getAddress1() {
		return address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress(String address, int i) {
		if (i == 1) {
			this.address1 = address;
		}
		else if(i == 2) {
			this.address2 = address;
		}
	}
	
	public void setAddress(String address1, String address2) {
		this.address1 = address1;
		this.address2 = address2;
	}
	
} // END CLASS DEFINITION
