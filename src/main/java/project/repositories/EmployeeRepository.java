package project.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.TypedQuery;
import javax.persistence.Query;

import project.entities.Employee;

@Repository
public class EmployeeRepository extends SuperRepositoryImpl<Employee> {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public Employee findById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Employee object = entityManager.find(Employee.class, id);
		object.getDrillOwned().size();
		object.getScrewTapOwned().size();
		object.getLatheKnifeCutterOwned().size();
		entityManager.close();
		return object;
	}
	
	public Employee findByIdentificator(String identificator) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<Employee> typedQuery = entityManager.createNamedQuery("Employee.findByIdentificator", Employee.class);
		typedQuery.setParameter(1, identificator);
		entityTransaction.begin();
		Employee object = typedQuery.getSingleResult();
		object.getDrillOwned().size();
		object.getScrewTapOwned().size();
		object.getLatheKnifeCutterOwned().size();
		entityTransaction.commit();
		entityManager.close();
		return object;
	}
	
	public boolean existByIdentificator(String identificator) {
		if(identificator != null && identificator.length() > 0) {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			Query query = entityManager.createNamedQuery("Employe.existByIdentificator");
			query.setParameter(2, identificator);
			entityTransaction.begin();
			boolean result = (boolean) query.getSingleResult();
			entityTransaction.commit();
			entityManager.close();
			if(result == true) {
				return true;
			}	
		}
		return false;
	}

}
