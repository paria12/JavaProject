package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import modele.ConnexionBD;
import modele.Ecurie;
import modele.Equipe;
import modele.ErreurBD;

public class TestEcurie {
	private Ecurie e;
	private Ecurie e2;
	
	/**
	 * Suppression de toutes les donnees ajoutes dans la base de donnees par le test
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeUpdate("delete equipe where nom = 'test'");
			st.executeUpdate("delete Ecurie where nom = 'test'");
			st.executeUpdate("delete Ecurie where nom = 'test2'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creation de deux ecuries au debut des tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.e = new Ecurie("KCorp");
		this.e2 = new Ecurie("Vitality","Professionnelle");
	}

	/**
	 * Les ecuries sont null a la fin des tests
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.e = null;
		this.e2 = null;
	}

	/**
	 * Test contruction d'une ecurie a partir de son nom
	 */
	@Test
	public void testEcurieNom() {
		Ecurie t = new Ecurie("KCorp");
		assertEquals(t,this.e);
	}

	/**
	 * Test constructeur ecurie a partir de son nom et son type
	 */
	@Test
	public void testEcurieNomType() {
		Ecurie t = new Ecurie("Vitality","Professionnelle");
		assertEquals(t,this.e2);
	}

	/**
	 * Test ajout d'une equipe dans la liste d'equipe d'une ecurie
	 */
	@Test
	public void testAddEquipe() {
		this.e2.addEquipe(new Equipe("Dream Team", 100, 1));
		try {
			assertEquals(1,this.e2.getEquipe().size());
		} catch (ErreurBD e) {
			fail("Erreur BD");
		}
	}

	/**
	 * Test de la suppression d'une equipe de la liste des equipes appartenant a une ecurie
	 */
	@Test
	public void testRemoveEquipe() {
		Equipe e = new Equipe("Dream Team", 100, 1);
		this.e2.addEquipe(new Equipe("test", 200, 2));
		this.e2.addEquipe(e);
		this.e2.removeEquipe(e);
		try {
			assertEquals(1,this.e2.getEquipe().size());
		} catch (ErreurBD x) {
			fail("Erreur BD");
		}
	}

	/**
	 * Test de la recuperation du nom d'une ecurie
	 */
	@Test
	public void testGetNom() {
		assertEquals("KCorp",this.e.getNom());
		assertEquals("Vitality",this.e2.getNom());
	}

	/**
	 * Test de la recuperation du type de l'ecurie
	 */
	@Test
	public void testGetType() {
		try {
			assertEquals("Professionnelle",this.e.getType());
			assertEquals("Professionnelle",this.e2.getType());
		} catch (ErreurBD e) {
			fail("ErreurBd");
		}
		
	}

	/**
	 * Test de la recuperation de toutes les equipes appartenant a une ecurie
	 */
	@Test
	public void testGetEquipe() {
		Ecurie t = new Ecurie("test","Professionnelle");
		Equipe test = new Equipe("test",0,1);
		try {
			t.insert("pwd");
			test.insert(Ecurie.getID(t));
		} catch (ErreurBD e) {
			fail("Erreur set up : "+e.getMessage());
		}
		
		Ecurie t2 = new Ecurie("test");
		try {
			assertEquals(test,t2.getEquipe().get(0));
		} catch (ErreurBD e) {
			fail("Erreur Get : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne insertion d'une equipe dans la base de donnees
	 */
	@Test
	public void testInsert() {
		Ecurie t = new Ecurie("test2","Professionnelle");
		try {
			t.insert("pwd");
		} catch (ErreurBD e) {
			fail("Erreur put : "+e.getMessage());
		}
		
		Ecurie t2 = new Ecurie("test2");
		try {
			assertEquals("Professionnelle",t2.getType());
		} catch (ErreurBD e) {
			fail("Erreur get : "+e.getMessage());
		}
	}

}
