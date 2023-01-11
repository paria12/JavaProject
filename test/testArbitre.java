package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Arbitre;
import code.ErreurBD;

public class testArbitre {
	private Arbitre t;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		t = new Arbitre("test");
	}

	@After
	public void tearDown() throws Exception {
		t = null;
	}

	@Test
	public void testArbitre() {
		Arbitre a = new Arbitre("test");
		assertEquals(t,a);
	}

	@Test
	public void testGetMatch() {
		Arbitre a = new Arbitre("chien");
		try {
			assertEquals(a.getMatch()[0].getEquipe1().getNom(),"Best OF The Best");
		} catch (ErreurBD e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAll() {
		try {
			assertEquals(Arbitre.getAll()[0],"arbre");
		} catch (ErreurBD e) {
			fail(e.getMessage());
		}
	}

}
