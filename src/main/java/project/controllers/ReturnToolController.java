package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import project.services.ToolRentService;

@Controller
public class ReturnToolController {
	
	private final ToolRentService toolRentService;
	
	@Autowired
	public ReturnToolController(ToolRentService toolRentService) {
		this.toolRentService = toolRentService;
	}
	
	@GetMapping(path = "/returnTool")
	public String get() {
		return "returnTool";
	}
	
	@RequestMapping(path = "/returnTool", method = RequestMethod.POST)
	public String returnTool(@RequestParam(name = "employeeIdent", defaultValue = "null") String employeeIdent,
			                 @RequestParam(name = "toolIdent", defaultValue = "null") String toolIdent,
			                 @RequestParam(name = "selectTool", defaultValue = "null") String selectTool) {
		
		toolRentService.handOverTheTool(employeeIdent, toolIdent, selectTool);
		
		return "returnTool";
	}

}
