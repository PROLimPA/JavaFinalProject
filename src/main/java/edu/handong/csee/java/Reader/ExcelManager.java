package edu.handong.csee.java.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelManager {
	
	public String readData(InputStream is) {
	    StringBuffer data = new StringBuffer();

		try (InputStream inp = is) {		    
			Workbook book = WorkbookFactory.create(inp);
		    Sheet sheet = book.getSheetAt(0);
		        
			Cell cell = null;
			Row row = null;
		        
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
			  	row = rowIterator.next();
			         
			    Iterator<Cell> cellIterator = row.cellIterator();
			    while (cellIterator.hasNext()) {
			    	cell = cellIterator.next();
			    	
			    	try {									
			    		data.append(cell.getStringCellValue() + ",");		                    	 
					} catch (Exception e) {
						data.append(String.valueOf(cell.getNumericCellValue()) + ",");
					}
			                   
			        data.append('\n'); 
			    }
			}
			
			book.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return data.toString();
	}
	
	public void writeData(String content, String resultPath){
        
		Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("firstSheet");
        
        Row row = null;
        Cell cell = null;
        
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(content);
        
        try {
            FileOutputStream fos = new FileOutputStream(new File(resultPath));
            book.write(fos);
            
            fos.close();
            book.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
