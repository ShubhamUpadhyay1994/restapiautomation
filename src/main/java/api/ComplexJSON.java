package api;

import files.CommonUtilities;
import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ComplexJSON
{
	//if your api is not developed you can use dummy response to work on agile environment
	@Test

	public void readJsonDataTest(){
		JsonPath jsonPath= CommonUtilities.rawToJson(PayLoad.CoursePrice());

		//Print No of courses returned by API
		int course=jsonPath.getInt("courses.size()");
		System.out.println("Get courses count "+course);
		Assert.assertEquals(3,course,"wrong courses count");

		//Print Purchase Amount
		int	price=jsonPath.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(100,price, "Purchase price is wrong");

		//Print Title of the first course
		String	title=jsonPath.get("courses[0].title");
		Assert.assertEquals("Selenium",title, "Title of array");

		for(int i=0;i<course;i++){
			title = jsonPath.get("courses["+i+"].title");
			price = jsonPath.getInt("courses["+i+"].price");
			System.out.println("title is "+title +"-----"+price);
		}

		//Print no of copies sold by RPA Course
		for(int i=0;i<course;i++){
			String Copies1=null;
			String title1 = jsonPath.get("courses["+i+"].title");
			System.out.println("title is ======="+title1);
			if(title1.trim().equals("Java")){
				Copies1 = jsonPath.get("courses["+i+"].Copies");
				System.out.println("copies sold is "+title1 +"-----"+Copies1);
			 break;
			}
		}
	}

	//get sum of all courses
	@Test
	public void getSum(){

		JsonPath jsonPath= CommonUtilities.rawToJson(PayLoad.CoursePrice());

		//Print No of courses returned by API
		int course=jsonPath.getInt("courses.size()");
		System.out.println("Get courses count "+course);

		int sum=0,price=0;

		for(int i=0;i<course;i++)
		{
			price = jsonPath.getInt("courses[" + i + "].price");
			sum = sum + price;
		}
		System.out.println("price is "+sum);
		int total=jsonPath.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(total,sum,"total value");
	}


}
