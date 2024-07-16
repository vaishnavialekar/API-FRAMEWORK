package stepdefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
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
	String status;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {int}")
	public void add_place_payload_with(String name, String language, int Accuracy) throws IOException {


			
		res = given().spec(requestSpecification()).body(data.AddplaceData(Accuracy, name, language));

	}

	@When("user calls {string} with POST http request")
	public void usercallsAddPlaceAPIwithPOSThttprequest(String AddPlaceAPI) {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response()
				.as(AddPlaceResponse.class);

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
	public void scopeinapiisapp(String status, String app) {
		
		String scope = response.getScope();
		System.out.println(scope.contentEquals("APP"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
