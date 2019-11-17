package edu.poly.Du_An_Tot_Ngiep.Controller;

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
//		AppUtils.getCookie("account", request)
//			.ifPresent(cookie -> {
//				User user = this.userService.findByEmail(cookie.getValue()).get();
//				model.addAttribute("email", user.getFullname());
//			});
		return "home/index";

	}

	@GetMapping("/product")
	public String ShowListProduct(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());

		request.getSession().setAttribute("productlist", null);

		return "redirect:/index/product/page/1";
	}

	@GetMapping("/product/page/{pageNumber}")
	public String showProductPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
		int pagesSize = 8;
		List<Product> list = productService.listProduct();
		System.out.println(list.size());

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesSize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("productlist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/product/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("product", pages);

		return "shop/shop";
	}

	@GetMapping("/about")
	public String ShowAbout(ModelMap model) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "shop/about";
	}

	@GetMapping("/contact")
	public String ShowContact(ModelMap model) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "shop/contact";
	}

	// code showCategoryById
	@GetMapping(value = "/showProductByIdCategory/{idCategory}")
	public String ShowProductByIdCategory(ModelMap model, @PathVariable("idCategory") int idCategory) {
		
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		Optional<Product> p = this.productService.findById(idCategory);
		if (p == null) {
			return "shop/productByIdCategory";
		}
		
		model.addAttribute("showProductByIdCategory", this.productService.showListProductByIdCategory(idCategory));

		return "shop/productByIdCategory";
	}

	@GetMapping(value = "/showProductSingle/{idProduct}")
	public String ShowProductByIdProductDetail(ModelMap model, @PathVariable("idProduct") int id, Product product) {

		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		List<Product> list = productService.showProductDetaiList(id);
		for (int i = 0; i < list.size(); i++) {
			Product p = new Product();
			p.getCategory().getIdCategory();
		}
		
		
		model.addAttribute("showProductSingle", this.productService.findById(id).get());
		
//			model.addAttribute("showProduct", this.productService.showListCategoryByIdCategory(id));
//		}

		return "shop/product-single";
	}

	@GetMapping("/searchProduct")
	public String searchProductByIdCategory(ModelMap model, @RequestParam("key") String key, Product product,
			HttpServletRequest request, RedirectAttributes redirect) {

		List<Product> products = this.productService.searchListProductByIdCategory(key);
		if (products.isEmpty() || products.contains(product)) {
			return "shop/searchProduct";
		}
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
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



}
