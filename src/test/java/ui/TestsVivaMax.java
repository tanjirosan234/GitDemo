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

import dataProvider.ConfigFileReaderVivaMax;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsVivaMax {

	WebDriver driver;
	String browserId;
	ConfigFileReaderVivaMax configFileReader= new ConfigFileReaderVivaMax();
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
	 **************************************************************************/
	@Test(priority=1, enabled=true)
	public void StartLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Select Login link
		driver.findElement(By.id("auto-btn-login")).click();
		//Check is the user field available
		driver.findElement(By.id("auto-login-emailLabel"));
		//Check is the password field available
		driver.findElement(By.id("auto-login-passwordLabel"));
		//Check is the check box "Remember me" available
		driver.findElement(By.id("auto-login-rememberMe"));
		//Check is the Button "Forgot password?" available
		driver.findElement(By.id("auto-login-forgotPassword"));
		//Check is the Button "Log In" available
		driver.findElement(By.id("auto-login-button"));
		
		//Check is the Button "Facebook Login" available
		driver.findElement(By.id("auto-login-facebookLogin"));
		//Check is the Button "Google Login" available
		driver.findElement(By.id("auto-login-googleLogin"));
		//Check is the Button "Apple Login" available
		driver.findElement(By.id("auto-login-appleLogin"));
		
		//Check is the link "New? Sign Up now..." available
		driver.findElement(By.id("auto-login-newSignUp2"));
	}
	
	/**************************************************************************
	Test for checking login with empty user and password
	***************************************************************************/
	@Test(priority=2, enabled=true)
	public void EmptyLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user field
		WebElement loginField = driver.findElement(By.id("auto-login-emailLabel"));
		loginField.findElement(By.tagName("input")).clear();
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto-login-passwordLabel"));
		passwordField.findElement(By.tagName("input")).clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.id("auto-login-button")).click();
			
		//Check message that need user to be entered
		Assert.assertEquals(loginField.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
		//Check message that need password to be entered
		Assert.assertEquals(passwordField.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and password
	 **************************************************************************/
	@Test(priority=3, enabled=true)
	public void WrongLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

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
		Assert.assertEquals(driver.findElement(By.id("auto-login-usernotfound")).getText(), configFileReader.getIncorrectUserPassMsgText());
	}
	
	/**************************************************************************
	//Test for checking successful login with correct user and password
	**************************************************************************/
	@Test(priority=4, enabled=true)
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

		//Check is logged?
		driver.findElement(By.id("auto-login-avatar-face")).click();
		Assert.assertEquals(driver.findElement(By.id("auto-login-logout")).getText(), configFileReader.getLogoutText());

	}
	
	/**************************************************************************
	//Test for checking My Account
	**************************************************************************/
	@Test(priority=5, enabled=true)
	public void MyAccountTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Open My Account page
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto-login-myAcc")).click();
		
		//Check Account name
		Thread.sleep(configFileReader.getSleepTime());
		Assert.assertEquals(driver.findElement(By.id("auto-test-membership")).getText(), configFileReader.getCorrectUserText());
	}
	
	/**************************************************************************
	//Test for checking Log out
	**************************************************************************/
	@Test(priority=6, enabled=true)
	public void LogoutTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Click on logout
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto-login-logout")).click();
		
		//Open again home page in order to avoid landing page
		driver.get(configFileReader.getApplicationUrl());
		
		//Check is logged?
		WebElement loginMenu = driver.findElement(By.id("auto-btn-login"));
		Assert.assertEquals(loginMenu.findElement(By.tagName("a")).getText(), configFileReader.getLoginTitleText());
	}	

	/**************************************************************************
	//Test for checking successful login with FaceBook
	//EXCLUDED FROM LIST OF ALL TESTS
	**************************************************************************/
	@Test(priority=7, enabled=false)
	public void SuccessfulFacebookLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Select Login link
		driver.findElement(By.id("auto-btn-login")).click();
		
		
		//Check is the Button "FaceBook Login" available
		driver.findElement(By.id("auto-login-facebookLogin")).click();
		
		//String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;
		String parent = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		
		driver.findElement(By.id("email")).sendKeys(configFileReader.getUserFB());
		driver.findElement(By.id("pass")).sendKeys(configFileReader.getPasswordFB());
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
	


	/**************************************	
	***************************************
	*Set of tests for Forgot Password page*
	***************************************
	**************************************/
	
	/**************************************************************************
	//Test for checking start Forgot Password Test
	**************************************************************************/
	@Test(priority=11, enabled=true)
	public void StartForgotPasswordTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Click on login
		driver.findElement(By.id("auto-btn-login")).click();
		
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
	@Test(priority=12, enabled=true)
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
	@Test(priority=13, enabled=true)
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
	@Test(priority=14, enabled=true)
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
	@Test(priority=15, enabled=true)
	public void CancelButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Cancel button
		driver.findElement(By.id("auto-test-cancel")).click();

	}
	
	/**************************************************************************
	//Test for close window and driver
	**************************************************************************/
	@Test(priority=16, enabled=true)
	public void CloseWindowTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Close driver
		driver.close();

	}
	
	/**************************************************************************
	//Test for open window and driver again
	**************************************************************************/
	@Test(priority=19, enabled=true)
	public void OpenWindowAgain() throws InterruptedException {
		
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
	****Set of tests for Subscribe page****
	***************************************
	**************************************/	
	
	/**************************************************************************
	//Test for starting subscribe from Subscribe button
	**************************************************************************/
	@Test(priority=20, enabled=false)
	public void SubscribeButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Open again home page in order to avoid landing page
		driver.get(configFileReader.getApplicationUrl());
				
		//Click on login
		driver.findElement(By.id("auto-test-subscribeButtonTop")).click();
	
	}
	
	/**************************************************************************
	//Test for button Cancel
	**************************************************************************/
	@Test(priority=21, enabled=false)
	public void SubscribeCancelTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Open again home page in order to avoid landing page
		//driver.get(configFileReader.getApplicationUrl());
		//driver.navigate().refresh();
		
		//Click on login
		driver.findElement(By.id("auto-btn-login")).click();

		//Click on "New? Sign up now..." link button
		driver.findElement(By.id("auto-login-newSignUp2")).click();
				
		//Click on Cancel button
		driver.findElement(By.id("autotest-cancel-Vivamax")).click();

	}
	
	/**************************************************************************
	//Test for starting subscribe
	**************************************************************************/
	@Test(priority=22, enabled=false)
	public void InitialSubscribeTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Open again home page in order to avoid landing page
		driver.get(configFileReader.getApplicationUrl());
		
		//Click on login
		driver.findElement(By.id("auto-btn-login")).click();

		//Click on "New? Sign up now..." link button
		driver.findElement(By.id("auto-login-newSignUp2")).click();
	
	}
	
	
	
	/**************************************************************************
	//Test for subscribe all empty fields
	**************************************************************************/
	@Test(priority=23, enabled=false)
	public void SubscribeEmptyFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for Email
		WebElement elemEmail = driver.findElement(By.id("autotest-email-vivamax"));
		Assert.assertEquals(elemEmail.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
		//Check message for Confirm Email
		WebElement elemConfirmEmail = driver.findElement(By.id("autotest-confirmEmail-vivamax"));
		Assert.assertEquals(elemConfirmEmail.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
		//Check message for Password
		WebElement elemPassword = driver.findElement(By.id("autotest-password-vivamax"));
		Assert.assertEquals(elemPassword.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
		//Check message for Confirm Password
		WebElement elemConfirmPassword = driver.findElement(By.id("autotest-confirmPassword-vivamax"));
		Assert.assertEquals(elemConfirmPassword.findElement(By.id("auto-customInput-fieldRequired1")).getText(), configFileReader.getFieldRequiredText());
	
		//Check Gender
		Assert.assertEquals(driver.findElement(By.id("auto-secondStep-fieldRequired3")).getText(), configFileReader.getFieldRequiredText());
	}
	
	
	/**************************************************************************
	//Test for subscribe bad formated mail
	**************************************************************************/
	@Test(priority=24, enabled=false)
	public void SubscribeBadFormatedMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter bad formated mail
		WebElement elemEmail = driver.findElement(By.id("autotest-email-vivamax"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());
		//Enter bad formated confirm mail
		WebElement elemConfirmEmail = driver.findElement(By.id("autotest-confirmEmail-vivamax"));
		elemConfirmEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());	
		
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for Email
		Assert.assertEquals(elemEmail.findElement(By.id("auto-customInput-fieldRequired2")).getText(), configFileReader.getNotValidMailText());
		//Check message for Confirm Email
		Assert.assertEquals(elemConfirmEmail.findElement(By.id("auto-customInput-fieldRequired2")).getText(), configFileReader.getNotValidMailText());
		
	}
	
	
	/**************************************************************************
	//Test for mail not matched
	**************************************************************************/
	@Test(priority=25, enabled=false)
	public void SubscribeMailNotMatchedTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter good formated mail
		WebElement elemEmail = driver.findElement(By.id("autotest-email-vivamax"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getGoodMail1());
		//Enter good formated but different confirm mail
		WebElement elemConfirmEmail = driver.findElement(By.id("autotest-confirmEmail-vivamax"));
		elemConfirmEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getGoodMail2());	
		
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for Confirm Email
		Assert.assertEquals(elemConfirmEmail.findElement(By.id("auto-customInput-fieldRequired3")).getText(), configFileReader.getNotMatchedMailText());
		
	}
	
	
	/**************************************************************************
	//Test for short password
	**************************************************************************/
	@Test(priority=26, enabled=false)
	public void SubscribeShortPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter short password
		WebElement elemPassword = driver.findElement(By.id("autotest-password-vivamax"));
		elemPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getShortPass());
		//Enter short confirm password
		WebElement elemConfirmPassword = driver.findElement(By.id("autotest-confirmPassword-vivamax"));
		elemConfirmPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getShortPass());	
		
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for Password
		Assert.assertEquals(elemPassword.findElement(By.id("auto-customInput-fieldRequired5")).getText(), configFileReader.getShortPassMsg());
		//Check message for Confirm Password
		Assert.assertEquals(elemConfirmPassword.findElement(By.id("auto-customInput-fieldRequired5")).getText(), configFileReader.getShortPassMsg());

	}
	
	
	/**************************************************************************
	//Test for not matched password
	**************************************************************************/
	@Test(priority=27, enabled=false)
	public void SubscribeNotMatchedPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter good password
		WebElement elemPassword = driver.findElement(By.id("autotest-password-vivamax"));
		elemPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getCorrectPassText());
		//Enter wrong confirm password
		WebElement elemConfirmPassword = driver.findElement(By.id("autotest-confirmPassword-vivamax"));
		elemConfirmPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getWrongPassText());	
		
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for Confirm Password
		Assert.assertEquals(elemConfirmPassword.findElement(By.id("auto-customInput-fieldRequired4")).getText(), configFileReader.getPassNotMatchedMsg());

	}
	
	
	/**************************************************************************
	//Test for subscribe already existed user
	**************************************************************************/
	@Test(priority=28, enabled=false)
	public void SubscribeExistedUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter existed mail
		WebElement elemEmail = driver.findElement(By.id("autotest-email-vivamax"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		//Enter existed confirm mail
		WebElement elemConfirmEmail = driver.findElement(By.id("autotest-confirmEmail-vivamax"));
		elemConfirmEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());	
		//Enter existed password
		WebElement elemPassword = driver.findElement(By.id("autotest-password-vivamax"));
		elemPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getCorrectPassText());
		//Enter existed confirm password
		WebElement elemConfirmPassword = driver.findElement(By.id("autotest-confirmPassword-vivamax"));
		elemConfirmPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getCorrectPassText());	
		
		//Check Male
		driver.findElement(By.id("autotest-male-Vivamax")).click();
		
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check message for already existed user
		Thread.sleep(3000);
		//WebElement elemToast = driver.findElement(By.id("toast-container"));
		WebElement elemMsg = driver.findElement(By.tagName("streann-second-step"));
		elemMsg.findElement(By.tagName("div"));
	}	
	
	
	/**************************************************************************
	//Test for creating new user
	**************************************************************************/
	@Test(priority=29, enabled=false)
	public void SubscribeCreateNewUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Set value for new user
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String newUser = dtf.format(now) + configFileReader.getAutoMailExtText();
		
		//Enter existed mail
		WebElement elemEmail = driver.findElement(By.id("autotest-email-vivamax"));
		elemEmail.findElement(By.tagName("input")).sendKeys(newUser);
		//Enter existed confirm mail
		WebElement elemConfirmEmail = driver.findElement(By.id("autotest-confirmEmail-vivamax"));
		elemConfirmEmail.findElement(By.tagName("input")).sendKeys(newUser);	
		//Enter existed password
		WebElement elemPassword = driver.findElement(By.id("autotest-password-vivamax"));
		elemPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getCorrectPassText());
		//Enter existed confirm password
		WebElement elemConfirmPassword = driver.findElement(By.id("autotest-confirmPassword-vivamax"));
		elemConfirmPassword.findElement(By.xpath("input")).sendKeys(configFileReader.getCorrectPassText());	
				
		//Check Male
		driver.findElement(By.id("autotest-male-Vivamax")).click();
				
		//Click on Continue button
		driver.findElement(By.id("autotest-continue-Vivamax")).click();

		//Check is the new user logged?
		driver.findElement(By.id("auto-login-avatar-face")).click();
		Assert.assertEquals(driver.findElement(By.id("auto-login-logout")).getText(), configFileReader.getLogoutText());
	
	}	
	
	

	
	@AfterTest
	public void Finilize() throws InterruptedException {
		Thread.sleep(configFileReader.getSleepTime());
		driver.close();
	}
	

}