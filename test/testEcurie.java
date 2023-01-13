package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.ConnexionBD;
import code.Ecurie;
import code.Equipe;
import code.ErreurBD;

public class testEcurie {
	private Ecurie e;
	private Ecurie e2;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeUpdate("delete equipe where nom = 'test'");
			st.executeUpdate("delete Ecurie where nom = 'test'");
			st.executeUpdate("delete Ecurie where nom = 'test2'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		this.e = new Ecurie("KCorp");
		this.e2 = new Ecurie("Vitality","Professionnelle");
	}

	@After
	public void tearDown() throws Exception {
		this.e = null;
		this.e2 = null;
	}

	@Test
	public void testEcurieString() {
		Ecurie t = new Ecurie("KCorp");
		assertEquals(t,this.e);
	}

	@Test
	public void testEcurieStringString() {
		Ecurie t = new Ecurie("Vitality","Professionnelle");
		assertEquals(t,this.e2);
	}

	@Test
	public void testAddEquipe() {
		this.e2.addEquipe(new Equipe("Dream Team", 100, 1));
		try {
			assertEquals(1,this.e2.getEquipe().size());
		} catch (ErreurBD e) {
			fail("Erreur BD");
		}
	}

	@Test
	public void testRemoveEquipe() {
		Equipe e = new Equipe("Dream Team", 100, 1);
		this.e2.addEquipe(new Equipe("test", 200, 2));
		this.e2.addEquipe(e);
		this.e2.removeEquipe(e);
		try {
			assertEquals(1,this.e2.getEquipe().size());
		} catch (ErreurBD x) {
			fail("Erreur BD");
		}
	}

	@Test
	public void testGetNom() {
		assertEquals(this.e.getNom(),"KCorp");
		assertEquals(this.e2.getNom(),"Vitality");
	}

	@Test
	public void testGetType() {
		try {
			assertEquals(this.e.getType(),"Professionnelle");
			assertEquals(this.e2.getType(),"Professionnelle");
		} catch (ErreurBD e) {
			fail("ErreurBd");
		}
		
	}

	@Test
	public void testGetEquipe() {
		Ecurie t = new Ecurie("test","Professionnelle");
		Equipe test = new Equipe("test",0,1);
		try {
			t.insert("pwd");
			test.insert(Ecurie.getID(t));
		} catch (ErreurBD e) {
			fail("Erreur set up : "+e.getMessage());
		}
		
		Ecurie t2 = new Ecurie("test");
		try {
			assertEquals(t2.getEquipe().get(0),test);
		} catch (ErreurBD e) {
			fail("Erreur Get : "+e.getMessage());
		}
	}

	@Test
	public void testInsert() {
		Ecurie t = new Ecurie("test2","Professionnelle");
		try {
			t.insert("pwd");
		} catch (ErreurBD e) {
			fail("Erreur put : "+e.getMessage());
		}
		
		Ecurie t2 = new Ecurie("test2");
		try {
			assertEquals(t2.getType(),"Professionnelle");
		} catch (ErreurBD e) {
			fail("Erreur get : "+e.getMessage());
		}
	}

}
