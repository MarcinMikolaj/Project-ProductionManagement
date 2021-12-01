package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
//Hibernate
@Entity
@Table(name = "manager")
public class Manager implements Serializable{
	
	private static final long serialVersionUID = 330L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificator;
		
	public Manager(String identificator) {
		super();
		this.identificator = identificator;
	}
	
}
