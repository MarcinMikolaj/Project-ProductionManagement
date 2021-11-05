package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToOne;

@Entity
@Table(name = "personal_information")
public class PersonalInformation implements Serializable {
	
	private static final long serialVersionUID = 10000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String pesel;
	private String cityOfResidence;
	private String streetOfResidenceAndNumber;
	
	@OneToOne(mappedBy = "personalInformation", fetch = FetchType.EAGER)
	private Account account;
	
	public PersonalInformation() {}

	public PersonalInformation(String name, String lastName, String phoneNumber, String email, String pesel,
			String cityOfResidence, String streetOfResidenceAndNumber) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.pesel = pesel;
		this.cityOfResidence = cityOfResidence;
		this.streetOfResidenceAndNumber = streetOfResidenceAndNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getCityOfResidence() {
		return cityOfResidence;
	}

	public void setCityOfResidence(String cityOfResidence) {
		this.cityOfResidence = cityOfResidence;
	}

	public String getStreetOfResidenceAndNumber() {
		return streetOfResidenceAndNumber;
	}

	public void setStreetOfResidenceAndNumber(String streetOfResidenceAndNumber) {
		this.streetOfResidenceAndNumber = streetOfResidenceAndNumber;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "PersonalInformation [id=" + id + ", name=" + name + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", pesel=" + pesel + ", cityOfResidence=" + cityOfResidence
				+ ", streetOfResidenceAndNumber=" + streetOfResidenceAndNumber + "]";
	};
	
}
