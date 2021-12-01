package project.repositories.materials;

import org.springframework.stereotype.Repository;

import project.entities.materials.MaterialOrder;
import project.repositories.SuperRepositoryImpl;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import java.util.List;

import lombok.Cleanup;

@Repository
public class MaterialOrderRepositoryImpl extends SuperRepositoryImpl<MaterialOrder> implements MaterialOrderRepository {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<MaterialOrder> findAll() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<MaterialOrder> typedQuery = entityManager.createNamedQuery("MaterialOrder.findAll", MaterialOrder.class);
		entityTransaction.begin();
		List<MaterialOrder> objectsResultList = typedQuery.getResultList();
		entityTransaction.commit();
		entityManager.close();
		return objectsResultList;
	}
	
	@Override
	public MaterialOrder findByOrderIdentificator(String orderIdentificator) throws NoResultException {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<MaterialOrder> typedQuery = entityManager.createNamedQuery("MaterialOrder.findByOrderIdentificator", MaterialOrder.class);
		typedQuery.setParameter("identificator", orderIdentificator);
		entityTransaction.begin();
		MaterialOrder result = typedQuery.getSingleResult();
		entityTransaction.commit();
		entityManager.close();
		return result;
	}

	//Umożliwia sprawdzenie czy w bazie istnie zamówienie o podanym identyfikatorze
	@Override
	public boolean existByIdentificator(String identificator) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("MaterialOrder.existByIdentificator");
		query.setParameter("identificator2", identificator);
		entityTransaction.begin();
		boolean result = (boolean) query.getSingleResult();
		entityTransaction.commit();
		entityManager.close();	
		return result;
	}

	@Override
	public void removeByIdentificator(String identificator) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("MaterialOrder.removeByIdentificator");
		query.setParameter("identificator3", identificator);
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
		entityManager.close();	
	}

}
