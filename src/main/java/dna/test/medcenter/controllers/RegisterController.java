package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic view controller to enable reaching the register page
 */
@Controller
public class RegisterController {

	@GetMapping("/register")
    public String redirectToRegisterPage(){
        return "/views/register.html";
    }
}