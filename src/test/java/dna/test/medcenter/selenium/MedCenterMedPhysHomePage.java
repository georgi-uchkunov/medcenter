package dna.test.medcenter.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedCenterMedPhysHomePage{
	
	@FindBy(id = "email")
	WebElement emailInput;
	
	@FindBy(id = "phone-number")
	WebElement phoneNumberInput;
	
	@FindBy(id = "first-name")
	WebElement firstNameInput;
	
	@FindBy(id = "last-name")
	WebElement lastNameInput;
	
	@FindBy(id = "male-radio")
	WebElement maleRadio;
	
	@FindBy(id = "dna")
	WebElement dnaInput;
	
	@FindBy(id = "address")
	WebElement addressInput;
	
	@FindBy(id = "symptom")
	WebElement symptomInput;
	
	@FindBy(id = "do-test")
	WebElement dnaTestButton;
	
	@FindBy(className = "submit")
	WebElement submitButton;
	
	@FindBy(className = "firstNext")
	WebElement firstNextButton;
	
	@FindBy(className = "second-next")
	WebElement secondNextButton;
	
	@FindBy(className = "third-next")
	WebElement thirdNextButton;
	
	@FindBy(id = "search-bar")
	WebElement searchInput;
	
	@FindBy(id = "search-button")
	WebElement searchButton;
	
	@FindBy(id = "do-test-modal")
	WebElement doTestModal;
	
	public boolean isDoTestModalExists() {
		return null != doTestModal;
	}
	
	public boolean isSearchInputExists() {
		return null != searchInput;
	}
	
	public void useEmailForm(final String email) {
		emailInput.sendKeys(email);
	}
	
	public void usePhoneNumberForm(final String phoneNumber) {
		phoneNumberInput.sendKeys(phoneNumber);
	}
	
	public void useFirstNameForm(final String firstName) {
		firstNameInput.sendKeys(firstName);
	}
	
	public void useLastNameForm(final String lastName) {
		lastNameInput.sendKeys(lastName);
	}
	
	public void useDnaForm(final String dna) {
		dnaInput.sendKeys(dna);
	}
	
	public void useSymptomForm(final String symptom) {
		symptomInput.sendKeys(symptom);
	}
	
	public void useAddressForm(final String address) {
		addressInput.sendKeys(address);
	}
	
	public void clickMaleRadio() {
		maleRadio.click();
	}
	
	public void useSearchForm(final String search) {
		searchInput.sendKeys(search);
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	public void clickFirstNextButton() {
		firstNextButton.click();
	}
	
	public void clickSecondNextButton() {
		secondNextButton.click();
	}
	
	public void clickThirdNextButton() {
		thirdNextButton.click();
	}
	
	public void clickSearchTestButton() {
		searchButton.click();
	}
	
	public void clickDnaTestButton() {
		dnaTestButton.click();
	}
}