package project.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SuperRepositoryImpl<G> implements SuperRepository<G> {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private Validator validator;
	
	@Override
	public void save(Object object) {
		
		try {
			validator.validate(object);
		} catch(ConstraintViolationException e) {
			
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			violations.forEach(q -> System.out.println(q.getMessage()));		
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(object);
		entityTransaction.commit();
		entityManager.close();
	}
	
	@Override
	public void update(G object) {
		
		try {
			validator.validate(object);
		} catch(ConstraintViolationException e) {
			
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			violations.forEach(q -> System.out.println(q.getMessage()));	
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(object);
		entityTransaction.commit();
		entityManager.close();
	}

	@Override
	public void createUpdateOrDeleteQuery(String myQuery) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createQuery(myQuery);
		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
		entityManager.close();		
	}
	
}
