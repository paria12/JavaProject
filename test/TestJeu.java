package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Test;

import modele.ErreurBD;
import modele.Jeu;

public class TestJeu {

	/**
	 * Suppression des elements ajoutes a la base de donnees par les tests
	 * @throws Exception
	 */
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

			st.executeQuery("delete FROM Jeu where nom like 'test%'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verification bonne recuperation nom du jeu
	 */
	@Test
	public void testGetNom() {
		Jeu t = new Jeu("test");
		assertEquals("test",t.getNom());
	}
	
	/**
	 * Verification bonne recuperation de la duree d'un jeu
	 * @throws ErreurBD
	 */
	@Test
	public void testGetDuree() throws ErreurBD {
		Jeu t = new Jeu("Overwatch",25);
		assertEquals(25,t.getDuree());
	}

	/**
	 * Verification bonne recuperation de la duree d'un jeu deja inserer dans la base de donnees
	 * @throws ErreurBD
	 */
	@Test
	public void testGetDureeFromBD() throws ErreurBD {
		Jeu t = new Jeu("Overwatch");
		assertEquals(25,t.getDuree());
	}

	/**
	 * Verification de la bonne insertion d'un jeu dans la base de donnees
	 * @throws ErreurBD
	 */
	@Test
	public void testInsert() throws ErreurBD {
		Jeu t = new Jeu("test",10000);
		try {
			t.insert();
		} catch (ErreurBD e) {
			fail("erreur bd");
		}
		Jeu t2 = new Jeu("test");
		assertEquals(10000,t2.getDuree());
	}

	/**
	 * Verification leve d'exception lorsque l'argument temps n'est pas renseigne
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionArgInsert() throws IllegalArgumentException {
		Jeu t = new Jeu("test");
		try {
			t.insert();
		} catch (ErreurBD e) {
			fail("erreur bd");  
		}
	}
	
	
	/**
	 * Verification de la recuperation de tous les jeux de la base de donnees
	 */
	@Test
	public void testGetAll() {
		try {
			assertEquals("Call of Duty",Jeu.getAll()[0]);
		} catch (ErreurBD e) {
			fail("erreur bd");
		}
	}
	
	/**
	 * Verification de la recuperation du bon id du jeu
	 */
	@Test
    public void testGetId() {
        Jeu t = new Jeu("Overwatch");
        try {
            assertEquals(1,Jeu.getID(t));
        }catch(ErreurBD e) {
            fail("erreur BD");
        }
    }
    
	/**
	 * Verification de la recuperation du nom du jeu a partir de l'id du jeu
	 */
    @Test
    public void testGetNomFromId() {
        Jeu t = new Jeu("test1",2000);
        try {
            t.insert();
            assertEquals("test1",Jeu.getNomFromID(Jeu.getID(t)));
        }catch (ErreurBD e){
            fail("erreurBD"+e);
        }
    }
    
    /**
     * Verification de la recuperation du temps du jeu a partir de son id
     */
    @Test
    public void testGetTimeFromId() {
        Jeu t = new Jeu("Overwatch");
        try {
            assertEquals(25,Jeu.getTimeFromID(Jeu.getID(t)));
        }catch(ErreurBD e) {
            
        }
    }
}
