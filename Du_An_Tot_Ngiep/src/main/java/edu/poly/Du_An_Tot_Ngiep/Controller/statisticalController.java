package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class statisticalController {
	
	@GetMapping(value = "/manager/statistical")
	public String statistical() {
		return "/manager/statistical/statistical";
	}
}
