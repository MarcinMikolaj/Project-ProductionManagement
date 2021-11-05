package project.repositories.tools;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import project.entities.tools.LatheKnifeCutter;

import project.entities.Employee;

@Repository
public interface LatheKnifeCutterRepository extends PagingAndSortingRepository<LatheKnifeCutter, Long> {
	
	public LatheKnifeCutter findByIdentificator(String identifiactor);
	
	@Modifying
	@Transactional
	@Query("UPDATE LatheKnifeCutter l SET l.itIsOnLoad = ?1 WHERE l.id = ?2")
	public void updateItIsOnLoad(boolean value, Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE LatheKnifeCutter l SET l.employee = ?1 WHERE l.id = ?2")
	public void updateEmployee(Employee employee, Long id);
	
	@Transactional
	@Query("SELECT CASE WHEN count(l) > 0 THEN true ELSE false END FROM LatheKnifeCutter l WHERE l.identificator = ?1")
	public boolean existByIdentificator(String identificator);

}