package project.repositories.tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import project.entities.tools.Drill;
import project.entities.tools.LatheKnifeCutter;
import project.entities.tools.ScrewTap;

@Repository
public class UpdateRepoForSpring {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public void updateDrill(Drill drill) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(drill);
		entityTransaction.commit();
		entityManager.close();		
	}
	
	public void updateScrewTap(ScrewTap screwTap) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(screwTap);
		entityTransaction.commit();
		entityManager.close();		
	}
	
	public void updateLatheKnifeCutter(LatheKnifeCutter latheKnifeCutter) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(latheKnifeCutter);
		entityTransaction.commit();
		entityManager.close();		
	}

}
