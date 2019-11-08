package edu.poly.Du_An_Tot_Ngiep.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
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
			ModelMap model,HttpServletResponse response) {
// find account
		if (userService.findByName(email).isPresent()) {
			User users = userService.findByName(email).get();
			Cookie cookie = new Cookie("account", users.getEmail());
//			response.addCookie(cookie);
//			session.setAttribute("account", users.getEmail());
			if (users.getPassword().equals(password)) {
				if (users.isRole() == false) {
					response.addCookie(cookie);
//					System.out.println(cookie.getValue());
//					cookie.setHttpOnly(true);
//					model.addAttribute("useremail", cookie.getValue());
//					cookie.getName();
					
//					session.setAttribute("account", users.getEmail());
					return "redirect:/manager";
				}else {
					response.addCookie(cookie);
//					cookie.setHttpOnly(true);
//					session.setAttribute("account", users.getEmail());
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
        for(int i = 0; i< cookies.length ; ++i){
            if(cookies[i].getName().equals("account")){
                //Cookie cookie = new Cookie("user", cookies[i].getValue());
                //cookie.setMaxAge(0);
                //response.addCookie(cookie);
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
                break;
            }
        } 
		return "login/login1";
	}
	
	
	@GetMapping(value = "/manager/listUser")
	public String listProduct(ModelMap model, @CookieValue(value = "account") String username) {
		model.addAttribute("listuser", this.userService.findAll());
		model.addAttribute("username", username);
		return "/manager/users/listUser";
	}
}
