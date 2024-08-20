package org.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario2 {
	 WebDriver driver;
     WebDriverWait wait;
	    @BeforeClass
	    public void setup() {
	    	WebDriverManager.chromiumdriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }
	    @Test
	    public void sendChatMessage() throws InterruptedException, IOException  {
	    	driver.get("https://onship.app/onship");
	    	 
	    	 WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autocomplete='username']")));
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
	         driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	        driver.findElement(By.xpath("//label[text()='Contacts']")).click();
	        
	    
	        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Anbu");
	        driver.findElement(By.xpath("//div[@class='list-body MuiBox-root css-1pw4nq3']")).click(); 

	   
	        WebElement messageBox = driver.findElement(By.xpath("//input[@type='textmessage']"));
	        messageBox.sendKeys("Hello, How are you Anbu!");
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	      
	        driver.findElement(By.linkText("Logout")).click();
	    }

	    @AfterClass
	    public void closeBrowser() {
	    
	            driver.quit();
	        
	    }

	   

}
