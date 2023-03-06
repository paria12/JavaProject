package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import modele.ConnexionBD;
import modele.Equipe;
import modele.ErreurBD;
import modele.Joueur;

public class TestJoueur {
	private Joueur j;
	private Joueur j2;
	
	/**
	 * Construction de deux joueurs au début du test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.j = new Joueur("Dylan","Bob");
		this.j2 = new Joueur("Muru","Jean-Batiste",new Date(1075503600000L), 'M', "0685588528", "jb.muru@gmail.com");
	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.j = null;
		this.j2 = null;
	}

	/**
	 * Suppression de toutes les donnees inseree dans la base de donnees par les tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeUpdate("delete Joueur where nom = 'test' and prenom = 'test'");
			st.executeUpdate("delete equipe where nom = 'test'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test constructeur d'un joueur a partir de son nom et prenom
	 */
	@Test
	public void testConstructionJoueurNomPrenom() {
		Joueur t = new Joueur("Dylan","Bob");
		assertEquals(t,this.j);
	}

	/**
	 * Test constructeur d'un joueur a partir de son nom, prenom, date de naissance, son sexe, numero de
	 * telephone et son email
	 */
	@Test
	public void testConstructeurJoueurNomPrenomDateNaissanceSexeNumTelEmail() {
		Joueur t = new Joueur("Muru","Jean-Batiste",new Date(1075503600000L), 'M', "0685588528", "jb.muru@gmail.com");
		assertEquals(t,this.j2);
	}

	/**
	 * Test de la bonne recuperation du nom du joueur
	 */
	@Test
	public void testGetNom() {
		assertEquals("Dylan",this.j.getNom());
		assertEquals("Muru",this.j2.getNom());
	}

	/**
	 * Test de la bonne recuperation du prenom du joueur
	 */
	@Test
	public void testGetPrenom() {
		assertEquals("Bob",this.j.getPrenom());
		assertEquals("Jean-Batiste",this.j2.getPrenom());
	}

	/**
	 * Test de la bonne recuperation de la date de naissance
	 * @throws ErreurBD
	 */
	@Test
	public void testGetDateNaissance() throws ErreurBD {
		assertEquals(new Date(1004310000000L),this.j.getDateNaissance());
		assertEquals(new Date(1075503600000L),this.j2.getDateNaissance());
	}

	/**
	 * Test de la bonne recuperation du sexe du joueur
	 * @throws ErreurBD
	 */
	@Test
	public void testGetSexe() throws ErreurBD {
		assertEquals('M',this.j.getSexe());
		assertEquals('M',this.j2.getSexe());
	}

	/**
	 * Test de la bonne recuperation du numero de telephone du joueur
	 * @throws ErreurBD
	 */
	@Test
	public void testGetTel() throws ErreurBD {
		assertEquals("0756349812",this.j.getTel());
		assertEquals("0685588528",this.j2.getTel());
	}

	/**
	 * Test de la bonne recuperation de l'email du joueur
	 * @throws ErreurBD
	 */
	@Test
	public void testGetEmail() throws ErreurBD {
		assertEquals("bobdylan@gmail.com",this.j.getEmail());
		assertEquals("jb.muru@gmail.com",this.j2.getEmail());
	}

	/**
	 * Test de la bonne insertion d'un joueur dans la base de donnees
	 * @throws ErreurBD
	 */
	@Test
	public void testInsert() throws ErreurBD{
		Equipe test = new Equipe("test",0,1);
		Joueur t = new Joueur("test","test",new Date(1075503600000L), 'M', "0000000000", "test");
		try {
			test.insert(1);
			t.insert(test.getID());
		} catch (ErreurBD e) {
			fail("erreur bd put : "+e.getMessage());
		}
		
		Joueur t2 = new Joueur("test","test");
		try {
			assertEquals("0000000000",t2.getTel());
		} catch (ErreurBD e) {
			fail("erreur bd get");
		}
		
	}

}
