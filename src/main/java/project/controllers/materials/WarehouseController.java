package project.controllers.materials;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import project.services.MaterialService;

import javax.validation.constraints.NotBlank;
import project.validator.annotation.OrderIdentificator;

@Controller
@Validated
public class WarehouseController {
	
	private MaterialService materialService;
	private String selectMaterialType = "";
	private String selectProfile = "";
	private String sortBy = "" ;
	
	@Autowired
	public WarehouseController(MaterialService materialService) {
		this.materialService = materialService;
	}
	
	//Odpowiada za wyświetlenie aktualnego stanu magazynu
	@RequestMapping(path = "/order/warehouse", method = RequestMethod.GET)
	public String get(Model model) {
			
		model.addAttribute("pipes",materialService.getPipesFromWarehuse(sortBy, selectMaterialType, selectProfile));
		model.addAttribute("rods", materialService.getRodsFromWarehouse(sortBy, selectMaterialType, selectProfile));
		model.addAttribute("identificator");
		model.addAttribute("sortBy");
		model.addAttribute("materials", materialService.getAllaterialType());
		model.addAttribute("profiles", materialService.getAllaterialProfile());
				
		return "warehouse";
	}
	
	//Odpowiada za obsłużenie możliwości przekazania identyfikatora zamówienia
	//które ma zostać przekazane do magazynu
	@RequestMapping(path = "/order/warehouse/add/order", method = RequestMethod.POST)
	public String addOrder(@RequestParam @NotBlank(message = "Pole nie może być puste") @OrderIdentificator String identificator) {		
		
		materialService.addMaterialFromTheOrder(identificator);
		
		return "redirect:/order/warehouse";
	}
	
	
	//Umożliwia obsłużenie wyboru materiałów które mają zostać wyświetlone
	//Wybór odbywa się poprzez wybranie przez użytkownika gatunku materiałowego oraz profilu
	@RequestMapping(path = "/order/warehouse/choose/materialtype", method = RequestMethod.POST)
	public String choose(@RequestParam String selectMaterialType, @RequestParam String selectProfile) {
		this.selectMaterialType = selectMaterialType;
		this.selectProfile = selectProfile;
		return "redirect:/order/warehouse";
	}
	
	//Odpowiada za obsłużenie wyboru sortowania materiałów wybranych przez użytkownika
	@RequestMapping(path = "/order/warehouse/sort", method = RequestMethod.POST)
	public String sortBy(@RequestParam String sortBy) {
		this.sortBy = sortBy;
		return "redirect:/order/warehouse";
	}	
	
	//Umożliwia pobranie z magazynu materiałów (Pipe)
	@RequestMapping(path = "/order/warehouse/take/pipe", method = RequestMethod.POST)
	public String takePipeFromWarehouse(@RequestParam (name = "numberToRemovePipe") String numberToRemovePipe,
			@RequestParam (name = "PipeId") String PipeId) {	
		materialService.takePipesFromWarehouse(numberToRemovePipe, PipeId);
		return "redirect:/order/warehouse";
	}
	
	//Umożliwia pobranie z magazynu materiałów (Rod)
	@RequestMapping(path = "/order/warehouse/take/rod", method = RequestMethod.POST)
	public String takeRodFromWarehouse(@RequestParam (name = "RodId") String RodId,
			@RequestParam (name = "numberToRemoveRod") String numberToRemoveRod) {
		materialService.takeRodFromWarehouse(numberToRemoveRod, RodId);
		return "redirect:/order/warehouse";
	}
	
	//Umożliwia obsłużenie walidacji na pól
	@ExceptionHandler(ConstraintViolationException.class)
    public String handleError(ConstraintViolationException ex,
                                    WebRequest request,
                                    RedirectAttributes atts) {

        var identificator = request.getParameter("identificator");

        var errorMessages = new ArrayList<String>();
        var violations = ex.getConstraintViolations();

        violations.forEach(violation -> {
            var error = String.format(violation.getMessage());
            errorMessages.add(error);
        });

        if (!StringUtils.isEmptyOrWhitespace(identificator)) {
            atts.addFlashAttribute("identificator", identificator);
        }

        atts.addFlashAttribute("messages", errorMessages);

        return "redirect:/order/warehouse";
    }
	
}
