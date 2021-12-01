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
import lombok.AccessLevel;
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
@Table(name = "drill")
public class Drill implements Serializable {
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 1000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String identificator;
	private int quantity;
	private boolean itIsOnLoad;
    private String totalLength;
    private String workingLength;
    private String diameter;
    private String apexAngle;
    private String destiny;
    private String materialExecution;
    private String handleType;
    private boolean quickClampingSystem;
    
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Employee employee;

	public Drill(String identificator, int quantity, String totalLength, String workingLength, String diameter,
			String apexAngle, String destiny, String materialExecution, String handleType,
			boolean quickClampingSystem) {
		super();
		this.identificator = identificator;
		this.quantity = quantity;
		this.totalLength = totalLength;
		this.workingLength = workingLength;
		this.diameter = diameter;
		this.apexAngle = apexAngle;
		this.destiny = destiny;
		this.materialExecution = materialExecution;
		this.handleType = handleType;
		this.quickClampingSystem = quickClampingSystem;	
		this.itIsOnLoad = false;
		this.employee = null;
	}
  
}
