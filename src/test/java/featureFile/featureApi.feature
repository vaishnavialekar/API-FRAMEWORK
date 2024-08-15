
Feature: Validating Add place API	


  Scenario Outline: Add new place into Server
  
  Given Add Place Payload with "<name>" "<Language>" <Accuracy>
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code "OK"
	And "status" in response body is "ok"
	And "Scope" in API is "APP"
	And verify place ID CREATED "<name>" in "getPlaceAPI"
	
	Examples:
|name     |Language |Accuracy|
|vaishnavi|english  |60      |
#|sony     |hindi    |70      |
