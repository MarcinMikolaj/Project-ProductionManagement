package project.entities.materials;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;

import project.validator.algorithms.Dictionary;
import project.validator.annotation.Curses;

import java.util.List;
import java.util.ArrayList;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//**Lombok**
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter
@ToString
//**Hibernate**
@Entity
@Table(name = "material_provider")
@NamedQueries({
	@NamedQuery(name = "MaterialProvider.removeByNip", query = "DELETE FROM MaterialProvider m WHERE m.nip = :nip1"),
	@NamedQuery(name = "MaterialProvider.findByNip", query = "SELECT m FROM MaterialProvider m WHERE m.nip = :nip2"),
	@NamedQuery(name = "MaterialProvider.findByCompanyName", query = "SELECT m FROM MaterialProvider m WHERE m.companyName = :companyName"),
	@NamedQuery(name = "MaterialProvider.findAll", query = "SELECT m FROM MaterialProvider m"),
	@NamedQuery(name = "MaterialProvider.existByNip", query = "SELECT CASE WHEN count(e) > 0 THEN true ELSE false END FROM MaterialProvider e WHERE e.nip = :nip3")
})
public class MaterialProvider implements Serializable {
	private static final long serialVersionUID = 7340978513006236173L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotBlank(message = "The field is required")
	@Curses(isActive = true, dictionary = {Dictionary.ENG, Dictionary.PL}, message = "The word ${validatedValue} is not allwowe")
	@Column(unique = true)
	private String companyName;
	
	@NotBlank(message = "The field is required")
	@Column(name = "adress", unique = false, nullable = false)
	private String adress;
	
	@Email
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@NotBlank(message = "The field is required")
	@Column(name = "number", unique = true, nullable = false)
	private String number;
	
	@NIP
	@Column(name = "nip", unique = true, nullable = false)
	private String nip;
	
	@REGON
	@NotBlank(message = "The field is required")
	@Column(name = "regon", unique = true, nullable = false)
	private String regon;
	
	@OneToMany
	@ElementCollection
	@LazyCollection(LazyCollectionOption.TRUE)
	@ToString.Exclude
	private List<Pipe> steelPipeList = new ArrayList<Pipe>();
	
	@OneToMany
	@ElementCollection
	@LazyCollection(LazyCollectionOption.TRUE)
	@ToString.Exclude
	private List<Rod> rodList = new ArrayList<Rod>();

	public MaterialProvider(@NotBlank(message = "The field is required") String companyName,
			@NotBlank(message = "The field is required") String adress, @Email String email,
			@NotBlank(message = "The field is required") String number, @NIP String nip,
			@REGON @NotBlank(message = "The field is required") String regon) {
		super();
		this.companyName = companyName;
		this.adress = adress;
		this.email = email;
		this.number = number;
		this.nip = nip;
		this.regon = regon;
	}
	
}
