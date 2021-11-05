package project.controllers.execution;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import project.entities.execution.Project;

import project.services.ExecutionService;

@Controller
public class ManageTheProjectController {
	
	private final ExecutionService executionService;
	
	@Autowired
	public ManageTheProjectController(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	
	//******************* Create new Project **********************
	
	@GetMapping(path = "/createNewProject")
	public String createNewProject_GET(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("managerName");
		model.addAttribute("managerLastName");
		return "createNewProject";
	}
	
	@RequestMapping(path = "/createNewProject", method = RequestMethod.POST)
	public String createNewProject_POST(@ModelAttribute Project project,
			@RequestParam(name = "managerName", defaultValue = "null") String managerName,
			@RequestParam(name = "managerLastName", defaultValue = "null") String managerLastName) {
		
		executionService.addProject(project, managerName, managerLastName);
		
		return "createNewProject";
	}
}
