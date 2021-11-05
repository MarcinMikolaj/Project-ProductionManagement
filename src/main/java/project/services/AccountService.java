package project.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import project.entities.Account;
import project.entities.Employee;
import project.entities.Manager;
import project.entities.PersonalInformation;

import project.repositories.AccountRepository;
import project.repositories.ManagerRepository;
import project.repositories.EmployeeRepository;

import project.repositories.PersonalInformationRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	
	//private AccountRepositoryJPA accountRepositoryJPA;
	private EmployeeRepository employeeRepository;
	private ManagerRepository managerRepository;
	private PersonalInformationRepository personalInformationRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	//@Autowired
	//public void setAccountRepositoryJPA(AccountRepositoryJPA accountRepositoryJPA) {
	//	this.accountRepositoryJPA = accountRepositoryJPA;
	//}
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Autowired
	public void setManagerRepository(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public void setPersonalInformationRepository(PersonalInformationRepository personalInformationRepository) {
		this.personalInformationRepository = personalInformationRepository;
	}
	
	public boolean saveInDataBase(Account account) {
		if(dataRecordingConditions(account) == true) {		
			account.setRole("ROLE_EMPLOYEE");
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			//accountRepositoryJPA.save(account); 
			accountRepository.save(account);
			System.out.println("Do bazy zapisano nowe konto: " + account.toString());
			return true;
		}
		System.out.println("AccountService.saveInDataBase: cant save account in data base");
		return false;
	}
	
	private boolean dataRecordingConditions(Account account) {
		if(account != null && 
				accountRepository.existByUsername(account.getUsername()) == false &&
						accountRepository.existByEmail(account.getEmail()) == false &&
				account.getPassword().equals(account.getConfirmPassword()) == true &&
				account.isPrivacyPolicy() == true &&
				account.isTermsAndConditions() == true) {
			return true;
		}
		return false;
	}
	
	
	//****** Personal Information ***********
	
	public boolean addPersonalInformation(String username, PersonalInformation personalInformation) {
		
		if(accountRepository.existByUsername(username) == false) {
			System.out.println("AccountService:addPersonalInformation: niezapisuje");
			return false;
		}
		   personalInformationRepository.save(personalInformation);
		   Account account = accountRepository.findByUsername(username);
		   personalInformation.setAccount(account);
		   account.setPersonalInformation(personalInformation);		 	   
		   accountRepository.update(account);	 		   		 		   
		   return true;
	}
	
	public boolean addEmployee(String username, Employee employee) {
		
		//if(employeeRepository.existByIdentificator(employee.getIdentificator()) == true) {
		//	System.err.println("AccountService.addEmployee: nie można dodać pracownika");
		//	return false;
		//}
		
		if(employee == null || employee.getId() != null) {
			System.err.println("AccountService.addEmployee: nie można dodać pracownika");
			return false;
		}
		
		Account account = accountRepository.findByUsername(username);
		employeeRepository.save(employee);
		account.setEmployee(employee);	
		accountRepository.update(account);
		
		Account account1 = accountRepository.findByUsername(username);
		System.out.println(account1.getEmployee().getPosition() + "      ZZZZZZZZZZZZZZZZZZZ");
		return true;
	}
	
	
	public boolean addManager(String username, Manager manager) {
		
		if(accountRepository.existByUsername(username) == false) {
			System.out.println("Nie można przypisać encji manager do konta, konto nie istnieje");
			return false;
		}
			
		Account account = accountRepository.findByUsername(username);
		
		if(account.getManager() != null || account.getEmployee() != null) {
			System.out.println("Nie można przypisać encji manager do konta, do konta jest już przypisana encja");
			return false;
		}
		
		managerRepository.save(manager)	;
		account.setManager(manager);
		accountRepository.update(account);
		return true;	
	}
	
	//Return Manager, parameters: name and last name form PersonalInformation entity	
	public Manager findManagerByNameAndLastName(String name, String lastName) {
		
		if(personalInformationRepository.existByName(name) == false || 
				personalInformationRepository.existByLastName(lastName) == false) {
			System.out.println("AccountServie.findManagerByNameAndLastName: Nie można znaleść encji dane osobowe, złe imie lub nazwisko");
			return null;
		}
		
		PersonalInformation personalInformation = personalInformationRepository.findByNameAndLastName(name, lastName);
		
		if(personalInformation.getAccount() == null) {
			System.out.println("AccountServie.findManagerByNameAndLastName: brak przypisanej encjii account do encji personalInformation");
			return null;
		}
		
		
		//***
		if(accountRepository.findByUsername(personalInformation.getAccount().getUsername()) == null){
			System.out.println("AccountService.findManagerByNameAndLastName: Nie odnaleziono konta w bazie");			
			return null;
		}

		Account account = accountRepository.findByUsername(personalInformation.getAccount().getUsername());
		//***
		//Account account = personalInformation.getAccount();	
		
		if(account.getManager() == null) {
			System.out.println("AccountServie.findManagerByNameAndLastName: brak przypisanej encjii manager do encji account");
			return null;
		}
		
		Manager manager = account.getManager();		
		return manager;
	}
	
	
	public Employee findEmployeeByNameAndLastName(String name, String lastName) {
		
		if(personalInformationRepository.existByName(name) == false || personalInformationRepository.existByLastName(lastName) == false) {
			System.out.println("AccountService.findEmployeeByNameAndLastName: Nie można znaleść encji dane osobowe, złe imie lub nazwisko");			
			return null;
		}
		
		PersonalInformation personalInformation = personalInformationRepository.findByNameAndLastName(name, lastName);
		
		if(accountRepository.findByUsername(personalInformation.getAccount().getUsername()) == null){
			System.out.println("AccountService.findByUsername: Nie odnaleziono konta w bazie");			
			return null;
		}

		Account account = accountRepository.findByUsername(personalInformation.getAccount().getUsername());
		
		if(account.getEmployee() != null) {
			Employee employee = account.getEmployee();	
			System.out.println(employee.toString());
			return employee;		
		}
		
		return null;		
	}
	
}
