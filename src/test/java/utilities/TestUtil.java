package utilities;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
	// Fix excel url
	static String excelUrl = "C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\src\\resource\\Demo1.xlsx";
	static Xls_Reader reader;

	// Trường hợp statuscode = 200
	// Read Excel
	public static ArrayList<Object[]> getDataFromExcel200() {
		return handleReadDataFromSheet("statusCode_200");
	}

	// Trường hợp statuscode = 400
	// Read Excel
	public static ArrayList<Object[]> getDataFromExcel400() {
		return handleReadDataFromSheet("statusCode_400");
	}

	/**
	 * Handle write data to excel
	 * 
	 * @param cellValue
	 * @param statusValue
	 * @param columnNameValue
	 */
	public static void writeDataToExcelToSheetName(String sheetName, String cellValue, String statusValue,
			String columnNameValue) {
		try {
			reader = new Xls_Reader(excelUrl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowNum = reader.getCellRowNum(sheetName, "ID", cellValue);
		reader.setCellData(sheetName, columnNameValue, rowNum, statusValue);
	}

	/**
	 * Handle read data excel by sheet name
	 * 
	 * @param sheetName
	 * @return
	 */
	public static ArrayList<Object[]> handleReadDataFromSheet(String sheetName) {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		List<String> coursesList = new ArrayList<String>();
		try {
			reader = new Xls_Reader(excelUrl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rowNum = 2; rowNum <= reader.getRowCount(sheetName); rowNum++) {
			String idSheet = reader.getCellData(sheetName, "ID", rowNum);
			String firstName = reader.getCellData(sheetName, "firstName", rowNum);
			String lastName = reader.getCellData(sheetName, "lastName", rowNum);
			String email = reader.getCellData(sheetName, "email", rowNum);
			String programme = reader.getCellData(sheetName, "programme", rowNum);
			String courses1 = reader.getCellData(sheetName, "courses1", rowNum);
			coursesList.add(courses1);
			String courses2 = reader.getCellData(sheetName, "courses2", rowNum);
			coursesList.add(courses2);
			// String image = reader.getCellData("statusCode_200", "image", rowNum);
			Object ab[] = { idSheet, firstName, lastName, email, programme, coursesList };
			myData.add(ab);
		}

		return myData;
	}

}
