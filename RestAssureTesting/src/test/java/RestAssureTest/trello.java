package RestAssureTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class trello {
	
	//don't you think thr base URL is common of all actions
	public static String baseurl = "https://api.trello.com";
	public static String id;
	
	@Test(priority = 0)
	public void createBoard()
	{
		//I want to fetch the base url so that i can ass other things to that base url
		//This is the first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//in rest assured we have three keyword
		//given: precondition includes parameter, header, authorisation
		//when: condition that iam testing like resource.//we have to provide the name// that is called resource
		//then: post condition is something like response, check response
		
		 Response response = given().queryParam("name", "jadav Moolya Board")
		.queryParam("key", "a59d74332c95132df2989a278578be52")
		.queryParam("token","036d8d979d1d45b5549722c41534e94b423e5ebc94f95984c46d131235791da6")
		.header("content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		 //this is to fetch a response string
		 String jsonresponse = response.asString();
		 //i want response in json format
		 //why do i use json path to convert to string into json format
		 JsonPath js = new JsonPath(jsonresponse);
		 //now I have to get the id
		 id = js.get("id");
		 System.out.println(id);
	}
	
	//if i make any @test method enabled =false , then that method will not execute
	@Test(priority =1)
	public void GetBoard()
	{
		//I want to fetch the base url so that i can ass other things to that base url
		//This is the first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//in rest assured we have three keyword
		//given: precondition includes parameter, header, authorisation
		//when: condition that iam testing like resource.//we have to provide the name// that is called resource
		//then: post condition is something like response, check rsponse
		
		Response response = given()
		.queryParam("key", "a59d74332c95132df2989a278578be52")
		.queryParam("token","036d8d979d1d45b5549722c41534e94b423e5ebc94f95984c46d131235791da6")
		.header("content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		 String jsonresponse = response.asString();
		 System.out.println(id);
		 
	}
	
	@Test(priority =2)
	public void DeleteBoard()
	{
		//I want to fetch the base url so that i can ass other things to that base url
		//This is the first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//in rest assured we have three keyword
		//given: precondition includes parameter, header, authorisation
		//when: condition that iam testing like resource.//we have to provide the name// that is called resource
		//then: post condition is something like response, check rsponse
		
		Response response = given()
		.queryParam("key", "a59d74332c95132df2989a278578be52")
		.queryParam("token","036d8d979d1d45b5549722c41534e94b423e5ebc94f95984c46d131235791da6")
		.header("content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		 String jsonresponse = response.asString();
		 System.out.println(id);
		 
	}
}
