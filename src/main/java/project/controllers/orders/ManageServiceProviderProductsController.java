package project.controllers.orders;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import project.test.function.MaterialProviderRepository;
import project.test.function.OrderService;
import project.test.function.SteelPipe;

@Controller
public class ManageServiceProviderProductsController {
	
	private MaterialProviderRepository materialProviderRepository;
	private OrderService orderService;
	private String select;
	
	@Autowired
	public ManageServiceProviderProductsController(MaterialProviderRepository materialProviderRepository, OrderService orderService) {
		this.materialProviderRepository = materialProviderRepository;
		this.orderService = orderService;
	}
		
	@RequestMapping(path = "/order/menu/providerproducts", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("SteelPipe", new SteelPipe());
		model.addAttribute("providers", materialProviderRepository.findAll());
		
		return "addNewPipeFrom";
	}
	
	@RequestMapping(path = "/order/add/pipe", method = RequestMethod.POST)
	public String post(@RequestParam String select, @ModelAttribute(name = "SteelPipe") SteelPipe steelPipe) {
		
		this.select = select;
		System.out.println(select);
		orderService.addSteelPipeToProvider(steelPipe, select);
		
		return "redirect:/order/menu/providerproducts";
	}

}
