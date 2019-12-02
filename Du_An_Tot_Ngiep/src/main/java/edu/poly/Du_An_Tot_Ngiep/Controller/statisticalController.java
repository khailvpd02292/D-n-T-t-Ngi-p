package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import edu.poly.Du_An_Tot_Ngiep.Service.StatisticalService;

@Controller
public class statisticalController {

	@Autowired
	StatisticalService statisticalService;

	@GetMapping(value = "/manager/statistical")
	public String statistical(ModelMap model) {
		model.addAttribute("months", statisticalService.statisticalForMonth());
		model.addAttribute("years", statisticalService.statisticalForYear());
		model.addAttribute("products", statisticalService.statisticalForProduct());
		return "/manager/statistical/statistical";
	}

}
