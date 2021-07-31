package API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataHandler {

	public Object ReadData_Excel(String dataPath, int row, int col) throws IOException {
		File excelFile = new File(dataPath);
		FileInputStream file = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Object value = "";

		if (sheet.getRow(row).getCell(col).getCellType().equals(CellType.NUMERIC)) {
			value = sheet.getRow(row).getCell(col).getNumericCellValue();
			System.out.println("Reading as numberic value: " + value);
		} else if (sheet.getRow(row).getCell(col).getCellType().equals(CellType.STRING)) {
			value = sheet.getRow(row).getCell(col).getStringCellValue();
			System.out.println("Reading as string value: " + value);
		}

		workbook.close();
		file.close();

		return value;
	}

	public void WriteData_Excel(String dataPath, Object writethis, int row, int col, CellType type) throws IOException {

		File excelFile = new File(dataPath);
		FileInputStream file = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		// CellType.STRING
		// sheet.createRow(row);
		sheet.getRow(row).createCell(col).setCellType(type);
		// sheet.getRow(1).createCell(4).setCellType(CellType.NUMERIC);
		// sheet.getRow(1).createCell(5).setCellType(CellType.STRING);
		sheet.getRow(row).getCell(col).setCellValue(writethis.toString());
		// sheet.getRow(1).getCell(4).setCellValue(resp.statusCode());
		// sheet.getRow(1).getCell(5).setCellValue(resp.asString());

		FileOutputStream outFile = new FileOutputStream(excelFile);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		file.close();
	}
}
