package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import javax.sql.DataSource;

public class Arbitre {
	private String login;

	/** Constructeur d'un arbitre en fonction de son login
	 * 
	 * @param log
	 */
	public Arbitre(String log) {
		this.login = log;
	}

	/** Retourne l'id de l'arbitre depuis la base de donnees
	 * 
	 * @return int : id
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public int getID() throws ErreurBD {
		int ID = -1;
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select id_arbitre from arbitre where login ='"+this.login+"'");
			rs.next();
			ID = rs.getInt(1);

			connx.close();
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
		return ID;
	}

	/** Retourne les matchs assignes a l'arbitre
	 * 
	 * @return tableau de match : t
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public Match[] getMatch() throws ErreurBD {
		ArrayList<Match> result = new ArrayList<Match>();
		Match[] t = null;
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select e1.nom,e2.nom,heuredebut,heurefin from matchs, equipe e1, equipe e2, poule, tournoi, arbitre "
					+ "where e1.id_equipe=matchs.id_equipe and e2.id_equipe=matchs.id_equipe1 "
					+ "and matchs.id_poule=poule.id_poule and poule.id_tournoi=tournoi.id_tournoi "
					+ "and tournoi.id_arbitre=arbitre.id_arbitre and arbitre.login='"+this.login+"'");

			while(rs.next()) {
				result.add(new Match(new Equipe(rs.getString(1)),new Equipe(rs.getString(2)),rs.getTimestamp(3),rs.getTimestamp(4)));
			}

			connx.close();

			t = new Match[result.size()];
			t = result.toArray(t);
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
		return t;

	}

	/** Retourne un tableau de tous les noms des arbitres de la base de donnees
	 * 
	 * @return tableau de string : t
	 * @throws ErreurBD
	 */
	public static String[] getAll() throws ErreurBD {
		ArrayList<String> result = new ArrayList<String>();
		String[] t = null;
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select login from arbitre order by 1");

			while(rs.next()) {
				result.add(rs.getString(1));
			}

			connx.close();

			t = new String[result.size()];
			t = result.toArray(t);

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
		return t;
	}

	/** Insert un nouvel arbitre dans la base de donnees
	 * 
	 * @param pwd 
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public void insert(String pwd) throws ErreurBD {

		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeQuery("INSERT INTO arbitre values(seq_arbitre.nextVal,'"+this.login+"','"+Connexion.sta256(pwd)+"')");

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
		return Objects.hash(login);
	}

	/** Redefinition de la methode equals
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Arbitre)) {
			return false;
		}
		Arbitre other = (Arbitre) obj;
		return Objects.equals(login, other.login);
	}
}
