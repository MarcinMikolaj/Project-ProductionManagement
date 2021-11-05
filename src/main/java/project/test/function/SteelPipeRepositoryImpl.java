package project.test.function;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import project.repositories.SuperRepositoryImpl;

@Repository
public class SteelPipeRepositoryImpl extends SuperRepositoryImpl<SteelPipe> implements SteelPipeRepository{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public SteelPipe findById(Long id) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		SteelPipe object = entityManager.find(SteelPipe.class, id);
		entityTransaction.commit();
		entityManager.close();
		return object;
	}

}
