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
import project.entities.materials.MaterialOrder;

import project.repositories.materials.MaterialProviderRepository;
import project.repositories.materials.PipeRepository;
import project.repositories.materials.RodRepository;
import project.services.OrderService;

import java.util.List;
import java.util.ArrayList;

//Kontroler umożliwia obsłużenie tworzenia nowych zamówień materiałowych opartych o dostawców materiałowych
@Controller
public class CreateMaterialOrderController {
	
	private MaterialProviderRepository materialProviderRepository;
	private PipeRepository pipeRepository;	
	private RodRepository rodRepository;
	private OrderService orderService;
	private String choose = "";
	
	private List<Pipe> pipeMaterialList = new ArrayList<Pipe>();
	private List<Rod> rodMaterialList = new ArrayList<Rod>();

	
	@Autowired
	public CreateMaterialOrderController(MaterialProviderRepository materialProviderRepository, OrderService orderService,
			RodRepository rodRepository, PipeRepository pipeRepository) {
		this.materialProviderRepository = materialProviderRepository;
		this.orderService = orderService;
		this.rodRepository = rodRepository;
		this.pipeRepository = pipeRepository;
	}
	
	@RequestMapping(path = "/order/new", method = RequestMethod.GET)
	public String get(Model model) {
		
		model.addAttribute("provider", materialProviderRepository.findAll());
		model.addAttribute("materialOrder", new MaterialOrder());
		model.addAttribute("choose", choose);
		model.addAttribute("PipeOffert", orderService.getPipesByMaterialProvider(choose));
		model.addAttribute("RodsOffert", orderService.getRodsByMaterialProvider(choose));	
		model.addAttribute("PipesInTheBasket", pipeMaterialList);
		model.addAttribute("RodsInTheBasket", rodMaterialList);
		model.addAttribute("pipe", new Pipe());
		model.addAttribute("rod", new Rod());
		
		return "materialOrder";
	}
	
	//Obsługuje możliwość wybrania dostawcy usług materiałowych 
	@RequestMapping(path = "/order/chooseprovider", method = RequestMethod.POST)
	public String chooseProvider(@RequestParam(name = "select") String select, Model model) {
		this.choose = select;
		pipeMaterialList.clear();
		rodMaterialList.clear();
		return "redirect:/order/new";
	}
	
	@RequestMapping(path = "/order/send", method = RequestMethod.POST)
	public String CreateMaterialOrder(@ModelAttribute(name = "materialOrder") MaterialOrder materialOrder) {
		materialOrder.setRodList(rodMaterialList);
		materialOrder.setSteelPipeList(pipeMaterialList);
		orderService.CreateMaterialOrder(materialOrder, choose);
		pipeMaterialList.clear();
		rodMaterialList.clear();		
		return "redirect:/order/new";
	}
	
	//Umożliwia obsłużenie usunięcia materiału (pipe) z koszyka
	@RequestMapping(path = "/order/remove/pipe", method = RequestMethod.POST)
	public String removePipeFromCart(@RequestParam String PipeIdToRemove) {		
		pipeMaterialList = orderService.removePipeById(pipeMaterialList, PipeIdToRemove);
		return "redirect:/order/new";
	}
	
	//Umożliwia obsłużenie usunięcia materiału (rod) z koszyka
	@RequestMapping(path = "/order/remove/rod", method = RequestMethod.POST)
	public String removeRodFromCart(@RequestParam String RodIdToRemove) {	
		rodMaterialList = orderService.removeRodById(rodMaterialList, RodIdToRemove);	
		return "redirect:/order/new";
	}
	
	//Umożliwia przekazanie materiału (pipe) do koszyka
	@RequestMapping(path = "/order/add/cart/pipe", method = RequestMethod.POST)
	public String addPipeProduct(@RequestParam(name = "id") String id, Model model) {	
		pipeMaterialList.add(pipeRepository.findById(Long.parseLong(id)));			
		return "redirect:/order/new";
	}
	
	//Umożliwia przekazanie materiału (rod) do koszyka
	@RequestMapping(path = "/order/add/cart/rod", method = RequestMethod.POST)
	public String addRodProduct(@RequestParam(name = "id") String id, Model model) {
		rodMaterialList.add(rodRepository.findById(Long.parseLong(id)));		
		return "redirect:/order/new";
	}
	
}
