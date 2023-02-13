package code;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @author leobb
 *
 */
public class Connexion {

	/** Connexion a l'application par les differents utilisateurs
	 * @param identifiant
	 * @param mdp
	 * @return int
	 * @throws SQLException
	 */
	public static int connexion(String identifiant, String mdp) throws SQLException {
		DataSource bd = new ConnexionBD();

		Connection connx = bd.getConnection();
			
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
		
		
		// Connexion arbitre
		ResultSet resa = st.executeQuery("select mdp from arbitre where login='"+identifiant+"'");
		if (resa.next()) {
			if (resa.getString(1).equals(sta256(mdp))) {
				return 2;
			}
		}
		
		
		return -1;
	}
	
	/**Crypter un string en hexadecimal
	 * @param str
	 * @return string
	 */
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
	
	/**Transformer un tableau de bytes en string qui correspond a l'hexadecimal de ce tableau
	 * @param hash
	 * @return string 
	 */
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
