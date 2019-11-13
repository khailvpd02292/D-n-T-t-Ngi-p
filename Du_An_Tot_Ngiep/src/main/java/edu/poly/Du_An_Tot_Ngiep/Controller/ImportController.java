package edu.poly.Du_An_Tot_Ngiep.Controller;

import java.util.List;

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

import edu.poly.Du_An_Tot_Ngiep.Entity.Import;
import edu.poly.Du_An_Tot_Ngiep.Service.ImportService;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@Controller
public class ImportController {
	
	@Autowired 
	private ImportService importService;
	
	@Autowired 
	private ProductService productService;
	
	
	@GetMapping(value = "/manager/import")
	public String listImport(ModelMap model) {
		List<Import> list = (List<Import>) importService.findAll();
		model.addAttribute("import", list);
		return "/manager/import/import";
	}
	
	@GetMapping(value = "/manager/addImport")
	public String addImport(ModelMap model, @CookieValue(value ="account") String username) {
		model.addAttribute("import", new Import());
		model.addAttribute("username",username);
		model.addAttribute("listProduct", productService.findAll());
//		System.out.println(username);
		return "/manager/import/addImport";
	}
	
	@PostMapping(value = "/manager/addImport")
	public String addImport(@ModelAttribute(value = "import") @Valid Import import1, BindingResult result) {
//		if(result.hasErrors()) {
//			return "/manager/addImport";
//		}
//		@CookieValue(value ="account") String username,ModelMap model
		
		this.importService.save(import1);
		return "redirect:/manager/import";
	}
	
	
	@GetMapping(value = "/manager/updateImport/{idImport}")
	public String updateImport(ModelMap model, @PathVariable(name = "idImport")int id ) {
		model.addAttribute("listProduct", productService.findAll());
		model.addAttribute("import", this.importService.findById(id).isPresent()? this.importService.findById(id).get() : null);
		
		return "/manager/import/updateImport";
	}
	
	
	@PostMapping(value = "/manager/updateImport")
	public String updateImport(@ModelAttribute(value = "import") @Valid Import import1,BindingResult result, @RequestParam("idImport") int idImport) {
		if(result.hasErrors()) {
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
