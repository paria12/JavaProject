package code;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class Jeu {

	private String nom;
	private int duree;

	/**constructeur de Jeu à partir de son nom
	 * @param nom
	 */
	public Jeu(String nom) {
		this.nom = nom;
		this.duree = -1;
	}

	/**constructueur de Jeu à partir de son nom et sa durée
	 * @param nom
	 * @param duree
	 */
	public Jeu(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
	}

	/** renvoie le nom du jeu
	 * @return string : nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** retourne la dur�e de la partie du Jeu
	 * @return int : durée
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 */
	public int getDuree() throws ErreurBD {
		if (this.duree>0) {
			return this.duree;
		} else {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select temps_partie from jeu where nom='"+this.nom+"'");

				rese.next();
				this.duree = rese.getInt(1);
				
				connx.close();
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requête a la bd");
			}
			return this.duree;
		}
	}

	/** insérer toutes les informations dans la base de données
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 * @throws IllegalArgumentException
	 * @exception la dur�e est inf�rieure � 0
	 */
	public void insert() throws ErreurBD, IllegalArgumentException{
		if (this.duree < 0) {
			throw new IllegalArgumentException("La dur�e n'est pas valide");
		} else {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO jeu values(seq_jeu.nextVal,'"+this.nom+"',"+this.duree+")");

				connx.close();
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
		}
	}
	
	/**Renvoie tous les noms des jeux contenus dans la base de données
	 * @return un tableau d'objet
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 */
	public static Object[] getAll() throws ErreurBD {
		List<String> l = new ArrayList<String>();

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select nom from jeu order by 1");
			
			while(rs.next()) {
				l.add(rs.getString(1));
			}

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requête a la bd : "+e);
		}
		return l.toArray();
	}
	
	/**renvoie l'id d'un jeu en fonction de son nom
	 * @param j
	 * @return int
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 */
	public static int getID(Jeu j) throws ErreurBD {
		int retour = 0;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select id_jeu from jeu where nom='"+j.getNom()+"'");
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requête à la bd : "+e);
		}
		return retour;
	}
	
	/**renvoie la durée d'un jeu en fonction de son id
	 * @param id
	 * @return int
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 */
	public static int getTimeFromID(int id) throws ErreurBD {
		int retour = 0;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select temps_partie from jeu where id_jeu="+id);
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requéte a la bd : "+e);
		}
		return retour;
	}
}