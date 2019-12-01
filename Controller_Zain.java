
public class Controller_Zain {
	private String address1; // takes two input addresses from the UI
	private String address2;
	private int radius;
	
	public Location findMidDistance(Step[] step) {
		/*
		 * Inputs an array of step objects, each with Start/End location objects and Distance/Time integers as attributes
		 * Finds Total distance from first to last step, then finds the middle distance according to ratio
		 * Then iterates through array to find the step that reaches the middle distance (without surpassing)
		 */
		
		int ratio = 2; // cut in half
		
		// Find Total Distance---
		float totDist = 0; // total distance tracker
		for (int t = 0; t < step.length; t++) { // iterate through step
			totDist = totDist + step[t].getDistance(); // appends step distance to total
		}
		float midDist = totDist/ratio; // Finds middle point

		// Find Mid Step ---
		float cumuDist = 0; // distance covered tracker
		Step midStep = null; // Variable to store midpoint step object. Initialized to null for compilation

		for (int t = 0; t < step.length; t++) { // Itereates through step again
			cumuDist = cumuDist + step[t].getDistance(); // appends step distance to cumulative
			if (cumuDist >= midDist) { // Checks if new cumulative exceeds midpoint as a result of change
				// If true, the step t is the road containing the midStep. Break Loop.
				midStep = step[t];
				break;
			}
			// If cumulative still < midDist, continue iterating
		}
		
		// NEW: CALCULATE MIDPOINT LOCATION OBJECT FROM MIDSTEP---
		float x = cumuDist - midDist; // the difference between midPoint and endLoc
		float midLng = (float) ((x / midStep.getDistance()) * Math.sqrt( (Integer.parseInt(midStep.getStartLoc().getLng()) ^2) + (Integer.parseInt(midStep.getEndLoc().getLng()) ^2) ) + Integer.parseInt(midStep.getStartLoc().getLng()));
		float midLat = (float) ((x / midStep.getDistance()) * Math.sqrt( (Integer.parseInt(midStep.getStartLoc().getLng()) ^2) + (Integer.parseInt(midStep.getEndLoc().getLng()) ^2) ) + Integer.parseInt(midStep.getStartLoc().getLng()));
		Location loc = new Location(Float.toString(midLat) , Float.toString(midLng));
		return loc; // returns the Location Object
	}
	
	public Location findMidTime(Step[] step) {
		/*
		 * Inputs an array of step objects, each with Start/End location objects and Distance/Time integers as attributes
		 * Finds Total duration from first to last step, then finds the middle duration according to ratio
		 * Then iterates through array to find the step that reaches the middle duration (without surpassing)
		 */
		
		int ratio = 2; // Cut in half
		
		// Find Total Duration
		float totTime = 0; // total duration tracker
		for (int t = 0; t < step.length; t++) { // iterate through step
			totTime = totTime + step[t].getDuration(); // appends step duration to total
		}
		float midTime = totTime/ratio; // Finds middle point

		// Find Mid Step
		float cumuTime = 0; // duration covered tracker
		Step midStep = null; // Variable to store midpoint step object. Initialized to null for compilation

		for (int t = 0; t < step.length; t++) { // Itereates through step again
			cumuTime = cumuTime + step[t].getDistance(); // appends step duration to cumulative
			if (cumuTime >= midTime) { // Checks if new cumulative exceeds midpoint as a result of change
				// If so, assign t'th step to midStep and break the loop
				midStep = step[t];
				break;
			}
			// If cumulative still < midTime, continue iterating
		}

		// NEW: CALCULATE MIDPOINT LOCATION OBJECT FROM MIDSTEP---
		float x = cumuTime - midTime; // the difference between midPoint and endLoc
		float midLng = (float) ((x / midStep.getDuration()) * Math.sqrt( (Integer.parseInt(midStep.getStartLoc().getLng()) ^2) + (Integer.parseInt(midStep.getEndLoc().getLng()) ^2) ) + Integer.parseInt(midStep.getStartLoc().getLng()));
		float midLat = (float) ((x / midStep.getDuration()) * Math.sqrt( (Integer.parseInt(midStep.getStartLoc().getLng()) ^2) + (Integer.parseInt(midStep.getEndLoc().getLng()) ^2) ) + Integer.parseInt(midStep.getStartLoc().getLng()));
		Location loc = new Location(Float.toString(midLat) , Float.toString(midLng));
		return loc; // returns the Location Object
	}
	
	public Location findMidPoint(Step[] step, int DiTi) {
		/*
		 * Run will call findMidPoint to iterate through each Step in step
		 */
		if (DiTi == 0) { // Calls Distance
			Location loc = findMidDistance(step);
			return loc;
		}
		else if (DiTi == 1) { // Calls Time
			Location loc = findMidTime(step);
			return loc;
		}
		else { // If invalid, returns none
			return null;
		}
	}
	
}
