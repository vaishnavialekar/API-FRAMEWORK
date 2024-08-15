package stepdefinations;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DeleetAPI {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.addHeader("Content-Type", "application/json").build();

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).build();

		RequestSpecification res = given().spec(reqspec).body("{\r\n"
				+ "    \"place_id\":\"02827f2cc568bf8aaea917c76fecf23c\"\r\n"
				+ "}\r\n"
				+ "");
				
				String response = res.when().delete("/maps/api/place/delete/json").then()
				.spec(resspec).extract().response().asString();
//
//		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//				.body("{\r\n" + "    \"place_id\":\"bb9ff1f11e7c073abd4d551f0baa538b\"\r\n" + "}\r\n").when()
//				.delete("/maps/api/place/delete/json").then().assertThat().statusCode(200).extract().response()
//				.asString();

		System.out.println("Response: " + response);
	}
}
