package project.controllers.orders;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import project.test.function.MaterialOrder;
import project.test.function.MaterialProviderRepository;
import project.test.function.OrderService;
import project.test.function.SteelPipe;
import project.test.function.SteelPipeRepository;

import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

@Controller
public class CreateMaterialOrderController {
	
	private MaterialProviderRepository materialProviderRepository;
	private OrderService orderService;
	private String choose = "";
	private SteelPipeRepository steelPipeRepository;	
	private List<SteelPipe> materialList = new ArrayList<SteelPipe>();

	
	@Autowired
	public CreateMaterialOrderController(MaterialProviderRepository materialProviderRepository, OrderService orderService) {
		this.materialProviderRepository = materialProviderRepository;
		this.orderService = orderService;
	}
	
	@Autowired
	public void setSteelPipeRepository(SteelPipeRepository steelPipeRepository) {
		this.steelPipeRepository = steelPipeRepository;
	}
	
	@RequestMapping(path = "/order/new", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("provider", materialProviderRepository.findAll());
		model.addAttribute("materialOrder", new MaterialOrder());
		model.addAttribute("choose", choose);
		model.addAttribute("offert", orderService.getSteelPipeByMaterialProvider(choose));
		
		model.addAttribute("pipe", new SteelPipe());
		
		return "materialOrder";
	}
	
	@RequestMapping(path = "/order/chooseprovider", method = RequestMethod.POST)
	public String chooseProvider(@RequestParam(name = "select") String select, Model model) {
		
		model.addAttribute("provider", materialProviderRepository.findAll());
		model.addAttribute("choose", choose);
		model.addAttribute("offert", orderService.getSteelPipeByMaterialProvider(choose));	
		model.addAttribute("pipe", new SteelPipe());
		this.choose = select;
		return "redirect:/order/new";
		
	}
	
	@RequestMapping(path = "/order/send", method = RequestMethod.POST)
	public String CreateMaterialOrder(@ModelAttribute(name = "materialOrder") MaterialOrder materialOrder) {
		
		materialOrder.setSteelPipeList(materialList);
		materialOrder.setMaterialProvider(materialProviderRepository.findByCompanyName(choose));
		
		orderService.CreateMaterialOrder(materialOrder);
		materialList.clear();
			
		return "redirect:/order/new";
	}
	
	@RequestMapping(path = "/order/addproduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam(name = "id") String id, Model model) {
		
		materialList.add(steelPipeRepository.findById(Long.parseLong(id)));
		
		return "redirect:/order/new";
	}
	
	
}
