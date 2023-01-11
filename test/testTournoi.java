package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Tournoi;

public class testTournoi {
	private Tournoi t1;
	private Tournoi t2;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.t1 = new Tournoi("Esport world convention",new Date(1673737200000L),1);
		this.t2 = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"test",3);
	}

	@After
	public void tearDown() throws Exception {
		this.t1 = null;
		this.t2 = null;
		
	}

	@Test
	public void testTournoiStringDateInt() {
		Tournoi test = new Tournoi("Esport world convention",new Date(1673737200000L),1);
		assertEquals(test,t1);
	}

	@Test
	public void testTournoiStringStringStringStringStringDateStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdresse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVille() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPays() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCodePostal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNotoriete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJeu() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEquipe() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenererPoule() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenererPouleFinal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenererScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelect() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllFromJeu() {
		fail("Not yet implemented");
	}

}
