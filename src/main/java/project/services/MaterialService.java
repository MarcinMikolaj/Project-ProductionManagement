package project.services;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;

import project.repositories.materials.MaterialOrderRepository;
import project.repositories.materials.PipeRepository;
import project.repositories.materials.RodRepository;
import project.entities.ConsoleColors;
import project.entities.materials.Rod;
import project.entities.materials.MaterialOrder;
import project.entities.materials.Pipe;
import project.entities.materials.PipeComparatorByQuantityInStockMinus;
import project.entities.materials.PipeComparatorByQuantityInStockPlus;
import project.entities.materials.RodComparatorByQuantityInStockMinus;
import project.entities.materials.RodComparatorByQuantityInStockPlus;

import java.util.List;
import java.util.ArrayList;

@Service
public class MaterialService {
	
	private MaterialOrderRepository materialOrderRepository;
	private RodRepository rodRepository;
	private PipeRepository pipeRepository;
	
	@Autowired
	public MaterialService(MaterialOrderRepository materialOrderRepository,
			RodRepository rodRepository, PipeRepository pipeRepository) {
		this.materialOrderRepository = materialOrderRepository;
		this.rodRepository = rodRepository;
		this.pipeRepository =  pipeRepository;
	}
	
	//Umożliwia dodanie do magazynu wszystkich materiałów
	//dostarczonych w danym zamówieniu
	//Zwraca liczbe dodanych materiałów
	public Long addMaterialFromTheOrder(String identificator) {
		
		try{
			MaterialOrder materialOrder = materialOrderRepository.findByOrderIdentificator(identificator);
			
			Stream<Pipe> stream = materialOrder.getSteelPipeList().stream();
			Long result = stream
			   .filter(q -> q != null)
			   .peek(q -> {
				   Pipe p = pipeRepository.findById(q.getId());
				   p.setQuantityInStock(1 + p.getQuantityInStock());
				   pipeRepository.update(p);
			   })
			   .count();
			return result;	
			
		} catch(NoResultException e) {
			System.out.println(ConsoleColors.GREEN + "MaterialService.addMaterialFromTheOrder" + e.getMessage()  + ConsoleColors.RESET);
		}
								
		return null;	
	}
	
	//Zwraca rury z magazynu w opowiedniej kolejności oraz tylko z określonymi parametrami 
	//przekazanymi do metody
	public List<Pipe> getPipesFromWarehuse(String sortBy, String materialType, String profile) {
		
		List<Pipe> pipeList = pipeRepository.findAllInWarehouse();
		
		if(materialType.equals("Material") == false)
			pipeList = getPipeListByMaterialType(pipeList, materialType);
		
		if(profile.equals("Profile") == false)
			pipeList = getPipeListByProfil(pipeList,profile);

		
		List<Pipe> result = null;
		Stream<Pipe> stream = pipeList.stream();
		
		if(sortBy.equals("quantityInStockMinus")) {
			result = stream
					  .filter(q -> q != null)
					  .sorted(new PipeComparatorByQuantityInStockMinus())
					  .collect(Collectors.toList());
		}
		else if(sortBy.equals("quantityInStockPlus")) {
			result = stream
					  .filter(q -> q != null)
					  .sorted(new PipeComparatorByQuantityInStockPlus())
					  .collect(Collectors.toList());
		}
		else {
			return pipeList;
		}		
		
		return result;
	}
		
	//Zwraca określone za pomocą właściwości pręty z magazynu w odpowiedniej kolejności
	//Jako argument przekazywany jest String wskazujący na sposób sortowania
	// "quantityInStockMinus" - Sotowanie od najmniejszej do największej
	public List<Rod> getRodsFromWarehouse(String sortBy, String materialType, String profile){
			
		List<Rod> rodList = rodRepository.findAllInWarehouse();
		
		if(materialType.equals("Material") == false)
			rodList = getRodListByMaterialType(rodList, materialType);
		
		if(profile.equals("Profile") == false)
			rodList = getRodsListByProfil(rodList, profile);
		
		List<Rod> result = null;
		Stream<Rod> stream = rodList.stream();
		
		if(sortBy.equals("quantityInStockMinus")) {
			result = stream
					  .filter(q -> q != null)
					  .sorted(new RodComparatorByQuantityInStockMinus())
					  .collect(Collectors.toList());
		}
		else if(sortBy.equals("quantityInStockPlus")) {
			result = stream
					  .filter(q -> q != null)
					  .sorted(new RodComparatorByQuantityInStockPlus())
					  .collect(Collectors.toList());
		}
		else {
			return rodList;
		}
				
		return result;
	}
	
	//Umożliwia zwrócenie listy z rurami o określonym profilu
	private List<Pipe> getPipeListByProfil(List<Pipe> pipeList, String profile){
		
		Stream<Pipe> stream = pipeList.stream();
		
		List<Pipe> result = stream
		  .filter(q -> q != null)
		  .filter(q -> q.getProfile().equals(profile))
		  .collect(Collectors.toList());
		
		return result;
	}
	
	//Umożliwia zwrócenie listy z prętami o określonym profilu
	private List<Rod> getRodsListByProfil(List<Rod> rodList, String profile){
		
		Stream<Rod> stream = rodList.stream();
		
		List<Rod> result = stream
		                    .filter(q -> q != null)
		                    .filter(q -> q.getProfile().equals(profile))
		                    .collect(Collectors.toList());
		return result;
	}
	
	//Przyjmuje liste z rurami oraz zwaraca tylko te z określonym rodzajem materiału
	private List<Pipe> getPipeListByMaterialType(List<Pipe> pipeList, String materialType){
			
		List<Pipe> result = new ArrayList<Pipe>();
			
		for(int i=0; i<pipeList.size(); i++) {
			Pipe pipe = pipeList.get(i);
			if(pipe.getMaterialType().equals(materialType))
				result.add(pipe);
		}
		return result;
	}
		
	//Przyjmuje liste z prętami oraz zwaraca tylko te z określonym rodzajem materiału
	private List<Rod> getRodListByMaterialType(List<Rod> rodList, String materialType){
			
			List<Rod> result = new ArrayList<Rod>();
			
			for(int i=0; i<rodList.size(); i++) {
				Rod rod = rodList.get(i);
				if(rod.getMaterialType().equals(materialType))
					result.add(rod);
		}
				
		return result;
	}
	
	//Zwaraca listę wszystkich gatunków materiałowych dla ruru i prętów w magazynie
	//Nie zawiera duplikatów
	public List<String> getAllaterialType(){
		
		List<String> result = new ArrayList<String>();
		
		getAllMaterialTypeForRod().stream()	
		   .filter(q -> q != null)
		   .peek(q -> result.add(q))
		   .count();
			
		getAllMaterialTypeForPipe().stream()
		   .filter(q -> q != null)
		   .peek(q -> {
			   if(result.contains(q) == false)
				   result.add(q);
		   }) 
		  .count();
	
		return result;
	}
	
	
	//Zwaraca listę wszystkich gatunków materiałowych dla ruru w magazynie 
	//Zwraca liste z unikalnymi elementami tzn. bez duplikatów
	private List<String> getAllMaterialTypeForPipe(){
		
		List<Pipe> pipeList = pipeRepository.findAllInWarehouse();
		List<String> result = new ArrayList<String>();
		result.add("Material");
		boolean key = true;

		for(int i=0; i<pipeList.size(); i++) {
			
			key = true;
			String s = pipeList.get(i).getMaterialType();
			
			for(int j=0; j<result.size(); j++) {
				if(result.get(j).equals(s)) 
					key = false;
			}	
			
			if(key==true)
				result.add(s);		
		}		
		return result;
	}
	
	//Zwaraca listę wszystkich gatunków materiałowych dla prętów w magazynie
	//Zwraca liste z unikalnymi elementami tzn. bez duplikatów
	private List<String> getAllMaterialTypeForRod(){
		
		List<Rod> rodList = rodRepository.findAllInWarehouse();
		List<String> result = new ArrayList<String>();
		result.add("Material");
		
		boolean key = true;
		
		for(int i=0; i<rodList.size(); i++) {
			key = true;
			String s = rodList.get(i).getMaterialType();
			for(int j=0; j<result.size(); j++) {			
				if(result.get(j).equals(s)) 
					key = false;
			}			
			if(key==true)
				result.add(s);		
		}				
		return result;
	}
	
	//Zwaraca listę wszystkich profili materiałowych dla ruru i prętów w magazynie
	//Nie zawiera duplikatów
	public List<String> getAllaterialProfile(){
			
		List<String> result = new ArrayList<String>();
			
		getAllProfilesForRod().stream()	
		   .filter(q -> q != null)
		   .peek(q -> result.add(q))
		   .count();
				
		getAllProfilesForPipe().stream()
		   .filter(q -> q != null)
		   .peek(q -> {
			   if(result.contains(q) == false)
				   result.add(q);
		   }) 
		  .count();
	
		return result;
	}
	
	//Zwraca aktualną listę profili jakie zostały utworzone dla prętów materiałowych
	//Nie zawiera duplikatów
	private List<String> getAllProfilesForRod(){
		
		List<Rod> rodList = rodRepository.findAllInWarehouse();
		List<String> result = new ArrayList<String>();
		String s = "";
		result.add("Profile");
		boolean key = true;
		
		for(int i=0; i<rodList.size(); i++) {
			key = true;
			s = rodList.get(i).getProfile();
			for(int j=0; j<result.size(); j++) {
				if(result.get(j).equals(s))
					key = false;
			}		
			if(key==true)
				result.add(s);		
		}	
		return result;
	}
	
	//Zwraca aktualną listę profili jakie zostały utworzone dla rur materiałowych
	//Nie zawiera duplikatów
	private List<String> getAllProfilesForPipe(){
		
		List<Pipe> pipeList = pipeRepository.findAllInWarehouse();
		List<String> result = new ArrayList<String>();
		String s = "";
		result.add("Profile");
		boolean key = true;
		
		for(int i=0; i<pipeList.size(); i++) {
			key = true;
			s = pipeList.get(i).getProfile();
			for(int j=0; j<result.size(); j++) {
				if(result.get(j).equals(s))
					key = false;
			}		
			if(key==true)
				result.add(s);		
		}	
		return result;
	}
	
	//Umożliwia zabranie określonej liczby materiałów (pipe) z magazynu
	//Wyszukiwanie materiału który ma być pobrany odbywa sie poprzez przekazy klucz (id)
	//Zwraca true gdy operacja została przeprowadzona
	public boolean takePipesFromWarehouse(String numberToRemovePipe, String id) {
		
		Pipe pipe = pipeRepository.findById(Long.parseLong(id));
		
		Integer toTake = Integer.parseInt(numberToRemovePipe);
		
		if(pipe.getQuantityInStock() >= toTake && toTake > 0) {
			pipe.setQuantityInStock(pipe.getQuantityInStock() - toTake);
			pipeRepository.update(pipe);
			return true;
		}	
		return false;
	}
	
	//Umożliwia zabranie określonej liczby materiałów (rod) z magazynu
	//Wyszukiwanie materiału który ma być pobrany odbywa sie poprzez przekazy klucz (id)
	//Zwraca true gdy operacja została przeprowadzona
	public boolean takeRodFromWarehouse(String numberToRemovePipe, String id) {
		
		Rod rod = rodRepository.findById(Long.parseLong(id));
	     
		Integer toTake = Integer.parseInt(numberToRemovePipe);
		
		if(rod.getQuantityInStock() >= toTake && toTake > 0) {
			rod.setQuantityInStock(rod.getQuantityInStock() - toTake);
			rodRepository.update(rod);
			return true;
		}
		return false;
	}
		
}
