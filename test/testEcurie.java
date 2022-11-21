package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Ecurie;
import code.Equipe;
import code.ErreurBD;

public class testEcurie {
	private Ecurie e;
	private Ecurie e2;

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

			st.executeQuery("delete Ecurie where nom = 'test'");

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
		try {
			assertEquals(this.e.getEquipe().get(0).getNom(),"Best OF The Best");
		} catch (ErreurBD e) {
			fail("ErreurBd");
		}
	}

	@Test
	public void testInsert() {
		Ecurie t = new Ecurie("test","Professionnelle");
		try {
			t.insert("pwd");
		} catch (ErreurBD e) {
			fail("erreur bd put");
		}
		
		Ecurie t2 = new Ecurie("test");
		try {
			assertEquals(t2.getType(),"Professionnelle");
		} catch (ErreurBD e) {
			fail("erreur bd get");
		}
	}

}
