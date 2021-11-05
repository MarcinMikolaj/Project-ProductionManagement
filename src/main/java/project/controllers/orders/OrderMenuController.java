package project.controllers.orders;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderMenuController {
	
	@RequestMapping(path = "/order/menu", method = RequestMethod.GET)
	public String get() {
		return "orderMenu";
	}

}
