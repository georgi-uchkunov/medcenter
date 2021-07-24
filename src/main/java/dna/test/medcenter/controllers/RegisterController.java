package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * View controller to enable reaching the registration page. Access available to all
 * {@link Role}
 */
@Controller
public class RegisterController {

	@GetMapping("/register")
    public String redirectToRegisterPage(){
        return "/views/register.html";
    }
}