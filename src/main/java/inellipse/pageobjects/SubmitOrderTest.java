package inellipse.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import inellipse.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
 String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();

				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox"); // Bypass OS security model
				options.addArguments("--remote-allow-origins=*");
				WebDriver driver = new ChromeDriver(options);
				driver.manage().window().maximize();
 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.get("https://rahulshettyacademy.com/client");
//login
LandingPage landingPage = new LandingPage(driver);
driver.findElement(By.id("userEmail")).sendKeys("vihynyr@getnada.com");
driver.findElement(By.id("userPassword")).sendKeys("Irakoski123");
driver.findElement(By.id("login")).click();
WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

//add product to the cart
WebElement prod = products.stream().filter(product-> 
product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
Assert.assertTrue(match);
driver.findElement(By.cssSelector(".totalRow button")).click();
//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("mace");
//Thread.sleep(3000);
//List<WebElement> options1 =driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
//for(WebElement option :options1)
//{
//	 if(option.getText().equalsIgnoreCase("Macedonia, The Former Yugoslav Republic of"))
//			 {
//		 option.click();
//		 break;
//			 }
//	 driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();

Actions a = new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country'")), "india").build().perform();

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
driver.findElement(By.cssSelector(".action__submit")).click();

String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.close();
}

	}


