package project;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import project.services.AccountUserDetailsService;

@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	private AccountUserDetailsService accountUserDetailsService;
			
	public SpringSecurityConfig() {};
	
	@Autowired
	public SpringSecurityConfig(AccountUserDetailsService accountUserDetailsService) {
		this.accountUserDetailsService = accountUserDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountUserDetailsService);
		//auth.inMemoryAuthentication().withUser("root").password(passwordEncoder().encode("root")).roles("USER");
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().disable()	
		 .authorizeRequests()
		 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
		    // .antMatchers("/renttool").permitAll()
		    // .antMatchers("/register").permitAll()
		 
		 
		    //.antMatchers("/").authenticated()
		    //.antMatchers("/register").permitAll()
		    
		    
		    .anyRequest().permitAll()
		 .and()
		    .formLogin()
		    .loginPage("/signIn").permitAll()
		    .usernameParameter("username")
		    .passwordParameter("password")
		 ;
	}
	
}
