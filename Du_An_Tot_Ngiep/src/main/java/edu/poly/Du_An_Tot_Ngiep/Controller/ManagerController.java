package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.util.WebUtils;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@Controller
public class ManagerController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/manager")
	public String manager(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				if (cookies[i].getName().equals("account")) {
					return "/manager/home/index";
				}
			}
		}
		return "redirect:/login";
	}

	@GetMapping(value = "/manager/listCategory")
	public String listCategory(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("category", list);
		return "/manager/category/listCategory";
	}

	@GetMapping(value = "/manager/addCategory")
	public String addCategory(ModelMap model) {
		model.addAttribute("category", new Category());
		return "/manager/category/addCategory";

	}

	@PostMapping(value = "/manager/addCategory")
	public String addCategory(@ModelAttribute(value = "category") @Valid Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "/manager/addCategory";
		}

		this.categoryService.save(category);

		return "redirect:/manager/listCategory";
	}

	@GetMapping(value = "/manager/updateCategory/{idCategory}")
	public String updateCategory(ModelMap model, @PathVariable(name = "idCategory") int idCategory) {

		model.addAttribute("category", categoryService.findById(idCategory));

		return "/manager/category/updateCategory";
	}

	@PostMapping(value = "/manager/updateCategory")
	public String updateCategory(@ModelAttribute(value = "category") @Valid Category category, BindingResult result,
			@RequestParam("idCategory") int idCategory) {
		if (result.hasErrors()) {
			return "/manager/updateCategory";
		}

		this.categoryService.save(category);
		return "redirect:/manager/listCategory";
	}

	@GetMapping(value = "/manager/deleteCategory/{idCategory}")
	public String deleteCategory(@PathVariable(name = "idCategory") int idCategory) {
		this.categoryService.deleteById(idCategory);
		return "redirect:/manager/listCategory";
	}

	// table product
	@GetMapping(value = "/manager/listProduct")
	public String listProduct(ModelMap model) {
		model.addAttribute("product", this.productService.findAll());
		return "/manager/product/listProduct";
	}

	@GetMapping(value = "/manager/addProduct")
	public String addProduct(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCategory", categoryService.findAll());
		return "/manager/product/addProduct";
	}

	@PostMapping(value = "/manager/addProduct")
	public String addProduct(@RequestParam(value = "image") MultipartFile image,
			@ModelAttribute(name = "product") @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "/manager/addProduct";
		} else {
			this.productService.save(product);
		}
		return "redirect:/manager/listProduct";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	@GetMapping(value = "/manager/updateProduct/{idProduct}")
	public String updateProduct(ModelMap model, @PathVariable(name = "idProduct") int id) {
		model.addAttribute("listCategory", this.categoryService.findAll());
		model.addAttribute("product",
				this.productService.findById(id).isPresent() ? this.productService.findById(id).get() : null);
		return "/manager/product/updateProduct";
	}

	@PostMapping(value = "/manager/updateProduct")
	public String updateProduct(@RequestParam(value = "image") MultipartFile image,
			@ModelAttribute(name = "product") @Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "/manager/updateProduct";
		} else {
			this.productService.save(product);
		}

		if (!image.isEmpty()) {
			try {
				product.setImage(image.getBytes());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			product.setImage(productService.findById(product.getIdProduct()).get().getImage());

		}

		return "redirect:/manager/listProduct";
	}

	@GetMapping(value = "/manager/deleteProduct/{idProduct}")
	public String deleteProduct(@PathVariable(name = "idProduct") int id) {
		this.productService.deleteById(id);
		return "redirect:/manager/listProduct";
	}

	// product Detail

}
