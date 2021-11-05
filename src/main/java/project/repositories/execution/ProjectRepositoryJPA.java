package project.repositories.execution;

import org.springframework.stereotype.Repository;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import project.entities.execution.Project;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProjectRepositoryJPA {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public void save(Project project) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(project);
		entityTransaction.commit();
		entityManager.close();		
	}
	
	public void update(Project project) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(project);
		entityTransaction.commit();
		entityManager.close();		
	}
	
	public Project findByIdentificator(String identificator) {
		
		if(identificator == null) {
			return null;
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		TypedQuery<Project> typedQuery = entityManager.createNamedQuery("Project.findByIdentificator", Project.class);
		typedQuery.setParameter(1, identificator);
		entityTransaction.begin();
		Project project = typedQuery.getSingleResult();
		entityTransaction.commit();
		entityManager.close();
		
		if(project == null) {
			System.out.println("ProjectRepository.findByIdentificator: nie znaleziono rekordu");
		}
		
		return project;
	}
	
	public boolean existByIdentificator(String identificator) {
		
		if(identificator == null || identificator.length() <= 0) {
			System.out.println("ProjectRepositoryJPA.existByIdentificator: identificator == null || identificator.length() <= 0");
			return false;
		}
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Query query = entityManager.createNamedQuery("Project.existByIdentificator");
		query.setParameter(2, identificator);
		entityTransaction.begin();
		boolean result = (boolean) query.getSingleResult();
		entityTransaction.commit();
		entityManager.close();
		if(result == false) {
			return false;
		}
		return true;
	}

}
