package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.ConnexionBD;
import code.Equipe;
import code.ErreurBD;
import code.Joueur;

public class testJoueur {
	private Joueur j;
	private Joueur j2;
	
	@Before
	public void setUp() throws Exception {
		this.j = new Joueur("Dylan","Bob");
		this.j2 = new Joueur("Muru","Jean-Batiste",new Date(1075503600000L), 'M', "0685588528", "jb.muru@gmail.com");
	}

	@After
	public void tearDown() throws Exception {
		this.j = null;
		this.j2 = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			st.executeUpdate("delete Joueur where nom = 'test'and prenom = 'test'");
			st.executeUpdate("delete equipe where nom = 'test'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJoueurStringString() {
		Joueur t = new Joueur("Dylan","Bob");
		assertEquals(this.j,t);
	}

	@Test
	public void testJoueurStringStringDateCharIntString() {
		Joueur t = new Joueur("Muru","Jean-Batiste",new Date(1075503600000L), 'M', "0685588528", "jb.muru@gmail.com");
		assertEquals(this.j2,t);
	}

	@Test
	public void testGetNom() {
		assertEquals("Dylan",this.j.getNom());
		assertEquals("Muru",this.j2.getNom());
	}

	@Test
	public void testGetPrenom() {
		assertEquals("Bob",this.j.getPrenom());
		assertEquals("Jean-Batiste",this.j2.getPrenom());
	}

	@Test
	public void testGetDateNaissance() throws ErreurBD {
		assertEquals(new Date(1004310000000L),this.j.getDateNaissance());
		assertEquals(new Date(1075503600000L),this.j2.getDateNaissance());
	}

	@Test
	public void testGetSexe() throws ErreurBD {
		assertEquals('M',this.j.getSexe());
		assertEquals('M',this.j2.getSexe());
	}

	@Test
	public void testGetTel() throws ErreurBD {
		assertEquals("0756349812",this.j.getTel());
		assertEquals("0685588528",this.j2.getTel());
	}

	@Test
	public void testGetEmail() throws ErreurBD {
		assertEquals("bobdylan@gmail.com",this.j.getEmail());
		assertEquals("jb.muru@gmail.com",this.j2.getEmail());
	}

	@Test
	public void testInsert() {
		Equipe test = new Equipe("test",0,1);
		Joueur t = new Joueur("test","test",new Date(1075503600000L), 'M', "0000000000", "test");
		try {
			test.insert(1);
			t.insert(test.getID());
		} catch (ErreurBD e) {
			fail("erreur bd put : "+e.getMessage());
		}
		
		Joueur t2 = new Joueur("test","test");
		try {
			assertEquals(t2.getTel(),"0000000000");
		} catch (ErreurBD e) {
			fail("erreur bd get");
		}
		
	}

}
