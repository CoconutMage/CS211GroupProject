/* The MeetingPlaceArrayList class stores multiple MeetingPlace objects into an
 * ArrayList. Instead of calling displayDetails for each object, the controller
 * can call displayDetails for this class and send all information to the User
 * Interface more efficiently.
 * 
 * Note: This class is similar to the ChainFilter class seen in Project 3.
 */
import java.util.ArrayList;

public class MeetingPlaceArrayList {
	// Declares an ArrayList of MeetingPlace objects.
	private ArrayList<MeetingPlace> meetingPlaces;
	
	// Adds a MeetingPlace to the ArrayList.
	public void add(MeetingPlace meetingPlace) {
		meetingPlaces.add(meetingPlace);
	}
	
	// Returns string with details for all MeetingPlace objects in meetingPlaces.
	public String displayDetails() {
		String details = "";
		for (int i = 0; i < meetingPlaces.size(); ++i) {
			details += meetingPlaces.get(i).displayDetails() + "/";
		}
		return details;
	}

}
