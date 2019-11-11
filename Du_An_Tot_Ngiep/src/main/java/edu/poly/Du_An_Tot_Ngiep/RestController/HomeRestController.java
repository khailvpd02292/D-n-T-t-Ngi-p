package edu.poly.Du_An_Tot_Ngiep.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@RestController
public class HomeRestController {
	
	@Autowired
	private ProductService productService;
	@GetMapping("/index/searchAjaxProduct")
	public List<Product> showListSearchProduct(Model model, @RequestBody Product product, @RequestParam("key") String key){
		
		Product p = (Product) this.productService.searchListProductByIdCategory(key);
		if (p !=null) {
			this.productService.searchListProductByIdCategory(key);
		}
		return this.productService.listProduct();
	}
}
