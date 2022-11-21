package code;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {

	public static int connexion(String identifiant, String mdp) throws SQLException {
		String loginBD = "ndf4080a";
		String mdpBD = "fatime31";
		String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);
			
		Statement st = connx.createStatement();
		
		// Connexion owner
		if (identifiant.equals("owner") && sta256(mdp).equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")) {
			return 0;
		}
		
		// Connexion ecurie
		ResultSet rese = st.executeQuery("select mdp from ecurie where nom='"+identifiant+"'");
		if (rese.next()) {
			if (rese.getString(1).equals(sta256(mdp))) {
				return 1;
			}
		}
		
		
		// Connection arbitre
		ResultSet resa = st.executeQuery("select mdp from arbitre where login='"+identifiant+"'");
		if (resa.next()) {
			if (resa.getString(1).equals(sta256(mdp))) {
				return 2;
			}
		}
		
		
		return -1;
	}
	
	public static String sta256(String str) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return "erreur";
		}
		byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(hash);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

}
