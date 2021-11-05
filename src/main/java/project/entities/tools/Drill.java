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
@Table(name = "drill")
public class Drill implements Serializable {
	
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
    private Employee employee;
    
    
    public Drill() {}

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getWorkingLength() {
		return workingLength;
	}

	public void setWorkingLength(String workingLength) {
		this.workingLength = workingLength;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getApexAngle() {
		return apexAngle;
	}

	public void setApexAngle(String apexAngle) {
		this.apexAngle = apexAngle;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public String getMaterialExecution() {
		return materialExecution;
	}

	public void setMaterialExecution(String materialExecution) {
		this.materialExecution = materialExecution;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public boolean isQuickClampingSystem() {
		return quickClampingSystem;
	}

	public void setQuickClampingSystem(boolean quickClampingSystem) {
		this.quickClampingSystem = quickClampingSystem;
	}
	
	public boolean getItIsOnLoad() {
		return itIsOnLoad;
	}
	
	public void setItIsOnLoad(boolean itIsOnLoad) {
		this.itIsOnLoad = itIsOnLoad;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmpolyee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Drill [id=" + id + ", identificator=" + identificator + ", quantity=" + quantity + ", totalLength="
				+ totalLength + ", workingLength=" + workingLength + ", diameter=" + diameter + ", apexAngle="
				+ apexAngle + ", destiny=" + destiny + ", materialExecution=" + materialExecution + ", handleType="
				+ handleType + ", quickClampingSystem=" + quickClampingSystem + "]";
	}
    
}
