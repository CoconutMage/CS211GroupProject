/* The Step class stores information about each step in the navigation from Address 1 to Address 2.
 * It is used explicitly as a way for the controller method ParseSteps to store the information
 * needed to calculate the midpoint.
 */

public class Step {
	// Declare attributes.
	private Location startLoc;
	private float distance;
	private int duration;
	private Location endLoc;
	
	// Declare constructor. There should be no instance of a default Step.
	public Step(Location startLoc, int distance, int duration, Location endLoc) {
		this.startLoc = startLoc;
		this.distance = distance;
		this.duration = duration;
		this.endLoc = endLoc;
	}
	
	// Declares getters only. Step information should not be edited after being constructed.
	public void setStartLoc(Location startLoc) {
		this.startLoc = startLoc;
	}
	public Location getStartLoc() {
		return startLoc;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getDistance() {
		return distance;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return duration;
	}
	
	public void setEndLoc(Location endLoc) {
		this.endLoc = endLoc;
	}
	public Location getEndLoc() {
		return endLoc;
	}
}
