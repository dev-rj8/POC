package com.ab.apachePOI.ExcelReadWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */
public class XcelRead 
{
	
	public String getExcelFile() {
		return getClass().getClassLoader().getResource("Infomaion.xlsx").getFile();
	}
	
    public static void main( String[] args ) throws IOException
    {
    	XcelRead x = new XcelRead();
    	System.out.println("path : "+x.getExcelFile());
    	
    	String excelFilePath = x.getExcelFile();
    
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet firstSheet = (XSSFSheet) workbook.getSheetAt(1);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        inputStream.close();
        workbook.close();
      
    }//main
}//XcelWR
