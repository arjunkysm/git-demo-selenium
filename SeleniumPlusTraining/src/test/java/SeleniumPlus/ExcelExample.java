package SeleniumPlus;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.google.common.io.FileBackedOutputStream;

public class ExcelExample {

	FileInputStream readerStream;
	FileBackedOutputStream writerStream;
	XSSFWorkbook Workbook1;
	XSSFSheet WorkbookSheet;
	
	
	
	public void readFile(String fileName) {
		
		try {
			readerStream =new FileInputStream(new File(fileName));
			
			
			Workbook1= new XSSFWorkbook(readerStream);
			WorkbookSheet = Workbook1.getSheet("Sheet1");
			int firstRownum= WorkbookSheet.getFirstRowNum();
			int lastRownum= WorkbookSheet.getLastRowNum();
			
			for(int i=firstRownum+1;i<=lastRownum;i++) {
				for(int j=0;j<WorkbookSheet.getRow(i).getLastCellNum()+1;j++) {
					System.out.print(WorkbookSheet.getRow(i).getCell(j).getStringCellValue()+"\t");
					System.out.println();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
