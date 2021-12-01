package project.entities.materials;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
//Hibernate
@Entity
@Table(name = "pipe")
@NamedQueries({
	@NamedQuery(name = "Pipe.findAllInMagazine", query = "SELECT p FROM Pipe p WHERE p.quantityInStock > 0")
})
public class Pipe implements Serializable {
	
	@Getter(AccessLevel.NONE)
	private static final long serialVersionUID = 5910636675914175466L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String profile; //Profil rury
	private String materialType; //Gatunek stali
	private String outerDiameter; //Średnica zewnętrzna
	private String thicknessOfTheWall; //Grubość ścianki
	private String length; //długość
	private String weight; //Waga
	private int quantityInStock; //Ilość sztuk w magazynie
	
	public Pipe(String profile, String materialType, String outerDiameter, String thicknessOfTheWall, String length,
			String weight, int quantityInStock) {
		super();
		this.profile = profile;
		this.materialType = materialType;
		this.outerDiameter = outerDiameter;
		this.thicknessOfTheWall = thicknessOfTheWall;
		this.length = length;
		this.weight = weight;
		this.quantityInStock = quantityInStock;
	}		
}
