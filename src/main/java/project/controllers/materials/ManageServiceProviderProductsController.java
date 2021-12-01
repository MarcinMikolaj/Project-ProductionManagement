package project.controllers.materials;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import project.entities.materials.Pipe;
import project.entities.materials.Rod;
import project.repositories.materials.MaterialProviderRepository;
import project.services.OrderService;

//Ten Kontroler odpowiedzialny jest za zarządzanie produktami oferowanymi przez
//dostawców usług materiałowych
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
		
		model.addAttribute("SteelPipe", new Pipe());
		model.addAttribute("Rod", new Rod());
		model.addAttribute("providers", materialProviderRepository.findAll());
		
		return "addMatrialsToProvider";
	}
	
	//Umożliwia obsłużenie przekazania obiektu matariałowego (rury) przez użytkownika
	@RequestMapping(path = "/order/add/pipe", method = RequestMethod.POST)
	public String postForPipe(@RequestParam String select, @ModelAttribute(name = "SteelPipe") Pipe steelPipe) {
		
		this.select = select;
		orderService.addPipeToProvider(steelPipe, select);
			
		return "redirect:/order/menu/providerproducts";
	}
	
	//Umożliwia obsłużenie przekazania obiektu matariałowego (pręta) przez użytkownika
	@RequestMapping(path = "/order/add/rod", method = RequestMethod.POST)
	public String postForRod(@RequestParam String select, @ModelAttribute(name = "Rod") Rod rod) {
		
		this.select = select;
		orderService.addRodToProvider(rod, select);		
		return "redirect:/order/menu/providerproducts";
	}

}
