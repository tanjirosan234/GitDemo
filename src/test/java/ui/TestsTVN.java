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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataProvider.ConfigFileReaderTVN;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsTVN {

	WebDriver driver;
	String browserId;
	ConfigFileReaderTVN configFileReader= new ConfigFileReaderTVN();
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
		driver.findElement(By.id("auto_login")).click();
		//Check is the user field available
		driver.findElement(By.id("auto_email_login"));
		//Check is the password field available
		driver.findElement(By.id("auto_login_pass"));
		//Check is the check box "Remember me" available
		driver.findElement(By.id("auto_rememberMe"));
		//Check is the Button "Forgot password?" available
		driver.findElement(By.id("auto_forgot_password"));
		//Check is the Button "Log In" available
		driver.findElement(By.id("auto_login"));
		
		//Check is the Subscribe new user available
		driver.findElement(By.id("auto_subscribeLogin"));
		
		/*
		//Check is the Button "Facebook Login" available
		driver.findElement(By.id("auto-login-facebookLogin"));
		//Check is the Button "Google Login" available
		driver.findElement(By.id("auto-login-googleLogin"));
		//Check is the Button "Apple Login" available
		driver.findElement(By.id("auto-login-appleLogin"));
		
		//Check is the link "New? Sign Up now..." available
		driver.findElement(By.id("auto-login-newSignUp2"));
		*/
	}
	
	/**************************************************************************
	Test for checking login with empty user and password
	***************************************************************************/
	@Test(priority=2, enabled=true)
	public void EmptyLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user field
		WebElement loginField = driver.findElement(By.id("auto_email_login"));
		loginField.findElement(By.tagName("input")).clear();
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto_login_pass"));
		passwordField.findElement(By.tagName("input")).clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-login/div[2]/form/div[2]/button")).click();
			
		//Check message that need user to be entered
		Assert.assertEquals(loginField.findElement(By.id("auto_fieldRequired")).getText(), configFileReader.getFieldRequiredText());
		//Check message that need password to be entered
		Assert.assertEquals(passwordField.findElement(By.id("auto_fieldRequired")).getText(), configFileReader.getFieldRequiredText());
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and password
	 **************************************************************************/
	@Test(priority=3, enabled=true)
	public void WrongLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

		driver.navigate().refresh();
		//Enter wrong user field
		WebElement loginField = driver.findElement(By.id("auto_email_login"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongUserText());
		
		//Enter wrong password field 
		WebElement passwordField = driver.findElement(By.id("auto_login_pass"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-login/div[2]/form/div[2]/button")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("userNotFound")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
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
		WebElement loginField = driver.findElement(By.id("auto_email_login"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("auto_login_pass"));
		passwordField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());

		//Click on the Button "Log In" 
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-login/div[2]/form/div[2]/button")).click();
				
		//Check is logged?
		driver.findElement(By.id("auto_dropdown")).click();
		Assert.assertEquals(driver.findElement(By.id("auto_logout")).getText(), configFileReader.getLogoutText());

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
		driver.findElement(By.id("auto_dropdown")).click();
		driver.findElement(By.id("auto_myaccount")).click();
		
		//Check Account name
		Thread.sleep(configFileReader.getSleepTime());
		Assert.assertTrue(driver.findElement(By.id("auto_email_myAccount")).getText().contains(configFileReader.getCorrectUserText()));
	}
	
	/**************************************************************************
	//Test for checking Log out
	**************************************************************************/
	@Test(priority=6, enabled=true)
	public void LogoutTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Click on logout
		driver.findElement(By.id("auto_dropdown")).click();
		driver.findElement(By.id("auto_logout")).click();
		
		//Check is logged?
		WebElement loginMenu = driver.findElement(By.id("auto_login"));
		Assert.assertEquals(loginMenu.getText(), configFileReader.getLoginTitleText());
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
		driver.findElement(By.id("auto_login")).click();
		
		//Click on Forgot password link button
		driver.findElement(By.id("auto_forgot_password")).click();
		
		//Click on Go back button
		driver.findElement(By.id("auto_forgot_password_back")).click();
		
		//Click again on Forgot password link button
		driver.findElement(By.id("auto_forgot_password")).click();
	}
	
	/**************************************************************************
	//Test for checking sending empty mail
	**************************************************************************/	
	@Test(priority=12, enabled=true)
	public void EmptyMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto_email_forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
			
		//Click on Send Mail button
		driver.findElement(By.id("auto_submitEmail")).click();
			
		//Check message for empty mail
		Assert.assertEquals(driver.findElement(By.id("auto_fieldRequired")).getText(), configFileReader.getFieldRequiredText());
	
	}
		
	
	/**************************************************************************
	//Test for checking sending wrong format of mail
	**************************************************************************/	
	@Test(priority=13, enabled=true)
	public void WrongFormatMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter bad formated user or mail field
		WebElement elemEmail = driver.findElement(By.id("auto_email_forgotpass"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto_submitEmail")).click();
			
		//Check message for empty mail
		Assert.assertTrue(driver.findElement(By.id("auto_emailInvalid")).getText().contains(configFileReader.getNotValidMailText()));
	
	}
	
	
	/**************************************************************************
	//Test for checking sending not existing of mail
	**************************************************************************/
	@Test(priority=14, enabled=true)
	public void NotExistingMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto_email_forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
		
		//Enter not existing user or mail field
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getIncorrectMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto_submitEmail")).click();
			
		Thread.sleep(configFileReader.getSleepTime());
		//Check message for not existed mail
		Assert.assertTrue(driver.findElement(By.id("auto-test-userNotFound")).getText().contains(configFileReader.getIncorrectMailMsgText()));
	
	}

	
	/**************************************************************************
	//Test for checking Cancel button
	**************************************************************************/
	@Test(priority=15, enabled=true)
	public void CancelButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Cancel button
		driver.findElement(By.id("auto_cancel")).click();

	}
	

	/**************************************	
	***************************************
	****Set of tests for Subscribe page****
	***************************************
	**************************************/	
	
	/**************************************************************************
	//Test for starting subscribe from Subscribe button
	**************************************************************************/
	@Test(priority=20, enabled=true)
	public void SubscribeButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on login
		driver.findElement(By.id("auto_subscribe")).click();
	
	}
	
	/**************************************************************************
	//Test for Cancel from Subscribe
	**************************************************************************/
	@Test(priority=21, enabled=true)
	public void SubscribeCancelTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Click on back in browser
		driver.findElement(By.id("auto_cancel")).click();

	}
	
	/**************************************************************************
	//Test for starting subscribe
	**************************************************************************/
	@Test(priority=22, enabled=true)
	public void InitialSubscribeTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on login
		driver.findElement(By.id("auto_login")).click();

		//Click on "New? Sign up now..." link button
		driver.findElement(By.id("auto_subscribeLogin")).click();
	
	}
	
	/**************************************************************************
	//Test for Check subscribe GUI
	**************************************************************************/
	@Test(priority=23, enabled=true)
	public void CheckSubscribeFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Check first name
		driver.findElement(By.id("auto_firstName"));
		//Check last name
		driver.findElement(By.id("auto_lastName"));

		//Check Email
		driver.findElement(By.id("auto_email"));
		//Check confirm mail
		driver.findElement(By.id("auto_email_confirmEmail"));
		//Check Password
		driver.findElement(By.id("auto_pass_subscribe"));
		//Check Confirm password
		driver.findElement(By.id("autoConfirmPass"));
		//Check date picker Birth date
		driver.findElement(By.id("auto_birthday"));
		//Check Phone
		driver.findElement(By.id("auto_phone"));
		//Check Male check box
		driver.findElement(By.id("123"));
		//Check Male check box
		driver.findElement(By.id("222"));
		//Check address (direction)
		driver.findElement(By.id("auto_address"));
		//Check City
		driver.findElement(By.id("auto_city"));
		//Check state (provincia)
		driver.findElement(By.id("auto_state"));
		//Check State
		driver.findElement(By.id("auto_select_country"));
		//Check Voucher (Coupon)
		driver.findElement(By.id("auto_voucherCode"));
		
		//Check Continue button
		driver.findElement(By.id("auto_button_cont"));
		//Check Continue button
		driver.findElement(By.id("auto_cancel"));	
	}	
	
	
	/**************************************************************************
	//Test for subscribe all empty fields
	**************************************************************************/
	@Test(priority=24, enabled=true)
	public void SubscribeEmptyFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Register button
		driver.findElement(By.id("auto_button_cont")).click();

		//Check message for First Name
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Last Name
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Email
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Confirm Email
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Password
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Phone
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Gender
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for City
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Country
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for State
		Assert.assertTrue(driver.findElement(By.id("auto_error_country")).getText().contains(configFileReader.getFieldRequiredText()));
	
	}
	
	
	/**************************************************************************
	//Test for subscribe bad formated mail
	**************************************************************************/
	@Test(priority=25, enabled=true)
	public void SubscribeBadFormatedMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter bad formated mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());

		//Check message for Email
		Assert.assertTrue(elemEmail.findElement(By.id("auto_emailInvalid")).getText().contains(configFileReader.getNotValidMailText()));
		
	}
	
	/**************************************************************************
	//Test for subscribe bad formated confirm mail
	**************************************************************************/
	@Test(priority=26, enabled=true)
	public void SubscribeBadFormatedConfirmMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter bad formated mail
		WebElement elemEmail = driver.findElement(By.id("auto_email_confirmEmail"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());

		//Check message for Email
		Assert.assertTrue(elemEmail.findElement(By.id("auto_emailInvalid")).getText().contains(configFileReader.getNotValidMailText()));
		
	}

	/**************************************************************************
	//Test for not matched emails
	**************************************************************************/
	@Test(priority=27, enabled=true)
	public void SubscribeNotMatchedEmailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter password
		WebElement elemPass = driver.findElement(By.id("auto_email"));
		elemPass.findElement(By.tagName("input")).clear();
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getGoodMail1());
		
		//Enter wrong confirm password
		WebElement elemConfirmPass = driver.findElement(By.id("auto_email_confirmEmail"));	
		elemConfirmPass.findElement(By.tagName("input")).clear();
		elemConfirmPass.findElement(By.tagName("input")).sendKeys(configFileReader.getGoodMail2());	

		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto_emailnotMatch")).getText().contains(configFileReader.getPassNotMatchedMsg()));

	}
	
	/**************************************************************************
	//Test for short password
	**************************************************************************/
	@Test(priority=28, enabled=true)
	public void SubscribeShortPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter short password
		WebElement elemPass = driver.findElement(By.id("auto_pass_subscribe"));
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getShortPass());
		//Check message for Password
		Assert.assertTrue(driver.findElement(By.id("auto_shortPass")).getText().contains(configFileReader.getShortPassMsg()));
		
	}
	
	
	/**************************************************************************
	//Test for short confirm password
	**************************************************************************/
	@Test(priority=29, enabled=true)
	public void SubscribeShortConfirmPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter password
		WebElement elemPass = driver.findElement(By.id("auto_pass_subscribe"));
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
				
		//Enter short confirm password
		WebElement elemConfirmPass = driver.findElement(By.id("autoConfirmPass"));	
		elemConfirmPass.findElement(By.tagName("input")).sendKeys(configFileReader.getShortPass());
		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto_shortPass")).getText().contains(configFileReader.getShortPassMsg()));

	}
	
	
	/**************************************************************************
	//Test for not matched password
	**************************************************************************/
	@Test(priority=30, enabled=true)
	public void SubscribeNotMatchedPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter password
		WebElement elemPass = driver.findElement(By.id("auto_pass_subscribe"));
		elemPass.findElement(By.tagName("input")).clear();
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
		
		//Enter wrong confirm password
		WebElement elemConfirmPass = driver.findElement(By.id("autoConfirmPass"));	
		elemConfirmPass.findElement(By.tagName("input")).clear();
		elemConfirmPass.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongPassText());	

		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto_passnotMatch")).getText().contains(configFileReader.getPassNotMatchedMsg()));

	}
	
	/**************************************************************************
	//Test for enter country
	**************************************************************************/
	@Test(priority=31, enabled=true)
	public void SubscribeEnterCountryTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		Select country = new Select(driver.findElement(By.id("auto_select_country")));
		country.selectByValue("Macedonia");
		
	}
	
	
	/**************************************************************************
	//Test for Select Gender check box
	**************************************************************************/
	@Test(priority=32, enabled=true)
	public void SubscribeSelectTermsPrivacyCheckBoxTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-subscription/streann-second-step/form/div[4]/div[3]/div/div/div[1]/label")).click();
	}
	
	/**************************************************************************
	//Test for Enter First Name
	**************************************************************************/
	@Test(priority=33, enabled=true)
	public void SubscribeEnterFirstNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
	}
	
	/**************************************************************************
	//Test for Enter Last Name
	**************************************************************************/
	@Test(priority=34, enabled=true)
	public void SubscribeEnterLastNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement lastName = driver.findElement(By.id("auto_lastName"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
	}

	/**************************************************************************
	//Test for Enter Future Date
	**************************************************************************/
	@Test(priority=35, enabled=false)
	public void SubscribeFutureDateTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Enter date in future
		WebElement elemDate = driver.findElement(By.id("auto_birthday"));
		elemDate.clear();
		elemDate.sendKeys(configFileReader.getFutureDate());
		
		//Click on Register button
		driver.findElement(By.id("auto_button_cont")).click();
		WebElement dateMessage = driver.findElement(By.id("auto_doNotMatch"));
		String futureDateMsg = dateMessage.findElement(By.tagName("div")).getText();
		Assert.assertTrue(futureDateMsg.contains(configFileReader.getDateNotValid()));

	}
	

	
	
	/**************************************************************************
	//Test for subscribe already existed user
	**************************************************************************/
	@Test(priority=36, enabled=true)
	public void SubscribeExistedUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter first name again
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
		//Enter Last name again
		WebElement lastName = driver.findElement(By.id("auto_lastName"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
		
		//Enter existed mail again
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		//Enter existed confirm mail again
		WebElement elemConfirmEmail = driver.findElement(By.id("auto_email_confirmEmail"));
		elemConfirmEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter password
		WebElement elemPass = driver.findElement(By.id("auto_pass_subscribe"));
		elemPass.findElement(By.tagName("input")).clear();
		elemPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());
		//Enter wrong confirm password
		WebElement elemConfirmPass = driver.findElement(By.id("autoConfirmPass"));	
		elemConfirmPass.findElement(By.tagName("input")).clear();
		elemConfirmPass.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectPassText());

		//Enter phone
		WebElement elemPhone = driver.findElement(By.id("auto_phone"));
		elemPhone.findElement(By.tagName("input")).sendKeys("123454321");
		
		//Select gender
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-subscription/streann-second-step/form/div[4]/div[3]/div/div/div[1]/label")).click();
		
		//Enter Address (Direction)
		WebElement elemDirection = driver.findElement(By.id("auto_address"));
		elemDirection.findElement(By.tagName("input")).sendKeys("street");
		
		//Enter City
		WebElement elemCity = driver.findElement(By.id("auto_city"));
		elemCity.findElement(By.tagName("input")).sendKeys("city");
		
		//Enter State (Provincia)
		WebElement elemState = driver.findElement(By.id("auto_state"));
		elemState.findElement(By.tagName("input")).sendKeys("state");
		
		//Enter Country
		Select country = new Select(driver.findElement(By.id("auto_select_country")));
		country.selectByValue("Macedonia");

		driver.findElement(By.id("auto_button_cont")).click();
		
		//Check message for already existed user
		Thread.sleep(configFileReader.getSleepTime());
		WebElement elemToast = driver.findElement(By.id("auto_useralreadyexist"));
		elemToast.findElement(By.tagName("div"));
	}	
	
	
	/**************************************************************************
	//Test for creating new user
	**************************************************************************/
	@Test(priority=37, enabled=true)
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
		
		//Set new confirm mail
		WebElement elemConfirmEmail = driver.findElement(By.id("auto_email_confirmEmail"));
		WebElement elemInputConfirmMail = elemConfirmEmail.findElement(By.tagName("input"));
		elemInputConfirmMail.clear();
		elemInputConfirmMail.sendKeys(newUser);
				
		//Click on Register button
		driver.findElement(By.id("auto_button_cont")).click();

		//Check is the new user logged?
		driver.findElement(By.id("auto_dropdown")).click();
		Assert.assertEquals(driver.findElement(By.id("auto_logout")).getText(), configFileReader.getLogoutText());
	}	
	
	

	
	@AfterTest
	public void Finilize() throws InterruptedException {
		Thread.sleep(configFileReader.getSleepTime());
		driver.close();
	}
	

}
