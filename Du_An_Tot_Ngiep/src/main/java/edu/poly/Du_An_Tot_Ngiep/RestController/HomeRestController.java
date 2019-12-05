package edu.poly.Du_An_Tot_Ngiep.RestController;

import java.util.*;

import edu.poly.Du_An_Tot_Ngiep.Entity.*;
import edu.poly.Du_An_Tot_Ngiep.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HomeRestController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderDetailsService orderDetailsServices;

	@Autowired
	private FeedBackService feedBackService;

	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/index/searchAjaxProduct")
	public List<Product> showListSearchProduct(Model model, @RequestBody Product product, @RequestParam("key") String key){
		
		Product p = (Product) this.productService.searchListProductByIdCategory(key);
		if (p !=null) {
			this.productService.searchListProductByIdCategory(key);
		}
		return this.productService.listProduct();
	}
	
	@GetMapping("/index/listProductAjax")
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
		Optional<Product> list =  this.productService.findById(id);
		return this.productService.showListProductByIdCategoryFilter(id);
	}

	@PostMapping("/insertproduct}")
	@ResponseBody
	public String insertProduct(@RequestParam(name = "idproduct") int idProduct, @RequestParam int amount , HttpSession session ){

		Product productOrder = this.productService.findByIdProduct(idProduct);

		if(productOrder==null || amount<=0){
			return "3";
		}
		if(session.getAttribute("cart")!=null){
			List<Product> list = (List<Product>)session.getAttribute("cart");
			boolean flag = false;
			for(int i=0;i<list.size();i++){
				int id_temp=list.get(i).getIdProduct();
				if (id_temp == idProduct){
					list.get(i).setAmount(list.get(i).getAmount()+amount);
					flag =true;
					return "4";
				}
			}
			if(flag==false) {
				productOrder.setAmount(amount);
			}
			list.add(productOrder);
			session.setAttribute("cart",list);
			session.setAttribute("countCart",list.size());
			return "1";
		}
		else {
			List<Product> list = new ArrayList<>();
			productOrder.setAmount(amount);
			list.add(productOrder);
			session.setAttribute("cart", list);
			session.setAttribute("countCart",list.size());
			return "1";
		}
	}
	@PostMapping(value = "/updatequatities")
	@ResponseBody
	public String updateQuantity( @RequestParam(name = "idproduct") int idProduct, @RequestParam(name = "quantity") int quantity , HttpSession session){
		if(quantity<0){
			return "0";
		}
		else if(quantity==0){
			List<Product> list = (List<Product>)session.getAttribute("cart");
			for(int i=0;i<list.size();i++){
				if(idProduct==list.get(i).getIdProduct()){
					list.remove(i);
					session.setAttribute("cart",list);
					return "2";
				}
			}
		}
		else if(session.getAttribute("cart")!=null ){
			List<Product> list = (List<Product>)session.getAttribute("cart");
			for(int i=0;i<list.size();i++){
				if(idProduct==list.get(i).getIdProduct()){
					list.get(i).setAmount(quantity);
					session.setAttribute("cart",list);
					return "1";
				}
			}
		}
		else  {
			return "0";
		}
		return "0";
	}

	@PostMapping(value = "/orders")
	@ResponseBody
	public String orders(HttpServletRequest request, HttpSession session, ModelMap model) {

		Customer user = null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("accountcustomer")) {
				user = this.customerService.findByPhoneCus(cookies[i].getValue()).get();
				break;
			}
		}
		//System.out.println("------UserID:"+user.getUserId()+"--------");

		if(user==null||user.getCustomerId()<=0)
			return "0";
		else{

			if(session.getAttribute("cart")!=null ){
				List<Product> list = (List<Product>) session.getAttribute("cart");
				Invoice invoice = new Invoice();
				//----date orders
				long millis=System.currentTimeMillis();
				java.sql.Date date=new java.sql.Date(millis);
				//---total---
				double total = 0;
				Set<InvoiceDetail> setDetail = new HashSet<>();
				for(int i=0;i<list.size();i++){
					//total++
					total+= list.get(i).getPrice() * list.get(i).getAmount();
					InvoiceDetail s = new InvoiceDetail();
					s.setProduct(list.get(i));
					setDetail.add(s);
				}
				invoice.setDateorders(date);
				invoice.setStatus("Chờ duyệt");
				invoice.setTotal(total);
				invoice.setVendor(user);
				System.out.println("------Amount Orders:"+list.size()+"--------");

				invoice.setDetails(setDetail);
				ordersService.save(invoice);
				for(int i=0;i<list.size();i++){
					total+=list.get(i).getPrice()*list.get(i).getAmount();
					InvoiceDetail s = new InvoiceDetail();
					s.setProduct(list.get(i));
					s.setAmount(list.get(i).getAmount());
					s.setInvoiceId(invoice);
					setDetail.add(s);
					orderDetailsServices.save(s);
				}
				session.setAttribute("cart",null);
				session.setAttribute("countCart",0);
			}
			else {
				return "-1";
			}
			return "1";
		}
	}

}
