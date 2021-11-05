package project.repositories;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.entities.Account;

public interface AccountRepository extends SuperRepository<Account> {
	
	public Account find(Long id);
	public Account findByUsername(String username) throws UsernameNotFoundException;
	public boolean existByUsername(String username);
	public boolean existByEmail(String email);

}
