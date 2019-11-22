package edu.poly.Du_An_Tot_Ngiep.RestController;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.StringUtils;
import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Entity.FeedBack;
import edu.poly.Du_An_Tot_Ngiep.Entity.Imports;
import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;
import edu.poly.Du_An_Tot_Ngiep.Service.FeedBackService;
import edu.poly.Du_An_Tot_Ngiep.Service.ImportService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@RestController
public class ManagerRestController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ImportService importService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedBackService feedBackSerice;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	

	@GetMapping("/manager/listCategoryAjax")
	public ResponseEntity<?> showListCategory() {
		return ResponseEntity.ok(this.categoryService.listCategory());
	}

	@PostMapping("/manager/addAjaxCategory")
	public List<Category> addAjaxCategory(@RequestBody Category category) {
		this.categoryService.save(category);
		return this.categoryService.listCategory();
	}

	@PostMapping("/manager/updateAjaxCategory")
	public List<Category> updateAjaxCategory(@RequestBody Category category) {

		Category c = this.categoryService.findCateById(category.getIdCategory());
		if (c != null) {
			c.setName(category.getName());
			this.categoryService.save(c);
		}
		return this.categoryService.listCategory();
	}

	@PostMapping("/manager/deleteAjaxCategory/{idCategory}")
	@ResponseBody
	public List<Category> deleteAjaxCategory(@PathVariable("idCategory") int id) {
		this.categoryService.deleteById(id);
		return this.categoryService.listCategory();
	}

//	table product
	@GetMapping("/manager/listProductAjax")
	public ResponseEntity<?> showListProduct() {
		return ResponseEntity.ok(this.productService.listProduct());
	}
	
	@PostMapping("/manager/showListCategoryForProduct")
	public ResponseEntity<?> showListCategoryForProduct(){
		return ResponseEntity.ok(this.categoryService.listCategory());
	}
	
	
	@PostMapping(value = "/manager/addAjaxProduct", consumes = "multipart/form-data")
	public List<Product> addAjaxProduct(@RequestParam("product") String productReq,
			@RequestParam(value = "image", required = false) MultipartFile image)
			throws JsonParseException, JsonMappingException, IOException {
		Product product = this.objectMapper.readValue(productReq, Product.class);
		if(image != null) {
			try {
				product.setImage(image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.productService.save(product);
		
		return this.productService.listProduct();
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}


	@PostMapping(value ="/manager/updateAjaxProduct", consumes = "multipart/form-data")
	public List<Product> updateAjaxProduct(@RequestParam("product") String products, @RequestParam(value = "image", required = false) MultipartFile image) 
			throws JsonParseException, JsonMappingException, IOException {
		Product product = this.objectMapper.readValue(products, Product.class);
		
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
		
		Product pro = this.productService.findByIdProduct(product.getIdProduct());
		if (pro != null) {
			pro.setName(product.getName());
			pro.setImage(product.getImage());
			pro.setPrice(product.getPrice());
			pro.setDateOfManufacture(product.getDateOfManufacture());
			pro.setOrigin(product.getOrigin());
			pro.setDescription(product.getDescription());
			this.productService.save(pro);
		}
		
		
		return this.productService.listProduct();
	}

	@PostMapping("/manager/deleteAjaxProduct/{idProduct}")
	public List<Product> deleteAjaxProduct(@PathVariable("idProduct") int id) {
		this.productService.deleteById(id);
		return this.productService.listProduct();
	}

//	table import

	@GetMapping("/manager/listImportAjax")
	public ResponseEntity<?> listImportAjx() {
		return ResponseEntity.ok(this.importService.listImport());
	}
	
	@GetMapping("/manager/listProductForImport")
	public ResponseEntity<?> listProductForImport(){
		return ResponseEntity.ok(this.productService.listProduct());
	}

	@PostMapping("/manager/addImportAjax")
	@ResponseBody
	public List<Imports> addImportAjax(@RequestBody Imports imports,  HttpServletRequest request,
			ModelMap model) {
		// add user for import
		Imports impl = new Imports();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				User user = this.userService.findByEmail(cookies[i].getValue()).get();
				model.addAttribute("fullname", user.getFullname());
				impl.setUsers(user.getFullname());
				break;
			}
		}
	
		impl.setProduct(imports.getProduct());
		impl.setQuantity(imports.getQuantity());
		// save
		this.importService.save(imports);
		return this.importService.listImport();
	}
	
	@PostMapping("/manager/updateImportAjax")
	public List<Imports> updateImportAjax(@RequestBody Imports imports){
		Imports im = this.importService.findByIdImport(imports.getIdImport());
		imports.setCreateDate(im.getCreateDate());
		
		im.setUsers(imports.getUsers());
		im.setUpdateDate(imports.getUpdateDate());
		im.setQuantity(imports.getQuantity());
		im.setProduct(imports.getProduct());
		this.importService.save(im);
		return this.importService.listImport();
	}
	
	@PostMapping("/manager/deleteImportAjax/{idImport}")
	public List<Imports> deleteImportAjax(@PathVariable("idImport") int id){
		this.importService.deleteById(id);
		return this.importService.listImport();
	}
	
//	table Users
	
	@GetMapping("/manager/listUserAjax")
	public ResponseEntity<?> listUser(){
		return ResponseEntity.ok(this.userService.findAll());
	}
	
	@PostMapping("/manager/updateUserAjax")
	public List<User> updateUserAjax(User user){
		User u = this.userService.findByIdUser(user.getUserId());
		u.setFullname(user.getFullname());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		u.setGender(user.isGender());
		u.setAddress(user.getAddress());
		u.setBirthday(user.getBirthday());
		u.setPassword(user.getPassword());
		u.setRole(user.isRole());
		this.userService.save(user);
		return this.userService.listUser();
	}
	
	
//	tabel FeedBack
	
	@GetMapping("/manager/listFeedBack")
	public ResponseEntity<?> listFeedBack(){
		return ResponseEntity.ok(this.feedBackSerice.findAll());
	}
	
	@PostMapping("/manager/addFeedBackAjax")
	public List<FeedBack>  addFeedBackAjax(@RequestBody FeedBack feedBack){
		this.feedBackSerice.save(feedBack);
		return this.feedBackSerice.findAll();
	}
	
	@PostMapping("/manager/deleteFeedBack/{idFeedBack}")
	public List<FeedBack> deleteFeedBack(@PathVariable("idFeedBack") int id){
		this.feedBackSerice.deleteById(id);
		return this.feedBackSerice.findAll();
	}
}
