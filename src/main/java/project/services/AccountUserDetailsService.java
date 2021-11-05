package project.services;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.repositories.AccountRepository;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountUserDetailsService implements UserDetailsService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepository.findByUsername(username);
	}
	
}
