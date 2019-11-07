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
//	@Autowired
//	public HttpSession session;
	
	@GetMapping(value = "/login")
	public String login(ModelMap model) {
		return "/login/login1";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap model, HttpSession session) {
// find account
		if (userService.findByName(email).isPresent()) {
			User users = userService.findByName(email).get();
//			session.setAttribute("account", users);
			if (users.getPassword().equals(password)) {
				if (users.isRole() == false) {
					session.setAttribute("account", users);
					return "redirect:/manager";
				}else {
					session.setAttribute("account", users);
					return "redirect:/index";
				}
			} else {
//				model.addAttribute("invalid", true);
				return "login/login1";
			}

		}
		return "login/login1";
	}
//	@GetMapping("/logout")
//	public String getlogout(ModelMap model) {
//		session.removeAttribute("accLoginC");
//		return "login/login1";
//	}
	
	
	@GetMapping(value = "/manager/listUser")
	public String listProduct(ModelMap model) {
		model.addAttribute("listuser", this.userService.findAll());
		return "/manager/users/listUser";
	}
}
