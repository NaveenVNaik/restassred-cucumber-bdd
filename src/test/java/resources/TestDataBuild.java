package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace getAddPlacePayload(String name, String language, String address){
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		ArrayList<String> type = new ArrayList<String>();
		type.add("shoe test");
		type.add("shop");
		
		AddPlace addPlace = new AddPlace();
		addPlace.setLocation(loc);
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress(address);
		addPlace.setTypes(type);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(language);
		
		return addPlace;
	}
	
	public String getDeletePlacePayload(String placeId){
		return "{ \"place_id\":\""+placeId+"\" }";
	}
}
