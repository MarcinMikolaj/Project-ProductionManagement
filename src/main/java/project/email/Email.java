package project.email;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NonNull;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString(onlyExplicitlyIncluded = true)
//Hibernate
@Entity
public class Email implements Serializable {
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = -2419437018945582293L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@ToString.Include
	private Long id;
	@ToString.Include
	@NonNull
	private String adres;
	@ToString.Include
	@NonNull
	private String title;
	@NonNull
	private String text;
	
    public Email(String adres, String title, String text) {
    	this.adres = adres;
    	this.title = title;
    	this.text = text;
    }
    
}
