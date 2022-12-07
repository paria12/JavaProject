package code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class Tournoi {
	private String nom;
	private String adresse;
	private String ville;
	private String pays;
	private String codePostal;
	private Date date;
	private String notoriete;
	
	public Tournoi(String nom, Date date) {
		this.nom = nom;
		this.adresse = null;
		this.ville = null;
		this.pays = null;
		this.codePostal = null;
		this.date = date;
		this.notoriete = null;
	}
	
	public Tournoi(String nom, String adresse, String ville, String pays, String codePostal, Date date, String notoriete) {
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.codePostal = codePostal;
		this.date = date;
		this.notoriete = notoriete;
	}
	
	
	
	public String getNom() {
		return nom;
	}

	public String getAdresse() throws ErreurBD {
		if (this.adresse == null) {
			this.select();
		}
		return adresse;
	}

	public String getVille() throws ErreurBD {
		if (this.ville == null) {
			this.select();
		}
		return ville;
	}

	public String getPays() throws ErreurBD {
		if (this.pays == null) {
			this.select();
		}
		return pays;
	}

	public String getCodePostal() throws ErreurBD {
		if (this.codePostal == null) {
			this.select();
		}
		return codePostal;
	}

	public Date getDate() {
		return date;
	}

	public String getNotoriete() throws ErreurBD {
		if (this.notoriete == null) {
			this.select();
		}
		return notoriete;
	}

	public void select() throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rese = st.executeQuery("select adresse, ville, pays, codepostal, notoriete from Tournoi where nom='"+this.nom+"' and datetournoi='"+this.date+"'");

			rese.next();
			if (this.adresse == null) {
				this.adresse = rese.getString(1);
			}
			if (this.ville == null) {
				this.ville = rese.getString(2);
			}
			if (this.pays == null) {
				this.pays = rese.getString(3);
			}
			if (this.codePostal == null) {
				this.codePostal = rese.getString(4);
			}
			if (this.notoriete == null) {
				this.notoriete = rese.getString(5);
			}

		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requette a la bd");
		}
	}
	
}
