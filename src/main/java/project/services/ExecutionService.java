package project.services;

import org.springframework.stereotype.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import org.springframework.beans.factory.annotation.Autowired;

//import project.repositories.ManagerRepository;
import project.repositories.execution.ProjectRepositoryJPA;


import project.entities.execution.Project;
import project.entities.Manager;
import project.entities.Employee;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ExecutionService {
	
	private ProjectRepositoryJPA projectRepositoryJPA;
	private AccountService accountService;
	
	@Autowired
	public void setProjectRepositoryJPA(ProjectRepositoryJPA projectRepositoryJPA) {
		this.projectRepositoryJPA = projectRepositoryJPA;
	}
		
	@Autowired
	public void setAccountService(AccountService accountService) {
			this.accountService = accountService;	
	}
	
	public void addProject(Project project, String managerName,String managerLastname) {	
		Manager manager = accountService.findManagerByNameAndLastName(managerName, managerLastname);
		project.setManager(manager);
		projectRepositoryJPA.save(project);
	}
	
	
	public boolean addEmployeeToProject(String projectIdentificator, String employeeName, String employeeLastName) {
				
		Employee employee = accountService.findEmployeeByNameAndLastName(employeeName, employeeLastName);
			
		if(projectRepositoryJPA.existByIdentificator(projectIdentificator) == false) {
			System.out.println("ExecutionService.addEmployeeToProject: projectRepositoryJPA.existByIdentificator(projectIdentificator) == false");
			return false;
		}
			
		Project project = projectRepositoryJPA.findByIdentificator(projectIdentificator);		
		project.getAssignedEmployees().add(employee);
		projectRepositoryJPA.update(project);
		System.out.println("Do projektu:" + project.getIdentificator() + " Został przypisany pracownik:" + employee.getIdentificator());
		return true;
	}
	
	
}
