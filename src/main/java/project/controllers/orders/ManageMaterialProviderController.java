package project.controllers.orders;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import project.test.function.MaterialProvider;
import project.test.function.MaterialProviderRepository;
import project.test.function.OrderService;
import project.test.function.SteelPipe;
import project.test.function.SteelPipeRepository;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageMaterialProviderController {
	
	private MaterialProviderRepository materialProviderRepository;
	private OrderService orderService;
	
	@Autowired
	public ManageMaterialProviderController(MaterialProviderRepository materialProviderRepository, OrderService orderService) {
		this.materialProviderRepository = materialProviderRepository;
		this.orderService = orderService;
	}
	
	@RequestMapping(path = "/provider/manage", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("additional", new MaterialProvider());
		model.addAttribute("materialProvider", new MaterialProvider());
		model.addAttribute("providers", materialProviderRepository.findAll());
		
		return "provider";
	}
	
	@RequestMapping(path = "/menu/order/provider/create", method = RequestMethod.POST)
	public String postCreate(@Valid @ModelAttribute(name = "materialProvider") MaterialProvider materialProvider,
			BindingResult result, Model model) {
			
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			errors.forEach(q -> System.out.println(q.getDefaultMessage()));
			return "provider";
		}
			
		orderService.updateMaterialProvider(materialProvider);
		
		return  "redirect:/provider/manage";
	}
	
	@RequestMapping(path = "/menu/order/provider/delete", method = RequestMethod.POST)
	public String postDelete(@RequestParam String nip, Model model) {
			
		orderService.deleteMaterialProvider(nip);
		
		return "redirect:/provider/manage";
	}
	
	
	

		
	@RequestMapping(path = "/order/test", method = RequestMethod.GET)
	public String test(Model model) {
		
		MaterialProvider materialProvider1 = new MaterialProvider("STAL IMPEX", "ul. Łukasiewicza 49, 38-400 Krosno" + 
				"38-400 Krosno", "marcin12@gmail.com", "697 704 319", "684-18-17-582", "008013634");
		
		MaterialProvider materialProvider2 = new MaterialProvider("KRONOS EMD", "Tupadły 129, 88-101 Inowrocław" + 
				"38-400 Krosno", "marcin09876a2@gmail.com", "604 412 364 ", "556-18-42-118", "000000000");
		
		orderService.updateMaterialProvider(materialProvider1);
		orderService.updateMaterialProvider(materialProvider2);
		
		orderService.addSteelPipeToProvider(new SteelPipe("Prostokątna1", "Szybkotnąca", "20", "5"), materialProvider1.getCompanyName());
		orderService.addSteelPipeToProvider(new SteelPipe("Prostokątna2", "Szybkotnąca2", "25", "2"), materialProvider1.getCompanyName());
		orderService.addSteelPipeToProvider(new SteelPipe("Prostokątna3", "Szybkotnąca3", "55", "15"), materialProvider1.getCompanyName());
		
		model.addAttribute("additional", new MaterialProvider());
		model.addAttribute("materialProvider", new MaterialProvider());
		model.addAttribute("providers", materialProviderRepository.findAll());
				
		return "redirect:/order/menu";
	}
	
}
