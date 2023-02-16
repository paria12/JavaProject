package test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import code.Connexion;

public class TestConnexion {

	@Test
	public void testOwner() throws SQLException {
		assertEquals(0,Connexion.connexion("owner","admin"));
	}
	
	@Test
	public void testNotOwner() throws SQLException {
		assertEquals(-1,Connexion.connexion("owner","mdp"));
	}
	
	@Test
	public void testArbitre() throws SQLException {
		assertEquals(2,Connexion.connexion("arbre","feuilles"));
	}
	
	@Test
	public void testNotArbitre() throws SQLException {
		assertEquals(-1,Connexion.connexion("arbre","racine"));
	}
	
	@Test
	public void testEcurie() throws SQLException {
		assertEquals(1,Connexion.connexion("Vitality","sport"));
	}
	
	@Test
	public void testNotEcurie() throws SQLException {
		assertEquals(-1,Connexion.connexion("Vitality","play"));
	}
	
}
