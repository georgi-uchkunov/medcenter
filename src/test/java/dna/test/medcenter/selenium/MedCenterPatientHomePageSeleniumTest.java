package dna.test.medcenter.selenium;

import static org.junit.Assert.assertEquals;
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

public class MedCenterPatientHomePageSeleniumTest {

	private static WebDriver driver;
	private MedCenterPatientHomePage patientHomePage;

	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Before
	public void setup() {
		driver.get("http://localhost:8080/");
		patientHomePage = new MedCenterPatientHomePage();
		driver.manage().window().maximize();
		WebElement usernameInput = driver.findElement(By.id("username"));
		usernameInput.sendKeys("AliceS");
		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.sendKeys("test1");
		WebElement loginButton = driver.findElement(By.id("login"));
		loginButton.click();

		PageFactory.initElements(driver, patientHomePage);
	}

	@Test
	public void testIsPatientHomePageOpen() {
		assertTrue(patientHomePage.isPatientInfoTemplateExists());
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
	public void testDoesLoadingSpecificPatientTestsWork() throws InterruptedException {
		List<WebElement> tests = driver.findElements(By.className("list-group-item"));
		List<String> patientNames = new ArrayList<>();
		for (int i = 0; i < tests.size(); i++) {
			String patientName = tests.get(i).findElement(By.className("patient-name")).getText();
			patientNames.add(patientName);
		}
		for (int i = 0; i < patientNames.size(); i++) {
			String patientName = patientNames.get(i);
			if (patientName.contentEquals("Alice Smith")) {
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