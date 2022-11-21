package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Equipe;
import code.ErreurBD;
import code.Joueur;


public class testEquipe {
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
		String loginBD = "ndf4080a";
		String mdpBD = "fatime31";
		String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

			Statement st = connx.createStatement();

			st.executeQuery("delete Equipe where nom = 'Jeanne'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testEquipeString() {
		Equipe t = new Equipe("Dream Team");
		assertEquals(this.e, t);
	}

	@Test
	public void testEquipeStringIntInt() {
		Equipe t = new Equipe("Test",10,1);
		assertEquals(this.e2,t);
	}

	@Test
	public void testGetNom() {
		assertEquals("Dream Team",this.e.getNom());
		assertEquals("Test",this.e2.getNom());
	}

	@Test
	public void testGetNbPoints() throws ErreurBD {
		assertEquals(5,this.e.getNbPoints());
		assertEquals(10,this.e2.getNbPoints());
	}

	@Test
	public void testGetIdJeu() throws ErreurBD{
		assertEquals(2,this.e.getIdJeu());
		assertEquals(1,this.e2.getIdJeu());
	}
	@Test
	public void testGetjoueur()throws ErreurBD{
		Joueur j = new Joueur("test","test");
		this.e2.addJoueur(j);
		assertEquals(this.e.getJoueur().get(1),new Joueur("Muru","Jean-Batiste",new Date(1075503600000L), 'H', "0685588528", "jb.muru@gmail.com"));
		assertEquals(this.e2.getJoueur().get(0),j);
	}

	@Test
	public void testAjoutDePoints() throws ErreurBD {
		this.e.AjoutDePoints(2);
		this.e2.AjoutDePoints(4);
		assertEquals(7,this.e.getNbPoints());
		assertEquals(14,this.e2.getNbPoints());
	}
	@Test
	public void testAddJoueur() throws ErreurBD {
		Joueur j = new Joueur("Muru","Jean-Batiste");
		this.e2.addJoueur(j);
		assertEquals(j,this.e2.getJoueur().get(0));
	}
	@Test
	public void testremoveJoueur() throws ErreurBD{
		Joueur j = new Joueur("Muru","Jean-Batiste");
		Joueur j2 = new Joueur("Leblanc","Hervé");
		this.e2.addJoueur(j);
		this.e2.addJoueur(j2);
		this.e2.removeJoueur(j2);
		assertEquals(1,this.e2.getJoueur().size());
		assertEquals(j,this.e2.getJoueur().get(0));
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
			assertEquals(e4.getIdJeu(),1);
		}
		catch(ErreurBD e) {
			fail("erreur BD get");
		}
	}



}