package API;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import utilities.Links;
import utilities.ReadFile;
import utilities.TestUtil;

public class POST {
// @Test
// public void test_post() {
////	 Map<String, Object> map = new HashMap<String, Object>(); 
////	 map.put("name", "Hanh");
////	 map.put("job", "tester");	 
////	 System.out.println(map);
//	 
//	 JSONObject request = new JSONObject();
//	 request.put("name", "Hong");
//	 request.put("json", "IT");
//	 
//	 System.out.println(request);
//	 System.out.println(request.toJSONString());
//	 
//	 given().
//	 	header("Content-Type", "application/json").
//	 	contentType(ContentType.JSON).
//	 	accept(ContentType.JSON).
//	 	body(request.toJSONString()).
//	 when().
//	 	post("https://reqres.in/api/users").
//	 then().
//	 	statusCode(201);
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
//		 	delete("https://reqres.in/api/users/2").
//		 then().
//		 	statusCode(204).
//		 	log().all();
//		 
//	 }
	
	@DataProvider
	public Iterator<Object[]> getTestData() {
		TestUtil.getDataFromExcel();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel();
		return testData.iterator();
	}
	
	 @Test(dataProvider = "getTestData")
	 public void test_excel_post(String name, String job) {		 
		 JSONObject request = new JSONObject();
		 request.put("name", name);
		 request.put("job", job);
		 
		 System.out.println(request);
		 System.out.println(request.toJSONString());
		 
		 given().
		 	header("Content-Type", "application/json").
		 	contentType(ContentType.JSON).
		 	accept(ContentType.JSON).
		 	body(request.toJSONString()).
		 when().
		 	post("https://reqres.in/api/users").
		 then().
		 	statusCode(201).
		 	log().all();
		 
	 }
	 
	 
}
