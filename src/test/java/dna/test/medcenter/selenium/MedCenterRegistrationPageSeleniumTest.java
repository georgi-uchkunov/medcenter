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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MedCenterRegistrationPageSeleniumTest{
	
	private static WebDriver driver;
	private MedCenterRegistrationPage registrationPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Before
	public void setup() {
		driver.get("http://localhost:8080/register");
		registrationPage = new MedCenterRegistrationPage();

		PageFactory.initElements(driver, registrationPage);
	}
	
	@Test
	public void testIsRegistrationPageOpen() {
		assertTrue(registrationPage.isRegisterCardExists());
	}
	
	@Test
	public void testDoesPositiveUsernameInputFeedbackWork() throws InterruptedException {
		registrationPage.useUsernameForm("LukeS");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.usernameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeUsernameInputFeedbackWork() throws InterruptedException {
		registrationPage.useUsernameForm("L");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.usernameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositivePasswordInputFeedbackWork() throws InterruptedException {
		registrationPage.usePasswordForm("jedi1");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.passwordInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
	}
	
	@Test
	public void testDoesNegativePasswordInputFeedbackWork() throws InterruptedException {
		registrationPage.usePasswordForm("jed");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.passwordInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositiveEmailInputFeedbackWork() throws InterruptedException {
		registrationPage.useEmailForm("luke@jedi.com");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.emailInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeEmailInputFeedbackWork() throws InterruptedException {
		registrationPage.useEmailForm("luke");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.emailInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositiveFirstNameInputFeedbackWork() throws InterruptedException {
		registrationPage.useFirstNameForm("Luke");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.firstNameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeFirstNameInputFeedbackWork() throws InterruptedException {
		registrationPage.useFirstNameForm("L");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.firstNameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositiveLastNameInputFeedbackWork() throws InterruptedException {
		registrationPage.useLastNameForm("Skywalker");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.lastNameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeLastNameInputFeedbackWork() throws InterruptedException {
		registrationPage.useLastNameForm("S");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.lastNameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositiveAddressInputFeedbackWork() throws InterruptedException {
		registrationPage.useAddressForm("Tatooine");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String positiveFeedback = registrationPage.addressInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);
		
	}
	
	@Test
	public void testDoesNegativeAddressInputFeedbackWork() throws InterruptedException {
		registrationPage.useAddressForm("1");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String negativeFeedback = registrationPage.addressInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesLoginLinkWork() throws InterruptedException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement registerLink = driver.findElement(By.linkText("Login"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", registerLink);
		Thread.sleep(4000);
		registerLink.click();
		Thread.sleep(1000);
		String currentPage = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/", currentPage);
	}
	
	@Test
	public void testDoesRegisterWork() throws InterruptedException {
		registrationPage.useUsernameForm("LukeS");
		Thread.sleep(4000);
		registrationPage.usePasswordForm("jedi1");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		registrationPage.useEmailForm("luke@jedi.com");
		Thread.sleep(4000);
		registrationPage.useFirstNameForm("Luke");
		Thread.sleep(4000);
		registrationPage.useLastNameForm("Skywalker");
		Thread.sleep(4000);
		registrationPage.clickMaleRadio();
		Thread.sleep(4000);
		Select dayDropdown = new Select(driver.findElement(By.id("day")));
		dayDropdown.selectByValue("25");
		Select monthDropdown = new Select(driver.findElement(By.id("month")));
		monthDropdown.selectByValue("05");
		Select yearDropdown = new Select(driver.findElement(By.id("year")));
		yearDropdown.selectByValue("1977");
		registrationPage.useAddressForm("Tatooine");
		Thread.sleep(4000);
		registrationPage.clickRegisterButton();
		Thread.sleep(4000);
		String currentPage = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/home_patient", currentPage);
		WebElement userGreeting = driver.findElement(By.id("username-nav"));
		assertEquals("Hello, LukeS", userGreeting.getText());
		
		
	}
	
	@AfterClass
	public static void stopDriver() {
		driver.close();
	}
	
	
}