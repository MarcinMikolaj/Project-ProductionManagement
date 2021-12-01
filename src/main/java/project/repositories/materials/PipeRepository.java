package project.repositories.materials;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import project.entities.materials.Pipe;
import project.repositories.SuperRepositoryImpl;

import java.util.List;

import lombok.Cleanup;

@Repository
public class PipeRepository extends SuperRepositoryImpl<Pipe> {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public Pipe findById(Long id) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Pipe result = entityManager.find(Pipe.class, id);
		entityTransaction.commit();
		entityManager.close();
		return result;
	}
	
	
	//Umożliwia odnalezienie wszystkich elementów w bazie dla których ilość 
	//sztuk w magazynie jest większa od 0
	public List<Pipe> findAllInWarehouse(){
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<Pipe> typedQuery = entityManager.createNamedQuery("Pipe.findAllInMagazine", Pipe.class);
		entityTransaction.begin();
		List<Pipe> resultList = typedQuery.getResultList();
		entityTransaction.commit();
		entityManager.close();
		return resultList;
	}
	
}
