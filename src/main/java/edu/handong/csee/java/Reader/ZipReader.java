package edu.handong.csee.java.Reader;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.ArrayList;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader{
	
	public String readFileInZip(File file) {
		ZipFile zipFile;
		String content = ""; 
		   
		try {
			zipFile = new ZipFile(file);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			
		    while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		        
		        ExcelManager myReader = new ExcelManager();
		        content = content + myReader.readData(stream);
		        
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return content; 
	}	
}
