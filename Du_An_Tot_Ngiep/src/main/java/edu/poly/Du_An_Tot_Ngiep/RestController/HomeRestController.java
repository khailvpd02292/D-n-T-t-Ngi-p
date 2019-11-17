package edu.poly.Du_An_Tot_Ngiep.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping("/index/listProductAjax")
	public ResponseEntity<?> showListProduct(){
		return ResponseEntity.ok(this.productService.listProduct());
	}
	@GetMapping("/index/listProductNewBest")
	public  ResponseEntity<?> showListProductNewBest() {

//		this.productService.listProductNewBest();

		return ResponseEntity.ok(this.productService.listProductNewBest());
	}
	
	@GetMapping("/index/listProductPriceDesc")
	public ResponseEntity<?> showListProductPriceDesc() {
		
//		this.productService.listProductPriceDesc();
		
		return ResponseEntity.ok(this.productService.listProductPriceDesc());
	}
	
	@GetMapping("/index/listProductPriceAsc")
	public ResponseEntity<?> showListProductPriceAsc(){
		
//		this.productService.listProductPriceAsc();
		
		return ResponseEntity.ok(this.productService.listProductPriceAsc());
	}
	
	@PostMapping("/index/listProductByIdCategoryFilter/{idCategory}")
	@ResponseBody
	public List<Product> showListProductByIdCategory(@PathVariable("idCategory") int id, Product p){
//		this.productService.showListProductByIdCategory();
		Optional<Product> list =  this.productService.findById(id);
		if (list.isPresent()) {
			return this.productService.showListProductByIdCategoryFilter(id);
		}
		return this.productService.listProduct();
	}

}
