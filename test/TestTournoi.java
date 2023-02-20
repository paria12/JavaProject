package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.ConnexionBD;
import code.Equipe;
import code.ErreurBD;
import code.Tournoi;

public class TestTournoi {
	private Tournoi t1;
	private Tournoi t2;

	/**
	 * Suppression des donnees inserer dans la base de donnees par les tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeUpdate("delete equipe where nom like 'test%'");
			st.executeUpdate("delete tournoi where nomtournoi like 'test%'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creation de deux tournois au debut du test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.DAY_OF_MONTH, 15);
		cal.set( Calendar.MONTH, 0);
		cal.set( Calendar.YEAR, 2023);
		this.t1 = new Tournoi("Esport world convention",new Date(cal.getTimeInMillis()),1);
		this.t2 = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"INTERNATIONAL",3);
	}

	/**
	 * t1 et t2 sont remis a null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.t1 = null;
		this.t2 = null;

	}

	/**
	 * Test de la bonne creation d'un tournoi a partir de son nom, sa date et l'id du jeu associe au tournoi
	 */
	@Test
	public void testTournoiNomDateIdJeu() {
		Tournoi test = new Tournoi("Esport world convention",new Date(1673737200000L),1);
		assertEquals(t1,test);
	}

	/**
	 * Test de la bonne creation d'un tournoi a partir de son nom, son adresse, sa ville, son pays, son code
	 * postal, sa date, sa notoriete et l'id du jeu auquel elle est associe
	 */
	@Test
	public void testTournoiNomAdresseVillePaysCodePostalDateNotorieteIdJeu() {
		Tournoi test = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"INTERNATIONAL",3);
		assertEquals(t2,test);
	}

	/**
	 * Test de la bonne recuperation du nom d'un tournoi
	 */
	@Test
	public void testGetNom() {
		assertEquals("Esport world convention",t1.getNom());
		assertEquals("test",t2.getNom());
	}

	/**
	 * Test dans la bonne recuperation de l'adresse d'un tournoi
	 * @throws ErreurBD
	 */
	@Test
	public void testGetAdresse() throws ErreurBD{
		try {
			assertEquals("123 rue du chemin",t1.getAdresse());
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals("test",t2.getAdresse());
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation de la ville d'un tournoi
	 * @throws ErreurBD
	 */
	@Test
	public void testGetVille() throws ErreurBD{
		try {
			assertEquals("Paris",t1.getVille());
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals("test",t2.getVille());
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation du pays d'un tournoi
	 * @throws ErreurBD
	 */
	@Test
	public void testGetPays() throws ErreurBD{
		try {
			assertEquals("France",t1.getPays());
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals("test",t2.getPays());
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation du code postal d'un tournoi
	 * @throws ErreurBD
	 */
	@Test
	public void testGetCodePostal() throws ErreurBD{
		try {
			assertEquals("75000",t1.getCodePostal());
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals("test",t2.getCodePostal());
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation de la date d'un tournoi
	 */
	@Test
	public void testGetDate() {
		assertEquals(new Date(1673737200000L),t1.getDate());
		assertEquals(new Date(1673775400000L),t2.getDate());
	}

	/**
	 * Test de la recuperation de la bonne notoriete d'un tournoi
	 * @throws ErreurBD
	 */
	@Test
	public void testGetNotoriete() throws ErreurBD{
		try {
			assertEquals("INTERNATIONAL",t1.getNotoriete());
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals("INTERNATIONAL",t2.getNotoriete());
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation de l'id du jeu d'un tournoi
	 */
	@Test
	public void testGetJeu() {
		assertEquals(1,t1.getJeu());
		assertEquals(3,t2.getJeu());
	}

	/**
	 * Test de la bonne insertion d'un tournoi dans la base de donnees
	 * @throws ErreurBD
	 */
	@Test
	public void testInsert() throws ErreurBD {
		try {
			t2.insert(1);
		} catch (IllegalArgumentException e) {
			fail("Ereur Arg : "+e.getMessage());
		} catch (ErreurBD e) {
			fail("Ereur BD : "+e.getMessage());
		}
		Tournoi verif = new Tournoi("test",new Date(1673775400000L),3);
		try {
			assertEquals("test",verif.getAdresse());
		} catch (ErreurBD e) {
			fail("Ereur BD : "+e.getMessage());
		}
	}

	/**
	 * Test de la bonne recuperation de tous les tournois de la base de donnees
	 * @throws ErreurBD
	 */
	@Test
	public void testGetAll() throws ErreurBD{
		try {
			assertEquals("Esport world convention",Tournoi.getAll()[0].getNom());
		} catch (ErreurBD e) {
			fail("Ereur : "+e.getMessage());
		}
	}

	/**
	 * Test de la recuperation des equipes pas encore inscrite a un tournoi mais qui ont la possibilite de le
	 * faire
	 * @throws ErreurBD
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetAvailableEquipe() throws ErreurBD,IllegalArgumentException{
		Tournoi test = new Tournoi("test2","test","test","test","test",new Date(1673775400000L),"INTERNATIONAL",4);
		Equipe te = new Equipe("test",0,4);
		try {
			te.insert(1);
			test.insert(1);
		} catch (IllegalArgumentException e) {
			fail("Ereur Arg setup : "+e.getMessage());
		} catch (ErreurBD e) {
			fail("Ereur BD setup : "+e.getMessage());
		}
		
		try {
			assertEquals(test,Tournoi.getAvailableEquipe(te)[0]);
		} catch (ErreurBD e) {
			fail("Ereur BD : "+e.getMessage());
		}
	}
}