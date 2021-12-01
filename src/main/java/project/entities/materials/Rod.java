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
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
//hibernate
@Entity
@Table(name = "rod")
@NamedQueries({
	@NamedQuery(name = "Rod.findById", query = "SELECT r FROM Rod r WHERE r.id = :id"),
	@NamedQuery(name = "Rod.findAllInWarehouse", query = "SELECT r FROM Rod r WHERE r.quantityInStock > 0")
})
public class Rod implements Serializable  {
	  
	@Getter(AccessLevel.NONE)
	private static final long serialVersionUID = -802599163010291188L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String profile; //Profil pręta
	private String materialType; //Gatunek materiału
	private String diameter; //Średnica zewnętrzna
	private String length; //długość
	private String weight; //Waga
	private String additional; //Dodatkowy opis
	private int quantityInStock;
	
	public Rod(String profile, String materialType, String diameter, String length, String weight, String additional,
			int quantityInStock) {
		super();
		this.profile = profile;
		this.materialType = materialType;
		this.diameter = diameter;
		this.length = length;
		this.weight = weight;
		this.additional = additional;
		this.quantityInStock = quantityInStock;
	}
}
