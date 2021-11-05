package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import project.services.ToolRentService;

@Controller
public class RentToolController {
	
	private ToolRentService toolRentService;
	
	@Autowired
	public void setToolRentService(ToolRentService toolRentService) {
		this.toolRentService = toolRentService;
	}
	
	@GetMapping(path = "/toolRoom")
	public String toolRoom_GET() {
		return "toolRoom";
	}
	
	@GetMapping(path = "/rent")
	public String renttool(Model model) {
		return "rent";
	}
	
	@RequestMapping(path = "/rent", method = RequestMethod.POST)
	public String rent(@RequestParam(name = "employeeIdent", defaultValue = "null") String employeeIdent,
			           @RequestParam(name = "toolIdent", defaultValue = "null") String toolIdent,
			           @RequestParam(name = "selectTool", defaultValue = "null") String selectTool) {
		
		System.out.println("Próba przypisania obiektów:" + employeeIdent + " " + toolIdent + " " + selectTool);
		
		toolRentService.rentTool(employeeIdent, toolIdent, selectTool);
		
		return "rent";
	}

}
