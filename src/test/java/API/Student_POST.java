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

public class Student_POST {

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

	// TestCase cho trường hợp statusCode = 201

	@DataProvider
	public Iterator<Object[]> getTestData201() {
		TestUtil.getDataFromExcel200();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel200();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData201")
	public void post_201(String idSheet, String firstName, String lastName, String email, String programme,
			List<String> coursesList) throws IOException, JSONException {
		// Request body params
		requestBodyParams(idSheet, "statusCode_200", firstName, lastName, email, programme, coursesList, 201);
	}

	// TestCase cho trường hợp statusCode = 500
	//Ở đây chỉ cần nhập data vào sheet statusCode_400 trong excel là được, cần sửa các param truyền lên ở dòng requestBodyParams(....)
	// Vì courses là 1 mảng có 2 phần tử nên trong exce sẽ tạo ra 2 cột courses1 và courses2 sau đó từ kết quả đọc được ở excel sẽ lưu vào coursesList
	//Lúc gọi cũng sẽ gọi ra coursesList
	@DataProvider
	public Iterator<Object[]> getTestData400() {
		TestUtil.getDataFromExcel400();
		ArrayList<Object[]> testData = TestUtil.getDataFromExcel400();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData400")
	public void post_400(String idSheet, String firstName, String lastName, String email, String programme,
			List<String> coursesList) throws IOException, JSONException {
		// Request body params
		requestBodyParams(idSheet, "statusCode_400", firstName, lastName, email, programme, coursesList, 500);
	}

	//Đây là hàm viết chung từ đó chỉ cần thay statusCode muốn test là được giảm thiểu được code
	@SuppressWarnings("unchecked")
	public void requestBodyParams(String idSheet, String sheetName, String firstName, String lastName, String email,
			String programme, List<String> coursesList, int statusCode) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstName", firstName);
		requestParams.put("lastName", lastName);
		requestParams.put("email", email);
		requestParams.put("programme", programme);
		requestParams.put("courses", coursesList);
//Càn thay đổi các param truyền lên ở đoạn này cùng với file TestUtil

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json").body(requestParams.toString());

		Response response = request.post("http://localhost:8080/student");
		//Cần thay đổi link URL cho đúng với API POST cần test

		System.out.println("PostUsers_Excel response is: " + response.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + response.statusCode());

		if (response.statusCode() == statusCode) {
			TestUtil.writeDataToExcelToSheetName(sheetName, idSheet, "OK", "statusCode");
		} else {
			TestUtil.writeDataToExcelToSheetName(sheetName, idSheet, "NG", "statusCode");
		}

		TestUtil.writeDataToExcelToSheetName(sheetName, idSheet, String.valueOf(response.statusCode()), "ResponseCode");
		TestUtil.writeDataToExcelToSheetName(sheetName, idSheet, response.asString(), "Response");
	}

	/*Ghi vào excel bằng cách dùng cột ID để xác định được row sau đó so sánh statusCode mong muốn với trả về có khớp với nhau
	không nếu khớp thì trả về OK không thì trả về NG
	Ghi response trả về: ResponseCode và respose mục đích có thể gửi file này cho KH nếu cần
	*/
}
