package RestAssureTest;

 

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Ecommerce {
	
	public static String baseurl ="https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	
	@Test(priority =0, enabled = false)
	public void signup()
	{
				 RestAssured.baseURI = baseurl;
				 
				 String requestbody ="{\r\n"
				 		+ "	\"email\": \"Meghana${=String.valueOf(Math.random()*1000).substring(0,5)}@gmail.com\",\r\n"
				 		+ "	\"password\": \"krishna@123\"\r\n"
				 		+ "}\r\n";
				 	
				 Response response = given()
				.header("content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).contentType(ContentType.JSON)
				.extract().response();
				 //this is to fetch a response string
				 String jsonresponse = response.asString();
				 //i want response in json format
				 //why do i use json path to convert to string into json format
				 JsonPath js = new JsonPath(jsonresponse);
				 //now I have to get the id
				 message = js.get("message");
				 System.out.println(message);
				 //return requestbody;
	}
	
	@Test(priority =1)
	public void logIn()
	{
		 RestAssured.baseURI = baseurl;
		 
		 String requestbody = "{\r\n"
			 		+ "	\"email\": \"mohankrishna176@gmail.com\",\r\n"
			 		+ "	\"password\": \"krishna@123\"\r\n"
			 		+ "}\r\n";
		 	
		 Response response = given()
		.header("content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		 //this is to fetch a response string
		 String jsonresponse = response.asString();
		 //i want response in json format
		 //why do i use json path to convert to string into json format
		 JsonPath js = new JsonPath(jsonresponse);
		 //now I have to get the id
		 accessToken = js.get("accessToken");
		 System.out.println(accessToken);
		
	}

}
