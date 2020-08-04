package com.tyss_practice.genericUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author Himanshu
 * 
 * This class is used to fetch data from excel
 *
 */
public class ExcelLib {
	String filePath = "./src/main/resources/testScriptData.xlsx";
	   /**
	    * used to read the data from excel workbook
	    * @param sheetName
	    * @param rowIndex
	    * @param cellIndex
	    * @return value
	    * @throws IOException
	    */
	    
		public String getExcelData(String sheetName , int rowIndex , int cellIndex)  throws Throwable {
			FileInputStream file = new FileInputStream(filePath);
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet(sheetName);
			Row row = sheet.getRow(rowIndex);
			book.close();
			
			return row.getCell(cellIndex).getStringCellValue();
					
		}
		/**
		 * used to set data back to excel based on parameter
		 * @param sheetName
		 * @param rowIndex
		 * @param cellIndex
		 * @param data
		 * @throws Throwable
		 */
		 		
		public void setExcelData(String sheetName , int rowIndex , int cellIndex ,String data) throws Throwable {
			FileInputStream file = new FileInputStream(filePath);
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet(sheetName);
			Row row = sheet.getRow(rowIndex);
			Cell cel = row.createCell(cellIndex);
			cel.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(filePath);
					book.write(fos);
			book.close();
		}
		/**
		 * used to get the excel used row count
		 * @param sheetName
		 * @return rowCount
		 * @throws Throwable
		 */
		public int getRowCount(String sheetName)throws Throwable {
			FileInputStream file = new FileInputStream(filePath);
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheet(sheetName);
		
			return sheet.getLastRowNum();
					
		}
}
