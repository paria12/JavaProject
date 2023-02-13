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

import code.ConnexionBD;
import code.Equipe;
import code.ErreurBD;
import code.Joueur;


public class TestEquipe {
	private Equipe e;
	private Equipe e2;

	@Before
	public void setUp() throws Exception {
		this.e = new Equipe("Dream Team");
		this.e2 = new Equipe("Test",10,1);
	}
	@After
	public void tearDown() throws Exception {
		this.e = null;
		this.e2 = null;
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeQuery("delete Equipe where nom = 'Jeanne'");
			st.executeUpdate("delete Joueur where nom = 'test'and prenom = 'test'");
			st.executeUpdate("delete equipe where nom = 'test'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testEquipeString() {
		Equipe t = new Equipe("Dream Team");
		assertEquals(t,this.e);
	}

	@Test
	public void testEquipeStringIntInt() {
		Equipe t = new Equipe("Test",10,1);
		assertEquals(t,this.e2);
	}

	@Test
	public void testGetNom() {
		assertEquals("Dream Team",this.e.getNom());
		assertEquals("Test",this.e2.getNom());
	}

	@Test
	public void testGetNbPoints() {
		try {
			assertEquals(5,this.e.getNbPoints());
			assertEquals(10,this.e2.getNbPoints());
		} catch (ErreurBD e) {
			fail("Erreur bd : "+e.getMessage());
		}
	}

	@Test
	public void testGetIdJeu() {
		try {
			assertEquals(1,this.e.getIdJeu());
			assertEquals(1,this.e2.getIdJeu());
		} catch (ErreurBD e) {
			fail("Erreur bd : "+e.getMessage());
		}
	}

	@Test
	public void testGetjoueur(){
		Joueur j = new Joueur("test","test");
		this.e2.addJoueur(j);
		try {
			assertEquals(j,this.e2.getJoueur().get(0));
		} catch (ErreurBD e) {
			fail("Erreur local : "+e.getMessage());
		}
		
		Equipe test = new Equipe("test",0,1);
		Joueur t = new Joueur("test","test",new Date(1075503600000L), 'M', "0000000000", "test");
		try {
			test.insert(1);
			t.insert(test.getID());
		} catch (ErreurBD e) {
			fail("erreur set up : "+e.getMessage());
		}
		
		Equipe test2 = new Equipe("test",0,1);
		try {
			assertEquals(t,test2.getJoueur().get(0));
		} catch (ErreurBD e) {
			fail("Erreur distant : "+e.getMessage());
		}
	}

	@Test
	public void testAjoutDePoints() {
		try {
			this.e.ajoutDePoints(2);
			this.e2.ajoutDePoints(4);
			assertEquals(7,this.e.getNbPoints());
			assertEquals(14,this.e2.getNbPoints());
		} catch (ErreurBD e) {
			fail("Erreur bd : "+e.getMessage());
		}
	}
	
	@Test
	public void testAddJoueur() {
		Joueur j = new Joueur("Muru","Jean-Batiste");
		this.e2.addJoueur(j);
		try {
			assertEquals(j,this.e2.getJoueur().get(0));
		} catch (ErreurBD e) {
			fail("Erreur bd : "+e.getMessage());
		}
	}
	
	@Test
	public void testremoveJoueur() {
		Joueur j = new Joueur("Muru","Jean-Batiste");
		Joueur j2 = new Joueur("Leblanc","Hervé");
		this.e2.addJoueur(j);
		this.e2.addJoueur(j2);
		this.e2.removeJoueur(j2);
		try {
			assertEquals(1,this.e2.getJoueur().size());
			assertEquals(j,this.e2.getJoueur().get(0));
		} catch (ErreurBD e) {
			fail("Erreur bd : "+e.getMessage());
		}
	}
	@Test
	public void testInsert() {
		Equipe e3 = new Equipe("Jeanne",50,1);
		try {
			e3.insert(1);
		}
		catch(ErreurBD e){
			fail("Erreur put");
		}

		Equipe e4 = new Equipe("Jeanne");
		try {
			assertEquals(1,e4.getIdJeu());
		}
		catch(ErreurBD e) {
			fail("erreur BD get");
		}
	}



}