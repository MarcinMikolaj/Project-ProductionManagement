package project.test.function;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class SteelPipe implements Serializable {
	
	private static final long serialVersionUID = 5910636675914175466L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String profile; //Profil rury
	private String steelGrade; //Gatunek stali
	private String outerDiameter; //Średnica zewnętrzna
	private String thicknessOfTheWall; //Grubość ścianki
	
	public SteelPipe() {}
		
	public SteelPipe(String profile, String steelGrade, String outerDiameter, String thicknessOfTheWall) {
		super();
		this.profile = profile;
		this.steelGrade = steelGrade;
		this.outerDiameter = outerDiameter;
		this.thicknessOfTheWall = thicknessOfTheWall;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public String getSteelGrade() {
		return steelGrade;
	}
	
	public String getOuterDiameter() {
		return outerDiameter;
	}
	
	public String getThicknessOfTheWall() {
		return thicknessOfTheWall;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}

	public void setOuterDiameter(String outerDiameter) {
		this.outerDiameter = outerDiameter;
	}

	public void setThicknessOfTheWall(String thicknessOfTheWall) {
		this.thicknessOfTheWall = thicknessOfTheWall;
	}

	@Override
	public String toString() {
		return "SteelPipes [id=" + id + ", profile=" + profile + ", steelGrade=" + steelGrade + ", outerDiameter="
				+ outerDiameter + ", thicknessOfTheWall=" + thicknessOfTheWall + "]";
	}

}
