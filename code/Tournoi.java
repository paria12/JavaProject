package code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
	private Poule[] poules;

	public Tournoi(String nom, Date date, int jeu) {
		this.nom = nom;
		this.adresse = null;
		this.ville = null;
		this.pays = null;
		this.codePostal = null;
		this.date = date;
		this.notoriete = null;
		this.idjeu = jeu;
		this.equipes = new ArrayList<Equipe>();
		this.poules = new Poule[5];
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
		this.equipes = new ArrayList<Equipe>();
		this.poules = new Poule[5];
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
		if (this.equipes.size()>=16) {
			throw new IllegalArgumentException("Le tournoi est deja plein");
		}
		this.equipes.add(e);
	}

	public void removeEquipe(Equipe e) {
		if(this.equipes.contains(e)) {
			this.equipes.remove(e);
		}
	}

	public void GenererPoule() {
		if(this.equipes.size()!=16) {
			throw new IllegalArgumentException("Impossible de genere les poule tant que toute les equipe ne sont pas inscrite");
		} else {
			Collections.sort(this.equipes,new Comparator<Equipe>() {
				@Override
				public int compare(Equipe e1, Equipe e2) {
					try {
						if(e1.getNbPoints()>e2.getNbPoints()) {
							return 1;
						} else if(e1.getNbPoints()==e2.getNbPoints()) {
							return 0;
						} else {
							return -1;
						}
					} catch(Exception e) {
						e.printStackTrace();
						return 0;
					}
				}});
			this.poules[0]=new Poule();
			this.poules[1]=new Poule();
			this.poules[2]=new Poule();
			this.poules[3]=new Poule();
			for(int i = 0; i < 16; i++) {
				this.poules[i%4].insérerEquipe(this.equipes.get(i));
			}
		}
	}

	public void GenererPouleFinal() throws IllegalArgumentException, ErreurBD {
		for(int k = 0; k < 4; k++) {
			if (this.poules[k]==null) {
				throw new IllegalArgumentException("Au moins une des poule n'est pas definié");
			}
		}
		this.poules[4] = new Poule();
		for(int i = 0; i < 4; i++) {
			this.poules[4].insérerEquipe(new Equipe(this.poules[i].getClassement()[0][0]));
		}
	}

	public String[][] getClassement() throws ErreurBD {
		try {
			String[][] classement = new String[2][16];

			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			int i = 0;
			ResultSet rs =st.executeQuery("SELECT equipe.nom, count(*) from equipe, matchs, poule, tournoi where matchs.gagnant=equipe.id_equipe and matchs.id_poule=poule.id_poule and poule.id_tournoi=tournoi.id_tournoi and tournoi.nomtournoi='"+this.nom+"' group by equipe.nom order by 2 desc");
			while (rs.next()) {
				classement[0][i] = rs.getString(1);
				classement[1][i] = rs.getString(2);
				i++;
			}
			if (i<15) {
				ArrayList<String> tmp = new ArrayList<String>();
				for(String s:classement[0]) {
					tmp.add(s);
				}
				for(Equipe e:this.equipes) {
					if(!tmp.contains(e.getNom())) {
						classement[0][i] = e.getNom();
						classement[1][i] = "0";
					}
				}
			}
			
			connx.close();
			
			return classement;
		} catch (SQLException e){ 
			throw new ErreurBD("Erreur de requete a la bd"+e);
		}
	}

	public void genererScore() throws ErreurBD {
		if (this.poules[4]==null) {
			throw new IllegalArgumentException("La poule final n'a pas etait faite");
		}
		int mult=0;
		switch (this.notoriete) {
		case "local":
			mult=1;
			break;
		case "national":
			mult=2;
			break;
		case "international":
			mult=3;
			break;
		default:
			throw new IllegalArgumentException("la notorièté du tournois n'est pas valide");
		}
		for (int i = 0; i < 4; i++) {
			try {
				DataSource bd = new ConnexionBD();

				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select nb_points from equipe where nom='"+this.poules[4].getClassement()[0][i]+"'");

				rese.next();
				int nbp = rese.getInt(1);

				switch (i) {
				case 0:
					nbp += 100*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				case 1:
					nbp += 60*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				case 2:
					nbp += 30*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				default:
					nbp += 10*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				}

				st.executeUpdate("Update equipe set nb_points="+nbp+" where nom='"+this.poules[4].getClassement()[0][i]+"'");

				connx.close();
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requette a la bd");
			}
		}
	}

	public void select() throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			
			System.out.println("select adresse, ville, pays, codepostal, notoriete from Tournoi where nomtournoi='"+this.nom+"' and datetournoi='"+this.date.getDate()+"/"+this.date.getMonth()+1+"/"+this.date.getYear()+"'");
			ResultSet rese = st.executeQuery("select adresse, ville, pays, codepostal, notoriete from Tournoi where nomtournoi='"+this.nom+"' and datetournoi='"+this.date.getDate()+"/"+this.date.getMonth()+1+"/"+(this.date.getYear()-100)+"'");

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

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur requette : "+e.getMessage());
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

				connx.close();
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
		}
	}

	public static Tournoi[] getAll() throws ErreurBD {
		List<Tournoi> l = new ArrayList<Tournoi>();

		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select nomtournoi, datetournoi, id_jeu from tournoi");

			while(rs.next()) {
				l.add(new Tournoi(rs.getString(1),rs.getDate(2),rs.getInt(3)));
			}

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requête a la bd : "+e);
		}
		Tournoi[] t = new Tournoi[l.size()];
		t = l.toArray(t);
		return t;
	}

	public static Tournoi[] getAvailableEquipe(Equipe e) throws ErreurBD {
		List<Tournoi> l = new ArrayList<Tournoi>();

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			int id_jeu = e.getIdJeu();
			ResultSet rs = st.executeQuery("select t.nomtournoi, t.datetournoi from tournoi t where id_jeu="+id_jeu+" and "+e.getID()+"not in (select distinct id_equipe from participation) and (SELECT COUNT(*) FROM Participation p WHERE p.id_tournoi = t.id_tournoi)<16");

			while(rs.next()) {
				l.add(new Tournoi(rs.getString(1),rs.getDate(2),id_jeu));
			}

			connx.close();
		} catch (SQLException ex) {
			throw new ErreurBD("Erreur de requête a la bd : "+ex.getMessage());
		}
		Tournoi[] t = new Tournoi[l.size()];
        t = l.toArray(t);
        return t;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, idjeu, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tournoi)) {
			return false;
		}
		Tournoi other = (Tournoi) obj;
		return Objects.equals(date, other.date) && idjeu == other.idjeu && Objects.equals(nom, other.nom);
	}
	
	@Override
	public String toString() {
		return this.date.toString()+" - "+this.nom;
	}	
}
