package project.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import project.entities.Account;
import project.entities.Employee;
import project.entities.PersonalInformation;

import project.services.AccountService;

@Controller
public class RegisterController {
	
	private AccountService accountService;
	private String key = null;
	
	@Autowired
	public RegisterController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	@GetMapping(path = "/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "register";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String formRegister(Model model, @ModelAttribute Account account) {
		model.addAttribute("personal", new PersonalInformation());
		boolean check = accountService.saveInDataBase(account);
		this.key = account.getUsername();
		
		if(check == true) {
			return "personal";
		}
		return "register";
		
	}
	
	//************ Controller for Personal Information Form ***********
	 
	
	
	@GetMapping(path = "/addPersonalInformation")
	public String personalInformation_GET(Model model) {
		model.addAttribute("personal", new PersonalInformation());
		return "personal";
	}
	
	@RequestMapping(path = "/addPersonalInformation", method = RequestMethod.POST)
	public String personalInformation_POST(Model model, @ModelAttribute PersonalInformation personal,
			@RequestParam(name = "employmentStructure", defaultValue = "null") String employmentStructure) {
		    model.addAttribute("personal", new PersonalInformation());   
		    model.addAttribute("employee", new Employee());
			accountService.addPersonalInformation(key, personal);
					
			if(employmentStructure.equals("Employee")) {
				System.out.println("przekierowanie");
				return "employeeForm";
			}
			
		return "personal";
	}
	
	
	//****************** Add Employee ********************
	
	@GetMapping(path = "/register/addEmployee")
	public String employeeForm_GET(Model model) {
		model.addAttribute("employee", new Employee());
		return "employeeForm";
	}
	
	@RequestMapping(path = "/register/addEmployee", method = RequestMethod.POST)
	public String employeeForm_POST(@ModelAttribute Employee employee) {
		accountService.addEmployee(key, employee);
		key = null;
		return "successful";
	}
	
	//********************* Successful Controller ************************
	
	@GetMapping(path = "/register/successful")
	public String successful_GET() {
		return "successful";
	}
	
}
