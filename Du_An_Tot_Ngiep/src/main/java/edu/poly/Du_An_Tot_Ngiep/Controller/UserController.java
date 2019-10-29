package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
//
//	@Autowired
//	private 
	@GetMapping(value = "/login")
	public String login() {
		return "/login/login1";
	}
	
	
	
}
