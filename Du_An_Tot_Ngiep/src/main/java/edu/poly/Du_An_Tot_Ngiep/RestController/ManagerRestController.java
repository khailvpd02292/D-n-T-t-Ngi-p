package edu.poly.Du_An_Tot_Ngiep.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;

@RestController
public class ManagerRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/manager/addAjaxCategory")
	public List<Category> addAjaxCategory(@RequestBody Category category){
		this.categoryService.save(category);
		return this.categoryService.findAll();
	}
	@PostMapping("/manager/updateAjaxCategory")
	public List<Category> updateAjaxCategory(@RequestBody Category category){
		this.categoryService.save(category);
		return this.categoryService.findAll();
	}
}
