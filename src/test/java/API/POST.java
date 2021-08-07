package API;
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
public class POST {
		
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
	@Test
	public void testBody400()throws IOException{			
		JSONObject requestParams = new JSONObject();
//		 requestParams.put("user_email", "tuannda@miichisoft.com"); // Cast
//		 requestParams.put("key", "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
//		 requestParams.put("title",  "title test 01");		
//		 requestParams.put("status", "1");
		 RestAssured.
		 given().
		 	header("Content-Type", "application/json").
		 	contentType(ContentType.JSON).
		 	body(requestParams.toJSONString())				
		// WHEN
		.when()
			.post("http://172.16.1.85:8080/api/articles")
		// THEN
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(400)
		.body("message", Matchers.equalTo("リクエストパラメータの内容が不正です"))
		.body("type", Matchers.equalTo("bad_request"))
		.body("code", Matchers.equalTo(1));
	}	
	
	//Check message lõi khi statusCode = 401		
		@Test
		public void testBody401()throws IOException{			
			String dataPath = System.getProperty("user.dir");
			dataPath = dataPath + "\\src\\resource\\response.json";
			System.out.println("Data path is: " + dataPath);
			JSONObject requestParams = new JSONObject();
			 requestParams.put("key", "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
			 requestParams.put("title",  "title test 01");		
			 requestParams.put("status", "1");
			 RestAssured.
			 given().
			 	header("Content-Type", "application/json").
			 	contentType(ContentType.JSON).
			 	body(requestParams.toJSONString())				
			// WHEN
			.when()
				.post("http://172.16.1.85:8080/api/articles")
			// THEN
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(401)
			.body("message", Matchers.equalTo("指定したアクセストークンが無効です"))
			.body("type", Matchers.equalTo("unauthorized_token"))
			.body("code", Matchers.equalTo(2));
		}	
		
		//Check message lõi khi statusCode = 404		
		@Test
		public void testBody404()throws IOException{			
			String dataPath = System.getProperty("user.dir");
			dataPath = dataPath + "\\src\\resource\\response.json";
			String extenReportPath = dataPath + "\\src\\resource\\response.json";
			System.out.println("Data path is: " + dataPath);
			JSONObject requestParams = new JSONObject();
			 requestParams.put("key", "$2y$10$eOCKQU9Ax6JAgMheCwOSqeMrWUD1z8NQUBDYGVDMkb5HeS7EKYgiu");
			 requestParams.put("title",  "title test 01");		
			 requestParams.put("status", "1");
			 RestAssured.
			 given().
			 	header("Content-Type", "application/json").
			 	contentType(ContentType.JSON).
			 	body(requestParams.toJSONString())				
			// WHEN
			.when()
				.post("http://172.16.1.85:8080/api/articles")
			// THEN
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(404)
			.body("message", Matchers.equalTo("指定したアクセストークンが無効です"))
			.body("type", Matchers.equalTo("unauthorized_token"))
			.body("code", Matchers.equalTo(2));
		}	
	
	//TestCase cho trường hợp statusCode = 200
	
	@DataProvider
	public Iterator<Object[]> getTestData200() {
		TestUtil.getDataFromExcel200();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel200();
		return testData.iterator();
	}
		
	@Test(dataProvider = "getTestData200")
	public void post_200( String user_email, String key, String title, String body, String  status) throws IOException, JSONException{				
		//Read Excel
		RestAssured.baseURI = "http://172.16.1.85:8080";
		JSONObject requestParams = new JSONObject();
		requestParams.put("user_email",user_email);
		requestParams.put("key", key);
		requestParams.put("title", title);
		requestParams.put("body", body);
		requestParams.put("status", status);
		//requestParams.put("image", new File(image.toString()));
		
		RequestSpecification request = RestAssured.given();
		request
		.header("Content-Type", "application/json")
		.body(requestParams.toString());

		Response response = request.post("/api/articles");

		System.out.println("PostUsers_Excel response is: " + response.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + response.statusCode());

//		
//		if (response.statusCode() == 200) {
//			TestUtil.writeData200(user_email, "OK");
//		} else {
//			TestUtil.writeData200(user_email, "NG");			
//		}
	}	
	
	
	//TestCase cho trường hợp statusCode = 400
	@DataProvider
	public Iterator<Object[]> getTestData400() {
		TestUtil.getDataFromExcel400();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel400();
		return testData.iterator();
	}
		
	@Test(dataProvider = "getTestData400")
	public void post_400( String user_email, String key, String title, String body, String  status) throws IOException, JSONException{		
		
		//Read Excel
		RestAssured.baseURI = "http://172.16.1.85:8080";
		JSONObject requestParams = new JSONObject();
		requestParams.put("user_email",user_email);
		requestParams.put("key", key);
		requestParams.put("title", title);
		requestParams.put("body", body);
		requestParams.put("status", status);
		//requestParams.put("image", new File(image.toString()));
		
		RequestSpecification request = RestAssured.given();
		request
		.header("Content-Type", "application/json")
		.body(requestParams.toString());

		Response response = request.post("/api/articles");

		System.out.println("PostUsers_Excel response is: " + response.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + response.statusCode());

		
		if (response.statusCode() == 400) {
			TestUtil.writeData400(user_email, "OK");
		} else {
			TestUtil.writeData400(user_email, "NG");			
		}
	}	
}
