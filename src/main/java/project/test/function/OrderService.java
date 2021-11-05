package project.test.function;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.time.LocalDate;

import java.sql.Date;
import java.sql.Time;

import java.util.List;

@Service
public class OrderService {
	
	private MaterialOrderRepository materialOrderRepository;
	private MaterialProviderRepository materialProviderRepository;
	private SteelPipeRepository steelPipeRepository;
	
	@Autowired
	public OrderService(MaterialOrderRepository materialOrderRepository, MaterialProviderRepository materialProviderRepository,
			SteelPipeRepository steelPipeRepository) {
		this.materialOrderRepository = materialOrderRepository;
		this.materialProviderRepository = materialProviderRepository;
		this.steelPipeRepository = steelPipeRepository;
	}
	
	
	//******MATERIAL ORDER*****
	
	public void addMaterialToMaterialProvider() {
		
	}
	
	//Umoliwia utworzenie zamówienia oraz jego zapisanie w bazie danych
	public void CreateMaterialOrder(MaterialOrder materialOrder) {
			
		materialOrder.setCreationDate(Date.valueOf(LocalDate.now()));
		materialOrder.setCreationTime(Time.valueOf(LocalTime.now()));
		materialOrderRepository.save(materialOrder);
		
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
	
	public void addSteelPipeToProvider(SteelPipe steelPipe, String companyName) {
		
		steelPipeRepository.save(steelPipe);
		MaterialProvider materialProvider = materialProviderRepository.findByCompanyName(companyName);
		materialProvider.getSteelPipeList().add(steelPipe);
		materialProviderRepository.update(materialProvider);
	
	}
	
	public List<SteelPipe> getSteelPipeByMaterialProvider(String companyName){
		
		if(companyName.equals("none") || companyName.equals(""))
			return null;
		
		List<SteelPipe> objectsListToReturn = materialProviderRepository.findByCompanyName(companyName).getSteelPipeList();
	
		return objectsListToReturn;
	
	}
	
}
