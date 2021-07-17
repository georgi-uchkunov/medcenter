package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic view controller to enable reaching the home page for users with the PATIENT role
 */
@Controller
public class HomePatientController {

	@GetMapping("/home_patient")
    public String redirectToHomePatientPage(){
        return "/views/home_patient.html";
    }
}