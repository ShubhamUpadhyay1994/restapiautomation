package api;

import api.pojo.AddPlace;
import api.pojo.Location;
import files.CommonUtilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleAPISerializeRequest
{

	String place_id;
	@Test
	void addPlaceUsingPOJOClass(){

		RestAssured.baseURI ="https://rahulshettyacademy.com";

		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setName("Rupa Store");
		addPlace.setAddress("janta complex, mittle shop, palghar 401208");
		addPlace.setLanguage("ENGLISH, HINDI, Marathi");
		addPlace.setPhone_number("+917777788888888");
		addPlace.setWebsite("https://codebeautify.org/jsonviewer");
		List types = new ArrayList();
		types.add("Cloth store");
		types.add("Shop to buy cloths for man women and child");
		types.add("Shop to buy for new baby");
		addPlace.setTypes(types);

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(34.383494);

		addPlace.setLocation(location);

		Response response = given().log().all().queryParam("key","qaclick123").contentType("application/json").body(addPlace)
				.when().post("/maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).extract().response();

		String responseIs = response.asString();
		System.out.println("response is -------" +responseIs);
		JsonPath jsonPath = new JsonPath(responseIs);
		 place_id=jsonPath.get("place_id");

		 System.out.println("place is "+ place_id);

	}

@Test
	void getCourseAndStroreResponseIntoPojoClass(){
		//place_id ="ced0b038c3f23c894a964e48fb15d301";
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		AddPlace addPlace = new AddPlace();
		addPlace = given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().as(AddPlace.class);

		System.out.println("location is "+addPlace.getWebsite());
	}
}
