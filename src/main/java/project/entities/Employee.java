package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.util.List;
import java.util.ArrayList;

import project.entities.tools.Drill;
import project.entities.tools.ScrewTap;
import project.entities.tools.LatheKnifeCutter;
import project.entities.execution.Project;

@Entity
@Table(name = "employee")
@NamedQueries({
	@NamedQuery(name = "Employe.existByIdentificator", query ="SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.identificator = ?2"),
	@NamedQuery(name = "Employee.findByIdentificator", query = "SELECT p FROM Employee p WHERE p.identificator = ?1")
})

public class Employee implements Serializable {
		
	private static final long serialVersionUID = -1969019177457447180L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	private String identificator;
	private String position;
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Drill> drillOwned = new ArrayList<Drill>();
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<ScrewTap> screwTapOwned = new ArrayList<ScrewTap>();
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<LatheKnifeCutter> latheKnifeCutterOwned = new ArrayList<LatheKnifeCutter>();

	//@ElementCollection
	//@ManyToMany(mappedBy = "assignedEmployees", cascade=CascadeType.ALL)
	//@LazyCollection(LazyCollectionOption.TRUE)
	//private List<Project> assignedProjects = new ArrayList<Project>();
	
	public Employee(String identificator, String position) {
		super();
		this.identificator = identificator;
		this.position = position;
	}
	
	public Employee() {};
	
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
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public List<Drill> getDrillOwned() {
		return drillOwned;
	}
	
	public void setDrillOwned(List<Drill> drillOwned) {
		this.drillOwned = drillOwned;
	}
	
	public List<ScrewTap> getScrewTapOwned(){
		return screwTapOwned;
	}
	
	public void setScrewTapOwned(List<ScrewTap> screwTapOwned) {
		this.screwTapOwned = screwTapOwned;
	}
		
	public List<LatheKnifeCutter> getLatheKnifeCutterOwned() {
		return latheKnifeCutterOwned;
	}

	public void setLatheKnifeCutterOwned(List<LatheKnifeCutter> latheKnifeCutterOwned) {
		this.latheKnifeCutterOwned = latheKnifeCutterOwned;
	}
	
	//public List<Project> getAssignedProjects() {
	//	return assignedProjects;
	//}

	//public void setAssignedProjects(List<Project> assignedProjects) {
	//	this.assignedProjects = assignedProjects;
	//}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", identificator=" + identificator + ", position=" + position + "]";
	}
	
}
