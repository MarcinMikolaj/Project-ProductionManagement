package project.controllers.execution;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import project.services.ExecutionService;

@Controller
public class addEmployeeToProjectController {
	
	private ExecutionService executionService;
	
	@Autowired
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	@GetMapping(path = "/addEmployeeToProject")
	public String addEmployeeToProject_GET(Model model) {
		model.addAttribute("employeeName");
		model.addAttribute("employeeLastName");
		model.addAttribute("projectIdentificator");
		return "addEmployeeToProject";
	}
	
	@RequestMapping(path = "/addEmployeeToProject", method = RequestMethod.POST)
	public String addEmployeeToProject_POST(
			@RequestParam(name = "employeeName", defaultValue = "null")String employeeName,
			@RequestParam(name = "employeeLastName", defaultValue = "null")String employeeLastName,
			@RequestParam(name = "projectIdentificator", defaultValue = "null")String projectIdentificator
			) {
		
		executionService.addEmployeeToProject(projectIdentificator, employeeName, employeeLastName);
		
		return "addEmployeeToProject";
	}

}
