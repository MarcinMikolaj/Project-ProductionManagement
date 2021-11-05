package project.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import project.entities.ToolRent;

@Repository
public class ToolRentRepository extends SuperRepositoryImpl<ToolRent> {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public ToolRent findById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ToolRent object = entityManager.find(ToolRent.class, id);
		object.getDrillList().size();
		object.getScrewTapList().size();
		object.getLatheKnifeCutterList().size();
		entityManager.close();
		return object;
	}
	
	

}
