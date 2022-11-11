package ui;

import org.testng.annotations.Test;
import org.testng.Assert;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataProvider.ConfigFileReaderAllGymnastics;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsAllGymnastics {

	WebDriver driver;
	String browserId;
	ConfigFileReaderAllGymnastics configFileReader= new ConfigFileReaderAllGymnastics();
	//ConfigFileReaderMain configFileReaderMain= new ConfigFileReaderMain();
	
	@BeforeTest
	public void Prerequisites() {
		String browserId = configFileReader.getBrowserId();

		if (browserId.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(configFileReader.getApplicationUrl());
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
	 * @throws InterruptedException 
	 **************************************************************************/
	@Test(priority=1, enabled=true)
	public void StartLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

		//Select Login link
		//driver.findElement(By.id("auto-login")).click();
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[9]/a")).click();
		
		//Check is the email field available
		driver.findElement(By.id("auto-login-emailLabel"));
		//Check is the password field available
		driver.findElement(By.id("auto-login-passwordLabel"));
		//Check is the check box "Remember me" available
		driver.findElement(By.id("123"));
		//Check is the Button "Forgot password?" available
		driver.findElement(By.id("forgot-password"));
		//Check is the Button "Log In" available
		driver.findElement(By.id("auto-login-button"));
		
		/*
		//Check is the Button "Facebook Login" available
		driver.findElement(By.id("auto-login-facebookLogin"));
		//Check is the Button "Google Login" available
		driver.findElement(By.id("auto-login-googleLogin"));
		//Check is the Button "Apple Login" available
		driver.findElement(By.id("auto-login-appleLogin"));
		*/
		//Check is the link "New? Sign Up now..." available
		//driver.findElement(By.id("auto-login-newSignUp2"));
	}
	
	/**************************************************************************
	Test for checking login with empty EMail
	***************************************************************************/
	@Test(priority=2, enabled=true)
	public void EmptyMailLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear email field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).clear();
		//Fill password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());	
		
		//Click on the Button "Log In"
		driver.findElement(By.id("auto-login-button")).click();
			
		//Check message that need user to be entered
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	Test for checking login with empty Password
	***************************************************************************/
	@Test(priority=3, enabled=true)
	public void EmptyPasswordLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.navigate().refresh();
		
		//Fill email field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());;
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.id("auto-login-button")).click();
			
		//Check message that need password to be entered
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	Test for checking login with empty EMail and Password
	***************************************************************************/
	@Test(priority=4, enabled=true)
	public void EmptyMailPasswordLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.navigate().refresh();
		
		//Clear email field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).clear();
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.id("auto-login-button")).click();
			
		//Check message that need user to be entered
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message that need password to be entered
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and correct password
	 **************************************************************************/
	@Test(priority=5, enabled=true)
	public void WrongUserCorrectPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter wrong user field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto-login-button")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto-login-usernotfound")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking login with correct user and wrong password
	 **************************************************************************/
	@Test(priority=6, enabled=true)
	public void CorrectUserWrongPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter correct user field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter wrong password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto-login-button")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto-login-usernotfound")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and password
	 **************************************************************************/
	@Test(priority=7, enabled=true)
	public void WrongUserWrongPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter wrong user field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongUserText());
		
		//Enter wrong password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto-login-button")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto-login-usernotfound")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking successful login with correct user and password
	**************************************************************************/
	@Test(priority=8, enabled=true)
	public void SuccessfulLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Refresh the login page 
		driver.navigate().refresh();
			
		//Enter correct user field 
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());

		//Click on the Button "Log In" 
		driver.findElement(By.id("auto-login-button")).click();

		Thread.sleep(3000);
		//Check is logged?
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/a")).click();
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/div/a[2]")).getText().contains(configFileReader.getLogoutText()));
	}
	
	/**************************************************************************
	//Test for checking My Account
	**************************************************************************/
	@Test(priority=9, enabled=true)
	public void MyAccountTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Refresh the login page 
		driver.navigate().refresh();
		Thread.sleep(3000);
		//Open My Account page
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/a")).click();
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/div/a[1]")).click();
		
		//Check Account name
		Thread.sleep(configFileReader.getSleepTime());
		Assert.assertTrue(driver.findElement(By.id("auto-test-membership")).getText().contains(configFileReader.getCorrectUserText()));
	}
	
	/**************************************************************************
	//Test for checking Log out
	**************************************************************************/
	@Test(priority=10, enabled=true)
	public void LogoutTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click on logout
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/a")).click();
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[10]/div/a[2]")).click();
		
		//Check is logged?
		//WebElement loginMenu = driver.findElement(By.id("auto-btn-login"));
		//Assert.assertEquals(loginMenu.findElement(By.tagName("a")).getText(), configFileReader.getLoginTitleText());
	}	

	/**************************************************************************
	//Test for checking successful login with FaceBook
	//EXCLUDED FROM LIST OF ALL TESTS
	**************************************************************************/
	@Test(priority=11, enabled=false)
	public void SuccessfulFacebookLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Select Login link
		driver.findElement(By.id("auto-btn-login")).click();
		
		
		//Check is the Button "FaceBook Login" available
		driver.findElement(By.id("auto-login-facebookLogin")).click();
		
		//String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;
		//String parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		
		driver.findElement(By.name("email")).sendKeys(configFileReader.getUserFB());
		driver.findElement(By.name("pass")).sendKeys(configFileReader.getPasswordFB());
		driver.findElement(By.name("login")).click();	

		//Check is logged?
		/*driver.switchTo().window(parent);
		driver.findElement(By.id("auto-login-avatar-face")).click();
		Assert.assertEquals(driver.findElement(By.id("auto-login-logout")).getText(), configFileReader.getLogoutText());
		*/
		//Click on logout
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto-login-logout")).click();

	}
	
	/**************************************************************************
	//Test for checking successful login with Google
	//EXCLUDED FROM LIST OF ALL TESTS
	**************************************************************************/
	@Test(priority=12, enabled=false)
	public void SuccessfulGoogleLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Select Login link
		driver.findElement(By.id("auto-btn-login")).click();
		
		
		//Check is the Button "Google Login" available
		driver.findElement(By.id("auto-login-googleLogin")).click();
		
		//String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;
		String parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		
		driver.findElement(By.name("identifierId")).sendKeys(configFileReader.getUserFB());
		driver.findElement(By.id("identifierNext")).click();
		
		driver.findElement(By.name("password")).sendKeys(configFileReader.getPasswordFB());
		driver.findElement(By.id("passwordNext")).click();	

		//Check is logged?
		/*driver.switchTo().window(parent);
		driver.findElement(By.id("auto-login-avatar-face")).click();
		Assert.assertEquals(driver.findElement(By.id("auto-login-logout")).getText(), configFileReader.getLogoutText());
		*/
		//Click on logout
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto-login-logout")).click();

	}

	/**************************************	
	***************************************
	*Set of tests for Forgot Password page*
	***************************************
	**************************************/
	
	/**************************************************************************
	//Test for checking start Forgot Password Test
	**************************************************************************/
	@Test(priority=21, enabled=true)
	public void StartForgotPasswordTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click on login
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-custome-nav/nav/div/ul[4]/li[9]/a")).click();
		
		//Click on Forgot password link button
		driver.findElement(By.id("auto-login-forgotPassword")).click();
		
		//Click on Go back button
		driver.findElement(By.id("auto-test-forgotpass-back")).click();
		
		//Click again on Forgot password link button
		driver.findElement(By.id("auto-login-forgotPassword")).click();
	}
	
	/**************************************************************************
	//Test for checking sending empty mail
	**************************************************************************/	
	@Test(priority=22, enabled=true)
	public void EmptyMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-send")).click();
			
		//Check message for empty mail
		Assert.assertEquals(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
	
	}
		
	
	/**************************************************************************
	//Test for checking sending wrong format of mail
	**************************************************************************/	
	@Test(priority=23, enabled=true)
	public void WrongFormatMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter bad formated user or mail field
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-send")).click();
			
		//Check message for empty mail
		Assert.assertEquals(driver.findElement(By.id("auto-customInput-fieldRequired2")).getText(), configFileReader.getNotValidMailText());
	
	}
	
	
	/**************************************************************************
	//Test for checking sending not existing of mail
	**************************************************************************/
	@Test(priority=24, enabled=true)
	public void NotExistingMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
		
		//Enter not existing user or mail field
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getIncorrectMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-send")).click();
			
		Thread.sleep(configFileReader.getSleepTime());
		//Check message for not existed mail
		Assert.assertEquals(driver.findElement(By.id("auto-test-forgotpass1")).getText(), configFileReader.getIncorrectMailMsgText());
	
	}

	
	/**************************************************************************
	//Test for checking Cancel button
	**************************************************************************/
	@Test(priority=25, enabled=true)
	public void CancelButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Cancel button
		driver.findElement(By.id("auto-test-cancel")).click();

	}
	

	/**************************************	
	***************************************
	****Set of tests for Subscribe page****
	***************************************
	**************************************/	
	
	/**************************************************************************
	//Test for starting subscribe from Subscribe button
	**************************************************************************/
	@Test(priority=30, enabled=true)
	public void SubscribeButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Register button from menu
		driver.findElement(By.id("subscribeNow-autotest")).click();
		//Click on back in browser
		Thread.sleep(configFileReader.getSleepTime());
		driver.navigate().back();
		//Click on Register button from menu again
		Thread.sleep(configFileReader.getSleepTime());
		driver.findElement(By.id("subscribeNow-autotest")).click();
	}
	
	
	
	/**************************************************************************
	//Test for check all fields in subscribe page
	**************************************************************************/
	@Test(priority=31, enabled=true)
	public void CheckSubscribeFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Check first name
		driver.findElement(By.id("auto_firstName"));
		//Check last name
		driver.findElement(By.id("auto_lastname"));
		//Check Email
		driver.findElement(By.id("auto_email"));	
		//Check Confirm Email
		driver.findElement(By.id("auto_confirmEmail"));
		//Check Password
		driver.findElement(By.id("auto_password"));
		//Check Confirm password
		driver.findElement(By.id("auto_confirmPas"));
		//Check Country
		driver.findElement(By.id("auto_country"));
		//Check Fan check box
		driver.findElement(By.id("fan"));
		//Check Athlete check box
		driver.findElement(By.id("athlete"));
		//Check Continue button
		driver.findElement(By.id("auto_submit"));
		//Check Cancel button
		driver.findElement(By.id("auto_cancel"));
				
				
	}	
	
	/**************************************************************************
	//Test for subscribe all empty fields
	**************************************************************************/
	@Test(priority=32, enabled=true)
	public void SubscribeEmptyFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Continue to Register button
		driver.findElement(By.id("auto_submit")).click();

		//Check message for First Name
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Last Name
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Email
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Confirm Email
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Password
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Country
		Assert.assertTrue(driver.findElement(By.id("auto-secondStep-fieldRequired1")).getText().contains(configFileReader.getFieldRequiredText()));
				
	}
	
	
	/**************************************************************************
	//Test for subscribe bad formated mail
	**************************************************************************/
	@Test(priority=33, enabled=true)
	public void SubscribeBadFormatedMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		//driver.navigate().refresh();
		
		//Enter bad formated mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());

		//Check message for Email
		Assert.assertTrue(elemEmail.findElement(By.id("auto-customInput-fieldRequired2")).getText().contains(configFileReader.getNotValidMailText()));
		
	}
	
	
	
	/**************************************************************************
	//Test for short password
	**************************************************************************/
	@Test(priority=34, enabled=true)
	public void SubscribeShortPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter short password
		WebElement elem = driver.findElement(By.id("auto_password"));
		elem.findElement(By.tagName("input")).sendKeys(configFileReader.getShortPass());
		//Check message for Password
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired5")).getText().contains(configFileReader.getShortPassMsg()));
		
	}
	
	/**************************************************************************
	//Test for short confirm password
	**************************************************************************/
	@Test(priority=35, enabled=true)
	public void SubscribeShortConfirmPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter short confirm password
		WebElement elem = driver.findElement(By.id("auto_confirmPas"));	
		elem.findElement(By.tagName("input")).sendKeys(configFileReader.getShortPass());
		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired5")).getText().contains(configFileReader.getShortPassMsg()));

	}
	
	
	/**************************************************************************
	//Test for not matched password
	**************************************************************************/
	@Test(priority=36, enabled=true)
	public void SubscribeNotMatchedPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement elemPass = driver.findElement(By.id("auto_password"));
		elemPass = elemPass.findElement(By.tagName("input"));
		elemPass.clear();
		elemPass.sendKeys(configFileReader.getCorrectPassText());
		//Enter wrong confirm password
		WebElement elemConfPass = driver.findElement(By.id("auto_confirmPas"));	
		elemConfPass = elemConfPass.findElement(By.tagName("input"));
		elemConfPass.clear();
		elemConfPass.sendKeys(configFileReader.getWrongPassText());	

		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto-customInput-fieldRequired4")).getText().contains(configFileReader.getPassNotMatchedMsg()));

	}
	
	/**************************************************************************
	//Test for enter country
	**************************************************************************/
	@Test(priority=37, enabled=true)
	public void SubscribeEnterCountryTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		Select country = new Select(driver.findElement(By.id("auto_country")));
		country.selectByValue("Macedonia");
		
	}

	
	/**************************************************************************
	//Test for Enter First Name
	**************************************************************************/
	@Test(priority=39, enabled=true)
	public void SubscribeEnterFirstNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
	}
	
	/**************************************************************************
	//Test for Enter Last Name
	**************************************************************************/
	@Test(priority=40, enabled=true)
	public void SubscribeEnterLastNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement lastName = driver.findElement(By.id("auto_lastname"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
	}
	

	
	/**************************************************************************
	//Test for subscribe already existed user
	**************************************************************************/
	@Test(priority=41, enabled=true)
	public void SubscribeExistedUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter first name again
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
		//Enter Last name again
		WebElement lastName = driver.findElement(By.id("auto_lastname"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
		//Enter country
		Select country = new Select(driver.findElement(By.id("auto_country")));
		country.selectByValue("Macedonia");
		//Enter existed mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		//Enter existed confirm mail
		WebElement elemConfEmail = driver.findElement(By.id("auto_confirmEmail"));
		elemConfEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		//Enter good pass
		WebElement elemPass =  driver.findElement(By.id("auto_password"));
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
		//Enter good confirm pass
		WebElement elemConfPass = driver.findElement(By.id("auto_confirmPas"));
		elemConfPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
		
		
		//Click on Register button
		driver.findElement(By.id("auto_submit")).click();
		
		//Check message for already existed user
		Thread.sleep(configFileReader.getSleepTime());
		WebElement elemToast = driver.findElement(By.id("toast-container"));
		elemToast.findElement(By.tagName("div"));
	}	
	
	
	/**************************************************************************
	//Test for creating new user
	**************************************************************************/
	@Test(priority=42, enabled=true)
	public void SubscribeCreateNewUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Set value for new user
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String newUser = dtf.format(now) + configFileReader.getAutoMailExtText();
		
		//Set new mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		WebElement elemInputMail = elemEmail.findElement(By.tagName("input"));
		elemInputMail.clear();
		elemInputMail.sendKeys(newUser);
		
		//Click on Register button
		driver.findElement(By.id("auto_submit")).click();

		//Check is the new user logged?
		//driver.findElement(By.id("auto-login-avatar-face")).click();
		//Assert.assertEquals(driver.findElement(By.id("auto-login-logout")).getText(), configFileReader.getLogoutText());
		
	}	
	
	

	
	@AfterTest
	public void Finilize() throws InterruptedException {
		Thread.sleep(configFileReader.getSleepTime());
		driver.close();
	}
	

}
