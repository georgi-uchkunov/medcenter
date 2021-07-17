package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic view controller to enable reaching the home page for users with the MED_PHYSICIAN role
 */
@Controller
public class HomePhysicianController {

	@GetMapping("/home_physician")
    public String redirectToHomePhysicianPage(){
        return "/views/home_physician.html";
    }
}