package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Equipe;
import code.ErreurBD;
import code.Tournoi;

public class testTournoi {
	private Tournoi t1;
	private Tournoi t2;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.DAY_OF_MONTH, 15);
		cal.set( Calendar.MONTH, 0);
		cal.set( Calendar.YEAR, 2023);
		this.t1 = new Tournoi("Esport world convention",new Date(cal.getTimeInMillis()),1);
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
		Tournoi test = new Tournoi("test","test","test","test","test",new Date(1673775400000L),"test",3);
		assertEquals(test,t2);
	}

	@Test
	public void testGetNom() {
		assertEquals(t1.getNom(),"Esport world convention");
		assertEquals(t2.getNom(),"test");
	}

	@Test
	public void testGetAdresse() {
		try {
			assertEquals(t1.getAdresse(),"123 rue du chemin");
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals(t2.getAdresse(),"test");
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	@Test
	public void testGetVille() {
		try {
			assertEquals(t1.getVille(),"Paris");
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals(t2.getVille(),"test");
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	@Test
	public void testGetPays() {
		try {
			assertEquals(t1.getPays(),"France");
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals(t2.getPays(),"test");
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	@Test
	public void testGetCodePostal() {
		try {
			assertEquals(t1.getCodePostal(),"75000");
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals(t2.getCodePostal(),"test");
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	@Test
	public void testGetDate() {
		assertEquals(t1.getDate(),new Date(1673737200000L));
		assertEquals(t2.getDate(),new Date(1673775400000L));
	}

	@Test
	public void testGetNotoriete() {
		try {
			assertEquals(t1.getNotoriete(),"INTERNATIONAL");
		} catch (ErreurBD e) {
			fail("Ereur t1 : "+e.getMessage());
		}
		try {
			assertEquals(t2.getNotoriete(),"test");
		} catch (ErreurBD e) {
			fail("Ereur t2 : "+e.getMessage());
		}
	}

	@Test
	public void testGetJeu() {
		assertEquals(t1.getJeu(),1);
		assertEquals(t2.getJeu(),3);
	}

	@Test
	public void testAddEquipe() {
		t1.addEquipe(new Equipe("test"));
		t2.addEquipe(new Equipe("test"));
	}

	@Test
	public void testRemoveEquipe() {
		t1.addEquipe(new Equipe("test"));
		t2.addEquipe(new Equipe("test"));
		t1.removeEquipe(new Equipe("test"));
		t2.removeEquipe(new Equipe("test"));
	}

	@Test
	public void testGenererPoule() {
		for(int i = 0; i<16;i++) {
			Equipe e = new Equipe("t_e"+i,i,3);
			t2.addEquipe(e);
		}
		t2.GenererPoule();
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
			assertEquals(verif.getAdresse(),"test");
		} catch (ErreurBD e) {
			fail("Ereur BD : "+e.getMessage());
		}
	}

	@Test
	public void testGetAll() {
		try {
			assertEquals(Tournoi.getAll()[0].getNom(),"Esport world convention");
		} catch (ErreurBD e) {
			fail("Ereur : "+e.getMessage());
		}
	}

	@Test
	public void testGetAllFromJeu() {
		fail("unimplemented");
	}

}
