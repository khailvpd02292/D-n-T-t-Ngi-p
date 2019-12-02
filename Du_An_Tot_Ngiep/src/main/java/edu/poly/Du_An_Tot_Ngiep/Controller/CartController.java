package edu.poly.Du_An_Tot_Ngiep.Controller;

import edu.poly.Du_An_Tot_Ngiep.Entity.Invoice;
import edu.poly.Du_An_Tot_Ngiep.Entity.InvoiceDetail;
import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

	@Autowired
	private UserService userService;

	@Autowired
	CartService cart;

	@Autowired
	OrdersService oders;

	@Autowired
	OrderDetailsService orderDetailsService;

	@Autowired
	ProductService productService;

	@GetMapping("/manager/order")
	public String listOrder(ModelMap model) {
		List<Invoice> list = this.oders.listInvoice();
		model.addAttribute("listOrder",list);
		return "manager/order/order";
	}
	
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		return "index";
	}

	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		return "redirect:/product/list";
	}

	@GetMapping("/cart")
	public String viewCart(ModelMap model, HttpServletRequest request){
		int id = -1;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				User user = this.userService.findByEmail(cookies[i].getValue()).get();
				model.addAttribute("fullname", user.getFullname());
				if(user!=null){
					id=user.getUserId();
				}
				break;
			}
		}
		System.out.println("------IDUSer:"+id+"--------");
		System.out.println("------IDUSer:"+this.oders.listInvoiceByUser(id).size()+"--------");
		model.addAttribute("orders",this.oders.listInvoiceByUser(id));
		return "shop/cart";
	}

	@GetMapping(value = "/orderdetails/{id}")
	public String viewOrderdetails(@PathVariable("id") int id, ModelMap model, HttpServletRequest request){
		//-- id hóa đơn
		//-- findIdInvoice
		//-CHeck null or not
		//Sendrect 404
		List<InvoiceDetail> list = this.orderDetailsService.findDetailByInvoiceId(id);
		List<Product> productorder = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Product odrProduct = productService.findByIdProduct(list.get(i).getProduct().getIdProduct());
			odrProduct.setAmount(list.get(i).getAmount());
			productorder.add(odrProduct);
		}
		model.addAttribute("oldorders",productorder);
		return "shop/oderdetail";
	}


}
