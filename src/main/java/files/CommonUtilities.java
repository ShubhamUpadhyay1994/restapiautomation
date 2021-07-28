package files;

import io.restassured.path.json.JsonPath;

import java.util.Random;

public class CommonUtilities
{

	public static JsonPath rawToJson(String response){
		JsonPath js = new JsonPath(response);
		return js;
	}

	public static String randomNumber(){
		Random random = new Random();
		int x = random.nextInt(1000000);
		return Integer.toString(x);
	}
}
