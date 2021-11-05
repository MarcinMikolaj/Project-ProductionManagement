package project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
import java.util.ArrayList;

import project.entities.tools.Drill;
import project.entities.tools.ScrewTap;
import project.entities.tools.LatheKnifeCutter;

@Entity
public class ToolRent implements Serializable {
	
	private static final long serialVersionUID = 5914538600564100556L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String hallNumber;
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Drill> drillList = new ArrayList<Drill>();
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<ScrewTap> screwTapList = new ArrayList<ScrewTap>();
	
	@ElementCollection
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<LatheKnifeCutter> latheKnifeCutterList = new ArrayList<LatheKnifeCutter>();
		
	public ToolRent() {};
	
	public ToolRent(String hallNumber) {
		this.hallNumber = hallNumber;
	}
	
	//***Getters And Setters****

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(String hallNumber) {
		this.hallNumber = hallNumber;
	}
	
	//*******Drill List********
	
	public List<Drill> getDrillList(){
		return drillList;
	}
	
	public void setDrillList(List<Drill> drillList) {
		this.drillList = drillList;
	}
	
	//********LatheKnifeCutter List*********
	
	public List<LatheKnifeCutter> getLatheKnifeCutterList() {
		return latheKnifeCutterList;
	}
	
	public void setLatheKnifeCutterList(List<LatheKnifeCutter> latheKnifeCutterList) {
		this.latheKnifeCutterList = latheKnifeCutterList;
	}
	
	//*******ScrewTap List***********
	
	public List<ScrewTap> getScrewTapList() {
		return screwTapList;
	}

	public void setScrewTapList(List<ScrewTap> screwTapList) {
		this.screwTapList = screwTapList;
	}
	
	
	
	@Override
	public String toString() {
		return "ToolRent [id=" + id + ", hallNumber=" + hallNumber + "]";
	}

}
