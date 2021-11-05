package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.Model;

import project.services.ToolRentService;

import project.entities.tools.Drill;
import project.entities.tools.ScrewTap;
import project.entities.tools.LatheKnifeCutter;

@Controller
public class ToolManagementController {
	
	private ToolRentService toolRentService;
	
	@Autowired
	public void setToolRentService(ToolRentService toolRentService) {
		this.toolRentService = toolRentService;
	}
	
	//******************* Ad Drill ***************
	
	@GetMapping(path = "/returnTool/addDrill")
	public String addDrill_GET(Model model) {
		model.addAttribute("drill", new Drill());
		model.addAttribute("drillList", toolRentService.getDrillListFromToolRent());
		return "addDrill";
	}
	
	@RequestMapping(path = "/returnTool/addDrill", method = RequestMethod.POST)
	public String addDrill_POST(@ModelAttribute Drill drill) {
		toolRentService.addDrill(drill);
		return "redirect:addDrill";
	}
	
	//******************* Ad Screw Tap ***************
	
	@GetMapping(path = "/returnTool/addScrewTap")
	public String addDScrewTap_GET(Model model) {
		model.addAttribute("screwTap", new ScrewTap());
		model.addAttribute("screwTapList", toolRentService.getScrewTapListFromToolRent());
		return "addScrewTap";
	}
		
	@RequestMapping(path = "/returnTool/addScrewTap", method = RequestMethod.POST)
	public String addScrewTap_POST(@ModelAttribute ScrewTap screwTap) {
		toolRentService.addScrewTap(screwTap);
		return "redirect:addScrewTap";
	}
	
	//******************* Add Lathes Knife Cutter******************
	
	@GetMapping(path = "/returnTool/addLatheKnifeCutter")
	public String addLatheKnifeCutter_GET(Model model) {
		model.addAttribute("latheKnifeCutter", new LatheKnifeCutter());
		model.addAttribute("latheKnifeCutterList", toolRentService.getLatheKnifeCutterListFromToolRent());
		return "addLatheKnifeCutter";
	}
		
	@RequestMapping(path = "/returnTool/addLatheKnifeCutter", method = RequestMethod.POST)
	public String addLatheKnifeCutter_POST(@ModelAttribute LatheKnifeCutter latheKnifeCutter) {
		toolRentService.addLatheKnifeCutter(latheKnifeCutter);
		return "redirect:addLatheKnifeCutter";
	}
	
	
	//********************** Remove tool **********************
	
	@GetMapping(path = "/returnTool/removeTool")
	public String removeTool_GET(Model model) {
		model.addAttribute("identificator");
		model.addAttribute("selectTool");
		return "removeTool";
	}
		
	@RequestMapping(path = "/returnTool/removeTool", method = RequestMethod.POST)
	public String aremoveTool_POST(@RequestParam(name = "identificator", defaultValue = "null") String identificator,
			@RequestParam(name = "selectTool", defaultValue = "null") String selectTool) {
		toolRentService.removeTool(identificator, selectTool);
		return "removeTool";
	}
	
}
