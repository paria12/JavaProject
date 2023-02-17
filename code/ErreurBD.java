package code;

import java.sql.SQLException;

public  class ErreurBD  extends Exception {

	/** Lever d'exception lorsque la liaison à la base de donnée ne se fait pas correctement
	 * @param s
	 */
	public ErreurBD (String s) {
       super(s) ;
	}
	
	/**
	 * Message des erreurs lie a la bd
	 * @param e
	 * @throws ErreurBD
	 */
	public static void excSQL(SQLException e) throws ErreurBD {
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
