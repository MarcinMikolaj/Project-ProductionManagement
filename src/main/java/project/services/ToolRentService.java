package project.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import project.repositories.ToolRentRepository;
import project.repositories.EmployeeRepository;
import project.repositories.tools.DrillRepository;
import project.repositories.tools.LatheKnifeCutterRepository;
import project.repositories.tools.UpdateRepoForSpring;
import project.repositories.tools.ScrewTapRepository;

import project.entities.ToolRent;
import project.entities.Employee;

import project.entities.tools.Drill;
import project.entities.tools.ScrewTap;
import project.entities.tools.LatheKnifeCutter;

import java.util.List;
import java.util.ArrayList;

@Service
public class ToolRentService {
	
	private final Long TOOL_ID = 1L;
	
	private EmployeeRepository employeeRepository;
	private ToolRentRepository toolRentRepository;	
	private DrillRepository drillRepository;
	private ScrewTapRepository screwTapRepository;
	private LatheKnifeCutterRepository latheKnifeCutterRepository;
	
	private UpdateRepoForSpring updateRepoForSpring;
	
	@Autowired
	public void setUpdateRepoForSpring(UpdateRepoForSpring updateRepoForSpring) {
		this.updateRepoForSpring = updateRepoForSpring;
	}
	
	@Autowired
	public void setToolRentService(ToolRentRepository toolRentRepository) {
		this.toolRentRepository = toolRentRepository;
	}
	
	@Autowired
	public void setDrillRepository(DrillRepository drillRepository) {
		this.drillRepository = drillRepository;
	}
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Autowired
	public void setScrewTapRepository(ScrewTapRepository screwTapRepository) {
		this.screwTapRepository = screwTapRepository;
	}
	
	@Autowired
	public void setLatheKnifeCutterRepository(LatheKnifeCutterRepository latheKnifeCutterRepository) {
		this.latheKnifeCutterRepository = latheKnifeCutterRepository;
	}
	
	public void cretateToolRoom() {
		ToolRent toolRent = new ToolRent();
		toolRentRepository.save(toolRent);
	}
	
	
	//********ADD TOOLS AND UPDATE TO TOOL RENT********
	
	public boolean addDrill(Drill drill) {	
		
		if(drill == null) {
			return false;
		}
		
		if(drillRepository.existByIdentificator(drill.getIdentificator()) == true 
				&& drillRepository.findByIdentificator(drill.getIdentificator()).getId() != null) {
			drill.setId(drillRepository.findByIdentificator(drill.getIdentificator()).getId());
			updateRepoForSpring.updateDrill(drill);
			System.out.println("Zaaktualizowano wiertło: " + drill.toString());
			return true;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		drillRepository.save(drill);
		toolRent.getDrillList().add(drill);
		toolRentRepository.update(toolRent);
		//System.out.println("Do bazy dodano wiertło: " + drill.toString());
		return true;
	}
	
	public boolean addScrewTap(ScrewTap screwTap) {
		
		if(screwTap == null) {
			return false;
		}
		
		if(screwTapRepository.existByIdentificator(screwTap.getIdentificator()) == true 
				&& screwTapRepository.findByIdentificator(screwTap.getIdentificator()).getId() != null) {
			screwTap.setId(screwTapRepository.findByIdentificator(screwTap.getIdentificator()).getId());
			updateRepoForSpring.updateScrewTap(screwTap);
			System.out.println("Zaaktualizowano Gwintownik: " + screwTap.toString());
			return true;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		screwTapRepository.save(screwTap);
		toolRent.getScrewTapList().add(screwTap);
		toolRentRepository.update(toolRent);
		//System.out.println("Do bazy dodano gwintownik: " + screwTap.toString());
		return true;
	}
	
	
	public boolean addLatheKnifeCutter(LatheKnifeCutter latheKnifeCutter) {
		
		if(latheKnifeCutter == null) {
			return false;
		}
		
		if(latheKnifeCutterRepository.existByIdentificator(latheKnifeCutter.getIdentificator()) == true 
				&& latheKnifeCutterRepository.findByIdentificator(latheKnifeCutter.getIdentificator()).getId() != null) {			
			latheKnifeCutter.setId(latheKnifeCutterRepository.findByIdentificator(latheKnifeCutter.getIdentificator()).getId());
			updateRepoForSpring.updateLatheKnifeCutter(latheKnifeCutter);
			System.out.println("Zaaktualizowano Noż tokarsi przecinak: " + latheKnifeCutter.toString());
			return true;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		latheKnifeCutterRepository.save(latheKnifeCutter);	
		toolRent.getLatheKnifeCutterList().add(latheKnifeCutter);
		toolRentRepository.update(toolRent);		
		//System.out.println("Do bazy dodano nóż tokarski przecinak: " + latheKnifeCutter.toString());
		return true;
	}
		
	//********************** REMOVE TOOL **********************
	
	public void removeTool(String toolIdentificator, String toolType) {
		if(toolType.equals("Drill")) {
			removeDrill(toolIdentificator);			
		}
		else if(toolType.equals("Lathes Knife Cutter")) {
			removeLatheKnifeCutter(toolIdentificator);			
		}
         else if(toolType.equals("Screw Tap")) {
        	 removeScrewTap(toolIdentificator);      	 
		}
	}
	
	//Remove Drill
	private boolean removeDrill(String identificator) {
		
		if(identificator == null || drillRepository.existByIdentificator(identificator) == false) {
			System.out.println("ToolRentService.removeDrill: nie można znaleść obiektu");
			return false;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		Drill drill = drillRepository.findByIdentificator(identificator);	
		
		if(drill.isItIsOnLoad() == true || drill.getEmployee() != null) {
			System.out.println("ToolRentService.removeDrill: nie można usunąć narzędzia ponieważ jest wypożyczone");
			return false;
		}
		
		toolRent.getDrillList().removeIf(b -> b.getIdentificator().equals(drill.getIdentificator()));
		toolRentRepository.update(toolRent);			
		drillRepository.deleteById(drillRepository.findByIdentificator(identificator).getId());
		System.out.println("Usunięto wiertło, identificator = " + identificator);
		return true;
	}
	
	
	//Remove Screw Tap
	private boolean removeScrewTap(String identificator) {
		if(identificator == null || screwTapRepository.existByIdentificator(identificator) == false) {
			System.out.println("ToolRentService.removeScrewTap: nie można znaleść obiektu");
			return false;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		ScrewTap screwTap = screwTapRepository.findByIdentificator(identificator);	
		
		if(screwTap.isItIsOnLoad() == true || screwTap.getEmployee() != null) {
			System.out.println("ToolRentService.ScrewTap: nie można usunąć narzędzia ponieważ jest wypożyczone");
			return false;
		}
		
		toolRent.getScrewTapList().removeIf(b -> b.getIdentificator().equals(screwTap.getIdentificator()));
		toolRentRepository.update(toolRent);	
		screwTapRepository.deleteById(screwTapRepository.findByIdentificator(identificator).getId());
		System.out.println("Usunięto gwintownik, identificator = " + identificator);
		return true;
	}
	
	//Remove Lathes Knife Cutter
	private boolean removeLatheKnifeCutter(String identificator) {
		if(identificator == null || latheKnifeCutterRepository.existByIdentificator(identificator) == false) {
			System.out.println("ToolRentService.removeLatheKnifeCutter: nie można znaleść obiektu");
			return false;
		}
		
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		LatheKnifeCutter latheKnifeCutter = latheKnifeCutterRepository.findByIdentificator(identificator);	
		
		if(latheKnifeCutter.isItIsOnLoad() == true || latheKnifeCutter.getEmployee() != null) {
			System.out.println("ToolRentService.removeLatheKnifeCutter: nie można usunąć narzędzia ponieważ jest wypożyczone");
			return false;
		}
		
		toolRent.getLatheKnifeCutterList().removeIf(b -> b.getIdentificator().equals(latheKnifeCutter.getIdentificator()));
		toolRentRepository.update(toolRent);	
		latheKnifeCutterRepository.deleteById(latheKnifeCutterRepository.findByIdentificator(identificator).getId());
		System.out.println("Usunięto noż tokarski przecinak, identificator = " + identificator);
		return true;
	}
	
	
	
	
	
	//*******RENT TOOL***********
	
	public void rentTool(String employeeIdent, String toolIdent, String toolType) {
		if(toolType.equals("Drill")) {
			rentDrill(employeeIdent, toolIdent);
		}
		else if(toolType.equals("Lathes Knife Cutter")) {
			rentLatheKnifeCutter(employeeIdent, toolIdent);
		}
         else if(toolType.equals("Screw Tap")) {
        	 rentScrewTap(employeeIdent, toolIdent);
		}
	}
	
	public boolean rentDrill(String employeeIdent, String drillIdent) {
		
		if(drillRepository.existByIdentificator(drillIdent) == false) {
			System.out.println("Nie znaleziono wiertła w bazie danych");
			return false;			
		}
				
		Drill drill = drillRepository.findByIdentificator(drillIdent);
		
		if(drill.isItIsOnLoad() == false) {
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);
			
			drillRepository.updateItIsOnLoad(true, drill.getId());
			drillRepository.updateEmployee(employee, drill.getId());
			
			drill.setItIsOnLoad(true);
			
			drill.setEmployee(employee);
			employee.getDrillOwned().add(drill);		
			employeeRepository.update(employee);
			
			return true;
		}
		
		return false;
	}
	
	public boolean rentScrewTap(String employeeIdent, String screwTapIdent) {
		
		if(screwTapRepository.existByIdentificator(screwTapIdent) == false) {
			System.out.println("Nie znaleziono gwintownika w bazie danych");
			return false;
		}
			
		ScrewTap screwTap = screwTapRepository.findByIdentificator(screwTapIdent);
		
		if(screwTap.isItIsOnLoad() == false) {
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);	
			
			screwTapRepository.updateIsItOnLoad(true, screwTap.getId());
			screwTapRepository.updateEmployee(employee, screwTap.getId());
			
			screwTap.setItIsOnLoad(true);
			screwTap.setEmployee(employee);
			employee.getScrewTapOwned().add(screwTap);		
			employeeRepository.update(employee);	
			return true;
		}
			
		return false;
	}
	
	public boolean rentLatheKnifeCutter(String employeeIdent, String latheKnifeCutterIdent) {
		
		if(latheKnifeCutterRepository.existByIdentificator(latheKnifeCutterIdent) == false) {
			System.out.println("Nie znaleziono przecinaka w bazie danych");
			return false;
		}
		
		LatheKnifeCutter latheKnifeCutter = latheKnifeCutterRepository.findByIdentificator(latheKnifeCutterIdent);
			
		if(latheKnifeCutter.isItIsOnLoad() == false) {
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);
			
			latheKnifeCutterRepository.updateItIsOnLoad(true, latheKnifeCutter.getId());
			latheKnifeCutterRepository.updateEmployee(employee, latheKnifeCutter.getId());
			
			latheKnifeCutter.setItIsOnLoad(true);
			latheKnifeCutter.setEmployee(employee);
			employee.getLatheKnifeCutterOwned().add(latheKnifeCutter);		
			employeeRepository.update(employee);	
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	//*********Hand over the Tools*************
	
	public void handOverTheTool(String employeeIdent, String toolIdent, String toolType) {
		if(toolType.equals("Drill")) {
			handOverTheDrill(employeeIdent, toolIdent);
		}
		else if(toolType.equals("Lathes Knife Cutter")) {
			handOverTheLatheKnifeCutter(employeeIdent, toolIdent);
		}
         else if(toolType.equals("Screw Tap")) {
        	 handOverTheScrewTap(employeeIdent, toolIdent);
		}
	}
	
	
	public boolean handOverTheDrill(String employeeIdent, String drillIdent) {
		
		if(drillRepository.existByIdentificator(drillIdent) == false) {
			System.out.println("Nie zanleziono wiertła w bazie danych");
			return false;
		}
		
		Drill drill = drillRepository.findByIdentificator(drillIdent);
		
		if(drill.isItIsOnLoad() == true && drill.getEmployee() != null) {
					
			drillRepository.updateItIsOnLoad(false, drill.getId());
			drillRepository.updateEmployee(null, drill.getId());
			
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);
						
			employee.getDrillOwned().removeIf(b -> b.getIdentificator().equals(drill.getIdentificator()));
			employeeRepository.update(employee);
			
			return true;
		
		}
		
		return false;
	}
	
	public boolean handOverTheScrewTap(String employeeIdent, String screwTapIdent) {
		
		if(screwTapRepository.existByIdentificator(screwTapIdent) == false) {
			System.out.println("Nie znaleziono gwintownika w bazie dancy");
			return false;
		}
		
		ScrewTap screwTap = screwTapRepository.findByIdentificator(screwTapIdent);
		
		if(screwTap.isItIsOnLoad() == true && screwTap.getEmployee() != null) {
			
			screwTapRepository.updateIsItOnLoad(false, screwTap.getId());
			screwTapRepository.updateEmployee(null, screwTap.getId());
			
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);	
			employee.getScrewTapOwned().removeIf(b -> b.getIdentificator().equals(screwTap.getIdentificator()));		
			employeeRepository.update(employee);
			
			return true;
		}
		
		return false;
	}
	
	public boolean handOverTheLatheKnifeCutter(String employeeIdent, String latheKnifeCutterIdent) {
		
		if(latheKnifeCutterRepository.existByIdentificator(latheKnifeCutterIdent) == false) {
			System.out.println("Nie zanleziono precinaka tokarskiego w bazie danych");
			return false;
		}

		LatheKnifeCutter latheKnifeCutter = latheKnifeCutterRepository.findByIdentificator(latheKnifeCutterIdent);
		
		if(latheKnifeCutter.isItIsOnLoad() == true && latheKnifeCutter.getEmployee() != null) {
			
			Employee employee = employeeRepository.findByIdentificator(employeeIdent);
			
			latheKnifeCutterRepository.updateEmployee(null, latheKnifeCutter.getId());
			latheKnifeCutterRepository.updateItIsOnLoad(false, latheKnifeCutter.getId());
			
			employee.getLatheKnifeCutterOwned().removeIf(b -> b.getIdentificator().equals(latheKnifeCutter.getIdentificator()));
			employeeRepository.update(employee);
			
			return true;
		}
		return false;
	}
	
	//********************** Get Tool List *********************
	
	public List<Drill> getDrillListFromToolRent() {
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		
		if(toolRent.getDrillList() == null) {
			return null;
		}
		List<Drill> drillList = toolRent.getDrillList();
		return drillList;	
	}
	
	public List<ScrewTap> getScrewTapListFromToolRent(){
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		
		if(toolRent.getScrewTapList() == null){
			return null;
		}	
		List<ScrewTap> screwTapList = toolRent.getScrewTapList();
		return screwTapList;
	}
	
	public List<LatheKnifeCutter> getLatheKnifeCutterListFromToolRent(){
		ToolRent toolRent = toolRentRepository.findById(TOOL_ID);
		
		if(toolRent.getLatheKnifeCutterList() == null) {
			return null;
		}
		List<LatheKnifeCutter> latheKnifeCutterList = toolRent.getLatheKnifeCutterList();
		return latheKnifeCutterList;
	}
}
