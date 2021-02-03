package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable{
		if(StepDefination.place_id == null){
			StepDefination s = new StepDefination();
			s.add_Place_payload("Crazy XYZ", "Telugu", "Samsi");
			s.user_calls_with_http_request("AddPlaceAPI", "POST");
			s.to_verify_place_Id_created_maps_to_using("Crazy XYZ", "GetPlaceAPI");
			
		}
	}
}
