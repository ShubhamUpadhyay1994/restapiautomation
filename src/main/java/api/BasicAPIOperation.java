package api;

import files.CommonUtilities;
import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Test
public class BasicAPIOperation
{

	String placeId=null;
	String response =null;

	@Test
	void AddPlace() throws IOException
	{
		// validate if Add Place API is workimg as expected
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response

		//given - all input details
		//when - Submit the API -resource,http method
		//Then - validate the response
		RestAssured.baseURI ="https://rahulshettyacademy.com";



		response =	given().log().everything().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(new String(
						Files.readAllBytes(Paths.get("D:\\Program\\API Docs\\resources\\addPlace.txt"))))
				.when().post("/maps/api/place/add/json")
				.then().log().everything().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.header("server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();

		System.out.println("Response ---------" +response);

		JsonPath js = CommonUtilities.rawToJson(response);
		placeId =js.get("place_id");
		System.out.println("path is "+placeId);
	}


	//get response

	@Test
	void getPlace(){
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		response=given().log().all().param("key","qaclick123").param("place_id",placeId).header("Content-Type","application/json").
				when().get("/maps/api/place/get/json")
				.then().log().all().statusCode(200).body("types",equalTo("Maruti Courier Naigaon,Palghar")).
						body("name",equalTo("Nani Ka Ghar APNA DUKAN"))
				.header("server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();

		JsonPath js = CommonUtilities.rawToJson(response);
		Assert.assertEquals("29, side layout, cohen 09",js.get("address"),"Address is not matching");
	}

//delete place

	@Test
	void testDeletePlace(){
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		given().log().all().param("key","qaclick123").header("Content-type","application/json").
				body("{\n" + "    \"place_id\":\""+placeId +"\"\n" + "}").when().delete("/maps/api/place/delete/json")
				.then().log().all().statusCode(200).body("status",equalTo("OK"));
	}


	//updated address and verify it
}
