package project.entities.execution;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import java.util.List;
import java.util.ArrayList;

import project.entities.Manager;
import project.entities.Employee;

@Entity
@Table(name = "project")
@NamedQueries({
	@NamedQuery(name = "Project.findByIdentificator", query = "SELECT p FROM Project p WHERE p.identificator = ?1"),
	@NamedQuery(name = "Project.existByIdentificator", query = "SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM Project p WHERE p.identificator = ?2")
})

public class Project implements Serializable {
	
	private static final long serialVersionUID = 900L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long id;
	private String identificator;
	private String startDate;
	private String endDate;
	private boolean itFinished;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Manager manager;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "project_manager_add_employess",
    joinColumns = {@JoinColumn(name="id_project", referencedColumnName="project_id")},
    inverseJoinColumns = {@JoinColumn(name="id_employee", referencedColumnName="employee_id")})
	@ElementCollection
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Employee> assignedEmployees = new ArrayList<Employee>();
	
	public Project() {};
	
	public Project(String identificator, String startDate, String endDate, boolean itFinished) {
		super();
		this.identificator = identificator;
		this.startDate = startDate;
		this.endDate = endDate;
		this.itFinished = true;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isItFinished() {
		return itFinished;
	}

	public void setItFinished(boolean itFinished) {
		this.itFinished = itFinished;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public List<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}

	public void setAssignedEmployees(List<Employee> assignedEmployees) {
		this.assignedEmployees = assignedEmployees;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", identificator=" + identificator + ", startDate=" + startDate + ", endDate="
				+ endDate + ", ItFinished=" + itFinished + ", manager=" + manager + "]";
	}

}
