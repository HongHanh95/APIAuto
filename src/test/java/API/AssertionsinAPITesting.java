package API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssertionsinAPITesting {

	DataHandler dh = new DataHandler();

	@Test(enabled = true, priority = 2)
	public void getUsers_parsing_assertions() {

		RestAssured.baseURI = "https://reqres.in";

		RequestSpecification req = RestAssured.given();
		req.header("nexfix_id", "32748732jfbfhu82jsdfs").header("netfix_password", " jry8new84r4nfek").header("stream",
				"NETFLIX");
		req.auth().basic("username", "password");
		req.contentType("application/json");
		req.param("page", 2);

		Response resp = req.get("api/users");

		System.out.println("Get users response is: " + resp.asString());
		System.out.println("Get users response code is: " + resp.statusCode());
		System.out.println("Get users page is: " + resp.jsonPath().getChar("page"));
		System.out.println("Get users data in list format is: " + resp.jsonPath().getList("data"));

		System.out.println("Get users JSON object is: " + resp.jsonPath().getJsonObject("data"));
		System.out.println("Get users as Map is: " + resp.jsonPath().getMap("data[0]"));
		System.out.println("2nd element data for get Users is: " + resp.jsonPath().getChar("page"));
		System.out.println(
				"Email of 3rd element in data for getUsers is: " + resp.jsonPath().getMap("data[2]").get("email"));
		System.out.println("Get users response contenttype is: " + resp.getContentType());

		// Assertions
		Assert.assertEquals(200, resp.statusCode());
		Assert.assertEquals(2, resp.jsonPath().getInt("page"));
		int var = resp.jsonPath().getInt("page");
		Assert.assertTrue("Test is failed because ...etc:", (var == 2));

		// Real time validations
		int expected_result = 11;
		int actual_result = resp.jsonPath().getInt("total");
		if (expected_result == actual_result) {
			Reporter.log("Test is passed");
			Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
		} else {
			Reporter.log("Test is Failed");
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
//			Assert.fail("Test is failed as Expected result: " + expected_result + "doesm't math with Actual result: "
//					+ actual_result);
		}
	}

	@Test(enabled = true)
	public void postUser2_Excel() throws Exception {
		String dataPath = System.getProperty("user.dir");
		dataPath = dataPath + "/src/resource/PostUser.xlsx";
		System.out.println("Data path is: " + dataPath);

		File excelFile = new File(dataPath);
		FileInputStream file = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		String name = sheet.getRow(1).getCell(1).getStringCellValue();
		String job = sheet.getRow(1).getCell(2).getStringCellValue();
		System.out.println("job is: " + job);

		RestAssured.baseURI = "https://reqres.in/";
		JSONObject bodyParameter = new JSONObject();
		bodyParameter.put("name", name);
		bodyParameter.put("job", job);

		RequestSpecification req = RestAssured.given();
		req.body(bodyParameter.toString());

		Response resp = req.post("api/users");

		System.out.println("PostUsers_Excel response is: " + resp.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + resp.statusCode());

		if (resp.statusCode() == 201) {
			sheet.getRow(1).createCell(3).setCellType(CellType.STRING);
			sheet.getRow(1).createCell(4).setCellType(CellType.NUMERIC);
			sheet.getRow(1).createCell(5).setCellType(CellType.STRING);
			sheet.getRow(1).getCell(3).setCellValue("PASSED");
			sheet.getRow(1).getCell(4).setCellValue(resp.statusCode());
			sheet.getRow(1).getCell(5).setCellValue(resp.asString());

		} else {
			sheet.getRow(1).createCell(3).setCellType(CellType.STRING);
			sheet.getRow(1).createCell(4).setCellType(CellType.NUMERIC);
			sheet.getRow(1).createCell(5).setCellType(CellType.STRING);
			sheet.getRow(1).getCell(3).setCellValue("FAILED");
			sheet.getRow(1).getCell(4).setCellValue(resp.statusCode());
			sheet.getRow(1).getCell(5).setCellValue(resp.asString());
		}

		FileOutputStream outFile = new FileOutputStream(excelFile);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		file.close();

	}

	@Test(enabled = true)
	public void postUser3_Excel() throws IOException, JSONException {
		String dataPath = System.getProperty("user.dir");
		dataPath = dataPath + "/src/resource/PostUser.xlsx";
		System.out.println("Data path is: " + dataPath);

		// Read data
		Object name = dh.ReadData_Excel(dataPath, 1, 1);
		Object job = dh.ReadData_Excel(dataPath, 1, 2);

		RestAssured.baseURI = "https://reqres.in/";
		JSONObject bodyParameter = new JSONObject();
		bodyParameter.put("name", name.toString());
		bodyParameter.put("job", job.toString());

		RequestSpecification req = RestAssured.given();
		req.body(bodyParameter.toString());

		Response resp = req.post("api/users");

		System.out.println("PostUsers_Excel response is: " + resp.asString());
		System.out.println("PostUsers_Excel response statusCOde: " + resp.statusCode());

		if (resp.statusCode() == 201) {
			dh.WriteData_Excel(dataPath, "PASSED", 1, 3, CellType.STRING);
			dh.WriteData_Excel(dataPath, resp.statusCode(), 1, 4, CellType.NUMERIC);
			dh.WriteData_Excel(dataPath, resp.asString(), 1, 5, CellType.STRING);

		} else {
			dh.WriteData_Excel(dataPath, "FAILED", 1, 3, CellType.STRING);
			dh.WriteData_Excel(dataPath, resp.statusCode(), 1, 4, CellType.NUMERIC);
			dh.WriteData_Excel(dataPath, resp.asString(), 1, 5, CellType.STRING);
		}
	}

}
