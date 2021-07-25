package dna.test.medcenter.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
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

public class MedCenterMedPhysHomePageSeleniumTest {

	private static WebDriver driver;
	private MedCenterMedPhysHomePage medPhysHomePage;

	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Before
	public void setup() {
		driver.get("http://localhost:8080/");
		medPhysHomePage = new MedCenterMedPhysHomePage();
		driver.manage().window().maximize();
		WebElement usernameInput = driver.findElement(By.id("username"));
		usernameInput.sendKeys("doctor01");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.sendKeys("doctor01");
		WebElement loginButton = driver.findElement(By.id("login"));
		loginButton.click();

		PageFactory.initElements(driver, medPhysHomePage);
	}

	@Test
	public void testIsRegistrationPageOpen() {
		assertTrue(medPhysHomePage.isSearchInputExists());
		medPhysHomePage.clickDnaTestButton();
		assertTrue(medPhysHomePage.isDoTestModalExists());
	}

	@Test
	public void testDoesPositiveEmailInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useEmailForm("obi@jedi.com");
		Thread.sleep(4000);
		medPhysHomePage.phoneNumberInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.emailInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeEmailInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useEmailForm("obi");
		Thread.sleep(4000);
		medPhysHomePage.phoneNumberInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.emailInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositiveFirstNameInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.useFirstNameForm("Obi-Wan");
		Thread.sleep(4000);
		medPhysHomePage.lastNameInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.firstNameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeFirstNameInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.useFirstNameForm("O");
		Thread.sleep(4000);
		medPhysHomePage.lastNameInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.firstNameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}

	@Test
	public void testDoesPositiveLastNameInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.useLastNameForm("Kenobi");
		Thread.sleep(4000);
		medPhysHomePage.firstNameInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.lastNameInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeLastNameInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.useLastNameForm("K");
		Thread.sleep(4000);
		medPhysHomePage.firstNameInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.lastNameInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}

	@Test
	public void testDoesPositiveAddressInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useAddressForm("Tatooine");
		Thread.sleep(4000);
		medPhysHomePage.clickMaleRadio();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.addressInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeAddressInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useAddressForm("1");
		Thread.sleep(4000);
		medPhysHomePage.clickMaleRadio();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.addressInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}

	@Test
	public void testDoesPositiveDnaInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickThirdNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useDnaForm("ATGC");
		Thread.sleep(4000);
		medPhysHomePage.symptomInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.dnaInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeDnaInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickThirdNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useDnaForm("dna");
		Thread.sleep(4000);
		medPhysHomePage.symptomInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.dnaInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}

	@Test
	public void testDoesPositiveSymptomInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickThirdNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useSymptomForm("Muscle pain");
		Thread.sleep(4000);
		medPhysHomePage.dnaInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.symptomInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}

	@Test
	public void testDoesNegativeSymptomInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickSecondNextButton();
		Thread.sleep(4000);
		medPhysHomePage.clickThirdNextButton();
		Thread.sleep(4000);
		medPhysHomePage.useSymptomForm("1");
		Thread.sleep(4000);
		medPhysHomePage.dnaInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.symptomInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}
	
	@Test
	public void testDoesPositivePhoneInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.usePhoneNumberForm("881231237");
		Thread.sleep(4000);
		medPhysHomePage.emailInput.click();
		Thread.sleep(4000);
		String positiveFeedback = medPhysHomePage.phoneNumberInput.getCssValue("border-color");
		assertNotNull(positiveFeedback);
		assertEquals("rgb(67, 204, 41)", positiveFeedback);

	}
	
	@Test
	public void testDoesNegativePhoneInputFeedbackWork() throws InterruptedException {
		medPhysHomePage.clickDnaTestButton();
		medPhysHomePage.clickFirstNextButton();
		Thread.sleep(4000);
		medPhysHomePage.usePhoneNumberForm("1");
		Thread.sleep(4000);
		medPhysHomePage.emailInput.click();
		Thread.sleep(4000);
		String negativeFeedback = medPhysHomePage.phoneNumberInput.getCssValue("border-color");
		assertNotNull(negativeFeedback);
		assertEquals("rgb(229, 37, 39)", negativeFeedback);
	}

	@Test
	public void testDoesLogoutLinkWork() throws InterruptedException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement logoutLink = driver.findElement(By.id("logout"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", logoutLink);
		Thread.sleep(4000);
		logoutLink.click();
		Thread.sleep(1000);
		String currentPage = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/", currentPage);
	}
	
	@Test
	public void testDoesSearchWork() throws InterruptedException {
		medPhysHomePage.useSearchForm("bob");
		medPhysHomePage.clickSearchTestButton();
		Thread.sleep(4000);
		List<WebElement> tests = driver.findElements(By.className("list-group-item"));
		List<String> patientNames = new ArrayList<>();
		for(int i = 0; i < tests.size(); i++) {
			String patientName = tests.get(i).findElement(By.className("patient-name")).getText();
			patientNames.add(patientName);
		}
		for(int i = 0; i < patientNames.size(); i++) {
			String patientName = patientNames.get(i);
			if(patientName.contentEquals("Bob Jones")) {
				boolean nameCheck = true;
				assertTrue(nameCheck);
				
			}
		}
	}

	@AfterClass
	public static void stopDriver() {
		driver.close();
	}

}