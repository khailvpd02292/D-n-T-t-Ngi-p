package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String Home(ModelMap model) {
		model.addAttribute("prods", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "home/index";
	}

	@GetMapping("/product")
	public String ShowListProduct(ModelMap model) {
		model.addAttribute("product", this.productService.findAll());
		model.addAttribute("category", this.categoryService.findAll());
		return "shop/shop";
	}

	@GetMapping("/about")
	public String ShowAbout(ModelMap model) {
		return "shop/about";
	}

	@GetMapping("/contact")
	public String ShowContact(ModelMap model) {
		return "shop/contact";
	}

	@GetMapping(value = "/index/product/{idProduct}")
	public String ShowProduct(ModelMap model, @PathVariable("idProduct") int product) {

		return "";
	}
	// code showCategoryById

}
