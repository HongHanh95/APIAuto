package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtil {
	static Xls_Reader reader;

// Trường hợp statuscode = 200
//Read Excel
	public static ArrayList<Object[]> getDataFromExcel200() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		List<String> coursesList = new ArrayList();
		try {
			reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\src\\resource\\Demo1.xlsx");

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rowNum = 2; rowNum <= reader.getRowCount("statusCode_200"); rowNum++) {
			String firstName = reader.getCellData("statusCode_200", "firstName", rowNum);
			String lastName = reader.getCellData("statusCode_200", "lastName", rowNum);
			String email = reader.getCellData("statusCode_200", "email", rowNum);
			String programme = reader.getCellData("statusCode_200", "programme", rowNum);
			String courses1 = reader.getCellData("statusCode_200", "courses1", rowNum);
			coursesList.add(courses1);
			String courses2 = reader.getCellData("statusCode_200", "courses2", rowNum);
			coursesList.add(courses2);
			// String image = reader.getCellData("statusCode_200", "image", rowNum);
			Object ab[] = { firstName, lastName, email, programme, coursesList};
			myData.add(ab);
		}

		return myData;
	}

//Write excel
	public static void writeData200(String cellValue, String statusValue, String columnNameValue) {
		try {
			reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\src\\resource\\Demo1.xlsx");

		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowNum = reader.getCellRowNum("statusCode_200", "email", cellValue);
		reader.setCellData("statusCode_200", columnNameValue, rowNum, statusValue);

	}
	
	// Trường hợp statuscode = 400
	//Read Excel
		public static ArrayList<Object[]> getDataFromExcel400() {
			ArrayList<Object[]> myData = new ArrayList<Object[]>();
			List<String> coursesList = new ArrayList();
			try {
				reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\src\\resource\\PostUser.xlsx");

			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int rowNum = 2; rowNum <= reader.getRowCount("statusCode_400"); rowNum++) {
				String firstName = reader.getCellData("statusCode_400", "firstName", rowNum);
				String lastName = reader.getCellData("statusCode_400", "lastName", rowNum);
				String email = reader.getCellData("statusCode_400", "email", rowNum);
				String programme = reader.getCellData("statusCode_400", "programme", rowNum);
				String courses1 = reader.getCellData("statusCode_200", "courses1", rowNum);
				coursesList.add(courses1);
				String courses2 = reader.getCellData("statusCode_200", "courses2", rowNum);
				coursesList.add(courses2);
				Object ab[] = { firstName, lastName, email, programme, coursesList};
				myData.add(ab);
			}

			return myData;
		}

	//Write excel
		public static void writeData400(String cellValue, String statusValue) {
			try {
				reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\src\\resource\\PostUser.xlsx");

			} catch (Exception e) {
				e.printStackTrace();
			}
			int rowNum = reader.getCellRowNum("statusCode_400", "email", cellValue);
			reader.setCellData("statusCode_400", "statusCode", rowNum, statusValue);

		}
//		String userDir = System.getProperty("user.dir");
//		File outputJsonFile = new File(userDir+ "\\src\\test\\resources\\EmployeePayload.json");
	
}
