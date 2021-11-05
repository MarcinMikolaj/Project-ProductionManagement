package project.repositories;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import project.entities.Account;

@Repository
public class AccountRepositoryImpl extends SuperRepositoryImpl<Account> implements AccountRepository {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public Account find(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Account account = entityManager.find(Account.class, id);
		entityManager.close();
		return account;
	}
	
	public Account findByUsername(String username) throws UsernameNotFoundException {	
		if(existByUsername(username) == false) {
			System.out.println("AccountRepository.findByUsername: nie znaleziono");
		}
		
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			TypedQuery<Account> typedQuery = entityManager.createNamedQuery("Account.findByUsername", Account.class);
			typedQuery.setParameter(1, username);
			entityTransaction.begin();
			Account object = typedQuery.getSingleResult();
			entityTransaction.commit();
			entityManager.close();
		    return object;	
	}
	
	public boolean existByUsername(String username) {
		
		if(username == null || username.length() <= 0) {
			return false;
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("Account.existByUsername");
		query.setParameter(2, username);
		entityTransaction.begin();
		boolean result = (boolean) query.getSingleResult();
		entityTransaction.commit();
		entityManager.close();	
		if(result == true) {
			return true;
		}
		else {
			
			return false;
		}
		
	}
	
	public boolean existByEmail(String email) {
		
		if(email == null || email.length() <= 0) {
			return false;
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("Account.existByEmail");
		query.setParameter(3, email);
		entityTransaction.begin();
		boolean result = (boolean) query.getSingleResult();
		entityTransaction.commit();
		entityManager.close();	
		
		if(result == true) {
			return true;
		}
		else {
			
			return false;
		}		
	}

}
