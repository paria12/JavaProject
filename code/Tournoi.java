package code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Tournoi {
	private String nom;
	private String adresse;
	private String ville;
	private String pays;
	private String codePostal;
	private Date date;
	private String notoriete;
	private int idjeu;
	
	private List<Equipe> equipes;
	
	public Tournoi(String nom, Date date, int jeu) {
		this.nom = nom;
		this.adresse = null;
		this.ville = null;
		this.pays = null;
		this.codePostal = null;
		this.date = date;
		this.notoriete = null;
		this.idjeu = jeu;
		equipes = new ArrayList<Equipe>();
	}
	
	public Tournoi(String nom, String adresse, String ville, String pays, String codePostal, Date date, String notoriete, int jeu) {
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.codePostal = codePostal;
		this.date = date;
		this.notoriete = notoriete;
		this.idjeu = jeu;
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
	
	public int getJeu() {
		return idjeu;
	}
	
	public void addEquipe(Equipe e) {
		if (this.equipes.size()>16) {
			throw new IllegalArgumentException("Le tournoi est deja plein");
		}
		this.equipes.add(e);
	}

	public void select() throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rese = st.executeQuery("select adresse, ville, pays, codepostal, notoriete from Tournoi where nomtournoi='"+this.nom+"' and datetournoi='"+this.date+"'");

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
	
	public void insert(int arbitre) throws ErreurBD, IllegalArgumentException{
		if (this.adresse != null && this.codePostal != null && this.notoriete != null && this.pays != null && this.ville != null) {
			throw new IllegalArgumentException("Un des argument n'est pas valide");
		} else {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO tournoi values(seq_tournoi.nextVal,'"+this.ville+"','"+this.pays+"','"+this.codePostal+"',"+this.date+",'"+this.notoriete+"','"+this.nom+"','"+this.adresse+"',"+arbitre+","+
						this.idjeu+")");

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
		}
	}
	
}
