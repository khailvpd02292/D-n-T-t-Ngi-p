package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;

@Controller
public class ManagerCotroller {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/manager")
	public String manager() {
		return "/manager/index";
	}
	
	@GetMapping(value = "/manager/listCategory")
	public String listCategory(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("category", list);
		return "/manager/listCategory";
	}
	
	@GetMapping(value = "/manager/addCategory")
	public String addCategory(ModelMap model) {
		model.addAttribute("category", new Category());
		return "/manager/addCategory";
		
	}
	
	@PostMapping(value = "/manager/addCategory")
	public String addCategory(@ModelAttribute(value = "category")@Valid Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "/manager/addCategory";
		}
		
		this.categoryService.save(category);
		
		return "redirect:/manager/listCategory";
	}
	
	@GetMapping(value = "/manager/updateCategory/{idCategory}")
	public String updateCategory(ModelMap model, @PathVariable(name = "idCategory") int idCategory) {
		
		model.addAttribute("category", categoryService.findById(idCategory));
		
		return "/manager/updateCategory";
	}
	
	@PostMapping(value = "/manager/updateCategory")
	public String updateCategory(@ModelAttribute(value = "category")@Valid Category category, BindingResult result, @RequestParam("idCategory") int idCategory) {
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
}
