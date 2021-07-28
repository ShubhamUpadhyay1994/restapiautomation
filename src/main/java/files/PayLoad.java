package files;

public class PayLoad
{
	public static String addPlace(){
		return "{\n" + "  \"location\": {\n" + "    \"lat\": -38.383494,\n" + "    \"lng\": 33.427362\n" + "  },\n"
				+ "  \"accuracy\": 50,\n" + "  \"name\": \"Nani Ka Ghar APNA DUKAN\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n" + "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n" + "    \"Maruti Courier Naigaon\",\n" + "    \"Palghar\"\n" + "      ],\n"
				+ "  \"website\": \"http://google.com\",\n" + "  \"language\": \"French-IN\"\n" + "}\n" + "\n";
	}

	public static String CoursePrice(){
		return "{\n" + "\"dashboard\" :{\n" + "  \"purchaseAmount\" :\"780\",\n"
				+ "  \"website\" : \"https://rahulshettyacademy.com\"\n" + "},\n" + "\"courses\":[\n" + "  {\n"
				+ "    \"title\" :\"Selenium\",\n" + "    \"price\" :\"100\",\n" + "    \"Copies\":\"20\"\n" + "  },\n"
				+ "   {\n" + "    \"title\" :\"Java\",\n" + "    \"price\" :\"230\",\n" + "    \"Copies\":\"50\"\n"
				+ "  },\n" + "   {\n" + "    \"title\" :\"Python\",\n" + "    \"price\" :\"450\",\n"
				+ "    \"Copies\":\"78\"\n" + "  }\n" + "  ]\n" + "}";
	}

	public static String addBook(String bookIdKey,String bookIdValue){
		return "{\n" + "\n" + "\"name\":\"Python\",\n" + "\"isbn\":\" "+bookIdKey+"\",\n" + "\"aisle\":\""+bookIdValue+"\",\n"
				+ "\"author\":\"Shubham Upadhyay\"\n" + "}\n";
	}
}
