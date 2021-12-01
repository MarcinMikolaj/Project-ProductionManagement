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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
//Hibernate
@Entity
@Table(name = "screwtap")
public class ScrewTap implements Serializable {
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 5766101572064508187L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String identificator;
	private boolean itIsOnLoad;
	private String brand;
	private String totalLength;
	private String workongLength;
	private String thread;
	private String threadType;
	private String threadProfile;
	private String hardness;
	private String materialExecution;
	
	@OneToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Employee employee;

	public ScrewTap(String identificator, String brand, String totalLength, String workongLength, String thread,
			String threadType, String threadProfile, String hardness, String materialExecution) {
		super();
		this.identificator = identificator;
		this.brand = brand;
		this.totalLength = totalLength;
		this.workongLength = workongLength;
		this.thread = thread;
		this.threadType = threadType;
		this.threadProfile = threadProfile;
		this.hardness = hardness;
		this.materialExecution = materialExecution;
		this.itIsOnLoad = false;
	}

}
