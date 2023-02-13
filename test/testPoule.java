package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public class TestPoule {

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
	public  static void tearDownAfterClass() throws Exception {
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
			
			ResultSet rs = st.executeQuery("SELECT id_poule from Poule order by 1 desc");
			rs.next();
			int id = rs.getInt(1);
			st.executeQuery("delete Joueur where nom like 'Test%'");
			st.executeQuery("delete Participer where id_poule = "+id);
			st.executeQuery("delete Matchs where id_poule ="+id);
			st.executeQuery("delete Poule where id_poule = "+id);
			st.executeQuery("delete Equipe where nom like 'Test%'");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testInsererEquipe() {
		Equipe e = new Equipe("Test", 10,1);
		this.p.insererEquipe(e);
		assertEquals(e,this.p.getEquipe()[0]);
	}

	@Test
	public void testGetEquipe() {
		Equipe e = new Equipe("Test", 10,1);
		Equipe e2 = new Equipe("Test2",10,1);
		this.p.insererEquipe(e);
		this.p.insererEquipe(e2); 
		assertEquals(e,this.p.getEquipe()[0]);
		assertEquals(e2,this.p.getEquipe()[1]);
	}
	
	@Test
	public void testInsert() throws ErreurBD {
		Poule p1 = new Poule();
		Equipe e1 = new Equipe("Test1",60,1);
		Equipe e2 = new Equipe("Test2", 40,1);
		Equipe e3 = new Equipe("Test3", 20,1);
		Equipe e4 = new Equipe("Test4",10,1);
		p1.insererEquipe(e1);
		p1.insererEquipe(e2);
		p1.insererEquipe(e3);
		p1.insererEquipe(e4);
		Joueur je1 = new Joueur("Test1","T",new Date(1075503600000L),'M',"0797435176","ot@gmail.com");
		Joueur je2 = new Joueur("Test2","I",new Date(1095503600000L),'M',"0787435976","pi@gmail.com");
		Joueur je3 = new Joueur("Test3","A",new Date(1075503700000L),'M',"0647435176","ya@gmail.com");
		Joueur je4 = new Joueur("Test4","Z",new Date(1075503600000L),'M',"0797135176","az@gmail.com");
		Joueur je11 = new Joueur("Test5","X",new Date(1075503600000L),'M',"0697435176","gx@gmail.com");
		Joueur je12 = new Joueur("Test6","X",new Date(1075503600000L),'M',"0797035176","qx@gmail.com");
		Joueur je13 = new Joueur("Test7","H",new Date(1075503600000L),'M',"0737435176","ih@gmail.com");
		Joueur je14 = new Joueur("Test8","D",new Date(1075503600000L),'M',"0797435176","vd@gmail.com");
		Joueur je21 = new Joueur("Test9","O",new Date(1075503600000L),'M',"0797485176","no@gmail.com");
		Joueur je22 = new Joueur("Test10","P",new Date(1075503600000L),'M',"0697435179","ap@gmail.com");
		Joueur je23 = new Joueur("Test11","T",new Date(1075503600000L),'M',"0797495176","pt@gmail.com");
		Joueur je24 = new Joueur("Test12","X",new Date(1075503600000L),'M',"0797035186","jx@gmail.com");
		Joueur je31 = new Joueur("Test13","W",new Date(1075503600000L),'M',"0727435196","sw@gmail.com");
		Joueur je32 = new Joueur("Test14","T",new Date(1075503600000L),'M',"0697495376","bt@gmail.com");
		Joueur je33 = new Joueur("Test15","A",new Date(1075503600000L),'M',"0607430176","na@gmail.com");
		Joueur je34 = new Joueur("Test16","D",new Date(1075503600000L),'M',"0697935177","id@gmail.com");
		try {
			e1.insert(1);
			e2.insert(1);
			e3.insert(1);
			e4.insert(1);
		} catch (ErreurBD e5) {
			// TODO Auto-generated catch block
			fail(e5.getMessage());
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
			fail(e5.getMessage());
		}
		p1.GenererMatch(Timestamp.valueOf("2023-12-22 10:00:00"));
		try {
			p1.insert(1);
		} catch(ErreurBD e) {
			fail("Erreur put"+e);

		}
	}
	
	@Test
	public void testGenererMatch()  {
		Equipe e = new Equipe("Toto",10,1);
		Equipe e1 = new Equipe("M",11,1);
		Equipe e2 = new Equipe("N",50,1);
		Equipe e3 = new Equipe("G", 8,1);
		this.p.insererEquipe(e);
		this.p.insererEquipe(e1);
		this.p.insererEquipe(e2);
		this.p.insererEquipe(e3);
		try {
			this.p.GenererMatch(Timestamp.valueOf("2023-01-22 10:00:00"));
		} catch (ErreurBD e4) {
			// TODO Auto-generated catch block
			fail(e4.getMessage());
		}
		assertEquals(e2,this.p.getMatch()[0].getEquipe1());
		assertEquals(e3,this.p.getMatch()[0].getEquipe2());
	}
}