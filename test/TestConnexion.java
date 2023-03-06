package test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import modele.Connexion;

public class TestConnexion {

	/** 
	 * Verification de la bonne connexion de l'utilisateur owner
	 * @throws SQLException
	 */
	@Test
	public void testConnexionCorrectOwner() throws SQLException {
		assertEquals(0,Connexion.connexion("owner","admin"));
	}
	
	/**
	 * Verification de la non connexion lorsque le mot de passe est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testPasswordIncorrectOwner() throws SQLException {
		assertEquals(-1,Connexion.connexion("owner","mdp"));
	}
	
	/**
	 * Verification non connexion lorsque le login est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testLoginIncorrectOwner() throws SQLException {
		assertEquals(-1,Connexion.connexion("owne","admin"));
	}
	
	/**
	 * Test de la bonne connexion d'un arbire
	 * @throws SQLException
	 */
	@Test
	public void testConnexionCorrectArbitre() throws SQLException {
		assertEquals(2,Connexion.connexion("alice","feuilles"));
	}
	
	/**
	 * Test de la non connexion d'un arbitre lorsque le mot de passe est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testPasswordIncorrectArbitre() throws SQLException {
		assertEquals(-1,Connexion.connexion("alice","racine"));
	}
	
	/**
	 * Test de la non connexion d'un arbitre lorsque le login est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testLoginIncorrectArbitre() throws SQLException {
		assertEquals(-1,Connexion.connexion("Alice","feuilles"));
	}
	
	/**
	 * Test de la bonne connexion d'une ecurie
	 * @throws SQLException
	 */
	@Test
	public void testConnexionCorrectEcurie() throws SQLException {
		assertEquals(1,Connexion.connexion("Vitality","sport"));
	}
	
	/**
	 * Test de la connexion d'une ecurie lorsque le mot de passe est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testPasswordIncorrectEcurie() throws SQLException {
		assertEquals(-1,Connexion.connexion("Vitality","play"));
	}
	
	/**
	 * Test de la connexion d'une ecurie lorsque le login est incorrect
	 * @throws SQLException
	 */
	@Test
	public void testLoginIncorrectEcurie() throws SQLException {
		assertEquals(-1,Connexion.connexion("Vitaliti","sport"));
	}
	
}
