package api;

import files.CommonUtilities;
import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
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

public class JIRAAPIOperation
{

	//create a bug, add comment , updae comment, add attachment.
String response;
JsonPath js ;
String staticIssueId = null;

@Test
	public void createBug()
{

	RestAssured.baseURI = "http://localhost:8080";

	//authenticate user and get session id

	SessionFilter session = new SessionFilter();

	//relaxedHTTPSValidation required for https , now we are running on local so relaxedHTTPSValidation was not reuired but for web apps which is on server and https need this method
	response = given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json").body("{ \"username\": \"shubhamupadhyay69\", \"password\": \"Automation@21\" }").filter(session).when()
			.post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response().asString();

	//post request
	response = given().log().all().header("Content-Type", "application/json")
			.body("{\n" + "    \"fields\": {\n" + "        \"project\": {\n" + "            \"key\": \"APITEST\"\n" + "        },\n"
					+ "        \"summary\": \"Duplicate name by automation- Post Request\",\n" + "        \"description\": \"my first credit card bug\",\n" + "        \"issuetype\": {\n"
					+ "            \"name\": \"Bug\"\n" + "        }\n" + "    }\n" + "}\n" + "        ").filter(session).when().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract()
			.asString();

	js = new JsonPath(response);
	String issueID = js.get("id");
	System.out.println("IssueId ---------------" + issueID);

	//add comment
	response = given().log().all().pathParam("key", issueID).header("Content-Type", "application/json").filter(session)
			.body("{\n" + "    \"body\": \"Bug is fixed into dev and ready for testing\",\n" + "    \"visibility\": {\n"
					+ "        \"type\": \"role\",\n" + "        \"value\": \"Administrators\"\n" + "    }\n" + "}")
			.when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().asString();

	js = new JsonPath(response);
	String comment = js.get("body");
	String commentId = js.get("id");

	Assert.assertEquals(comment, "Bug is fixed into dev and ready for testing");

	//update comment

	response = given().log().all().pathParam("commentId", commentId).pathParam("issueID", issueID)
			.header("Content-Type", "application/json")
			.body("{\n" + "    \"body\": \"APNA APPLICATION BUG FREE HOGA\",\n" + "    \"visibility\": {\n" + "        \"type\": \"role\",\n" + "        \"value\": \"Administrators\"\n" + "    }\n" + "}")
			.filter(session).when().put("/rest/api/2/issue/{issueID}/comment/{commentId}").then().log().all().statusCode(200).extract().asString();

//	 add attachment  - Getting 403 error
//	response = given().log().all().header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data").pathParam("issueId",issueID).filter(session)
//			.multiPart("file",new File("D:\\Program\\restapiAutomation\\src\\main\\resources\\Info.txt"))
//			.when().post(" /rest/api/2/issue/{issueId}/attachments").then().log().all().assertThat().statusCode(200).extract().asString();


	//get issue details and verify comment id
	response = given().log().all().header("Content-Type","application/json").pathParam("issueId",issueID).filter(session).
			when().get("/rest/api/2/issue/{issueId}").then().log().all().statusCode(200).extract().asString();

	System.out.println("response ---------"+response);

	JsonPath js = new JsonPath(response);
	System.out.println("body is "+js.get("id"));
	int count = js.getInt("fields.comment.comments.size()");
	int arr[] = new int[10];
	String bodyMsg []=new String[10];
	for(int i =0;i<count;i++){
		arr[i] = js.getInt("fields.comment.comments["+i+"].id");
		bodyMsg[i]=js.get("fields.comment.comments["+i+"].body");
	}

	boolean status = false;
	for(int i =0;i<count;i++){
		if(arr[i]==Integer.parseInt(commentId)){
			status =true;
		}
	}
	Assert.assertEquals(status,true);
}

}

