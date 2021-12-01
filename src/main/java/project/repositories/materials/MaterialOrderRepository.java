package project.repositories.materials;

import project.entities.materials.MaterialOrder;
import project.repositories.SuperRepository;

import java.util.List;

public interface MaterialOrderRepository extends SuperRepository<MaterialOrder> {
	
	public List<MaterialOrder> findAll();
	public MaterialOrder findByOrderIdentificator(String orderIdentificator);
	public boolean existByIdentificator(String identificator);
	public void removeByIdentificator(String identificator);
}
