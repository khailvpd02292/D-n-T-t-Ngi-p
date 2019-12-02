package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping(value = "/index")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	void getName(HttpServletRequest request, ModelMap model) {
		// show user
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
	public String Home(ModelMap model, HttpServletRequest request,
			@CookieValue(value = "account", required = false) String username, HttpServletResponse response,
			HttpSession session) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				if (cookies[i].getName().equals("account")) {
					User user = this.userService.findByEmail(cookies[i].getValue()).get();
					if (user.isRole() == true) {
						if (session.getAttribute("cart") != null) {
							List<Product> listpro = (List<Product>) session.getAttribute("cart");
							session.setAttribute("countCart", listpro.size());
						} else {
							session.setAttribute("countCart", 0);
						}
						// show user
						cookieDetail(model, request, response);

						initHomeResponse(model);

						return "home/index";
					} else {
						return "redirect:/manager";
					}
				}

			}
		} else {
			if (session.getAttribute("cart") != null) {
				List<Product> listpro = (List<Product>) session.getAttribute("cart");
				session.setAttribute("countCart", listpro.size());
			} else {
				session.setAttribute("countCart", 0);
			}
			// show user
			cookieDetail(model, request, response);

			initHomeResponse(model);

			return "home/index";
		}
		return "home/index";
	}

	@GetMapping("/product")
	public String ShowListProduct(ModelMap model, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		// show user
		cookieDetail(model, request, response);

		model.addAttribute("showProduct", this.productService.listProduct());

		return "shop/shop";
	}

	@GetMapping("/about")
	public String ShowAbout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		// show user
		cookieDetail(model, request, response);
		return "shop/about";
	}

	@GetMapping("/contact")
	public String ShowContact(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		model.addAttribute("feedback", new FeedBack());
		// show user
		cookieDetail(model, request, response);
		return "shop/contact";
	}

	// code showCategoryById
	@GetMapping(value = "/showProductByIdCategory/{idCategory}")
	public String ShowProductByIdCategory(ModelMap model, @PathVariable("idCategory") int idCategory,
			HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		Optional<Product> p = this.productService.findById(idCategory);
		if (p == null) {
			return "shop/productByIdCategory";
		}
		// show user
		cookieDetail(model, request, response);

		model.addAttribute("showProductByIdCategory", this.productService.showListProductByIdCategory(idCategory));

		return "shop/productByIdCategory";
	}

	@GetMapping(value = "/showProductSingle/{idProduct}")
	public String ShowProductByIdProductDetail(ModelMap model, @PathVariable("idProduct") int id,
			HttpServletRequest request, HttpServletResponse response) {

		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());

		model.addAttribute("showProductSingle", this.productService.findById(id).get());
		// show user
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
		// show user
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
