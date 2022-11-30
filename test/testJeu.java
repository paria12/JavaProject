package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Test;

import code.ErreurBD;
import code.Jeu;

public class testJeu {

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

			st.executeQuery("delete Jeu where nom = 'test'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetNom() {
		Jeu t = new Jeu("test");
		assertEquals(t.getNom(),"test");
	}
	
	@Test
	public void testGetDuree() throws ErreurBD {
		Jeu t = new Jeu("Overwatch",25);
		assertEquals(25,t.getDuree());
	}

	@Test
	public void testGetDureeFromBD() throws ErreurBD {
		Jeu t = new Jeu("Overwatch");
		assertEquals(25,t.getDuree());
	}

	@Test
	public void testInsert() throws ErreurBD {
		Jeu t = new Jeu("test",10000);
		try {
			t.insert();
		} catch (ErreurBD e) {
			fail("erreur bd");
		}
		Jeu t2 = new Jeu("test");
		assertEquals(t2.getDuree(),10000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionArgInsert() {
		Jeu t = new Jeu("test");
		try {
			t.insert();
		} catch (ErreurBD e) {
			fail("erreur bd");  
		}
	}
	
	@Test(expected = ErreurBD.class)
	public void testExceptionBDInsert() throws ErreurBD {
		Jeu t = new Jeu("test2");
		t.getDuree();
	}
	
	@Test
	public void testGetAll() {
		try {
			assertEquals(Jeu.getAll()[0],"Call of Duty");
		} catch (ErreurBD e) {
			fail("erreur bd");
		}
	}
}
