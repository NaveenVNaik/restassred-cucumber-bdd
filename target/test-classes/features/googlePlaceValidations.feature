Feature: Validating the place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being added successfully using AddPlaceAPI
	Given Add Place payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call is successful with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And to verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
		| name  | language| address   |
		|Naveen | Kannada | Karnataka |
		|Praveen| English | Honnavar  |

@DeletePlace @Regression
Scenario: Verify if Delete Place API is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call is successful with status code 200
	And "status" in response body is "OK"