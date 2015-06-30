package modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long matricule;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	
	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	

	
}
