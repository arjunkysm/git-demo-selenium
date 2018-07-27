package SeleniumPlus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("restriction")
public class NewTest7_1 {
	WebDriver driver;
	@Test
	public void testLogin()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		//readWriteExcel();
		readXML();
	}

	public String login(String username,String password)
	{
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("login")).click();

		if(driver.getTitle().startsWith("Find"))
		{
			Assert.assertTrue(true);
			driver.findElement(By.linkText("SIGN-OFF")).click();
			return "VALID USER";
		}
		else
		{
			Assert.assertTrue(driver.getTitle().startsWith("Sign-on"));
			return "INVALID USER";
		}
	}

	public void readWriteExcel()
	{
		File file=new File("Book1.xlsx");
		try {
			InputStream is=new FileInputStream(file);

			XSSFWorkbook xssf=new XSSFWorkbook(is);

			XSSFSheet sheet1=xssf.getSheet("Sheet1");
			//System.out.println("First Row Number"+sheet1.getFirstRowNum());
			//System.out.println("Last Row Number"+sheet1.getLastRowNum());
			for(int i=1;i<=sheet1.getLastRowNum();i++)
			{
				String username=sheet1.getRow(i).getCell(0).getStringCellValue();
				String password=sheet1.getRow(i).getCell(1).getStringCellValue();
				String result=login(username, password);
				sheet1.getRow(i).createCell(2).setCellValue(result);
			}
			OutputStream os=new FileOutputStream(file);
			xssf.write(os);
			xssf.close();



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("restriction")
	public void readXML()
	{
		try {
			JAXBContext jaxb=JAXBContext.newInstance(TestUsers.class);
			Unmarshaller unmarsh=jaxb.createUnmarshaller();
			TestUsers testuser=(TestUsers) unmarsh.unmarshal(new File("users.xml"));
			List<Users> users=testuser.getUser();
			
			for (Users u:users) {
				login(u.getUsername(),u.getPassword());
				System.out.println(u.getType());
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
