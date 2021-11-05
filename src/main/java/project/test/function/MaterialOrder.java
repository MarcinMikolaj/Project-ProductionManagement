package project.test.function;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;

import java.sql.Date;
import java.sql.Time;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "material_order")
@NamedQueries({
	@NamedQuery(name = "MaterialOrder.findAll", query = "SELECT o FROM MaterialOrder o"),
})
public class MaterialOrder implements Serializable {
	
    private static final long serialVersionUID = -612019065103318317L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private MaterialProvider materialProvider;
	
	private String orderTitle;
	private String additionalMessage;
	
	private Date creationDate;
	private Time creationTime;
	
	@ManyToMany
	private List<SteelPipe> steelPipeList = new ArrayList<SteelPipe>();
	
	public MaterialOrder() {}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialProvider getMaterialProvider() {
		return materialProvider;
	}

	public void setMaterialProvider(MaterialProvider materialProvider) {
		this.materialProvider = materialProvider;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

	public List<SteelPipe> getSteelPipeList() {
		return steelPipeList;
	}

	public void setSteelPipeList(List<SteelPipe> steelPipeList) {
		this.steelPipeList = steelPipeList;
	}

	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Time getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}


	@Override
	public String toString() {
		return "MaterialOrder [id=" + id + ", materialProvider=" + materialProvider + ", orderTitle=" + orderTitle
				+ ", additionalMessage=" + additionalMessage + ", steelPipeList=" + steelPipeList + "]";
	}

}
