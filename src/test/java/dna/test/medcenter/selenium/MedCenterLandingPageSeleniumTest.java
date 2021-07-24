package dna.test.medcenter.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class MedCenterLandingPageSeleniumTest{
	
	private static WebDriver driver;
	private MedCenterLandingPage landingPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Before
	public void setup() {
		driver.get("http://localhost:8080/");
		landingPage = new MedCenterLandingPage();

		PageFactory.initElements(driver, landingPage);
	}
	
	@Test
	public void testIsLandingPageOpen() {
		assertTrue(landingPage.isLoginCardExists());
	}
	
	@Test
	public void testDoesPositiveUsernameInputFeedbackWork() throws InterruptedException {
		landingPage.useUsernameForm("AliceS");
		Thread.sleep(4000);
		landingPage.clickLoginButton();
		Thread.sleep(4000);
		String positiveFeedback = landingPage.usernameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeUsernameInputFeedbackWork() throws InterruptedException {
		landingPage.useUsernameForm("A");
		Thread.sleep(4000);
		landingPage.clickLoginButton();
		Thread.sleep(4000);
		String negativeFeedback = landingPage.usernameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositivePasswordInputFeedbackWork() throws InterruptedException {
		landingPage.usePasswordForm("test1");
		Thread.sleep(4000);
		landingPage.clickLoginButton();
		Thread.sleep(4000);
		String positiveFeedback = landingPage.passwordInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
	}
	
	@Test
	public void testDoesNegativePasswordInputFeedbackWork() throws InterruptedException {
		landingPage.usePasswordForm("tes");
		Thread.sleep(4000);
		landingPage.clickLoginButton();
		Thread.sleep(4000);
		String negativeFeedback = landingPage.passwordInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesRegisterLinkWork() throws InterruptedException {
		WebElement registerLink = driver.findElement(By.linkText("register"));
		registerLink.click();
		Thread.sleep(1000);
		String currentPage = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/register", currentPage);
	}
	
	@Test
	public void testDoesLoginWork() throws InterruptedException {
		landingPage.useUsernameForm("AliceS");
		Thread.sleep(4000);
		landingPage.usePasswordForm("test1");
		Thread.sleep(4000);
		landingPage.clickLoginButton();
		Thread.sleep(4000);
		String currentPage = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/home_patient", currentPage);
		WebElement userGreeting = driver.findElement(By.id("username-nav"));
		assertEquals("Hello, AliceS", userGreeting.getText());
		
		
	}
	
	@AfterClass
	public static void stopDriver() {
		driver.close();
	}
	
	
}