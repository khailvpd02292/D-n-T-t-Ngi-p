package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.Du_An_Tot_Ngiep.Entity.Imports;
import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Service.ImportService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;

@Controller
public class ImportController {

	@Autowired
	private ImportService importService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	void getName(HttpServletRequest request, ModelMap model) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals("account")) {
				User user = this.userService.findByEmail(cookies[i].getValue()).get();
				model.addAttribute("fullname", user.getFullname());
				break;
			}
		}
	}

	@GetMapping(value = "/manager/import")
	public String listImport(ModelMap model, @CookieValue(value = "account") String username,
			HttpServletRequest request, HttpServletResponse response) {
		List<Imports> list = (List<Imports>) importService.findAll();
		model.addAttribute("import", list);
		model.addAttribute("username", username);
		getName(request, model);
		return "/manager/import/import";
	}

	@GetMapping(value = "/manager/addImport")
	public String addImport(ModelMap model, @CookieValue(value = "account") String username,
			HttpServletRequest request) {
		model.addAttribute("import", new Imports());
		model.addAttribute("username", username);
		model.addAttribute("listProduct", productService.findAll());
		getName(request, model);
//		System.out.println(username);
		return "/manager/import/addImport";
	}

	@PostMapping(value = "/manager/addImport")
	public String addImport(@ModelAttribute(value = "import") @Valid Imports import1,
			BindingResult result, HttpServletRequest request,
			ModelMap model) {

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
	
		impl.setProduct(import1.getProduct());
		impl.setQuantity(import1.getQuantity());
		importService.save(impl);
		return "redirect:/manager/import";
	}

	@GetMapping(value = "/manager/updateImport/{idImport}")
	public String updateImport(ModelMap model, @PathVariable(name = "idImport") int id) {
		model.addAttribute("listProduct", productService.findAll());
		model.addAttribute("import",
				this.importService.findById(id).isPresent() ? this.importService.findById(id).get() : null);

		return "/manager/import/updateImport";
	}

	@PostMapping(value = "/manager/updateImport")
	public String updateImport(@ModelAttribute(value = "import") @Valid Imports import1, BindingResult result,
			@RequestParam("idImport") int idImport) {
		if (result.hasErrors()) {
			return "/manager/updateImport";
		}
		this.importService.save(import1);
		return "redirect:/manager/import";
	}

	@GetMapping(value = "/manager/deleteImport/{idImport}")
	public String deleteImport(@PathVariable(name = "idImport") int idImport) {
		this.importService.deleteById(idImport);
		return "redirect:/manager/import";
	}
}
