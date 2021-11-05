package project.controllers.orders;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.test.function.OrderService;

import org.springframework.ui.Model;

@Controller
public class CheckTheOrderListController {
	
	private OrderService orderService;
	
	@Autowired
	public CheckTheOrderListController(OrderService orderService){
		this.orderService = orderService;
	}
	
	@RequestMapping(path = "/order/check", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("orders", orderService.getMaterialOrders());
		
		return "checkOrderList";
	}

}
