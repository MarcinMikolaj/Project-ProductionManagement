package project.controllers.materials;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project.entities.materials.MaterialProvider;
import project.entities.materials.Pipe;
import project.repositories.materials.MaterialProviderRepository;
import project.services.OrderService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import project.validator.annotation.TaxIdentificatorNumberIsaDataBase;

import org.springframework.validation.annotation.Validated;
import org.hibernate.validator.constraints.pl.NIP;

import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

//Ten kontroller odpowiedzialny jest za obsłużenie możliwości zarządzania Dostawcami materiałów
@Controller
//@Validated
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
		
		  if (!model.containsAttribute("materialProvider")) {
			    model.addAttribute("materialProvider", new MaterialProvider());
			  } 
		
		//model.addAttribute("additional", new MaterialProvider());
		//model.addAttribute("materialProvider", new MaterialProvider());
		model.addAttribute("providers", materialProviderRepository.findAll());
		
		return "provider";
	}
	
	@RequestMapping(path = "/menu/order/provider/create", method = RequestMethod.POST)
	public String postCreate(@Valid @ModelAttribute(name = "materialProvider") MaterialProvider materialProvider,
			BindingResult result, RedirectAttributes redirectAttributes,  Model model) {
			
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.materialProvider", result);
			redirectAttributes.addFlashAttribute("materialProvider", materialProvider);
			List<ObjectError> errors = result.getAllErrors();
			errors.forEach(q -> System.out.println(q.getDefaultMessage()));
			return "redirect:/provider/manage";
		}		
		orderService.updateMaterialProvider(materialProvider);	
		return  "redirect:/provider/manage";
	}
	
	//Obsługuje możliwość usunięcia zamówienia za pomocą numeru NIP
	@RequestMapping(path = "/menu/order/provider/delete", method = RequestMethod.POST)
	public String postDelete(@RequestParam @NIP(message = "Niepoprawny numer Nip") @TaxIdentificatorNumberIsaDataBase String nip, Model model) {		
		orderService.deleteMaterialProvider(nip);	
		return "redirect:/provider/manage";
	}
			
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleError(ConstraintViolationException ex,
			WebRequest request, RedirectAttributes atts) {
		
		var nip = request.getParameter("nip");
		
		var errorMessages = new ArrayList<String>();
		var violations = ex.getConstraintViolations();
		
		violations.forEach(violation -> {
			var error = String.format(violation.getMessage());
			errorMessages.add(error);
		});
		
		if(!StringUtils.isEmptyOrWhitespace(nip)){
			atts.addFlashAttribute("nip", nip);
		}
		
		atts.addFlashAttribute("messages", errorMessages);	
		return "redirect:/provider/manage";
	}

}
