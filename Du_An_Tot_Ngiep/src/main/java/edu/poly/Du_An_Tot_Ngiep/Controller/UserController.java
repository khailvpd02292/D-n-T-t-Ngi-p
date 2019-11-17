package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String login(ModelMap model) {
		return "/login/login1";
	}

	void getName(HttpServletRequest request, ModelMap model) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				User user = this.userService.findByEmail(cookies[i].getValue()).get();
				model.addAttribute("fullname", user.getFullname());
				break;
			}
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap model,
			HttpServletResponse response) {
// find account
		if (userService.findByEmail(email).isPresent()) {
			User users = userService.findByEmail(email).get();
			Cookie cookie = new Cookie("account", users.getEmail());

			if (users.getPassword().equals(password)) {
				if (users.isRole() == false) {
					response.addCookie(cookie);
					cookie.setMaxAge(7 * 24 * 60 * 60);
//					model.addAttribute("fullname", users.getFullname());
					return "redirect:/manager";
				} else {
					response.addCookie(cookie);
//					model.addAttribute("fullname", users.getFullname());
					return "redirect:/index";
				}
			} else {
//				model.addAttribute("invalid", true);
				return "login/login1";
			}

		}
		return "login/login1";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
//		session.removeAttribute("accLoginC");

		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break;
			}
		}
		return "redirect:/login";
	}

	@GetMapping(value = "/manager/listUser")
	public String listProduct(ModelMap model, @CookieValue(value = "account") String username
			,HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("listuser", this.userService.findAll());
		model.addAttribute("username", username);
		getName(request, model);		
		return "/manager/users/listUser";
	}

	@GetMapping(value = "/registration")
	public String registration(ModelMap model) {
		model.addAttribute("registration", new User());
		return "/login/registred";
	}

	@PostMapping(value = "/registration")
	public String addProduct(@ModelAttribute(name = "registration") User registration, ModelMap model,
			@RequestParam boolean gender, @RequestParam Date birthday) {
		model.addAttribute("registration", new User());
		User usr = new User();
		usr.setAddress(registration.getAddress());
		usr.setBirthday(birthday);
		usr.setEmail(registration.getEmail());
		usr.setFullname(registration.getFullname());
		usr.setGender(gender);
		usr.setPassword(registration.getPassword());
		usr.setPhone(registration.getPhone());
		usr.setRole(true);
		userService.save(usr);
		return "redirect:login";
	}

	@GetMapping(value = "/manager/updateUser/{userId}")
	public String updateProduct(ModelMap model, @PathVariable(name = "userId") int id) {
		model.addAttribute("listuser", this.userService.findAll());
		model.addAttribute("usernameID",
				this.userService.findById(id).isPresent() ? this.userService.findById(id).get() : null);
		return "/manager/users/updateUser";
	}
	
	@PostMapping(value = "/manager/updateUser")
	public String updateProduct(@ModelAttribute(name = "usernameID") @Valid User usernameID, BindingResult result) {
		userService.save(usernameID);

		return "redirect:/manager/listUser";
	}

}
