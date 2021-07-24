package dna.test.medcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * View controller to enable reaching the landing page. Access available to all
 * {@link Role}
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String redirectToIndexPage() {
		return "/views/index.html";
	}
}