package project.controllers.materials;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.services.OrderService;

import org.springframework.ui.Model;

//Umożliwia wyświetlenie listy zamówień materiałowych oraz zarządzania nią przez użytkownika
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
	
	//Umożliwia obsłużenie akcji usunięcia zamówienia za pomocą przesłanego
	//identyfikatora tego zamówienia
	@RequestMapping(path = "/order/check/remove")
	public String removeOrder(@RequestParam (name = "OrderToRemove") String OrderToRemove) {
		orderService.removeMaterialOrderByIdentificator(OrderToRemove);
		return "redirect:/order/check";
	}

}
