package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import code.Equipe;
import code.ErreurBD;
import code.Joueur;
import code.Poule;

public class testPoule {

	private Poule p;
	 
	@Before
	public void setUp() throws Exception {
		this.p = new Poule();
	}

	@After
	public void tearDown() throws Exception {
		this.p = null;
	}
	
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

			
			st.executeQuery("delete Joueur where nom = 'O'");
			st.executeQuery("delete Joueur where nom = 'P'");
			st.executeQuery("delete Joueur where nom = 'Y'");
			st.executeQuery("delete Joueur where nom = 'A'");
			st.executeQuery("delete Joueur where nom = 'G'");
			st.executeQuery("delete Joueur where nom = 'Q'");
			st.executeQuery("delete Joueur where nom = 'I'");
			st.executeQuery("delete Joueur where nom = 'V'");
			st.executeQuery("delete Joueur where nom = 'N'");
			st.executeQuery("delete Joueur where nom = 'J'");
			st.executeQuery("delete Joueur where nom = 'S'");
			st.executeQuery("delete Joueur where nom = 'B'");
			st.executeQuery("delete Participer where id_poule = 54");
			st.executeQuery("delete Poule where id_tournoi = 1");
			st.executeQuery("delete Equipe where nom = 'Moi'");
			st.executeQuery("delete Equipe where nom = 'M'");
			st.executeQuery("delete Equipe where nom = 'N'");
			st.executeQuery("delete Equipe where nom = 'P'");
			st.executeQuery("delete Matchs where id_poule = 54");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsererEquipe() {
		Equipe e = new Equipe("Test", 10,1);
		this.p.insérerEquipe(e);
		assertEquals(e,this.p.getEquipe()[0]);
	}

	@Test
	public void testGetEquipe() {
		Equipe e = new Equipe("Test", 10,1);
		Equipe e2 = new Equipe("Test2",10,1);
		this.p.insérerEquipe(e);
		this.p.insérerEquipe(e2); 
		assertEquals(e,this.p.getEquipe()[0]);
		assertEquals(e2,this.p.getEquipe()[1]);
	}
	
	@Test
	public void testInsert() throws ErreurBD {
		Poule p1 = new Poule();
		Equipe e1 = new Equipe("Moi",60,1);
		Equipe e2 = new Equipe("M", 40,1);
		Equipe e3 = new Equipe("N", 20,1);
		Equipe e4 = new Equipe("P",10,1);
		p1.insérerEquipe(e1);
		p1.insérerEquipe(e2);
		p1.insérerEquipe(e3);
		p1.insérerEquipe(e4);
		Joueur je1 = new Joueur("O","T",new Date(1075503600000L),'M',"0797435176","ot@gmail.com");
		Joueur je2 = new Joueur("P","I",new Date(1095503600000L),'M',"0787435976","pi@gmail.com");
		Joueur je3 = new Joueur("Y","A",new Date(1075503700000L),'M',"0647435176","ya@gmail.com");
		Joueur je4 = new Joueur("A","Z",new Date(1075503600000L),'M',"0797135176","az@gmail.com");
		Joueur je11 = new Joueur("G","X",new Date(1075503600000L),'M',"0697435176","gx@gmail.com");
		Joueur je12 = new Joueur("Q","X",new Date(1075503600000L),'M',"0797035176","qx@gmail.com");
		Joueur je13 = new Joueur("I","H",new Date(1075503600000L),'M',"0737435176","ih@gmail.com");
		Joueur je14 = new Joueur("V","D",new Date(1075503600000L),'M',"0797435176","vd@gmail.com");
		Joueur je21 = new Joueur("N","O",new Date(1075503600000L),'M',"0797485176","no@gmail.com");
		Joueur je22 = new Joueur("A","P",new Date(1075503600000L),'M',"0697435179","ap@gmail.com");
		Joueur je23 = new Joueur("P","T",new Date(1075503600000L),'M',"0797495176","pt@gmail.com");
		Joueur je24 = new Joueur("J","X",new Date(1075503600000L),'M',"0797035186","jx@gmail.com");
		Joueur je31 = new Joueur("S","W",new Date(1075503600000L),'M',"0727435196","sw@gmail.com");
		Joueur je32 = new Joueur("B","T",new Date(1075503600000L),'M',"0697495376","bt@gmail.com");
		Joueur je33 = new Joueur("N","A",new Date(1075503600000L),'M',"0607430176","na@gmail.com");
		Joueur je34 = new Joueur("I","D",new Date(1075503600000L),'M',"0697935177","id@gmail.com");
		try {
			e1.insert(1);
			e2.insert(1);
			e3.insert(1);
			e4.insert(1);
		} catch (ErreurBD e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		try {
			je1.insert(e1.getID());
			je2.insert(e1.getID());
			je3.insert(e1.getID());
			je4.insert(e1.getID());
			je11.insert(e2.getID());
			je12.insert(e2.getID());
			je13.insert(e2.getID());
			je14.insert(e2.getID());
			je21.insert(e3.getID());
			je22.insert(e3.getID());
			je23.insert(e3.getID());
			je24.insert(e3.getID());
			je31.insert(e4.getID());
			je32.insert(e4.getID());
			je33.insert(e4.getID());
			je34.insert(e4.getID());
		} catch (ErreurBD e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		p1.GenererMatch(Timestamp.valueOf("2023-01-22 10:00:00"));
		try {
			p1.insert(1);
		} catch(ErreurBD e) {
			fail("Erreur put"+e);

		}
	}
	
	@Test
	public void testGenererMatch() throws ErreurBD {
		Equipe e = new Equipe("Toto",10,1);
		Equipe e1 = new Equipe("M",11,1);
		Equipe e2 = new Equipe("N",50,1);
		Equipe e3 = new Equipe("G", 8,1);
		this.p.insérerEquipe(e);
		this.p.insérerEquipe(e1);
		this.p.insérerEquipe(e2);
		this.p.insérerEquipe(e3);
		this.p.GenererMatch(Timestamp.valueOf("2023-01-22 10:00:00"));
		assertEquals(e2,this.p.getMatch()[0].getEquipe1());
		assertEquals(e3,this.p.getMatch()[0].getEquipe2());
	}
}