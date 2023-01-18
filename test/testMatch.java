package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Equipe;
import code.Match;

public class testMatch {
	private Match m;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.m = new Match(new Equipe("test1"),new Equipe("test2"),new Timestamp(2383797600000L),new Timestamp(2383800000000L));
	}

	@After
	public void tearDown() throws Exception {
		this.m = null;
	}

	@Test
	public void testMatch() {
		Match test = new Match(new Equipe("test1"),new Equipe("test2"),new Timestamp(2383797600000L),new Timestamp(2383800000000L));
		assertEquals(test, this.m);
	}

	@Test
	public void testGetHDebut() {
		assertEquals(new Timestamp(2383797600000L), this.m.getHDebut());
	}

	@Test
	public void testGetHFin() {
		assertEquals(new Timestamp(2383800000000L), this.m.getHFin());
	}

	@Test
	public void testGetEquipe1() {
		assertEquals(new Equipe("test1"),this.m.getEquipe1());
	}

	@Test
	public void testGetEquipe2() {
		assertEquals(new Equipe("test2"),this.m.getEquipe2());
	}

	@Test
	public void testSetWiner() {
		fail("Not yet implemented");
	}
}
