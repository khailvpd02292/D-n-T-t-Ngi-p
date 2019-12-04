package edu.poly.Du_An_Tot_Ngiep.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.StatisticalService;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@Controller
public class statisticalController {

	@Autowired
	StatisticalService statisticalService;
	
	@Autowired
	private UserService userService;


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
	
	@GetMapping(value = "/manager/statistical")
	public String manager(ModelMap model, @CookieValue(value = "account", required = false) String username, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				if (cookies[i].getName().equals("account")) {
					User user = this.userService.findByEmail(cookies[i].getValue()).get();
					if (user.isRole() == false) {
						model.addAttribute("username", username);
						model.addAttribute("fullname", user.getFullname());
						model.addAttribute("months", statisticalService.statisticalForMonth());
						model.addAttribute("years", statisticalService.statisticalForYear());
						model.addAttribute("products", statisticalService.statisticalForProduct());
						return "/manager/statistical/statistical";
					} else {
						return "redirect:/index";
					}
				}

			}
		}
		return "redirect:/login";
	}

}
