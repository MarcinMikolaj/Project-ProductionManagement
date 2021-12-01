package project.entities.materials;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Date;
import java.sql.Time;

import java.util.List;
import java.util.ArrayList;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@Table(name = "material_order")
@NamedQueries({
	@NamedQuery(name = "MaterialOrder.findAll", query = "SELECT o FROM MaterialOrder o"),
	@NamedQuery(name = "MaterialOrder.existByIdentificator", query = "SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM MaterialOrder e WHERE e.orderIdentificator = :identificator2"),
	@NamedQuery(name = "MaterialOrder.findByOrderIdentificator", query = "SELECT o FROM MaterialOrder o WHERE o.orderIdentificator = :identificator"),
	@NamedQuery(name = "MaterialOrder.removeByIdentificator", query = "DELETE FROM MaterialOrder m WHERE m.orderIdentificator = :identificator3")
})
public class MaterialOrder implements Serializable {
	
    private static final long serialVersionUID = -612019065103318317L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String materialProviderCompanyName;
	private String orderIdentificator;
	private String orderTitle;
	private String additionalMessage;
	private Date creationDate;
	private Time creationTime;
	
	public MaterialOrder(MaterialProvider materialProvider, String orderIdentificator, String orderTitle) {
		super();
		this.orderIdentificator = orderIdentificator;
		this.orderTitle = orderTitle;
	}

	@ManyToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	@ToString.Exclude
	private List<Pipe> steelPipeList = new ArrayList<Pipe>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	@ToString.Exclude
	private List<Rod> rodList = new ArrayList<Rod>();
	
}
