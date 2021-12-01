package project.repositories.materials;

import org.springframework.stereotype.Repository;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import project.repositories.SuperRepositoryImpl;
import project.entities.materials.Rod;

import java.util.List;

import lombok.Cleanup;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RodRepository extends SuperRepositoryImpl<Rod>{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	//Zwraca obiekt materiałowy za pomocą klucza głównego
	public Rod findById(Long id) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<Rod> typedQuery = entityManager.createNamedQuery("Rod.findById", Rod.class);
		typedQuery.setParameter("id", id);
		entityTransaction.begin();
		Rod result = typedQuery.getSingleResult();
		entityTransaction.commit();
		entityManager.close();
		return result;
	}
	
	//Umożliwia pobranie wszystkich prętów znajdujących się w magazynie materiałów
	public List<Rod> findAllInWarehouse(){
		
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<Rod> typedQuery = entityManager.createNamedQuery("Rod.findAllInWarehouse", Rod.class);
		entityTransaction.begin();
		List<Rod> resultList = typedQuery.getResultList();
		entityTransaction.commit();
		entityManager.close();
		return resultList;
	}

}
