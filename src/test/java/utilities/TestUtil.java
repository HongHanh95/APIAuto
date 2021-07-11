package utilities;

import java.util.ArrayList;

public class TestUtil {
	static Xls_Reader reader;

//Ramen
	/**
	 * Ä‘á»�c dá»¯ liá»‡u tá»« excel
	 * 
	 * @return
	 */
	public static ArrayList<Object[]> getDataFromExcel() {
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\data\\create_user.xlsx");

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rowNum = 2; rowNum <= reader.getRowCount("Sheet1"); rowNum++) {
			String fullName = reader.getCellData("Sheet1", "name", rowNum);
			String userName = reader.getCellData("Sheet1", "job", rowNum);		
			Object ab[] = { fullName, userName };
			myData.add(ab);
		}

		return myData;
	}

	/**
	 * Ghi káº¿t quáº£ Ok, NG
	
	 * @param cellValue: lÃ  
	 * @param statusValue: OK or NG
	 */
	public static void writeData(String cellValue, String statusValue) {
		try {
			reader = new Xls_Reader("C:\\Users\\Admin\\eclipse-workspace\\TestAPI\\data\\create_user.xlsx");

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		int rowNum = reader.getCellRowNum("Sheet1", "userName", cellValue);
		
		reader.setCellData("Sheet1", "status", rowNum, statusValue);
	}
}
