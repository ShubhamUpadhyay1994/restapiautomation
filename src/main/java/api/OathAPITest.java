package api;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;


public class OathAPITest
{

	String response = null;
	@Test
	public void loginWithAccessToken() throws InterruptedException
	{

//		//Setting system properties of ChromeDriver
//		System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver_win32\\chromedriver.exe");
//
//	//	System.setProperty("webdriver.edge.driver","D:\\driver\\edgedriver_win32\\msedgedriver.exe");
//
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data\\");
//
//
//		//Creating an object of ChromeDriver
//		WebDriver driver = new ChromeDriver(options);
//
//		driver.manage().window().maximize();
////
//		//Deleting all the cookies
//		driver.manage().deleteAllCookies();
//
//		//Specifiying pageLoadTimeout and Implicit wait
//		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//		//launching the specified URL
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
//driver.findElement(By.id("identifierId")).sendKeys("shupa144");
//		Thread.sleep(4000);
//driver.findElement(By.cssSelector("div[id=\"identifierNext\"] button[type=\"button\"]")).click();
//		Thread.sleep(4000);
//driver.findElement(By.name("password")).sendKeys("password");
//		Thread.sleep(4000);
//driver.findElement(By.id("passwordNext")).click();
//Thread.sleep(4000);

	String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g7q9r4r1j64P-XTY8OtBK-gVyIw-Cy0c_B1nSiJyO0OqL4FXXl85QzVI4s-uuRpHQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";

	String[] arrUrl =new String[5];
	arrUrl =url.split("code=");
	arrUrl =arrUrl[1].split("&scope");
	String code = arrUrl[0];

		//String code  ="4%2F0AY0e-g7q9r4r1j64P-XTY8OtBK-gVyIw-Cy0c_B1nSiJyO0OqL4FXXl85QzVI4s-uuRpHQ";

		//authenticate user and get session id
		String accessToken = given().log().all().urlEncodingEnabled(false).queryParams("code",code).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_url","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("GrantType","authorization_code")
				.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

	JsonPath js = new JsonPath(accessToken);
	String getAccessToken = js.get("access_token");
	System.out.println("access token variable "+ js.get("access_token"));

		response = given().queryParam("access_token",getAccessToken).when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println("response is "+response);
	}

}
