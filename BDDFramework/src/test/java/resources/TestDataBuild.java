package resources;

import java.util.ArrayList;

import pojo.AddPlacePojo;
import pojo.LocationPojo;

public class TestDataBuild {
	
	public AddPlacePojo addPlacePayload(String name, String language, String address)
	{
		AddPlacePojo ap=new AddPlacePojo();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("1234567890");
		ap.setAddress(address);
		ap.setWebsite("http://mgkacademy.com");
		ap.setLanguage(language);
		
		ArrayList<String> arr=new ArrayList<String>();
		arr.add("Park");
		arr.add("Shop");
		
		ap.setTypes(arr);
		
		LocationPojo loc=new LocationPojo();
		loc.setLat(10.133500);
		loc.setLng(-15.54200);
		ap.setLocation(loc);
		
		return ap;
	}
	
	public static String deletePlacePayload(String place_Id)
	{
		String str="{\r\n"
				+ "    \"place_id\": \""+place_Id+"\"\r\n"
				+ "}";
		return str;
	}
}
