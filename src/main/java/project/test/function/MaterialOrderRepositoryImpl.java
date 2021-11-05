package project.test.function;

import org.springframework.stereotype.Repository;

import project.repositories.SuperRepositoryImpl;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

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

}
