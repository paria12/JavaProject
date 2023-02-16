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

	@Before
	public void setUp() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.DAY_OF_MONTH, 15);
		cal.set( Calendar.MONTH, 0);
		cal.set( Calendar.YEAR, 2023);
		this.t1 = new Tournoi("Esport world convention",new Date(cal.getTimeInMillis()),1);
		this.t2 = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"INTERNATIONAL",3);
	}

	@After
	public void tearDown() throws Exception {
		this.t1 = null;
		this.t2 = null;

	}

	@Test
	public void testTournoiStringDateInt() {
		Tournoi test = new Tournoi("Esport world convention",new Date(1673737200000L),1);
		assertEquals(t1,test);
	}

	@Test
	public void testTournoiStringStringStringStringStringDateStringInt() {
		Tournoi test = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"INTERNATIONAL",3);
		assertEquals(t2,test);
	}

	@Test
	public void testGetNom() {
		assertEquals("Esport world convention",t1.getNom());
		assertEquals("test",t2.getNom());
	}

	@Test
	public void testGetAdresse() {
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

	@Test
	public void testGetVille() {
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

	@Test
	public void testGetPays() {
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

	@Test
	public void testGetCodePostal() {
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

	@Test
	public void testGetDate() {
		assertEquals(new Date(1673737200000L),t1.getDate());
		assertEquals(new Date(1673775400000L),t2.getDate());
	}

	@Test
	public void testGetNotoriete() {
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

	@Test
	public void testGetJeu() {
		assertEquals(1,t1.getJeu());
		assertEquals(3,t2.getJeu());
	}

	@Test
	public void testInsert() {
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

	@Test
	public void testGetAll() {
		try {
			assertEquals("Esport world convention",Tournoi.getAll()[0].getNom());
		} catch (ErreurBD e) {
			fail("Ereur : "+e.getMessage());
		}
	}

	@Test
	public void testGetAvailableEquipe() {
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