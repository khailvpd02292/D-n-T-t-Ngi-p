package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Entity.Customer;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.CustomerService;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@Controller
@RequestMapping("")
public class CustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	void getName(HttpServletRequest request, ModelMap model) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("accountuser")) {
				User user = this.userService.findByPhone(cookies[i].getValue()).get();

				model.addAttribute("fullname", user.getFullname());
				break;
			}
		}
	}

	@GetMapping(value = "/manager/listCustomer")
	public String listProduct(ModelMap model, @CookieValue(value = "accountuser", required = false) String phone,
			HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				if (cookies[i].getName().equals("accountuser")) {
					User user = this.userService.findByPhone(cookies[i].getValue()).get();
//					if (user.isRole() == false) {
						model.addAttribute("listcustomer", this.customerService.findAll());
						model.addAttribute("username", phone);
						model.addAttribute("fullname", user.getFullname());
						return "/manager/users/listCustomer";
//					} else {
//						return "/manager/home/index";
//					}
				}
			}

		}
		return "redirect:/login";
	}

	@GetMapping(value = "/registration")
	public String registration(ModelMap model) {
		model.addAttribute("registration", new Customer());
		return "/login/registred";
	}

	@PostMapping(value = "/registration")
	public String addProduct(@ModelAttribute(name = "registration") Customer registration, ModelMap model,
			@RequestParam boolean gender, @RequestParam Date birthday, @RequestParam("phone") String phone) {
		model.addAttribute("registration", new Customer());
		if (customerService.findByPhoneCus(phone).isPresent() || userService.findByPhone(phone).isPresent()) {
			model.addAttribute("error", "Số điện thoại đã tồn tại");
			return "/login/registred";
		} else {
			customerService.save(registration);
			return "redirect:login";
		}
	}

}
