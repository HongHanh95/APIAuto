package API;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET {
//	@Test
//	public void Test01() {
//		Response response = get("https://reqres.in/api/users?page=2");
//		System.out.println(response.asString());
//		System.out.println(response.getBody().asString());
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getStatusLine());
//		System.out.println(response.getHeader("content-type"));
//		System.out.println(response.getTime());
//
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 200);
//	}
//
//	@Test
//	public void Test_01() {
//		given().
//		//header("Content-Type", "applicattion/Json");
//		get("https://reqres.in/api/users?page=2").
//		then().
//		statusCode(200).
//		body("data.id[1]",equalTo(8)).
//		body("data.first_name",hasItems("Michael","Lindsay")).
//		log().all();
//		/*log().all(); log ra toan bo ressponse cua API*/
//	}
//
//
//	@Test
//	public void GetWeatherStatusLine()
//	{
//	 baseURI = "https://restapi.demoqa.com/utilities/weather/city";
//	 RequestSpecification httpRequest = given();
//	 Response response = httpRequest.get("/Hyderabad");
//	 
//	 // Get the status line from the Response and store it in a variable called statusLine
//	 String statusLine = response.getStatusLine();
//	 Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
//	}
	
//	@Test(enabled = true, priority = 2)
//	public void getUsers_parsing_assertions() {
//
//		RestAssured.baseURI = "http://172.16.1.85:8080/api/articles";
//
//		RequestSpecification req = RestAssured.given();
//		req.header("nexfix_id", "32748732jfbfhu82jsdfs").header("netfix_password", " jry8new84r4nfek").header("stream",
//				"NETFLIX");
//		req.auth().basic("username", "password");
//		req.contentType("application/json");
//		req.param("page", 2);
//
//		Response resp = req.get("api/users");
//
//		System.out.println("Get users response is: " + resp.asString());
//		System.out.println("Get users response code is: " + resp.statusCode());
//		System.out.println("Get users page is: " + resp.jsonPath().getChar("page"));
//		System.out.println("Get users data in list format is: " + resp.jsonPath().getList("data"));
//
//		System.out.println("Get users JSON object is: " + resp.jsonPath().getJsonObject("data"));
//		System.out.println("Get users as Map is: " + resp.jsonPath().getMap("data[0]"));
//		System.out.println("2nd element data for get Users is: " + resp.jsonPath().getChar("page"));
//		System.out.println(
//				"Email of 3rd element in data for getUsers is: " + resp.jsonPath().getMap("data[2]").get("email"));
//		System.out.println("Get users response contenttype is: " + resp.getContentType());
//
//		// Assertions
//		Assert.assertEquals(200, resp.statusCode());
//		Assert.assertEquals(2, resp.jsonPath().getInt("page"));
//		int var = resp.jsonPath().getInt("page");
//		Assert.assertTrue("Test is failed because ...etc:", (var == 2));
//
//		// Real time validations
//		int expected_result = 11;
//		int actual_result = resp.jsonPath().getInt("total");
//		if (expected_result == actual_result) {
//			Reporter.log("Test is passed");
//			Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
//		} else {
//			Reporter.log("Test is Failed");
//			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
////			Assert.fail("Test is failed as Expected result: " + expected_result + "doesm't math with Actual result: "
////					+ actual_result);
//		}
//	}	


}
