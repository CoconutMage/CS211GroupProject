package coconuts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JOptionPane;

public class Playground
{
	public static void main(String[] args) throws IOException
	{
		String url = "https://maps.googleapis.com/maps/api/directions/json?origin=9645+Braddock+Rd&destination=Kingsley+Commons+Townhouses&key=AIzaSyCIBgYyJdTPJOtax_vFkYEd9QiTNBqwx9A";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		String[] temp = ParseData(response.toString());
		for(int i = 0; i < temp.length;i++)
		{
			System.out.println(temp[i]);
		}
		//JOptionPane.showMessageDialog(null, "Hello World");
	}
	public static String[] ParseData(String s)
	{
		String steps = s.split("steps")[1];
		steps = steps.split("traffic_speed_entry")[0];
		String[] stepArray = steps.split(",");
		String[] shorterStepArray = stepArray.split("Value");
		return shorterStepArray;
	}
}