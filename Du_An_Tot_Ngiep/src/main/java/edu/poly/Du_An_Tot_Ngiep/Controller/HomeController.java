package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.Du_An_Tot_Ngiep.Entity.FeedBack;
import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;
import edu.poly.Du_An_Tot_Ngiep.utils.AppUtils;

@Controller
@RequestMapping(value = "/index")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;

//	@GetMapping()
//	public String Home(ModelMap model, HttpServletRequest request) {
//			model.addAttribute("prods", this.productService.findAll());
//			model.addAttribute("category", this.categoryService.findAll());
//			Cookie[] cookies = request.getCookies();
//			if(cookies != null) {
//				Cookie account = Stream.of(cookies)
//						.filter(cookie -> cookie.getName().equalsIgnoreCase("account"))
//						.findFirst()
//						.get();
//				model.addAttribute("email", account.getValue());
//			}
//			model.addAttribute("showProduct", this.productService.showListProductForIndex());
//			return "home/index";
//	
//	}
	
	void getName(HttpServletRequest request, ModelMap model) {
		//show user
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				User user = this.userService.findByEmail(cookies[i].getValue()).get();
				model.addAttribute("fullname", user.getFullname());
				break;
			}
		}
	}
	
	void initHomeResponse(ModelMap model) {
		model.addAttribute("prods", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		model.addAttribute("showProduct", this.productService.showListProductForIndex());
	}

	@GetMapping()
	public String Home(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		//show user
		cookieDetail(model, request, response);
		initHomeResponse(model);
//		AppUtils.getCookie("account", request)
//			.ifPresent(cookie -> {
//				User user = this.userService.findByEmail(cookie.getValue()).get();
//				model.addAttribute("email", user.getFullname());
//			});
		return "home/index";

	}

	@GetMapping("/product")
	public String ShowListProduct(ModelMap model, RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		//show user
		cookieDetail(model, request, response);
		
		model.addAttribute("showProduct", this.productService.listProduct());

		return "shop/shop";
	}


	@GetMapping("/about")
	public String ShowAbout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		//show user
		cookieDetail(model, request, response);
		return "shop/about";
	}

	@GetMapping("/contact")
	public String ShowContact(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		model.addAttribute("feedback", new FeedBack());
		//show user
		cookieDetail(model, request, response);
		return "shop/contact";
	}

	// code showCategoryById
	@GetMapping(value = "/showProductByIdCategory/{idCategory}")
	public String ShowProductByIdCategory(ModelMap model, @PathVariable("idCategory") int idCategory, HttpServletRequest request, HttpServletResponse response) {
		
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		Optional<Product> p = this.productService.findById(idCategory);
		if (p == null) {
			return "shop/productByIdCategory";
		}
		//show user
		cookieDetail(model, request, response);
		
		model.addAttribute("showProductByIdCategory", this.productService.showListProductByIdCategory(idCategory));

		return "shop/productByIdCategory";
	}

	@GetMapping(value = "/showProductSingle/{idProduct}")
	public String ShowProductByIdProductDetail(ModelMap model, @PathVariable("idProduct") int id, HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
	
		model.addAttribute("showProductSingle", this.productService.findById(id).get());
		//show user
		cookieDetail(model, request, response);
		Product product = this.productService.findById(id).get();
		Product p = this.productService.findByIdProduct(product.getIdProduct());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setImage(product.getImage());
		List<Product> list = this.productService.findByIdCategory(p.getCategory().getIdCategory());
		
		for (int i = 0; i < list.size(); i++) {
			p = list.get(i);
			if (p.getIdProduct() == product.getIdProduct()) {
				list.remove(list.get(i));
				break;
			}
		}
		model.addAttribute("showProductByCategory", list);
		
		
		return "shop/product-single";
	}

	@GetMapping("/searchProduct")
	public String searchProductByIdCategory(ModelMap model, @RequestParam("key") String key, Product product,
			 RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		List<Product> products = this.productService.searchListProductByIdCategory(key);
		//show user
		cookieDetail(model, request, response);
		
		
		if (products.isEmpty() || products.contains(product)) {
			return "shop/searchProduct";
		}
		
		
		
		model.addAttribute("searchProduct", this.productService.searchListProductByIdCategory(key));
//		request.getSession().setAttribute("productList", null);
		return "shop/searchProduct";
	}
	
	@RequestMapping("/logout1")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		initHomeResponse(model);
		Cookie cookie = new Cookie("account", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		model.addAttribute("email", null);
		return "redirect:/index";
	}
	
	public void cookieDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				if (cookies[i].getName().equals("account")) {
					User user = this.userService.findByEmail(cookies[i].getValue()).get();
					model.addAttribute("fullname", user.getFullname());
					break;
				}
			}
		}
		initHomeResponse(model);
	}





}
