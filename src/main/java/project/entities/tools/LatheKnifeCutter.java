package project.entities.tools;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.OneToOne;
import javax.persistence.FetchType;

import project.entities.Employee;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
//Hibernate
@Entity
@Table(name = "lathe_knife_cutter")
public class LatheKnifeCutter implements Serializable {
	
	private static final long serialVersionUID = 4000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificator;
	private boolean itIsOnLoad;
	private String brand;
	private String name;
	private String kindOfKnife;
	private String totalLength;
	private String handleHeight;
	
	@OneToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Employee employee;
	
	public LatheKnifeCutter(String identificator, String brand, String name, String kindOfKnife,
			String totalLength, String handleHeight) {
		super();
		this.identificator = identificator;
		this.itIsOnLoad = false;
		this.brand = brand;
		this.name = name;
		this.kindOfKnife = kindOfKnife;
		this.totalLength = totalLength;
		this.handleHeight = handleHeight;
	}

}
