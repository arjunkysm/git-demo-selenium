package SeleniumPlus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver=null;
	
	public void testLogin() throws InterruptedException {
		WebDriver LocalDriver = getMyDriver("chrome", "http://newtours.demoaut.com/");

		LocalDriver.findElement(By.name("userName")).sendKeys("mercury");
		LocalDriver.findElement(By.name("password")).sendKeys("mercury");
		
		LocalDriver.findElement(By.name("login")).click();
		
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Find a Flight: Mercury Tours:");
	}
	
	
	
	
	public void actionsExample() {
		WebDriver LocalDriver = getMyDriver("chrome", "https://www.hdfcbank.com/");
		
		Actions action = new Actions(LocalDriver);
		
		action.moveToElement(driver.findElement(By.xpath(".//a[@href='/personal/products']"))).perform();
		action.moveToElement(driver.findElement(By.xpath(".//a[@href='/personal/products/demat']"))).perform();
		action.moveToElement(driver.findElement(By.xpath(".//a[@href='/personal/products/demat/demat-account']"))).click().perform();
		
		Assert.assertTrue(driver.getTitle().startsWith("Demat"));
		
	}
	
	
	public void keysActionsExample() throws InterruptedException {
		WebDriver LocalDriver = getMyDriver("chrome", "https://www.cleartrip.com/");
		
		Actions action = new Actions(LocalDriver);
		
		WebElement From = driver.findElement(By.id("FromTag"));
		WebElement To = driver.findElement(By.id("ToTag"));
		
		action.keyDown(From,Keys.SHIFT).sendKeys(From, "hyd").keyUp(Keys.SHIFT).perform();
		Thread.sleep(10000);
		action.sendKeys(Keys.ENTER).perform();
		
		
		action.moveToElement(To).click().click().sendKeys("Bang").perform();
		Thread.sleep(5000);
		WebElement cityUL = driver.findElement(By.id("ui-id-2"));
		List<WebElement> cityList = cityUL.findElements(By.tagName("li"));
		
		for(WebElement e:cityList) {
			
			action.moveToElement(e).perform();
			
			if(e.getText().contains("BKK")) {
				e.click();
				break;
			}
		}
				
		
	}
	
	public WebDriver getMyDriver(String browserName,String URL) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\a07208trng_b4b.04.24\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\a07208trng_b4b.04.24\\Downloads\\chromedriver_win32\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\a07208trng_b4b.04.24\\Downloads\\chromedriver_win32\\geckodriver.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
}
