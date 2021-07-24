package dna.test.medcenter.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedCenterRegistrationPage{
	
	@FindBy(id = "username")
	WebElement usernameInput;
	
	@FindBy(id = "password")
	WebElement passwordInput;
	
	@FindBy(id = "email")
	WebElement emailInput;
	
	@FindBy(id = "first-name")
	WebElement firstNameInput;
	
	@FindBy(id = "last-name")
	WebElement lastNameInput;
	
	@FindBy(id = "male-radio")
	WebElement maleRadio;
	
	@FindBy(id = "address")
	WebElement addressInput;
	
	@FindBy(id = "register")
	WebElement registerButton;
	
	@FindBy(id = "register-card")
	WebElement registerCard;
	
	public boolean isRegisterCardExists() {
		return null != registerCard;
	}
	
	public void useUsernameForm(final String username) {
		usernameInput.sendKeys(username);
	}
	
	public void usePasswordForm(final String password) {
		passwordInput.sendKeys(password);
	}
	
	public void useEmailForm(final String email) {
		emailInput.sendKeys(email);
	}
	
	public void useFirstNameForm(final String firstName) {
		firstNameInput.sendKeys(firstName);
	}
	
	public void useLastNameForm(final String lastName) {
		lastNameInput.sendKeys(lastName);
	}
	
	public void useAddressForm(final String address) {
		addressInput.sendKeys(address);
	}
	
	public void clickMaleRadio() {
		maleRadio.click();
	}
	
	public void clickRegisterButton() {
		registerButton.click();
	}
}