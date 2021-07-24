package dna.test.medcenter.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedCenterLandingPage{
	
	@FindBy(id = "username")
	WebElement usernameInput;
	
	@FindBy(id = "password")
	WebElement passwordInput;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(id = "login-card")
	WebElement loginCard;
	
	@FindBy(id = "register-link")
	WebElement registerLink;
	
	public boolean isLoginCardExists() {
		return null != loginCard;
	}
	
	public void useUsernameForm(final String username) {
		usernameInput.sendKeys(username);
	}
	
	public void usePasswordForm(final String password) {
		passwordInput.sendKeys(password);
	}
	
	public void clickRegisterLink() {
		registerLink.click();
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
}