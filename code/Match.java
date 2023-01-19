package code;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

import javax.sql.DataSource;

public class Match {
	private Timestamp Hdebut;
	private Timestamp Hfin;
	private Equipe E1;
	private Equipe E2;

	public Match(Equipe e1, Equipe e2, Timestamp hd, Timestamp hf) {
		if (e1 == null || e2 == null || hd == null || hf == null) {
			throw new IllegalArgumentException("L'un des paramètres est null");
		} else if (hd.compareTo(hf) >= 0) {
			throw new IllegalArgumentException("L'heure de début est supérieur ou égal à l'heure de fin");
		}
		this.E1 = e1;
		this.E2 = e2;
		this.Hdebut = hd;
		this.Hfin = hf;
	}

	public Timestamp getHDebut() {
		return Hdebut;
	}

	public Timestamp getHFin() {
		return Hfin;
	}

	public Equipe getEquipe1() {
		return E1;
	}

	public Equipe getEquipe2() {
		return E2;
	}

	public void setWinner(Equipe e) throws ErreurBD {
		if (!e.equals(this.E1)&&!e.equals(this.E2)) {
			throw new IllegalArgumentException("L'equipe est invalide");
		}
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(this.Hdebut);

			st.executeUpdate("Update matchs set gagnant = "+e.getID()+" where ID_equipe ="+this.E1.getID()+" and ID_equipe1 ="+this.E2.getID()+"and heuredebut ="
					+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+" "
					+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+"'");

			connx.close();
		} catch (SQLException exc) {
			switch(exc.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est déjà présent dans la base de données");
            case 2291:
                throw new ErreurBD("Il manque la clé étrangère");
            case 2292:
                throw new ErreurBD("Impossibilité de supprimer car l'enregistrement est présent dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
                
            }
            if (200000<= exc.getErrorCode() && exc.getErrorCode() <=20999) {
                throw new ErreurBD("Transgréssion de l'un des déclencheurs de la base de données");
            }
		}
	}

	public void insert(int poule) throws ErreurBD, IllegalArgumentException{
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(this.Hdebut);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(this.Hfin);

			st.executeQuery("INSERT INTO matchs values("+this.E1.getID()+","+this.E2.getID()+","+poule+",null,'"
					+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+" "
					+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+"','"
					+cal2.get(Calendar.DAY_OF_MONTH)+"/"+(cal2.get(Calendar.MONTH)+1)+"/"+(cal2.get(Calendar.YEAR))+" "
					+cal2.get(Calendar.HOUR_OF_DAY)+":"+cal2.get(Calendar.MINUTE)+":"+cal2.get(Calendar.SECOND)+"')");

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est déjà présent dans la base de données");
            case 2291:
                throw new ErreurBD("Il manque la clé étrangère");
            case 2292:
                throw new ErreurBD("Impossibilité de supprimer car l'enregistrement est présent dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgréssion de l'un des déclencheurs de la base de données");
            }
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(E1, E2, Hdebut, Hfin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Match)) {
			return false;
		}
		Match other = (Match) obj;
		return Objects.equals(E1, other.E1) && Objects.equals(E2, other.E2) && Objects.equals(Hdebut, other.Hdebut)
				&& Objects.equals(Hfin, other.Hfin);
	}
}
