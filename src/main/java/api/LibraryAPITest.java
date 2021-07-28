package api;

import files.CommonUtilities;
import files.PayLoad;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;

public class LibraryAPITest
{
	JsonPath js=null;
	String id=null;

	@Test(dataProvider="BookData")
	public void addBook(String bookNo, String bookId){
		RestAssured.baseURI ="http://216.10.245.166";
		String response =given().header("Content-Type","application/json").body(PayLoad.addBook(bookNo,bookId))
				.when().post("Library/Addbook.php")
				.then().assertThat().statusCode(200).body("Msg",equalTo("successfully added")).extract().asPrettyString();

		 js = new JsonPath(response);
		 id =js.get("ID");
		 String msg = js.get("msg");
		 System.out.println("id is "+id +"msg is "+msg);
		 System.out.println("Post req is "+response);

	}

	@Test(dataProvider="getBook")
	public void getBook(String authorName,String id){


		RestAssured.baseURI ="http://216.10.245.166";
		String response =given().header("Content-Type","application/json")
				.when().get("/Library/GetBook.php?AuthorName="+authorName+"")
				.then().assertThat().statusCode(200).extract().asString();


		js = new JsonPath(response);
	//	JSONArray array = new JSONArray(response);
		//id =js.get("book_name");
		System.out.println("book name is "+id);
		System.out.println("get req is "+response);


	}


	@DataProvider(name="BookData")
	public Object[][] getData(){
		return new Object[][] {{"BKNO",CommonUtilities.randomNumber()},{"BKNO",CommonUtilities.randomNumber()},{"BKNO",CommonUtilities.randomNumber()}};
	}

	@DataProvider(name="getBook")
	public Object[][] getAuthorName(){
		return new Object[][] {{"AMIT SHARMA",""},{"Satyam Thakur",""},{"Satyam Sinha",""}};
	}

}
