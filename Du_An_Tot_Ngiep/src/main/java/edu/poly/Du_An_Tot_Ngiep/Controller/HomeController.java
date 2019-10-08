package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {
	
	@GetMapping(value = "/index")
	public String testHome() {
		return "/index";
	}
}
