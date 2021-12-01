package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.OneToMany;
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

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

//Lombok
@NoArgsConstructor()
@AllArgsConstructor()
@EqualsAndHashCode
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString(onlyExplicitlyIncluded = true)
//Hibernate
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
	@ToString.Include
	private Long id;
	@ToString.Include
	private String identificator;
	@ToString.Include
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

	public Employee(String identificator, String position) {
		super();
		this.identificator = identificator;
		this.position = position;
	}
	
}
