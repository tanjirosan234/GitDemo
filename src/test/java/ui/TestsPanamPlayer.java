package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataProvider.ConfigFileReaderPanam;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsPanamPlayer {

	WebDriver driver;
	String browserId;
	ConfigFileReaderPanam configFileReader= new ConfigFileReaderPanam();
	//ConfigFileReaderMain configFileReaderMain= new ConfigFileReaderMain();
	
	@BeforeTest
	public void Prerequisites() {
		String browserId = configFileReader.getBrowserId();

		if (browserId.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://panamsportweb.streann.com/custom-layout");
			driver.manage().window().maximize();
		}
		if (browserId.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get(configFileReader.getApplicationUrl());
			driver.manage().window().maximize();
		}
		if (browserId.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(configFileReader.getApplicationUrl());
			driver.manage().window().maximize();
		}
	}
/**************************************	
***************************************
****Set of tests for Login page********
***************************************
**************************************/	
	
	/**************************************************************************
	  Test for starting logging page
	 **************************************************************************/
	@Test(priority=1, enabled=true)
	public void StartLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Select Login link
		driver.findElement(By.id("auto-btn-login")).click();
		
	}
	
	
	/**************************************************************************
	//Test for checking successful login with correct user and password
	**************************************************************************/
	@Test(priority=2, enabled=true)
	public void SuccessfulLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter correct user field 
		WebElement loginField = driver.findElement(By.id("autotest-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("autotest-login-passwordLabel"));
		passwordField.sendKeys(configFileReader.getCorrectPassText());

		//Click on the Button "Log In" 
		driver.findElement(By.id("autotest-login-button")).click();
		
	}
	
	@Test(priority=3, enabled=true)
	public void OpenFirstVODDetailPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//First VOD image
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-landing-home-panamsport/div[3]/div[3]/div[1]/div[1]/div/streann-category-view/div[2]/div/div/cdk-virtual-scroll-viewport/div[1]/streann-category-child-view[1]/div/div/div/div[1]/img")).click();
		

	}
	
	@Test(priority=4, enabled=true)
	public void StartVODPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Watch Now
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-category-child-details-view/div/div[2]/div/div[1]/div/div[2]/div[1]/div/button")).click();
		
		//Mute
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"player\"]/div[4]/div[1]/button/span[1]")).click();
		//Unmute
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"player\"]/div[4]/div[1]/button/span[1]")).click();
		//Pause
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"player\"]/div[4]/button[1]/span[1]")).click();
		//Unpause
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"player\"]/div[4]/button[1]/span[1]")).click();
		
	}

	
	
	@AfterTest
	public void Finilize() throws InterruptedException {
		Thread.sleep(configFileReader.getSleepTime());
		driver.close();
	}
	

}
