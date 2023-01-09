package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.sql.DataSource;

public class Poule {

	private Equipe[] equipes;
	private Match[] matchs;
	private int pointeur;
	private int id;

	public Poule (){
		this.equipes = new Equipe[4];
		this.matchs = new Match[6];
		this.pointeur = 0;
	}

	public void insérerEquipe(Equipe e) throws IllegalArgumentException{
		if (this.pointeur == 4) {
			throw new IllegalArgumentException("La poule est déjà pleine");
		} else {
			this.equipes[pointeur] = e;
			this.pointeur++;
		}
	}
	
	public Equipe[] getEquipe () {
		return this.equipes;
	}

	public void GenererMatch(Timestamp hdebut) throws ErreurBD {
		int temps = Jeu.getTimeFromID(this.equipes[0].getIdJeu());
		this.matchs[0] = new Match(this.equipes[2],this.equipes[3],hdebut,new Timestamp(hdebut.getTime()+temps));
		this.matchs[1] = new Match(this.equipes[0],this.equipes[1],hdebut,new Timestamp(hdebut.getTime()+temps));
		this.matchs[2] = new Match(this.equipes[0], this.equipes[2],new Timestamp(hdebut.getTime()+temps), new Timestamp(hdebut.getTime()+2*temps));
		this.matchs[3] = new Match(this.equipes[1], this.equipes[3], new Timestamp(hdebut.getTime()+temps), new Timestamp(hdebut.getTime()+2*temps));
		this.matchs[4] = new Match(this.equipes[0], this.equipes[3],new Timestamp(hdebut.getTime()+2*temps) , new Timestamp(hdebut.getTime()+3*temps));
		this.matchs[5] = new Match(this.equipes[1], this.equipes[2], new Timestamp(hdebut.getTime()+2*temps), new Timestamp(hdebut.getTime()+3*temps));
	}

	public Match[] getMatch() {
		return this.matchs;
	}
	
	public void insert(int tournoi) throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			st.executeQuery("Insert into Poule values(seq_poule.nextVal, "+tournoi+")");
			for (Equipe e : this.equipes) {
				st.executeQuery("Insert into Participer values ( "+e.getID()+ ", seq_poule.currval)");
			}
			ResultSet rs = st.executeQuery("select seq_poule.currval from poule");
			this.id = rs.getInt(1);
			for (Match m : this.matchs) { 
				m.insert(this.id);
			}
		}catch (SQLException e){ 
			throw new ErreurBD("Erreur de requete a la bd"+e);
		}    
	}

	public String[][] getClassement() throws ErreurBD {
		try {
			String[][] classement = new String[2][4];
			
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			
			int i = 0;
			ResultSet rs =st.executeQuery("SELECT equipe.nom, count(*) from equipe, matchs where matchs.gagnant=equipe.id_equipe and matchs.id_poule="+this.id+" group by equipe.nom order by 2 desc");
			while (rs.next()) {
				classement[0][i] = rs.getString(1);
				classement[1][i] = rs.getString(2);
				i++;
			}
			return classement;
		} catch (SQLException e){ 
			throw new ErreurBD("Erreur de requete a la bd"+e);
		}
	}

}