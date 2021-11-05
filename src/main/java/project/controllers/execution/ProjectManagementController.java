package project.controllers.execution;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectManagementController {
	
	@GetMapping(path = "/projectManagement")
	public String projectManagement_GET() {
		return "projectManagement";
	}

}
