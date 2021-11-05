package project.email;

import project.repositories.SuperRepository;

public interface EmailRepository extends SuperRepository<Email> {
	
	public Email findById(Long id);
	public void remove(Long id);

}
