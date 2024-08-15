package stepdefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.AddPlaceResponse;
import pojo.Location;
import resources.TestDataBuild;
import resources.utils;

public class StepDefination extends utils {
	RequestSpecification req_spec;
	ResponseSpecification resspec;
	RequestSpecification res;
	AddPlaceResponse response;
	AddPlace responses;
	String status;
	String placeid;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {int}")
	public void add_place_payload_with(String name, String language, int Accuracy) throws IOException {

		res = given().spec(requestSpecification()).body(data.AddplaceData(Accuracy, name, language));

	}

	@When("user calls {string} with {string} http request")
	public void usercallsAddPlaceAPIwithPOSThttprequest(String AddPlaceAPI, String method) {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
			 response =res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
			else if(method.equalsIgnoreCase("GET"))
				 response =res.when().get(resourceAPI.getResource());
			
		
		

		else {

			if (method.equalsIgnoreCase("GET")) {
				responses = res.when().get("/maps/api/place/get/json").then().spec(resspec).extract().response()
						.as(AddPlace.class);

			}
		}

	}

	@Then("the API call got success with status code {string}")
	public void APIcallgotsuccesswithstatuscodeOK(String String) {
		status = response.getStatus();

	}

	@And("{string} in response body is {string}")
	public void statusinresponsebodyisok(String status, String ok) {
		boolean yes = status.equals("OK");
		System.out.println(yes);

	}

	@And("{string} in API is {string}")
	public void scopeinapiisapp(String Expectedscope, String app) {

		String ActualScope = response.getScope();
		System.out.println(ActualScope.contentEquals("APP"));
		//assert actual scope with expectedScope

	}

	@And("verify place ID CREATED {string} in {string}")
	public void verifyplaceIDCREATED(String ExpectedName, String resource) throws IOException {

		placeid = response.getPlace_id();
		res = given().spec(requestSpecification()).queryParam("place_id", placeid);
		usercallsAddPlaceAPIwithPOSThttprequest(resource, "get");
		
		

	}

}
