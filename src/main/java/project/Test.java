package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.entities.PersonalInformation;

import project.entities.execution.Project;
import project.entities.Account;
import project.entities.Employee;
import project.entities.Manager;
import project.entities.tools.Drill;
import project.entities.tools.LatheKnifeCutter;
import project.entities.tools.ScrewTap;


import project.repositories.PersonalInformationRepository;

import project.repositories.execution.ProjectRepositoryJPA;
import project.repositories.AccountRepository;
import project.repositories.EmployeeRepository;

import project.services.AccountService;
import project.services.ExecutionService;
import project.services.ToolRentService;

@Component
public class Test {
	
	private PersonalInformationRepository personalInformationRepository;
	private AccountRepository accountRepository;
	private ToolRentService toolRentService;	
	private EmployeeRepository employeeRepository;
	private AccountService accountService;
	private ExecutionService executionService;
	private ProjectRepositoryJPA projectRepositoryJPA;
	
	
	
	@Autowired
	public Test(PersonalInformationRepository personalInformationRepository) {
		this.personalInformationRepository = personalInformationRepository;
	}
	
	@Autowired
	public void setAccount(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Autowired
	public void setToolRentService(ToolRentService toolRentService) {
		this.toolRentService = toolRentService;
	}
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	@Autowired
	public void setProjectRepositoryJPA(ProjectRepositoryJPA projectRepositoryJPA) {
		this.projectRepositoryJPA = projectRepositoryJPA;
	}
	
	
	public Test() {
		dataBaseTest();
		
	}
	
	
	public void createTestAccount() {
		Account account = new Account("admin", "123");
		accountRepository.save(account);
	}
	
	public void dataBaseTest() {
		PersonalInformation object = new PersonalInformation("Marcin", "Mikołajczyk", "515471590", "marcin3246a55@o2.pl","12345678910", "Nowy Sącz", "Biegonicka 22");
		personalInformationRepository.save(object);
		
		
		//************Create Administrator Account****************
		Account account = new Account("root", "root", "ROLE_ADMIN", "@", true, true);
		Account account1 = new Account("user", "12345", "ROLE_USER", "@", true, true);
		
		accountRepository.save(account);
		accountRepository.save(account1);
		
		//***********Create Tool Rent***************
		toolRentService.cretateToolRoom();
		
		//*********Create Drills*************
		Drill drill01 = new Drill("W1", 3, "65mm", "37mm", "3.2mm", "124st", "metal", "HSS", "cylindryczny", false);
		Drill drill02 = new Drill("W2", 2, "100mm", "63mm", "5", "124st", "metal", "HSS", "cylindryczny", false);
		Drill drill03 = new Drill("W3", 1, "120mm", "80mm", "10", "124st", "metal", "HSS", "cylindryczny", false);
		Drill drill04 = new Drill("W4", 1, "80mm", "40mm", "8.5", "124st", "metal", "HSS", "cylindryczny", false);
		Drill drill05 = new Drill("W5", 1, "130mm", "74mm", "14", "124st", "metal", "HSS", "cylindryczny", false);
		
	    toolRentService.addDrill(drill01);
	    toolRentService.addDrill(drill02);
	    toolRentService.addDrill(drill03);
	    toolRentService.addDrill(drill04);
	    toolRentService.addDrill(drill05);
	        
	    //******Create Screw Tap******************************
	    
	   	    
	    ScrewTap scrawTap01 = new ScrewTap("G1", "Horn / Artpol", "80", "40", "M7x1.00", "metryczny,zwykły,prawoskrętny", "60stopni", "60HRc", "NC6");
	    ScrewTap scrawTap02 = new ScrewTap("G2", "Horn / Artpol", "80", "40", "M7x1.00", "metryczny,zwykły,prawoskrętny", "60stopni", "60HRc", "NC6");
	    ScrewTap scrawTap03 = new ScrewTap("G3", "Horn / Artpol", "80", "40", "M7x1.00", "metryczny,zwykły,prawoskrętny", "60stopni", "60HRc", "NC6");
	    ScrewTap scrawTap04 = new ScrewTap("G4", "Horn / Artpol", "80", "40", "M7x1.00", "metryczny,zwykły,prawoskrętny", "60stopni", "60HRc", "NC6");
	    ScrewTap scrawTap05 = new ScrewTap("G5", "Horn / Artpol", "80", "40", "M7x1.00", "metryczny,zwykły,prawoskrętny", "60stopni", "60HRc", "NC6");
	    
	    toolRentService.addScrewTap(scrawTap01);
	    toolRentService.addScrewTap(scrawTap02);
	    toolRentService.addScrewTap(scrawTap03);
	    toolRentService.addScrewTap(scrawTap04);
	    toolRentService.addScrewTap(scrawTap05);
			
		//**************Create Employee***************
	    
	    
	    //Employee 00    
	    Account account00 = new Account("tomek", "123", "@", "ROLE_USER", true, true);
	    accountRepository.save(account00);    
	    PersonalInformation employeePersonalInformation00 = new PersonalInformation("tomek", "t", "111111111", "metusz@o2.pl","12345678910", "Nowy Sącz", "ulica");
	    accountService.addPersonalInformation("tomek", employeePersonalInformation00);    
	    Employee employee00 = new Employee("P0", "test");    
	    accountService.addEmployee("tomek", employee00);
	     
	    //Employee 01    
	    Account account01 = new Account("mac123", "123", "@", "ROLE_USER", true, true);
	    accountRepository.save(account01);
	    PersonalInformation employeePersonalInformation01 = new PersonalInformation("Maciek", "banan", "222222222", "maciekbana@o2.pl","12345678910", "Nowy Sącz", "Zielona 22");
	    accountService.addPersonalInformation("mac123", employeePersonalInformation01);
	    Employee employee01 = new Employee("P1", "Operator CNC");
	    accountService.addEmployee("mac123", employee01);
	    
	    
	    
	    
	    Employee employee02 = new Employee("P2", "Operator CNC");
	    Employee employee03 = new Employee("P3", "Operator CNC");
	    Employee employee04 = new Employee("P4", "Operator CNC");
	    Employee employee05 = new Employee("P5", "Operator CNC");
	    Employee employee06 = new Employee("P6", "Operator CNC");
	    Employee employee07 = new Employee("P7", "Operator CNC");
	    Employee employee08 = new Employee("P8", "Operator CNC");
	    
	   
	    employeeRepository.save(employee02);
	    employeeRepository.save(employee03);
	    employeeRepository.save(employee04);
	    employeeRepository.save(employee05);
	    employeeRepository.save(employee06);
	    employeeRepository.save(employee07);
	    employeeRepository.save(employee08);
	    
	    
	    // ***************Create Manager **********************
	    
	    Account managerAccount1 = new Account("paulina", "123", "ROLE_ADMIN", "@", true, true);
		Account managerAccount2 = new Account("weronika", "123", "ROLE_ADMIN", "@", true, true);
		
		accountRepository.save(managerAccount1);
		accountRepository.save(managerAccount2);
	      
	    PersonalInformation managerPersonalInformation1 = new PersonalInformation("paulina", "p", "111111111", "metusz@o2.pl","12345678910", "Nowy Sącz", "ulica");
		PersonalInformation managerPersonalInformation2 = new PersonalInformation("weronika", "w", "222222222", "miłosz@o2.pl","12345678910", "Nowy Sącz", "ulica");
		
		accountService.addPersonalInformation("paulina", managerPersonalInformation1);
		accountService.addPersonalInformation("weronika", managerPersonalInformation2);
		
		Manager manager01 = new Manager("Ma1");
	    Manager manager02 = new Manager("Ma2");
		
	    accountService.addManager("paulina",manager01);
	    accountService.addManager("weronika",manager02);
	    
	    //***************Create LatheKnifeCutter*********************
	    
	    LatheKnifeCutter latheKnifeCutter01 = new LatheKnifeCutter("N1", "AKKO", "ADKT K-R 2020 3 T20 AKKO", "Przecinak prawy", "125mm", "20mm");
	    LatheKnifeCutter latheKnifeCutter02 = new LatheKnifeCutter("N2", "AKKO", "ADKT K-R 2020 3 T20 AKKO", "Przecinak prawy", "125mm", "20mm");
	    LatheKnifeCutter latheKnifeCutter03 = new LatheKnifeCutter("N3", "AKKO", "ADKT K-R 2020 3 T20 AKKO", "Przecinak prawy", "125mm", "20mm");
	    LatheKnifeCutter latheKnifeCutter04 = new LatheKnifeCutter("N4", "AKKO", "ADKT K-R 2020 3 T20 AKKO", "Przecinak prawy", "125mm", "20mm");
	    LatheKnifeCutter latheKnifeCutter05 = new LatheKnifeCutter("N5", "AKKO", "ADKT K-R 2020 3 T20 AKKO", "Przecinak prawy", "125mm", "20mm");
	    
	    toolRentService.addLatheKnifeCutter(latheKnifeCutter01);
	    toolRentService.addLatheKnifeCutter(latheKnifeCutter02);
	    toolRentService.addLatheKnifeCutter(latheKnifeCutter03);
	    toolRentService.addLatheKnifeCutter(latheKnifeCutter04);
	    toolRentService.addLatheKnifeCutter(latheKnifeCutter05);
	    
	    //*******Rent Drills*******
	    toolRentService.rentDrill("P1", "W1");
	    toolRentService.rentDrill("P2", "W3");
	    toolRentService.rentDrill("P2", "W4");
	    
	    toolRentService.handOverTheDrill("P2", "W3");
	    
	    //*******Rent Screw Tap*******
	    toolRentService.rentScrewTap("P1", "G1");
	    toolRentService.rentScrewTap("P2", "G2");
	    toolRentService.rentScrewTap("P4", "G4");
	    
	    toolRentService.handOverTheScrewTap("P2", "G2");
	    
	    //*******Rent Lathes Knife Cutter*******
	    toolRentService.rentLatheKnifeCutter("P2", "N4");
	    toolRentService.rentLatheKnifeCutter("P4", "N1");
	    toolRentService.rentLatheKnifeCutter("P2", "N5");
	    
	    
	    //*******************************Project Manager***************************************
	    
	    Project project01 = new Project("pro1", "", "", false);
	    Project project02 = new Project("pro2", "", "", false);
	    projectRepositoryJPA.save(project01);
	    projectRepositoryJPA.save(project02);   
	    
	    executionService.addEmployeeToProject("pro1", "tomek", "t");
	    executionService.addEmployeeToProject("pro1", "Maciek", "banan");	    
	   // executionService.addEmployeeToProject("pro2", "Maciek", "banan");
	   	           
	}

}
