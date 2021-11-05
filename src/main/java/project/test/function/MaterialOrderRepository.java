package project.test.function;

import project.repositories.SuperRepository;

import java.util.List;

public interface MaterialOrderRepository extends SuperRepository<MaterialOrder> {
	
	public List<MaterialOrder> findAll();
}
