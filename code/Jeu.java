package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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

	/**constructueur de la classe Jeu et de nom et dur�e
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

	/** retourne la dur�e de la partie du Jeu
	 * @return dur�e
	 * @throws ErreurBD
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
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
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
			return this.duree;
		}
	}

	/** ins�rer toutes les informations dans la base de donn�es
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
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
		}
	}
	
	public static Object[] getAll() throws ErreurBD {
		List<String> l = new ArrayList<String>();
		
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

			ResultSet rs = st.executeQuery("select nom from jeu order by 1");
			
			while(rs.next()) {
				l.add(rs.getString(1));
			}

		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requéte a la bd : "+e);
		}
		return l.toArray();
	}

}
