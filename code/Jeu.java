package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Jeu {

	private String nom;
	private int duree;

	/**constructeur de la classe Jeu et nom
	 * @param nom
	 */
	public Jeu(String nom) {
		this.nom = nom;
		this.duree = -1;
	}

	/**constructueur de la classe Jeu et de nom et durée
	 * @param nom
	 * @param duree
	 */
	public Jeu(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
	}

	/** renvoie le nom du jeu
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** retourne la durée de la partie du Jeu
	 * @return durée
	 * @throws ErreurBD
	 * @exception mauvaise connexion à la BD
	 * @exception mauvaise requête
	 */
	public int getDuree() throws ErreurBD {
		if (this.duree>0) {
			return this.duree;
		} else {
			String loginBD = "ndf4080a";
			String mdpBD = "fatime31";
			String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de connexion a la bd");
			}

			try {
				Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select temps_partie from jeu where nom='"+this.nom+"'");

				rese.next();
				this.duree = rese.getInt(1);

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requête a la bd");
			}
			return this.duree;
		}
	}

	/** insérer toutes les informations dans la base de données
	 * @throws ErreurBD
	 * @exception mauvaise connexion à la BD
	 * @exception mauvaise requête
	 * @throws IllegalArgumentException
	 * @exception la durée est inférieure à 0
	 */
	public void insert() throws ErreurBD, IllegalArgumentException{
		if (this.duree < 0) {
			throw new IllegalArgumentException("La durée n'est pas valide");
		} else {
			String loginBD = "ndf4080a";
			String mdpBD = "fatime31";
			String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de connexion a la bd");
			}

			try {
				Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO jeu values(seq_jeu.nextVal,'"+this.nom+"',"+this.duree+")");

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requête a la bd");
			}
		}
	}

}
