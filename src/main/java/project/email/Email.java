package project.email;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Email implements Serializable {
	
	private static final long serialVersionUID = -2419437018945582293L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String adres;
	private String title;
	private String text;
	
	
    public Email() {};
    
    public Email(String adres, String title, String text) {
    	this.adres = adres;
    	this.title = title;
    	this.text = text;
    }
    
	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

	@Override
	public String toString() {
		return "Email [adres=" + adres + ", title=" + title + ", text=" + text + "]";
	}
    
}
