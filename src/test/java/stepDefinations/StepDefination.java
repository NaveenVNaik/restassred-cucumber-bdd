package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

public class StepDefination extends Utilities{
	TestDataBuild testData = new TestDataBuild();;
	RequestSpecification req;
	Response resp;
	static String place_id;
	
	@Given("^Add Place payload \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_payload(String name, String language, String address) throws Throwable {
		System.out.println("Inside add_Place_payload");
		
		req = given()
				.spec(getRequestSpec())
				.body(testData.getAddPlacePayload(name,language,address));
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String api, String httpMethod) throws Throwable {
		System.out.println("Inside user_calls_AddPlaceAPI_with_POST_http_request");
		
		APIResources apiResource= APIResources.valueOf(api);
		System.out.println(apiResource.getAPIResorces());
		
		/*resp = req.when()
				.post(apiResource.getAPIResorces())
				.then().spec(getResponseSpec())
				.body("scope",equalTo("APP"))
				.extract().response();*/
		if(httpMethod.equalsIgnoreCase("POST")){
			resp = req.when()
				.post(apiResource.getAPIResorces());
		}else if(httpMethod.equalsIgnoreCase("GET")){
			resp = req.when()
					.get(apiResource.getAPIResorces());
		}
		
	}

	@Then("^the API call is successful with status code (\\d+)$")
	public void the_API_call_is_successful_with_status_code(int arg1) throws Throwable {
		System.out.println("Inside the_API_call_is_successful_with_status_code");
		
		assertEquals(resp.getStatusCode(),200);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String key, String expectedValue) throws Throwable {
		System.out.println("Inside in_response_body_is");
	    assertEquals(getJSONFieldValue(resp,key),expectedValue);
	}
	
	@Then("^to verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void to_verify_place_Id_created_maps_to_using(String expectedName, String apiName) throws Throwable {
		System.out.println("Inside to_verify_place_Id_created_maps_to_using");
		place_id = getJSONFieldValue(resp,"place_id");
		System.out.println(place_id);
		
		req= given().spec(getRequestSpec()).queryParam("place_id", place_id);	
	    
		user_calls_with_http_request(apiName,"GET");
		
		String actualName = getJSONFieldValue(resp,"name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("^DeletePlace payload$")
	public void deleteplace_payload() throws Throwable {
		System.out.println("Inside deleteplace_payload = "+place_id);
		req = given().spec(getRequestSpec())
				.body(testData.getDeletePlacePayload(place_id));
	}
	
}
