package dna.test.medcenter.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedCenterPatientHomePage{
	
	@FindBy(className = "patient-name")
	WebElement patientName;
	
	@FindBy(className = "list-group-item")
	WebElement patientInfoTemplate;
	
	@FindBy(id = "username-nav")
	WebElement usernameInNav;
	
	public boolean isPatientInfoTemplateExists() {
		return null != patientInfoTemplate;
	}
}