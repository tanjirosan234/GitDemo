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

import dataProvider.ConfigFileReaderEuropa;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsEuropa {

	WebDriver driver;
	String browserId;
	ConfigFileReaderEuropa configFileReader= new ConfigFileReaderEuropa();
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
	
	/**************************************************************************
	  Test for changing language
	 **************************************************************************/
	@Test(priority=1, enabled=true)
	public void ChangeLanguageTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Click on language triangle
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/div")).click();
		//Select English language
		driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/div/div/div/a[1]")).click();
	}
	
	
/**************************************	
***************************************
****Set of tests for Login page********
***************************************
**************************************/	

	/**************************************************************************
	  Test for starting logging page
	 **************************************************************************/
	@Test(priority=11, enabled=true)
	public void StartLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		//Select Login link
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-landing/nav/div/div/a[3]")).click();
	}
	
	/**************************************************************************
	  Test for checking logging page
	 **************************************************************************/
	@Test(priority=12, enabled=true)
	public void CheckLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Check is the email field available
		driver.findElement(By.id("auto_emailLogin"));
		//Check is the password field available
		driver.findElement(By.id("auto_passlogin"));
		//Check is the check box "Remember me" available
		driver.findElement(By.id("auto_rememberMe"));
		//Check is the Button "Forgot password?" available
		driver.findElement(By.id("auto_loginforgerPass"));
		//Check is the Button "Sign In" available
		driver.findElement(By.id("auto_loginButton"));

	}
	
	/**************************************************************************
	Test for checking login with empty EMail
	***************************************************************************/
	@Test(priority=13, enabled=true)
	public void EmptyMailLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear email field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).clear();
		//Fill password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.sendKeys(configFileReader.getCorrectPassText());	
		
		//Click on the Button "Log In"
		driver.findElement(By.id("auto_loginButton")).click();
			
		//Check message that need user to be entered
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	Test for checking login with empty Password
	***************************************************************************/
	@Test(priority=14, enabled=true)
	public void EmptyPasswordLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.navigate().refresh();
		
		//Fill email field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());;
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.id("auto_loginButton")).click();
			
		//Check message that need password to be entered
		Assert.assertTrue(driver.findElement(By.id("auto_loginRequiredfield")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	Test for checking login with empty EMail and Password
	***************************************************************************/
	@Test(priority=15, enabled=true)
	public void EmptyMailPasswordLoginTest() {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.navigate().refresh();
		
		//Clear email field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).clear();
		//Clear password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.clear();
			
		//Click on the Button "Log In"
		driver.findElement(By.id("auto_loginButton")).click();
			
		//Check message that need user to be entered
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message that need password to be entered
		Assert.assertTrue(driver.findElement(By.id("auto_loginRequiredfield")).getText().contains(configFileReader.getFieldRequiredText()));
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and correct password
	 **************************************************************************/
	@Test(priority=16, enabled=true)
	public void WrongUserCorrectPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter wrong user field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.sendKeys(configFileReader.getCorrectPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto_loginButton")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto_loginDanger")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking login with correct user and wrong password
	 **************************************************************************/
	@Test(priority=17, enabled=true)
	public void CorrectUserWrongPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter correct user field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter wrong password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.sendKeys(configFileReader.getWrongPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto_loginButton")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto_loginDanger")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking login with wrong user and password
	 **************************************************************************/
	@Test(priority=18, enabled=true)
	public void WrongUserWrongPasswordLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.navigate().refresh();
		
		//Enter wrong user field
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getWrongUserText());
		
		//Enter wrong password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.sendKeys(configFileReader.getWrongPassText());
			
		//Click on the Button "Log In" 
		driver.findElement(By.id("auto_loginButton")).click();
		
		Thread.sleep(configFileReader.getSleepTime());
		//Check message that informs wrong user/pass are entered
		Assert.assertTrue(driver.findElement(By.id("auto_loginDanger")).getText().contains(configFileReader.getIncorrectUserPassMsgText()));
	}
	
	/**************************************************************************
	//Test for checking successful login with correct user and password
	**************************************************************************/
	@Test(priority=19, enabled=true)
	public void SuccessfulLoginTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Refresh the login page 
		driver.navigate().refresh();
			
		//Enter correct user field 
		WebElement loginField = driver.findElement(By.id("auto_emailLogin"));
		loginField.findElement(By.tagName("input")).sendKeys(configFileReader.getCorrectUserText());
		
		//Enter correct password field 
		WebElement passwordField = driver.findElement(By.id("auto_passlogin"));
		passwordField.sendKeys(configFileReader.getCorrectPassText());

		//Click on the Button "Log In" 
		driver.findElement(By.id("auto_loginButton")).click();

		//Check is Profile page opened?
		driver.findElement(By.id("auto_selectProfileTitle"));
		
	}
	
	
	/**************************************************************************
	//Test for select profile
	**************************************************************************/
	@Test(priority=20, enabled=true)
	public void SelectProfileTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

		//Select second profile
		driver.findElement(By.xpath("//img[contains(@src,'avatar')]")).click();

		//Check is logged?
		driver.findElement(By.id("auto-login-avatar-face"));
		
	}	
	
	/**************************************************************************
	//Test for checking My Account
	**************************************************************************/
	@Test(priority=21, enabled=true)
	public void MyAccountTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Open My Account page
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto_userAccount")).click();
		
		//Check Account name
		Thread.sleep(configFileReader.getSleepTime());
		WebElement elem = driver.findElement(By.id("auto_email_admin"));
		WebElement parentElem = elem.findElement(By.xpath("./.."));
		//Assert.assertTrue(driver.findElement(By.id("auto_email_admin")).getText().contains(configFileReader.getCorrectUserText()));
		Assert.assertTrue(parentElem.getText().contains(configFileReader.getCorrectUserText()));
	}
	
	/**************************************************************************
	//Test for checking Log out
	**************************************************************************/
	@Test(priority=22, enabled=true)
	public void LogoutTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Click on logout
		driver.findElement(By.id("auto-login-avatar-face")).click();
		driver.findElement(By.id("auto_logout")).click();
		
		//Check is logged?
		driver.findElement(By.id("auto_register"));
		driver.findElement(By.id("auto_login"));
		driver.findElement(By.id("auto_dropdownLang"));

	}	

	/**************************************************************************
	//Test for checking successful login with FaceBook
	//EXCLUDED FROM LIST OF ALL TESTS
	**************************************************************************/
	@Test(priority=23, enabled=false)
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
	@Test(priority=24, enabled=false)
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
	@Test(priority=31, enabled=true)
	public void StartForgotPasswordTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		driver.get(configFileReader.getApplicationUrl());
		//driver.navigate().refresh();
		
		//Click on login
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-landing/nav/div/div/a[3]")).click();
		
		//Click on Forgot password link button
		driver.findElement(By.id("auto_loginforgerPass")).click();
		
		//Go back
		//driver.navigate().back();
		
		//Click again on Forgot password link button
		//driver.findElement(By.id("auto_loginforgerPass")).click();
	}
	
	/**************************************************************************
	//Test for checking sending empty mail
	**************************************************************************/	
	@Test(priority=32, enabled=true)
	public void EmptyMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-cont")).click();
			
		//Check message for empty mail
		Assert.assertEquals(driver.findElement(By.id("auto_fieldRequired")).getText(), configFileReader.getFieldRequiredText());
	
	}
		
	
	/**************************************************************************
	//Test for checking sending wrong format of mail
	**************************************************************************/	
	@Test(priority=33, enabled=true)
	public void WrongFormatMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Enter bad formated user or mail field
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getBadFormatedMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-cont")).click();
			
		//Check message for empty mail
		Assert.assertEquals(driver.findElement(By.id("auto_emailNotValid")).getText(), configFileReader.getNotValidMailText());
	
	}
	
	
	/**************************************************************************
	//Test for checking sending not existing of mail
	**************************************************************************/
	@Test(priority=34, enabled=true)
	public void NotExistingMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Clear user or mail field for every case
		WebElement elemEmail = driver.findElement(By.id("auto-test-forgotpass"));
		elemEmail.findElement(By.tagName("input")).clear();
		
		//Enter not existing user or mail field
		elemEmail.findElement(By.tagName("input")).sendKeys(configFileReader.getIncorrectMailText());
			
		//Click on Send Mail button
		driver.findElement(By.id("auto-test-cont")).click();
			
		Thread.sleep(configFileReader.getSleepTime());
		//Check message for not existed mail
		//Assert.assertEquals(driver.findElement(By.id("auto_userNotFound")).getText(), configFileReader.getIncorrectMailMsgText());
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/streann-root/div/streann-forgot-password/div")).getText().contains(configFileReader.getIncorrectMailMsgText()));
	}

	
	/**************************************************************************
	//Test for checking Cancel button 
	**************************************************************************/
	@Test(priority=35, enabled=true)
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
	@Test(priority=40, enabled=true)
	public void SubscribeButtonTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Register button from menu
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-landing/nav/div/div/a[2]")).click();
		//Click on back in browser
		Thread.sleep(configFileReader.getSleepTime());
		driver.navigate().back();
		//Click on Register button from menu again
		Thread.sleep(configFileReader.getSleepTime());
		driver.findElement(By.xpath("/html/body/streann-root/div/streann-landing/nav/div/div/a[2]")).click();
	}
	
	
	
	/**************************************************************************
	//Test for check all fields in subscribe page
	**************************************************************************/
	@Test(priority=41, enabled=true)
	public void CheckSubscribeFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Check first name
		driver.findElement(By.id("auto_firstName"));
		//Check last name
		driver.findElement(By.id("auto_surName"));
		//Check Country
		driver.findElement(By.id("cars"));
		//Check Phone
		driver.findElement(By.id("auto_phone"));
		//Check Email
		driver.findElement(By.id("auto_email"));		
		//Check Password
		driver.findElement(By.id("auto_pass"));
		//Check Confirm password
		driver.findElement(By.id("auto_confirmPass"));
		//Check Accept terms and Privacy
		driver.findElement(By.id("auto_check_privacy_policy"));
		//Check Accept receive mails
		driver.findElement(By.id("auto_email_subsribe"));
		//Check Continue to register button
		driver.findElement(By.id("auto-continue-btn"));
				
				
	}	
	
	/**************************************************************************
	//Test for subscribe all empty fields
	**************************************************************************/
	@Test(priority=42, enabled=true)
	public void SubscribeEmptyFieldsTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Click on Register button
		driver.findElement(By.id("auto-continue-btn")).click();

		//Check message for First Name
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Last Name
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Country
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Phone
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Email
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Confirm password
		Assert.assertTrue(driver.findElement(By.id("auto_fieldRequired")).getText().contains(configFileReader.getFieldRequiredText()));
		//Check message for Accept Terms and Privacy Check Box
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/streann-root/div/streann-subscription/streann-second-step/form/div[8]/div[1]/div/label")).getText().contains(configFileReader.getFieldCheckBoxText()));
				
	}
	
	
	/**************************************************************************
	//Test for subscribe bad formated mail
	**************************************************************************/
	@Test(priority=43, enabled=true)
	public void SubscribeBadFormatedMailTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		//driver.navigate().refresh();
		
		//Enter bad formated mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.sendKeys(configFileReader.getBadFormatedMailText());

		//Check message for Email
		//Assert.assertTrue(elemEmail.findElement(By.id("auto_emailNotValid")).getText().contains(configFileReader.getNotValidMailText()));
		Assert.assertTrue(elemEmail.findElement(By.xpath("/html/body/streann-root/div/streann-subscription/streann-second-step/form/div[4]/div")).getText().contains(configFileReader.getNotValidMailText()));
	}
	
	
	
	/**************************************************************************
	//Test for short password NOT IMPLEMENTED
	**************************************************************************/
	@Test(priority=44, enabled=false)
	public void SubscribeShortPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter short password
		driver.findElement(By.id("auto-subscription-password")).sendKeys(configFileReader.getShortPass());
		
		//Check message for Password
		System.out.println(driver.findElement(By.id("shortPassword-autotest")).getText());
		Assert.assertTrue(driver.findElement(By.id("shortPassword-autotest")).getText().contains(configFileReader.getShortPassMsg()));
		
	}
	
	/**************************************************************************
	//Test for short confirm password NOT IMPLEMENTED
	**************************************************************************/
	@Test(priority=45, enabled=false)
	public void SubscribeShortConfirmPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Enter good pass
		driver.findElement(By.id("auto_pass")).sendKeys(configFileReader.getCorrectPassText());
				
		//Enter short confirm password
		driver.findElement(By.id("auto_confirmPass")).sendKeys(configFileReader.getShortPass());	

		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("shortPassword-autotest")).getText().contains(configFileReader.getShortPassMsg()));

	}
	
	
	/**************************************************************************
	//Test for not matched password
	**************************************************************************/
	@Test(priority=46, enabled=true)
	public void SubscribeNotMatchedPasswordTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		driver.findElement(By.id("auto_pass")).clear();
		driver.findElement(By.id("auto_pass")).sendKeys(configFileReader.getCorrectPassText());
		//Enter wrong confirm password
		driver.findElement(By.id("auto_confirmPass")).clear();
		driver.findElement(By.id("auto_confirmPass")).sendKeys(configFileReader.getWrongPassText());	

		//Check message for Confirm Password
		Assert.assertTrue(driver.findElement(By.id("auto_passNotMatch")).getText().contains(configFileReader.getPassNotMatchedMsg()));

	}
	
	/**************************************************************************
	//Test for enter country
	**************************************************************************/
	@Test(priority=47, enabled=true)
	public void SubscribeEnterCountryTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		Select country = new Select(driver.findElement(By.id("cars")));
		country.selectByValue("Macedonia");
		
	}
	
	
	/**************************************************************************
	//Test for Select Terms and Privacy check box
	**************************************************************************/
	@Test(priority=48, enabled=true)
	public void SubscribeSelectTermsPrivacyCheckBoxTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement elem = driver.findElement(By.id("auto_check_privacy_policy"));
		elem.findElement(By.tagName("span")).click();
	}
	
	/**************************************************************************
	//Test for Enter First Name
	**************************************************************************/
	@Test(priority=49, enabled=true)
	public void SubscribeEnterFirstNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
	}
	
	/**************************************************************************
	//Test for Enter Last Name
	**************************************************************************/
	@Test(priority=50, enabled=true)
	public void SubscribeEnterLastNameTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		WebElement lastName = driver.findElement(By.id("auto_surName"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
	}
	
	
	/**************************************************************************
	//Test for subscribe already existed user
	**************************************************************************/
	@Test(priority=51, enabled=true)
	public void SubscribeExistedUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			
		//Refresh the login page 
		driver.navigate().refresh();
		
		//Enter first name again
		WebElement firstName = driver.findElement(By.id("auto_firstName"));
		firstName.findElement(By.tagName("input")).sendKeys(configFileReader.getFirstName());
		//Enter Last name again
		WebElement lastName = driver.findElement(By.id("auto_surName"));
		lastName.findElement(By.tagName("input")).sendKeys(configFileReader.getLastName());
		//Enter country
		Select country = new Select(driver.findElement(By.id("cars")));
		country.selectByValue("Macedonia");
		//Enter Phone
		WebElement phone = driver.findElement(By.id("auto_phone"));
		phone.findElement(By.tagName("input")).sendKeys("1111111");
		//Enter existed mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.sendKeys(configFileReader.getCorrectUserText());
		//Enter good pass
		driver.findElement(By.id("auto_pass")).sendKeys(configFileReader.getCorrectPassText());
		//Enter good confirm pass
		driver.findElement(By.id("auto_confirmPass")).sendKeys(configFileReader.getCorrectPassText());
		
		//Select Accept Terms of use and Privacy
		WebElement elemAccept = driver.findElement(By.id("auto_check_privacy_policy"));
		elemAccept.findElement(By.tagName("span")).click();
		
		//Click on Register button
		driver.findElement(By.id("auto-continue-btn")).click();
		
		//Check message for already existed user
		Thread.sleep(configFileReader.getSleepTime());
		driver.findElement(By.id("toast-container"));
		
	}	
	
	
	/**************************************************************************
	//Test for creating new user
	**************************************************************************/
	@Test(priority=52, enabled=true)
	public void SubscribeCreateNewUserTest() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
		
		//Set value for new user
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String newUser = dtf.format(now) + configFileReader.getAutoMailExtText();
		
		//Set new mail
		WebElement elemEmail = driver.findElement(By.id("auto_email"));
		elemEmail.clear();
		elemEmail.sendKeys(newUser);
		
		//Click on Register button
		driver.findElement(By.id("auto-continue-btn")).click();

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
