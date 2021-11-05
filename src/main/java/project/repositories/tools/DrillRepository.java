package project.repositories.tools;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import project.entities.Employee;

import project.entities.tools.Drill;

@Repository
public interface DrillRepository extends PagingAndSortingRepository<Drill, Long> {
	
	public Drill findByIdentificator(String identificator);
	
	@Modifying
	@Transactional
	@Query("UPDATE Drill d SET d.itIsOnLoad = ?1 WHERE d.id = ?2")
	public void updateItIsOnLoad(boolean value, Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Drill d SET d.employee = ?1 WHERE d.id = ?2")
	public void updateEmployee(Employee employee, Long id);
	
	@Transactional
	@Query("SELECT CASE WHEN count(d) > 0 THEN true ELSE false END FROM Drill d WHERE d.identificator = ?1")
	public boolean existByIdentificator(String identificator);
	
	
}
