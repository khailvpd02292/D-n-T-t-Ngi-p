package edu.poly.Du_An_Tot_Ngiep.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@RestController
public class HomeRestController {

	@Autowired
	private ProductService productService;
}
