package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.ui.Model;

import project.services.AccountService;

import project.entities.Employee;

@Controller
public class UserStatistics {
	
	private AccountService accountService;
	private Employee empolyee;
	
	@Autowired
	public void AccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@RequestMapping(path = "/toolRoom/statistics", method = RequestMethod.GET)
	public String get(Model model) {
		
		if(empolyee != null) {
		model.addAttribute("drillList", empolyee.getDrillOwned());
		model.addAttribute("latheKnifeCutterList", empolyee.getLatheKnifeCutterOwned());
		model.addAttribute("screwTapList", empolyee.getScrewTapOwned());
		 empolyee.toString();
		}
		
			   
		return "statistics";
	}
	
	@RequestMapping(path = "/toolRoom/statistics/find", method = RequestMethod.POST)
	public String post(@RequestParam(name = "name")String name, @RequestParam(name = "lastName")String lastName) {		
		this.empolyee = accountService.findEmployeeByNameAndLastName(name, lastName);	
		return "redirect:/toolRoom/statistics";
	}

}
