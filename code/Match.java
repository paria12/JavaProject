package code;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

public class Match {
	private Timestamp Hdebut;
	private Timestamp Hfin;
	private Equipe E1;
	private Equipe E2;

	/** Constructeur d'un match a partir de deux equipes, un timestamp pour l'heure de debut et un timestamp
	 *  pour l'heure de fin
	 * @param e1
	 * @param e2
	 * @param hd
	 * @param hf
	 */
	public Match(Equipe e1, Equipe e2, Timestamp hd, Timestamp hf) {
		if (e1 == null || e2 == null || hd == null || hf == null) {
			throw new IllegalArgumentException("L'un des parametres est null");
		} else if (hd.compareTo(hf) >= 0) {
			throw new IllegalArgumentException("L'heure de debut est superieur ou egal a l'heure de fin");
		}
		this.E1 = e1;
		this.E2 = e2;
		this.Hdebut = hd;
		this.Hfin = hf;
	}

	/** Retourne l'heure de debut du match
	 * 
	 * @return timestamp : hdebut
	 */
	public Timestamp getHDebut() {
		return Hdebut;
	}

	/** Retourne l'heure de fin d'un match
	 * 
	 * @return timestamp : hfin
	 */
	public Timestamp getHFin() {
		return Hfin;
	}

	/** Retourne la premiere equipe du match
	 * 
	 * @return equipe : e1
	 */
	public Equipe getEquipe1() {
		return E1;
	}

	/** Retourne la seconde equipe du match
	 * 
	 * @return equipe : e2
	 */
	public Equipe getEquipe2() {
		return E2;
	}

	/** Rajoute le gagnant du match dans la base de donnee
	 * 
	 * @param e
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public void setWinner(Equipe e) throws ErreurBD {
		if (!e.equals(this.E1)&&!e.equals(this.E2)) {
			throw new IllegalArgumentException("L'equipe est invalide");
		}
		try {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(this.Hdebut);

	        ConnexionBD.Query("Update matchs set gagnant = "+e.getID()+" where ID_equipe ="+this.E1.getID()+" and ID_equipe1 ="+this.E2.getID()+"and heuredebut ="
					+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+" "
					+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+"'");
		} catch (SQLException exc) {
			switch(exc.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= exc.getErrorCode() && exc.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
	}

	/** Insert le match dans une poule donnee dans la base de donnee
	 * 
	 * @param poule
	 * @throws ErreurBD lorque une erreur lie a la base de donne est leve
	 */
	public void insert(int poule) throws ErreurBD {
		try {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(this.Hdebut);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(this.Hfin);

	        ConnexionBD.Query("INSERT INTO matchs values("+this.E1.getID()+","+this.E2.getID()+","+poule+",null,'"
					+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+" "
					+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+"','"
					+cal2.get(Calendar.DAY_OF_MONTH)+"/"+(cal2.get(Calendar.MONTH)+1)+"/"+(cal2.get(Calendar.YEAR))+" "
					+cal2.get(Calendar.HOUR_OF_DAY)+":"+cal2.get(Calendar.MINUTE)+":"+cal2.get(Calendar.SECOND)+"')");
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
	}

	/** Redefinition de la methode hashCode
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(E1, E2, Hdebut, Hfin);
	}
 
	/** Redefinition de la methode equals
	 * 
	 */
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
	
	/** Methode toString
	 * 
	 */
	@Override
    public String toString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Hdebut);
        String min = String.valueOf(cal.get(Calendar.MINUTE));
        if (min.length() <= 1) {
            min = "0"+min;
        }
        String hD = cal.get(Calendar.HOUR)+"h"+min;
        cal.setTime(Hfin);
        min = String.valueOf(cal.get(Calendar.MINUTE));
        if (min.length() <= 1) {
            min = "0"+min;
        }
        String hF = cal.get(Calendar.HOUR)+"h"+min;
        return E1+" VS. "+E2+" ("+hD+" - "+hF+")";
    }
}
