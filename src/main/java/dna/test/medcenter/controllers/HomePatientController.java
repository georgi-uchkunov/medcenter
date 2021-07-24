package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * View controller to enable reaching the home page for users with the "PATIENT"
 * {@link Role}
 */
@Controller
public class HomePatientController {

	@GetMapping("/home_patient")
	public String redirectToHomePatientPage() {
		return "/views/home_patient.html";
	}
}