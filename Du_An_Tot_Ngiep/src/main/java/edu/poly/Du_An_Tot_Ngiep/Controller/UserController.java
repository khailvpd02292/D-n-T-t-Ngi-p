package edu.poly.Du_An_Tot_Ngiep.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	public HttpSession session;
	
	@GetMapping(value = "/login")
	public String login(ModelMap model) {
		return "/login/login1";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap model) {

		if (userService.findByName(email).isPresent()) {
			User users = userService.findByName(email).get();
			if (users.getPassword().equals(password)) {
				if (users.isRole() == false) {
					session.setAttribute("accLoginC", users);
					return "redirect:/manager";
				}else {
					session.setAttribute("accLoginC", users);
					return "redirect:/index";
				}
			} else {
//				model.addAttribute("invalid", true);
				return "login/login1";
			}

		}
		return "login/login1";
	}
	@GetMapping("/logout")
	public String getlogout(ModelMap model) {
		session.removeAttribute("accLoginC");
		return "login/login";
	}
}
