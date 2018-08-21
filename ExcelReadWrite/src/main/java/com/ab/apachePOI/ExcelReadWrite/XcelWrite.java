package com.ab.apachePOI.ExcelReadWrite;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.transform.Shear;

public class XcelWrite {
	
public String getExcelFile() {
	return getClass().getClassLoader().getResource("Infomaion.xlsx").getFile();
}
	
public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
	
	XcelWrite x = new XcelWrite();
	System.out.println("path : "+x.getExcelFile());
	
	String excelFilePath = x.getExcelFile();
	
    /* writing into existing Information.xslx  file */
    
	FileInputStream inputStream = new FileInputStream(excelFilePath);
    Workbook  newWorkbook = WorkbookFactory.create(inputStream);
    
    /*  below code to create a new sheet , erasing the already present data in the " Information.xslx"
     * 
     * CreationHelper creationHelper = newWorkbook.getCreationHelper(); 
    XSSFSheet sheet = (XSSFSheet) newWorkbook.createSheet("Student"); */
    
    /* Below line is to add data in "Information.xslx" */
    XSSFSheet sheet = (XSSFSheet) newWorkbook.getSheet("Data");
	  Student  s1  =  new Student(1, "John", "Kennedy", 80);
	  Student s2  =  new Student(2, "Larry", "Page", 42);
	  Student s3  =  new Student(3, "sergy", "brin", 41);
	  Student s4  =  new Student(4, "Indra", "Nooyi", 42);
	  Student s5  =  new Student(100, "Elon", "Musk", 22);
  
  List<Student>  listOfNewStudents  = new ArrayList<Student>();
  listOfNewStudents.add(s1);
  listOfNewStudents.add(s2);
  listOfNewStudents.add(s3);
  listOfNewStudents.add(s4);
  listOfNewStudents.add(s5);
  
  int rowIndex = 1;
  Iterator<Student>  lItr = listOfNewStudents.iterator();
  while(lItr.hasNext()) {
	  Student student = lItr.next();
	  Row row = ((XSSFSheet) sheet).createRow(rowIndex++);
	  Cell cell0 = row.createCell(0);
	  cell0.setCellValue(student.getId());
	  Cell cell1 = row.createCell(1);
	  cell1.setCellValue(student.getName());
	  Cell cell2 = row.createCell(2);
	  cell2.setCellValue(student.getSurname());
	  Cell cell3 = row.createCell(3);
	  cell3.setCellValue(student.getAge());
  }
  FileOutputStream fos = new FileOutputStream(excelFilePath);
  newWorkbook.write(fos);
  fos.close();
  System.out.println(excelFilePath + " written successfully");
  newWorkbook.close();	
}//main
}//XcelW
