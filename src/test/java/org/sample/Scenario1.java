package org.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 {
	 WebDriver driver;
	 WebDriverWait wait;
	    
	    @BeforeClass
	    public void setup() {
	  WebDriverManager.chromiumdriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }
	    
	    @Test
	    public void Test1() throws InterruptedException, IOException   {
	    	 driver.get("https://onship.app/onship");
	    	 
	    	 WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
	         WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
	      File f= new File("C:\\Users\\SURENDRAN\\eclipse-workspace\\OnShip\\target\\Shipuserdetails.xlsx");
	      FileInputStream fin = new FileInputStream(f);
	      Workbook w= new XSSFWorkbook(fin);
	      Sheet s = w.getSheet("Sheet1");
	      Row r1 = s.getRow(1);
	      Cell c1 = r1.getCell(0);
	      Row r2 = s.getRow(1);
	      Cell c2 = r2.getCell(1);
	      String user = c1.getStringCellValue();
	      String pass = c2.getStringCellValue();
	    	 username.sendKeys(user);
	         password.sendKeys(pass);
	         WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
	         submit.submit();     
	      
}
	    @Test
	    public void Test2(){ 
	        driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[5]")));
	        profile.click();
	        WebElement myprofile = driver.findElement(By.xpath("//p[text()='My Profile']"));
	        myprofile.click();
	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("/my-profile"), "User is to the profile screen.");
	        WebElement uploadElement = driver.findElement(By.id("bootstrap-overrides")); 
	        uploadElement.sendKeys("C:\\Users\\SURENDRAN\\eclipse-workspace\\OnShip\\target\\pexels-pixabay-33045.jpg");
	        
		}
	    @Test
	    public void Test3()  {
	  
	    	
	    	   WebElement mobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='MuiStack-root css-ssrp0z'])[6]")));
			mobile.click();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@title,'India: + 91')]"))); 
		        WebElement countrySelection = driver.findElement(By.xpath("//div[contains(@title,'India: + 91')]"));
		        countrySelection.click();
		        WebElement phonenUmber = driver.findElement(By.xpath("//input[@placeholder='Enter country code followed by phone number']"));
		        phonenUmber.sendKeys("8508599900");
		          driver.findElement(By.id("address")).sendKeys("CHENNAI");
		          WebElement save = driver.findElement(By.xpath("//button[text()='Save Changes']"));
		          save.click();
		}
	    @Test
	    public void Test4()  {
	    	
	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentPassword"))).sendKeys("Surendran22@");
	        driver.findElement(By.id("newPassword")).sendKeys("Surendrank22@");
	        driver.findElement(By.id("confirmPassword")).sendKeys("Surendrank22@");
	        driver.findElement(By.id("changePasswordButton")).click();

	      
	        String passwordChangeMsg = driver.findElement(By.id("passwordChangeMsgId")).getText();
	        Assert.assertEquals(passwordChangeMsg, "Password changed successfully!");

	        driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("seafarerYes"))).click();
	        WebElement rankDropdown = driver.findElement(By.id("rankDropdownId"));
	        rankDropdown.click();
	        rankDropdown.findElement(By.xpath("//option[text()='Captain']")).click();

	        driver.findElement(By.id("saveButton")).click();
	        driver.findElement(By.linkText("Logout")).click();
	    	 

		}
	    @AfterClass
	    public void closeBrowser() {
			driver.quit();

		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}