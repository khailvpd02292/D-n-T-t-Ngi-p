package edu.poly.Du_An_Tot_Ngiep.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name="/index")
public class Config {
	
	
	@GetMapping("/index")
	public String index() {
		
		return "/index";
	}

}
