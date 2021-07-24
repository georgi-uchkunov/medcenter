package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * View controller to enable reaching the home page for users with the
 * "MED_PHYS" {@link Role} Access denied to other roles
 */
@Controller
public class HomePhysicianController {

	@GetMapping("/home_physician")
	public String redirectToHomePhysicianPage() {
		return "/views/home_physician.html";
	}
}