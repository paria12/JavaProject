package code;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class Jeu {

	private String nom;
	private int duree;

	/** Constructeur d'un jeu a partir de son nom
	 * @param nom
	 */
	public Jeu(String nom) {
		this.nom = nom;
		this.duree = -1;
	}

	/** Constructueur d'un jeu a partir de son nom et sa duree
	 * @param nom
	 * @param duree
	 */
	public Jeu(String nom, int duree) {
		this.nom = nom;
		this.duree = duree;
	}

	/** Renvoie le nom du jeu
	 * @return string : nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** retourne la duree de la partie d'un jeu
	 * @return int : duree
	 * @throws ErreurBD lorsque une erreur lie a la base de donne est leve
	 */
	public int getDuree() throws ErreurBD {
		if (this.duree>0) {
			return this.duree;
		} else {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select temps_partie from jeu where nom='"+this.nom+"'");

				rese.next();
				this.duree = rese.getInt(1);
				
				connx.close();
			} catch (SQLException e) {
				switch(e.getErrorCode()) {
	            case 1 : 
	                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
	            case 2291:
	                throw new ErreurBD("Il manque la cle etrangere");
	            case 2292:
	                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
	            case 2290:
	                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
	            case 1400:
	                throw new ErreurBD("Une valeur n'a pas ete renseigne");
	            case 1407:
	                throw new ErreurBD("Une valeur n'a pas ete renseigne");
	                
	            }
	            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
	                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
	            }
			}
			return this.duree;
		}
	}

	/** Inserer toutes les informations dans la base de donnees
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 * @throws IllegalArgumentException la duree est inferieure a 0
	 */
	public void insert() throws ErreurBD, IllegalArgumentException{
		if (this.duree < 0) {
			throw new IllegalArgumentException("La duree du jeu ne doit pas etre inférieur à 0 minutes");
		} else {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO jeu values(seq_jeu.nextVal,'"+this.nom+"',"+this.duree+")");

				connx.close();
			} catch (SQLException e) {
				switch(e.getErrorCode()) {
	            case 1 : 
	                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
	            case 2291:
	                throw new ErreurBD("Il manque la cle etrangere");
	            case 2292:
	                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
	            case 2290:
	                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
	            case 1400:
	                throw new ErreurBD("Une valeur n'a pas ete renseigne");
	            case 1407:
	                throw new ErreurBD("Une valeur n'a pas ete renseigne");
	                
	            }
	            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
	                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
	            }
			}
		}
	}
	
	/** Renvoie tous les noms des jeux contenus dans la base de donnees
	 * @return un tableau d'objet : object
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public static Object[] getAll() throws ErreurBD {
		List<String> l = new ArrayList<String>();

		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select nom from jeu order by 1");
			
			while(rs.next()) {
				l.add(rs.getString(1));
			}

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
		return l.toArray();
	}
	
	/** Renvoie l'id d'un jeu en fonction de son nom
	 * @param j
	 * @return int
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public static int getID(Jeu j) throws ErreurBD {
		int retour = 0;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select id_jeu from jeu where nom='"+j.getNom()+"'");
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
		return retour;
	}
	
	/** Recupere le nom d'un jeu a partir du parametre j donne
	 * 
	 * @param j
	 * @return string
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public static String getNomFromID(int j) throws ErreurBD {
		String retour = null;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select nom from jeu where id_jeu="+j);
			
			while(rs.next()) {
				retour=rs.getString(1);
			}

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
		return retour;
	}
	
	/** Renvoie la duree d'un jeu en fonction de son id
	 * @param id
	 * @return int
	 * @throws ErreurBD lorsque une erreur lie a la base donnees est leve
	 */
	public static int getTimeFromID(int id) throws ErreurBD {
		int retour = 0;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select temps_partie from jeu where id_jeu="+id);
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donnees");
            case 2291:
                throw new ErreurBD("Il manque la cle etrangere");
            case 2292:
                throw new ErreurBD("Impossibilite de supprimer car l'enregistrement est present dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas ete renseigne");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgression de l'un des declencheurs de la base de donnees");
            }
		}
		return retour;
	}
}