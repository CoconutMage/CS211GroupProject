package coconuts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Controller
{
	//Method declaration
	public String URLRequest(String s) throws IOException
	{
		//Declare URL object and open the connection with the google maps servers
		URL obj = new URL(s);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//Define the type of request we'll be making
		con.setRequestMethod("GET");
		
		//Get the response code and display to the console so if theres an error it can easily be identified
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + s);
		System.out.println("Response Code : " + responseCode);
		
		//Read the text from the input stream
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		//Writes each line of the response to the variable and checks for the end
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		//Closes the input stream and the http connection the the google servers
		in.close();
		
		//returns the string containing the response from google
		return response.toString();
	}
	//Method declaration
	public Step[] parseSteps(String addressOne, String addressTwo) throws IOException
	{
		//Adjust the given addresses to be in the correct format for the http request
		String firstAddress = "";
		for(int i = 0; i < addressOne.split(" ").length; i++)
		{
			firstAddress += (addressOne.split(" "))[i] + "+";
		}
		firstAddress = firstAddress.substring(0, firstAddress.length()-1);
		String secondAddress = "";
		for(int i = 0; i < addressTwo.split(" ").length; i++)
		{
			secondAddress += (addressTwo.split(" "))[i] + "+";
		}
		secondAddress = secondAddress.substring(0, secondAddress.length()-1);
		
		//Make http request for directions data from google maps api servers
		String data = URLRequest("https://maps.googleapis.com/maps/api/directions/json?origin=" + firstAddress + "&destination=" + secondAddress + "&key=AIzaSyCIBgYyJdTPJOtax_vFkYEd9QiTNBqwx9A");
		
		//Splits the data string a bazzilion times to filter out formatting and data that we dont need
		data = (data.split("\"steps\" : \\["))[1];
		data = (data.split("\\],")[0]);
		String[] splitData = data.split("html_instructions");
		String[] splitDataTwo = new String[splitData.length];
		Step[] step = new Step[1000];
		
		//Loops through the remaining data and pulls out the useful information
		for(int i = 0; i < splitData.length; i++)
		{
			if(splitData[i].contains("start_location"))
			{
				splitDataTwo[i] = (splitData[i].split("start_location"))[1];
			}
			else
			{
				splitDataTwo[i] = splitData[i];
			}
		}
		//Loops through the useful information left for each step
		for(int i = 0; i < splitDataTwo.length - 1; i++)
		{
			//Declare variables to store individual data
			String[] parsedData = (splitDataTwo[i].split(","));
			String startingLocation = "";
			String distance = "";
			String time = "";
			String endLocation = "";
			
			//Loops through the parsed data and assigns that information to the appropriate variable
			for(int j = 0; j < parsedData.length; j++)
			{
				if(i != 0)
				{
					startingLocation = parsedData[0] + " " + parsedData[1];
					distance = parsedData[3];
					time = parsedData[5];
					endLocation = parsedData[7] + " " + parsedData[8];
				}
				else
				{
					startingLocation = "";
					distance = parsedData[0];
					time = parsedData[2];
					endLocation = parsedData[4] + " " + parsedData[5];
				}
			}
			//Formats all the data in the manner in which it has to be stored in the Step objects
			if (startingLocation != "")
			{
				startingLocation = (startingLocation.split(":"))[2] + (startingLocation.split(":"))[3];
				startingLocation = (startingLocation.split(" "))[1] + " " + (startingLocation.split(" "))[28];
			}
			distance = (distance.split("text"))[1];
			distance = (distance.split("\""))[2];
			distance = (distance.split("mi"))[0];
			//Converts feet to miles
			if(distance.contains("ft"))
			{
				distance = String.valueOf(Float.parseFloat((distance.split("ft"))[0])*0.000189394);
			}
			time = (time.split("text"))[1];
			time = (time.split("\""))[2];
			//Converts hours to minutes
			if (time.contains("hours"))
			{
				time = String.valueOf(Float.parseFloat((time.split("hours"))[0])*60 + Float.parseFloat((time.split("hours")[1].split("mins"))[0]));
			}
			else if(time.contains("hour"))
			{
				time = String.valueOf(Float.parseFloat((time.split("hour"))[0])*60 + Float.parseFloat((time.split("hour")[1].split("mins"))[0]));
			}
			time = (time.split("mins"))[0];
			time = (time.split("min"))[0];
			endLocation = (endLocation.split("end_location"))[1];
			endLocation = (endLocation.split(":"))[2] + (endLocation.split(":"))[3];
			endLocation = (endLocation.split(" "))[1] + " " + (endLocation.split(" "))[28];
			
			//Prints the data for each step to ensure proper formatting
			System.out.println("Step: " + (i + 1) + "\nStart Location: " + startingLocation + "\nDistance: " + Float.parseFloat(distance) + "\nTime: " + Float.parseFloat(time) + "\nEnd Location: " + endLocation + "\n");
			
			//Creates location objects with the start and end location data to be passed into step object
			Location start = null;
			if (startingLocation != "")
			{
				start = new Location((startingLocation.split(" "))[0], (startingLocation.split(" "))[1]);
			}
			Location end = new Location((endLocation.split(" "))[0], (endLocation.split(" "))[1]);
			
			//Creates a new step object with the data and adds it to the array to be returned
			step[i] = new Step(start, Float.parseFloat(distance), (int) (Float.parseFloat(time)), end);
		}
		
		//Shaves the array to be returned down to the correct size based on actual number of steps for optimization
		int j = 0;
		for(int i = 0; i < step.length; i++)
		{
			if (step[i] != null)
			{
				j += 1;
			}
			else
			{
				break;
			}
		}
		
		//Creates the final step array and returns it
		Step[] steps = new Step[j];
		for (int i = 0; i < steps.length; i++)
		{
			steps[i] = step[i];
		}
		return steps;
	}
	//Method declaration
	public MeetingPlaceArrayList findMeetingPlaces(Location midpoint, int radius) throws IOException
	{
		//Makes http request to google for all the places near the given location. radius is given in meters, and location is a latitude and longitude
		String data = URLRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + midpoint.getLat() + "," + midpoint.getLng() + "&radius=" + radius + "&key=AIzaSyCIBgYyJdTPJOtax_vFkYEd9QiTNBqwx9A");
		//Splits the returned data to filter out the useless stuff
		String[] splitData = data.split("name");
		//Creates object to be returned
		MeetingPlaceArrayList meetingPlaces = new MeetingPlaceArrayList();
		
		//Loop through the split data
		for(int i = 0; i < splitData.length; i++)
		{
			//Declaring variables to store the information needed
			String name = "";
			String price = "";
			String rating = "";
			String type = "";
			String address = "";
			
			//The first string is useless so its skipped over
			if (i == 0)
			{
				splitData[i] = "";
				continue;
			}
			//Pulls the data from the strings
			if (splitData[i].contains("geometry")) splitData[i] = splitData[i].split("geometry")[0];
			if (splitData[i].contains("photos") && splitData[i].contains("global")) splitData[i] = splitData[i].split("photos")[0] + splitData[i].split("global")[1];
			if (splitData[i].contains("locality")) splitData[i] = "";
			if (splitData[i].length() <= 0) continue;
			splitData[i] = splitData[i].substring(5, splitData[i].length());
			String[] parsedData = splitData[i].split("\"");
			
			//Goes through and assigns the data variables based on their position in the parsed data
			name = parsedData[0];
			int j = 0;
			//Position differs if the location has a price level attached to it
			if (splitData[i].contains("price_level"))
			{
				price = parsedData[11];
				price = price.substring(3);
				price = (price.split(","))[0];
				rating = parsedData[13];
				rating = rating.substring(3);
				rating = (rating.split(","))[0];
				for (int k = 0; k < parsedData.length; k++)
				{
					if(parsedData[k].contains("vicinity"))
					{
						j = k + 2;
						break;
					}
				}
				address = parsedData[j];
				if (name.contains("AT&T")) type = parsedData[26];
				else
				{
					for (int k = 0; k < parsedData.length; k++)
					{
						if(parsedData[k].contains("type"))
						{
							j = k + 2;
							break;
						}
					}
					type = parsedData[j];
				}
				if (type == "point_of_interest")
				{
					type = "";
				}
				//Creates string to be displayed later to ensure data is correct and formatted properly
				splitData[i] = "Name: " + name + "\nPrice Level: " + price + "\nRating: " + rating + "\nTypes: " + type + "\nAddress: " + address + "\n";
				//Creates meeting place object and adds it to the array list
				Restaurant res = new Restaurant(name, address, type, rating, price);
				meetingPlaces.add(res);
			}
			//This is the exact same code as before but with data in different locations, see above comments for details
			else
			{
				rating = parsedData[11];
				rating = rating.substring(3);
				rating = (rating.split(","))[0];
				type = parsedData[22];
				for (int k = 0; k < parsedData.length; k++)
				{
					if(parsedData[k].contains("vicinity"))
					{
						j = k + 2;
						break;
					}
				}
				address = parsedData[j];
				for (int k = 0; k < parsedData.length; k++)
				{
					if(parsedData[k].contains("type"))
					{
						j = k + 2;
						break;
					}
				}
				type = parsedData[j];
				splitData[i] = "Name: " + name + "\nRating: " + rating + "\nTypes: " + type + "\nAddress: " + address + "\n";
				MeetingPlace meet = new MeetingPlace(name, address, type, rating);
				meetingPlaces.add(meet);
			}
			//Prints data to console to ensure proper parsing and formatting
			System.out.println(splitData[i]);
		}
		//Return the array list
		return meetingPlaces;
	}
}