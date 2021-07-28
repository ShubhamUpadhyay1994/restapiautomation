package api;

import api.pojo.AddPlace;
import api.pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest
{
	String place_id;

	@Test
	void addPlaceRequestWithSpecTest(){

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

		Response response = given().spec((RequestSpecification) SpecBuilderTest.requestSpecification()).body(addPlace)
				.when().post("/maps/api/place/add/json")
				.then().spec((ResponseSpecification) SpecBuilderTest.responseSpecification()).extract().response();

		String responseString = response.asString();
		System.out.println("response is -------" +responseString);

		JsonPath jsonPath = new JsonPath(responseString);
		place_id=jsonPath.get("place_id");

		System.out.println("place is "+ place_id);

	}

	@Test
	void getCourseResponseWithSpecTest(){
		AddPlace addPlace = new AddPlace();
		addPlace = RestAssured.given().spec(SpecBuilderTest.requestSpecification()).queryParam("place_id",place_id).when().get("/maps/api/place/get/json")
				.then().log().all().spec(SpecBuilderTest.responseSpecification()).extract().response().as(AddPlace.class);
		System.out.println("location is "+addPlace.getWebsite());
	}

	public static RequestSpecification requestSpecification(){

		RequestSpecification req = (RequestSpecification) new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).
				addQueryParam("key","qaclick123").build();

		return req;
	}

	public static ResponseSpecification responseSpecification(){
		ResponseSpecification res = (ResponseSpecification) new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}

}
