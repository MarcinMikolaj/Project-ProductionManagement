package project.email;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import project.repositories.SuperRepositoryImpl;

@Repository
public class EmailRepositoryImpl extends SuperRepositoryImpl<Email> implements EmailRepository  {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public Email findById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Email email = entityManager.find(Email.class, id);
		entityManager.close();
		return email;
	}
	
	public void remove(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Email email = entityManager.find(Email.class, id);
		entityTransaction.begin();
		entityManager.remove(email);
		entityTransaction.commit();
		entityManager.close();
	}
	
}
