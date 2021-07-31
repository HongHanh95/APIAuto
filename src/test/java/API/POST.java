package API;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import utilities.Links;
import utilities.ReadFile;
import utilities.TestUtil;

public class POST {
// @Test
// public void test_post() {
//	 
//	 JSONObject requestParams = new JSONObject();
//	 requestParams.put("user_email", "tuannda@miichisoft.com"); // Cast
//	 requestParams.put("key", "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
//	 requestParams.put("title",  "hanh");
//	 requestParams.put("body", "hanh test API");
//	 requestParams.put("status", "1");
//	 
//	 System.out.println(requestParams);
//	 System.out.println(requestParams.toJSONString());
//	 
//	 given().
//	 	header("Content-Type", "application/json").
//	 	contentType(ContentType.JSON).
//	 	accept(ContentType.JSON).
//	 	body(requestParams.toJSONString()).
//	 when().
//	 	post("http://172.16.1.85:8080/api/articles").
//	 then().
//	 	statusCode(200);
//	 
// }

//	 @Test
//	 public void test_put() {		 
//		 JSONObject request = new JSONObject();
//		 request.put("name", "Hong");
//		 request.put("json", "IT");
//		 
//		 System.out.println(request);
//		 System.out.println(request.toJSONString());
//		 
//		 given().
//		 	header("Content-Type", "application/json").
//		 	contentType(ContentType.JSON).
//		 	accept(ContentType.JSON).
//		 	body(request.toJSONString()).
//		 when().
//		 	put("https://reqres.in/api/users/2").
//		 then().
//		 	statusCode(200).
//		 	log().all();
//		 
//	 }

//	 @Test
//	 public void test_patch() {		 
//		 JSONObject request = new JSONObject();
//		 request.put("name", "Hong88");
//		 request.put("json", "IT");
//		 
//		 System.out.println(request);
//		 System.out.println(request.toJSONString());
//		 
//		 given().
//		 	header("Content-Type", "application/json").
//		 	contentType(ContentType.JSON).
//		 	accept(ContentType.JSON).
//		 	body(request.toJSONString()).
//		 when().
//		 	patch("https://reqres.in/api/users/2").
//		 then().
//		 	statusCode(200).
//		 	log().all();
//		 
//	 }

//	 @Test
//	 public void test_delete() {		 		 
//		 when().
//		 	delete("http://172.16.1.85:8080/api/articles").
//		 then().
//		 	statusCode(204).
//		 	log().all();
//		 
//	 }

//	@DataProvider
//	public Iterator<Object[]> getTestData() {
//		TestUtil.getDataFromExcel();
//		ArrayList<Object[]> testData = TestUtil.getDataFromExcel();
//		return testData.iterator();
//	}
//	
//	 @Test(dataProvider = "getTestData")
//	 public void test_excel_post(String name, String job) {		 
//		 JSONObject request = new JSONObject();
//		 request.put("name", name);
//		 request.put("job", job);
//		 
//		 System.out.println(request);
//		 System.out.println(request.toJSONString());
//		 
//		 given().
//		 	header("Content-Type", "application/json").
//		 	contentType(ContentType.JSON).
//		 	accept(ContentType.JSON).
//		 	body(request.toJSONString()).
//		 when().
//		 	post("https://reqres.in/api/users").
//		 then().
//		 	statusCode(201).
//		 	log().all();
//		 
//	 }

	@Test
	public void RegistrationSuccessful() {
		RestAssured.baseURI = "http://172.16.1.85:8080/api";
		RequestSpecification request = RestAssured.given();
		
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("user_email", "selmer09@gmail.com");
		 requestParams.put("key", 
				 "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
//		 requestParams.put("title",  "hanh12");
//		 requestParams.put("body", "hanh test API");
//		 requestParams.put("status", "1");

		request.body(requestParams.toJSONString());
		Response response = request.post("/articles");

		ResponseBody body = response.getBody();
		System.out.println(response.body().asString());

		if (response.statusCode() == 201) {
			// Deserialize the Response body into RegistrationFailureResponse
			RegistrationFailureResponse responseBody = body.as(RegistrationFailureResponse.class);

			// Use the RegistrationFailureResponse class instance to Assert the values of
			// Response.
			Assert.assertEquals("User already exists", responseBody.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
		} else if (response.statusCode() == 200) {
			// Deserialize the Response body into RegistrationSuccessResponse
			RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
			// Use the RegistrationSuccessResponse class instance to Assert the values of
			// Response.
			Assert.assertEquals("success", responseBody.type);
			Assert.assertEquals("", responseBody.message);
			
		System.out.println("DATa:" + responseBody.type);
		}
	}
}
