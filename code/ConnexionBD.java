package code;

import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class ConnexionBD extends OracleDataSource {

	/** Connexion de l'application à la base de donnée
	 * @throws SQLException
	 */
	public ConnexionBD() throws SQLException {
		super();
		this.setURL("jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre");
		this.setUser("ndf4080a");
		this.setPassword("fatime31");
	}
}