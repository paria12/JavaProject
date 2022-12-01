package code;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.sql.DataSource;

public class Match {
	private Time Hdebut;
	private Time Hfin;
	private Equipe E1;
	private Equipe E2;

	public Match(Equipe e1, Equipe e2, Time hd, Time hf) {
		if (e1 == null || e2 == null || hd == null || hf == null) {
			throw new IllegalArgumentException("L'un des paramètres est null");
		} else if (hd.compareTo(hf) <= 0) {
			throw new IllegalArgumentException("L'heure de début est supérieur ou égal à l'heure de fin");
		}
		this.E1 = e1;
		this.E2 = e2;
		this.Hdebut = hd;
		this.Hfin = hf;
	}

	public Time getHDebut() {
		return Hdebut;
	}

	public Time getHFin() {
		return Hfin;
	}

	public Equipe getEquipe1() {
		return E1;
	}

	public Equipe getEquipe2() {
		return E2;
	}

	public void setWiner(Equipe e) throws ErreurBD {
		if (!e.equals(this.E1)&&!e.equals(this.E2)) {
			throw new IllegalArgumentException("L'equipe est invalide");
		}
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeQuery("Update match set gagnant = "+e.getID()+" where ID_equipe ="+this.E1.getID()+" and ID_equipe1 ="+this.E2.getID()+"and heuredebut ="+this.Hdebut+")");

		} catch (SQLException exc) {
			throw new ErreurBD("Erreur de requéte a la bd");
		}
	}

	public void Insert(int poule) throws ErreurBD, IllegalArgumentException{
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeQuery("INSERT INTO match values("+this.E1.getID()+","+this.E2.getID()+","+poule+",null,"+this.Hdebut+","+this.Hfin+")");

		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requéte a la bd");
		}
	}
}
