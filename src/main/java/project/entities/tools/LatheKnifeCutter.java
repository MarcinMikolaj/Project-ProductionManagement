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
	private Employee employee;
	
	public LatheKnifeCutter() {}
	
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

	public boolean isItIsOnLoad() {
		return itIsOnLoad;
	}

	public void setItIsOnLoad(boolean itIsOnLoad) {
		this.itIsOnLoad = itIsOnLoad;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKindOfKnife() {
		return kindOfKnife;
	}

	public void setKindOfKnife(String kindOfKnife) {
		this.kindOfKnife = kindOfKnife;
	}

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getHandleHeight() {
		return handleHeight;
	}

	public void setHandleHeight(String handleHeight) {
		this.handleHeight = handleHeight;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "LatheKnifeCutter [id=" + id + ", identificator=" + identificator + ", itIsOnLoad=" + itIsOnLoad
				+ ", brand=" + brand + ", name=" + name + ", kindOfKnife=" + kindOfKnife + ", totalLength="
				+ totalLength + ", workingLength=" + ", handleHeight=" + handleHeight + "]";
	};
	
}
