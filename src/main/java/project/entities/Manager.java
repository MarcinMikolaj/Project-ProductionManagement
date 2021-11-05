package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "manager")
public class Manager implements Serializable{
	
	private static final long serialVersionUID = 330L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificator;
	
	public Manager() {}
	
	public Manager(String identificator) {
		super();
		this.identificator = identificator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificator() {
		return identificator;
	}

	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", identificator=" + identificator + "]";
	}
	
}
