package test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import code.Connexion;

public class testConnexion {

	@Test
	public void testOwner() throws SQLException {
		assertEquals(Connexion.connexion("owner","admin"),0);
	}
	
	@Test
	public void testNotOwner() throws SQLException {
		assertEquals(Connexion.connexion("owner","mdp"),-1);
	}
	
	@Test
	public void testArbitre() throws SQLException {
		assertEquals(Connexion.connexion("arbre","feuilles"),2);
	}
	
	@Test
	public void testNotArbitre() throws SQLException {
		assertEquals(Connexion.connexion("arbre","racine"),-1);
	}
	
	@Test
	public void testEcurie() throws SQLException {
		assertEquals(Connexion.connexion("Vitality","sport"),1);
	}
	
	@Test
	public void testNotEcurie() throws SQLException {
		assertEquals(Connexion.connexion("Vitality","play"),-1);
	}
	
}
