package code;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.sql.DataSource;

public class Poule {

	private Equipe[] equipes;
	private Match[] matchs;
	private int pointeur;

	public Poule (){
		this.equipes = new Equipe[4];
		this.matchs = new Match[6];
		this.pointeur = 0;
	}


	public void InsérerEquipe(Equipe e) throws IllegalArgumentException{
		for (Equipe i : this.equipes) {
			if (i.equals(e)) {
				throw new IllegalArgumentException("L'equipe est déjà présente dans la poule");
			}

		}
		if (this.pointeur == 4) {
			throw new IllegalArgumentException("La poule est déjà pleine");
		} else {
			this.equipes[pointeur] = e;
			this.pointeur++;
		}
	}

	public void insert(int tournoi) throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeQuery("Insert into Poule Values(seq_poule.nextVal, "+tournoi+")");

			for (Equipe e : this.equipes) {
				st.executeQuery("Insert into Participer Values ( "+e.getID()+ ", seq_poule.CURRVAL");
			}
		}catch (SQLException e){
			throw new ErreurBD("Erreur de requete a la bd");
		}    
	}



	public void GenererMatch(Time hdebut) throws ErreurBD {
		this.matchs[0] = new Match(this.equipes[0],this.equipes[1],hdebut, new Time(hdebut.getTime() + Jeu.getTimeFromID()));
		this.matchs[1] = new Match(this.equipes[2],this.equipes[3],hdebut,new Time(hdebut.getTime() + Jeu.getTimeFromID()) );
		this.matchs[2] = new Match(this.equipes[0], this.equipes[2],hdebut, new Time(hdebut.getTime() + Jeu.getTimeFromID()));
		this.matchs[3] = new Match(this.equipes[1], this.equipes[3], hdebut, new Time(hdebut.getTime() + Jeu.getTimeFromID()));
		this.matchs[4] = new Match(this.equipes[0], this.equipes[3], hdebut, new Time(hdebut.getTime() + Jeu.getTimeFromID()));
		this.matchs[5] = new Match(this.equipes[1], this.equipes[2], hdebut, new Time(hdebut.getTime() + Jeu.getTimeFromID()));
	}


}