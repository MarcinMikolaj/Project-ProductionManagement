package project.repositories;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import project.entities.PersonalInformation;

@Repository
public interface PersonalInformationRepository extends PagingAndSortingRepository<PersonalInformation, Long> {
	
	//public PersonalInformation findByNameAndLastName(String name, String lastName);
	
	@Transactional
	@Query("SELECT p FROM PersonalInformation p Where p.name = ?1 AND p.lastName = ?2")
	public PersonalInformation findByNameAndLastName(String name, String lastName);
	
	@Transactional
	@Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM PersonalInformation p WHERE p.name = ?1")
	public boolean existByName(String name);
	
	@Transactional
	@Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM PersonalInformation p WHERE p.lastName = ?1")
	public boolean existByLastName(String lastName);

}
