package project.repositories.tools;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import project.entities.tools.ScrewTap;

import project.entities.Employee;

@Repository
public interface ScrewTapRepository extends PagingAndSortingRepository<ScrewTap, Long> {
	
	public ScrewTap findByIdentificator(String identificator);
	
	@Modifying
	@Transactional
	@Query("UPDATE ScrewTap t SET t.itIsOnLoad = ?1 WHERE t.id = ?2")
	public void updateIsItOnLoad(boolean value, Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE ScrewTap t SET t.employee = ?1 WHERE t.id = ?2")
	public void updateEmployee(Employee employee, Long id);
	
	@Transactional
	@Query("SELECT CASE WHEN count(t) > 0 THEN true ELSE false END FROM ScrewTap t WHERE t.identificator = ?1")
	public boolean existByIdentificator(String identificator);

}
