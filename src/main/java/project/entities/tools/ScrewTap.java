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
@Table(name = "screwtap")
public class ScrewTap implements Serializable {
	
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
	private Employee employee;
	
	public ScrewTap() {}

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

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getWorkongLength() {
		return workongLength;
	}

	public void setWorkongLength(String workongLength) {
		this.workongLength = workongLength;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getThreadType() {
		return threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}

	public String getThreadProfile() {
		return threadProfile;
	}

	public void setThreadProfile(String threadProfile) {
		this.threadProfile = threadProfile;
	}

	public String getHardness() {
		return hardness;
	}

	public void setHardness(String hardness) {
		this.hardness = hardness;
	}

	public String getMaterialExecution() {
		return materialExecution;
	}

	public void setMaterialExecution(String materialExecution) {
		this.materialExecution = materialExecution;
	}
	
	
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ScrewTap [id=" + id + ", identificator=" + identificator + ", itIsOnLoad=" + itIsOnLoad + ", brand="
				+ brand + ", totalLength=" + totalLength + ", workongLength=" + workongLength + ", thread=" + thread
				+ ", threadType=" + threadType + ", threadProfile=" + threadProfile + ", hardness=" + hardness
				+ ", materialExecution=" + materialExecution + "]";
	}
	
}
