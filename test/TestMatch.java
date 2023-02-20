package test;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.Equipe;
import code.Match;

public class TestMatch {
	private Match m;

	/**
	 * Creation d'un match au debut des tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.m = new Match(new Equipe("test1"),new Equipe("test2"),new Timestamp(2383797600000L),new Timestamp(2383800000000L));
	}

	/**
	 * m devient null a la fin des tests
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.m = null;
	}

	/**
	 * Test de la bonne construction d'un match
	 */
	@Test
	public void testConstructeurMatch() {
		Match test = new Match(new Equipe("test1"),new Equipe("test2"),new Timestamp(2383797600000L),new Timestamp(2383800000000L));
		assertEquals(this.m, test);
	}

	/**
	 * Test de la bonne recuperation de l'heure de debut d'un match
	 */
	@Test
	public void testGetHDebut() {
		assertEquals(new Timestamp(2383797600000L), this.m.getHDebut());
	}

	/**
	 * Test de la bonne recuperation de l'heure de debut d'un match
	 */
	@Test
	public void testGetHFin() {
		assertEquals(new Timestamp(2383800000000L), this.m.getHFin());
	}

	/**
	 * Test de la bonne recuperation de la premiere equipe d'un match
	 */
	@Test
	public void testGetEquipe1() {
		assertEquals(new Equipe("test1"),this.m.getEquipe1());
	}

	/**
	 * Test de la bonne recuperation de la seconde equipe d'un match
	 */
	@Test
	public void testGetEquipe2() {
		assertEquals(new Equipe("test2"),this.m.getEquipe2());
	}

}
