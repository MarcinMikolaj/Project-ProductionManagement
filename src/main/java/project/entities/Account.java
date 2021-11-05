package project.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "account")
@NamedQueries({
	@NamedQuery(name = "Account.findByUsername", query = "SELECT p FROM Account p WHERE p.username = ?1"),
	@NamedQuery(name = "Account.existByUsername", query = "SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM Account e where e.username = ?2"), 
	@NamedQuery(name = "Account.existByEmail", query = "SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM Account p WHERE p.email = ?3")
})

public class Account implements UserDetails {
	
	private static final long serialVersionUID = 200L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;	
	
	@Transient
	private String confirmPassword;
	
	private String email;
	private String role;
	private boolean termsAndConditions;
	private boolean privacyPolicy;
	
	@OneToOne(cascade = CascadeType.MERGE) //(fetch = FetchType.LAZY)
	//@JoinColumn(name = "account_personalInformation_joincolumn")
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private PersonalInformation personalInformation;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Manager manager;
	
	public Account() {};
		
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Account(String username, String password, String email, String role, boolean termsAndConditions,
			boolean privacyPolicy) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.termsAndConditions = termsAndConditions;
		this.privacyPolicy = privacyPolicy;
		this.personalInformation = null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// BRAK ROLI %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		return null;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {	
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//***Getters and Setters***
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}
	
	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	public boolean isPrivacyPolicy() {
		return privacyPolicy;
	}
	
	public void setPrivacyPolicy(boolean privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}
	
	public void setPersonalInformation(PersonalInformation personalInformation) {
		this.personalInformation = personalInformation;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + ", termsAndConditions=" + termsAndConditions + ", privacyPolicy=" + privacyPolicy
				+ "]";
	}
	
}
