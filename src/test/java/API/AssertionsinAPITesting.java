package API;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.TestUtil;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AssertionsinAPITesting {

//	@Test
//	public void teststructe()throws IOException{			
//		String dataPath = System.getProperty("user.dir");
//		dataPath = dataPath + "\\src\\resource\\response.json";
//		System.out.println("Data path is: " + dataPath);
//		JSONObject requestParams = new JSONObject();
//		 requestParams.put("user_email", "tuannda@miichisoft.com"); // Cast
//		 requestParams.put("key", "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
//		 requestParams.put("title",  "title test 01");
//		 requestParams.put("body", "body test 01");
//		 requestParams.put("status", "1");
//		 RestAssured.
//		 given().
//		 	header("Content-Type", "application/json").
//		 	contentType(ContentType.JSON).
//		 	body(requestParams.toJSONString())				
//		// WHEN
//		.when()
//			.post("http://172.16.1.85:8080/api/articles")
//		// THEN
//		.then()
//			.log()
//			.all()
//			.assertThat()
//			.statusCode(200)
//			.body(Matchers.equalTo(new String(Files.readAllBytes(
//					Paths.get(System.getProperty("user.dir")+"\\src\\resource\\response.json")))));
//	}	

//Check message lõi khi statusCode = 400		
//	@Test
//	public void testBody400() throws IOException {
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("id", "a");
//		requestParams.put("firstName", "Tran");
//		requestParams.put("lastName", "Hanh");
//		requestParams.put("email", "test05@gmail.com");
//		requestParams.put("programme", "Analysis");
//		RestAssured.given().header("Content-Type", "application/json").contentType(ContentType.JSON)
//				.body(requestParams.toJSONString())
//				// WHEN
//				.when().post("http://localhost:8080/student")
//				// THEN
//				.then().log().all().assertThat().statusCode(400).body("path", Matchers.equalTo("/student"))
//				.body("error", Matchers.equalTo("Bad Request"));
//	}
//
//	// Check message lõi khi statusCode = 404
//	@Test
//	public void testBody404() throws IOException {
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("id", "a");
//		requestParams.put("firstName", "Tran");
//		requestParams.put("lastName", "Hanh");
//		requestParams.put("email", "test05@gmail.com");
//		requestParams.put("programme", "Analysis");
//		RestAssured.given().header("Content-Type", "application/json").contentType(ContentType.JSON)
//				.body(requestParams.toJSONString())
//				// WHEN
//				.when().post("http://localhost:8080/students")
//				// THEN
//				.then().log().all().assertThat().statusCode(404)
//				.body("message", Matchers.equalTo("No message available")).body("error", Matchers.equalTo("Not Found"));
//	}

	// TestCase cho trường hợp statusCode = 200

	@DataProvider
	public Iterator<Object[]> getTestData201() {
		TestUtil.getDataFromExcel200();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel200();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData201")
	public void post_201(String firstName, String lastName, String email, String programme, List<String> coursesList)
			throws IOException, JSONException {
		// Request body params
		requestBodyParams(firstName, lastName, email, programme, coursesList, 201);
	}

	@DataProvider
	public Iterator<Object[]> getTestData400() {
		TestUtil.getDataFromExcel400();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel400();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData400")
	public void post_400(String firstName, String lastName, String email, String programme, List<String> coursesList)
			throws IOException, JSONException {
		// Request body params
		requestBodyParams(firstName, lastName, email, programme, coursesList, 500);
	}

	@SuppressWarnings("unchecked")
	public void requestBodyParams(String firstName, String lastName, String email, String programme,
			List<String> coursesList, int statusCode) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstName", firstName);
		requestParams.put("lastName", lastName);
		requestParams.put("email", email);
		requestParams.put("programme", programme);
		requestParams.put("courses", coursesList);

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json").body(requestParams.toString());

		Response response = request.post("http://localhost:8080/student");

		System.out.println("PostUsers_Excel response is: " + response.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + response.statusCode());

		if (response.statusCode() == statusCode) {
			TestUtil.writeData200(email, "OK", "statusCode");
		} else {
			TestUtil.writeData200(email, "NG", "statusCode");
		}

		TestUtil.writeData200(email, String.valueOf(response.statusCode()), "ResponseCode");
		TestUtil.writeData200(email, response.asString(), "Response");
	}
	
	
}
