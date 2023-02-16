package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class ConnexionBD extends OracleDataSource {
	
	private static Connection connx = null;

	/** Connexion de l'application a la base de donnees
	 * @throws SQLException
	 */
	public ConnexionBD() throws SQLException {
		super();
		this.setURL("jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre");
		this.setUser("ndf4080a");
		this.setPassword("fatime31");
	}
	
	/** Appelle la connexion unique a la bd pour executer une requete sql
	 * 
	 * @param sql : requete a effectuer
	 * @return ResulSet : resultat de la requete
	 * @throws SQLException
	 */
	public static ResultSet Query(String sql) throws SQLException {
		if (ConnexionBD.connx==null) {
			DataSource bd = new ConnexionBD();
			ConnexionBD.connx = bd.getConnection();
		}Statement st = connx.createStatement();
		ResultSet rese = st.executeQuery(sql);
		
		return rese;
	}
}