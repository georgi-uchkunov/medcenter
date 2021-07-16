package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic view controller to enable reaching the landing page
 */
@Controller
public class IndexController {

	@GetMapping("/")
    public String redirectToIndexPage(){
        return "/views/index.html";
    }
}