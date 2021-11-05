package project.test.function;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import project.repositories.SuperRepositoryImpl;

import lombok.Cleanup;

@Repository
public class MaterialProviderRepositoryImpl extends SuperRepositoryImpl<MaterialProvider> implements MaterialProviderRepository {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public MaterialProvider removeByNip(String nip) {
		
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		MaterialProvider objectToRemove = findByNip(nip);
		Query query = entityManager.createNamedQuery("MaterialProvider.removeByNip");
		query.setParameter("nip1", nip);
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
		entityManager.close();
		return objectToRemove;
	}

	@Override
	public MaterialProvider findByNip(String nip) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<MaterialProvider> typedQuery = entityManager.createNamedQuery("MaterialProvider.findByNip", MaterialProvider.class);
		typedQuery.setParameter("nip2", nip);
		entityTransaction.begin();
		MaterialProvider objectToReturn = typedQuery.getSingleResult();
		objectToReturn.getSteelPipeList().size();
		entityTransaction.commit();
		entityManager.close();
		return objectToReturn;
	}
	
	@Override
	public MaterialProvider findByCompanyName(String companyName) {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<MaterialProvider> typedQuery = entityManager.createNamedQuery("MaterialProvider.findByCompanyName", MaterialProvider.class);
		typedQuery.setParameter("companyName", companyName);
		entityTransaction.begin();
		MaterialProvider objectToReturn = typedQuery.getSingleResult();
		objectToReturn.getSteelPipeList().size();
		entityTransaction.commit();
		entityManager.close();
		return objectToReturn;
	}

	@Override
	public List<MaterialProvider> findAll() {
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<MaterialProvider> typedQuery = entityManager.createNamedQuery("MaterialProvider.findAll", MaterialProvider.class);
		entityTransaction.begin();
		List<MaterialProvider> objects = typedQuery.getResultList();
		objects.size();
		entityTransaction.commit();
		entityManager.close();
		return objects;
	}

	@Override
	public boolean existByNip(String nip) {
		
		@Cleanup EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("MaterialProvider.existByNip");
		query.setParameter("nip3" , nip);
		entityTransaction.begin();
		boolean result = (boolean) query.getSingleResult();
		entityTransaction.commit();
		entityManager.close();
		
		return result;
	}

}
