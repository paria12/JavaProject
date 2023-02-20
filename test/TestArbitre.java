package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Arbitre;
import code.ErreurBD;

public class TestArbitre {
	private Arbitre t;

	/**
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	/** Au lancement du test creation d'un arbitre t
	 * 
	 * @throws Exception lorsque le login est null
	 */
	@Before
	public void setUp() throws Exception {
		t = new Arbitre("test");
	}

	/** A la fin du test t est null
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		t = null;
	}

	/** Test verifiant la construction correcte d'un arbitre
	 * 
	 */
	@Test
	public void testArbitre() {
		Arbitre a = new Arbitre("test");
		assertEquals(a,t);
	}

	/** Test verifiant la recuperation des matchs apparteneant a l'arbitre
	 * 
	 */
	@Test
	public void testGetMatch() {
		Arbitre a = new Arbitre("enzo");
		try {
			assertEquals("Dream Team",a.getMatch()[0].getEquipe1().getNom());
		} catch (ErreurBD e) {
			fail(e.getMessage());
		}
	}

	/** Test verifiant la bonne recuperation de tous les arbitres par la methode getAll
	 * 
	 */
	@Test
	public void testGetAll() {
		try {
			assertEquals("alice",Arbitre.getAll()[0]);
		} catch (ErreurBD e) {
			fail(e.getMessage());
		}
	}

	/** Test verifiant la bonne recuperation de l'id de l'arbitre
	 * 
	 */
	@Test
    public void testGetId() {
        Arbitre a = new Arbitre("enzo");
        try {
            assertEquals(1,a.getID());
        } catch (ErreurBD e){
            fail(e.getMessage());
        }
    }
}
