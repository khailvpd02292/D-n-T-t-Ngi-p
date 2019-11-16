package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@Controller
@RequestMapping(value = "/index")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping()
//	public String Home(ModelMap model, @CookieValue(value = "account") String email) {
//		if (email != null) {
//			model.addAttribute("prods", this.productService.findAll());
//			model.addAttribute("category", this.categoryService.findAll());
//			model.addAttribute("email", email);
//			model.addAttribute("showProduct", this.productService.showListProductForIndex());
//			return "home/index";
//		} else {
//			model.addAttribute("prods", this.productService.findAll());
//			model.addAttribute("category", this.categoryService.findAll());
//			model.addAttribute("showProduct", this.productService.showListProductForIndex());
//			return "home/index";
//		}
	public String Home(ModelMap model) {
			model.addAttribute("prods", this.productService.findAll());
			model.addAttribute("category", this.categoryService.findAll());
			model.addAttribute("showProduct", this.productService.showListProductForIndex());
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

		Optional<Product> p = this.productService.findById(idCategory);
		if (p == null) {
			return "shop/productByIdCategory";
		}
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		model.addAttribute("showProductByIdCategory", this.productService.showListProductByIdCategory(idCategory));

		return "shop/productByIdCategory";
	}

	@GetMapping(value = "/showProductSingle/{idProduct}")
	public String ShowProductByIdProductDetail(ModelMap model, @PathVariable("idProduct") int id, Product product) {

		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
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

//	@GetMapping("/searchProduct/page/{pageNumber}")
//	public String showSearchProductByIdCategory(Model model, HttpServletRequest request, @PathVariable int pageNumber, @RequestParam("key") String key) {
//		model.addAttribute("product", this.productService.findAll());
//		model.addAttribute("category", this.categoryService.findAll());
//		
//		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productList");
//		int pageSize = 8;
//		List<Product> list = productService.searchListProductByIdCategory(key);
//		System.out.println(list);
//		
//		if (pages == null) {
//			pages = new PagedListHolder<>(list);
//			pages.setPageSize(pageSize);
//		} else {
//			final int goToPage = pageNumber -1;
//			if (goToPage<= pages.getPageCount() && goToPage >=0) {
//				pages.setPage(goToPage);
//			}
//		}
//		request.getSession().setAttribute("productList", pages);
//		int current = pages.getPage() +1;
//		int begin = Math.max(1, current - list.size());
//		int end = Math.min(begin +5, pages.getPageCount());
//		int totalPageCount = pages.getPageCount();
//		String baseUrl = "/searchProduct/page/";
//		
//		model.addAttribute("beginIndex", begin);
//		model.addAttribute("endIndex", end);
//		model.addAttribute("currentIndex", current);
//		model.addAttribute("totalPageCount", totalPageCount);
//		model.addAttribute("baseUrl", baseUrl);
//		model.addAttribute("searchProduct", pages);
//		
//		return "shop/searchProduct";
//	}

}
