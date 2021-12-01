package project.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import project.entities.materials.MaterialOrder;
import project.entities.materials.MaterialProvider;
import project.entities.materials.Pipe;
import project.entities.materials.Rod;
import project.repositories.materials.MaterialOrderRepository;
import project.repositories.materials.MaterialProviderRepository;
import project.repositories.materials.PipeRepository;
import project.repositories.materials.RodRepository;

import java.time.LocalTime;
import java.time.LocalDate;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	private MaterialOrderRepository materialOrderRepository;
	private MaterialProviderRepository materialProviderRepository;
	private PipeRepository pipeRepository;
	private RodRepository rodRepository;
	
	@Autowired
	public OrderService(MaterialOrderRepository materialOrderRepository, MaterialProviderRepository materialProviderRepository,
			PipeRepository pipeRepository, RodRepository rodRepository) {
		this.materialOrderRepository = materialOrderRepository;
		this.materialProviderRepository = materialProviderRepository;
		this.pipeRepository = pipeRepository;
		this.rodRepository = rodRepository;
	}
	
	
	//Umoliwia utworzenie zamówienia oraz jego zapisanie w bazie danych
	public void CreateMaterialOrder(MaterialOrder materialOrder, String materialProviderName) {
			
		materialOrder.setMaterialProviderCompanyName(materialProviderName);
		materialOrder.setCreationDate(Date.valueOf(LocalDate.now()));
		materialOrder.setCreationTime(Time.valueOf(LocalTime.now()));
		materialOrderRepository.save(materialOrder);	
	}
	
	public void removeMaterialOrderByIdentificator(String identificator) {
		materialOrderRepository.removeByIdentificator(identificator);
	}
	
	public List<MaterialOrder> getMaterialOrders(){	
		List<MaterialOrder> objectsToReturn = materialOrderRepository.findAll();	
		return objectsToReturn;
	}
	
	//*****MATERIAL PROVIDER******
	
	//Umożliwia zapisanie nowego dostawcy usług z materiałami dla obróbki CNC
	//lub jego aktualizacje w przypadku gdy dostarczony dostawca posiada taki sam numer NIP
	//jak jeden z zapisanych w bazie danych
	public void updateMaterialProvider(MaterialProvider materialProvider) {
		
		if(materialProviderRepository.existByNip(materialProvider.getNip())) {
			MaterialProvider objToUpdate = materialProviderRepository.findByNip(materialProvider.getNip());
			materialProvider.setId(objToUpdate.getId());
			materialProviderRepository.update(materialProvider);
		}else {
			
			materialProviderRepository.save(materialProvider);
		}
				
	}
	
	//Umożliwia usunięcie dostawcy materiałów za pomocą numeru NIP
	public void deleteMaterialProvider(String nip) {
		
		if(materialProviderRepository.existByNip(nip) == false)
			return;
		
		materialProviderRepository.removeByNip(nip);
		
	}
	
	//Umożliwia dodanie oferty rury do wskazanego dostawcy materiałowego (po nazwie dostawcy)
	public void addPipeToProvider(Pipe pipe, String companyName) {	
		
		if(pipe == null)
			return;
		
		pipeRepository.save(pipe);	
		MaterialProvider materialProvider = materialProviderRepository.findByCompanyName(companyName);
		materialProvider.getSteelPipeList().add(pipe);
		materialProviderRepository.update(materialProvider);
	}
	
	//Umożliwia dodanie oferty pręta do wskazanego dostawcy materiałowego (po nazwie dostawcy)
	public void addRodToProvider(Rod rod, String companyName) {
		
		if(rod == null)
			return;
		
		rodRepository.save(rod);
		MaterialProvider materialProvider = materialProviderRepository.findByCompanyName(companyName);
		materialProvider.getRodList().add(rod);
		materialProviderRepository.update(materialProvider);
	}
	
	
	//Zwraca wszystkie rury danego dostawcy materiałowego
	public List<Pipe> getPipesByMaterialProvider(String companyName){	
		if(companyName.equals("none") || companyName.equals(""))
			return null;
		
		List<Pipe> objectsListToReturn = materialProviderRepository.findByCompanyName(companyName).getSteelPipeList();
		return objectsListToReturn;
	}
	
	
	public List<Rod> getRodsByMaterialProvider(String companyName) {
		if(companyName.equals("none") || companyName.equals(""))
			return null;
		
		List<Rod> objectsListToReturn = materialProviderRepository.findByCompanyName(companyName).getRodList();
		return objectsListToReturn;
	}
	

	//Umożliwia usunięcie z listy prętów, materiału o określonym id
	//Przyjmuje listę na której będą wykonane działania oraz id pręta który ma zostać usunięty
	public List<Rod> removeRodById(List<Rod> rodList, String id) {
		
		List<Rod> result = new ArrayList<Rod>();
		
		result = rodList.stream()
			    .filter(q -> q != null)
			    .filter(q -> q.getId() != Long.parseLong(id))
			    .collect(Collectors.toList());
		
		return result;
		
	}
	
	//Umożliwia usunięcie z listy prętów, materiału o określonym id
	//Przyjmuje listę na której będą wykonane działania oraz id pręta który ma zostać usunięty
	public List<Pipe> removePipeById(List<Pipe> pipeList, String id) {
			
		List<Pipe> result = new ArrayList<Pipe>();
			
		result = pipeList.stream()
			    .filter(q -> q != null)
			    .filter(q -> q.getId() != Long.parseLong(id))
			    .collect(Collectors.toList());
		
		return result;		
	}
	
}
