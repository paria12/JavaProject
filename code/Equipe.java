package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;


public class Equipe {

	private String nom;
	private int nbPoints = -1;
	private List<Joueur> joueur = new ArrayList<Joueur> ();
	private int id_jeu = -1;

	/** Constructeur d'equipe et nom
	 * @param nom
	 * @throws IllegalArgumentException
	 */
	public Equipe(String nom)throws IllegalArgumentException{
		if(nom == null) {
			throw new IllegalArgumentException("le nom ne peut pas etre null");
		}
		this.nom = nom;
	}

	/** Constructeur d'equipe en fonction de son nom, son nombre de points et l'id de son jeu
	 * @param nom
	 * @param nbPoints
	 * @param id_jeu
	 * @throws IllegalArgumentException
	 */
	public Equipe(String nom, int nbPoints, int id_jeu)throws IllegalArgumentException {
		if(nom == null) {
			throw new IllegalArgumentException("nom ne peut pas etre null");
		}
		this.nom =nom;
		this.nbPoints = nbPoints;
		this.id_jeu = id_jeu;
	}

	/** Renvoie le nom de l'equipe
	 * @return string
	 */
	public String getNom() {
		return this.nom;
	}

	/** Renvoie le nombre de points de l'equipe
	 * @return int : nbPoints
	 * @throws ErreurBD 
	 */
	public int getNbPoints() throws ErreurBD {
		if(this.nbPoints < 0) {
			this.select();
		}
		return this.nbPoints;
	}

	/** Renvoie l'id d'un jeu a laquelle une equipe est associee
	 * @return int : id_jeu
	 * @throws ErreurBD 
	 */
	public int getIdJeu() throws ErreurBD {
		if(this.id_jeu < 0) {
			this.select();
		}
		return this.id_jeu;
	}

	/** Renvoie la liste de joueur appartenant a cette equipe
	 * @return une liste de joueur
	 * @throws ErreurBD
	 */
	public List<Joueur> getJoueur() throws ErreurBD {
		if (!this.joueur.isEmpty()) {
			return this.joueur;
		} else {
			return this.selectJoueur();
		}
	}

	/** Ajout de point a la fin de chaque tournoi aux points de l'equipe
	 * @param nouveauPoint
	 * @throws ErreurBD 
	 */
	public void ajoutDePoints(int nouveauPoint) throws ErreurBD{
		this.nbPoints = this.getNbPoints() + nouveauPoint;
	}

	/** Ajoute un joueur a la liste
	 * @param j
	 */
	public void addJoueur(Joueur j) {
		this.joueur.add(j);
	}

	/** Supprimer un joueur de la liste
	 * @param j
	 */
	public void removeJoueur(Joueur j) {
		this.joueur.remove(j);
	}

	/** Renvoie l'id d'une equipe en fonction de son nom 
	 * @return int : ID
	 * @throws ErreurBD
	 */
	public int getID() throws ErreurBD{
		int ID = -1;
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select id_equipe from equipe where nom ='"+this.nom+"'");
			rs.next();
			ID = rs.getInt(1);

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
		return ID;
	}


	/** Insert des joueurs dans une equipe
	 * @param id_equipe
	 * @return List de joueur
	 * @throws ErreurBD
	 * @exception erreur de connexion a la BD
	 */
	public List<Joueur> selectJoueur()throws ErreurBD{
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select j.nom, j.prenom, j.date_naissance, j.sexe, j.numero_de_telephone, j.email from joueur j , equipe e where e.nom ='"+this.nom+"' and e.id_equipe = j.id_equipe");
			while(rs.next()){
				this.joueur.add(new Joueur(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4).charAt(0),rs.getString(5),rs.getString(6)));
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
		return joueur;

	}

	public static String[] getClassement(int jeu) throws ErreurBD {
        String [] classement = null;
        try {
            DataSource bd = new ConnexionBD();
            Connection connx = bd.getConnection();

            Statement st = connx.createStatement();

            ResultSet rs = st.executeQuery("select nom,nb_points from equipe where id_jeu= "+jeu+"order by nb_points desc");

            List<String> equipe = new ArrayList<String>();

            while(rs.next()) {
                equipe.add(rs.getString(1));
            }
            classement = Arrays.copyOf(equipe.toArray(), equipe.toArray().length,String[].class);

            connx.close();

        } catch (SQLException e) {
            switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est deja present dans la base de donn�es");
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
        return classement;
    }

	/** Importation des informations secondaires depuis la base de donnees
	 * @throws ErreurBD 
	 */
	public void select() throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rese = st.executeQuery("select nb_points, id_jeu from equipe where nom='"+this.nom+"'");

			rese.next();
			if (this.nbPoints < 0) {
				this.nbPoints = rese.getInt(1);
			}
			if(this.id_jeu < 0) {
				this.id_jeu = rese.getInt(2);
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

	}

	/** Inserer toutes les informations dans la base de donnees
	 * @throws ErreurBD 
	 * 
	 */
	public void insert(int ecurie) throws ErreurBD{
		if (this.nbPoints >= 0 && this.id_jeu > 0) {
			try {
				DataSource bd = new ConnexionBD();

				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO equipe values(seq_joueur.nextVal,'"+this.nom+"',"+this.nbPoints+","+this.id_jeu+","+ecurie+")");

				connx.close();
			} catch (SQLException e) {
				switch(e.getErrorCode()) {
				case 1 : 
					throw new ErreurBD("Le nom de cette equipe est deja present dans la base de donnees");
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
		} else {
			throw new IllegalArgumentException("Au moins un des parametres n'est pas valide/defini");
		}
	}

	public void delete() throws ErreurBD{
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			System.out.println("deleting "+this.nom);
			st.executeUpdate("delete participation where id_equipe = "+this.getID());
			st.executeUpdate("delete participer where id_equipe = "+this.getID());
			st.executeUpdate("delete matchs where id_equipe = "+this.getID()+" or id_equipe1 = "+this.getID());
			st.executeUpdate("delete joueur where id_equipe = "+this.getID());
			st.executeUpdate("delete equipe where nom = '"+this.nom+"'");

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
			case 1 : 
				throw new ErreurBD("Le nom de cette equipe est deja present dans la base de donnees");
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

	/** Redefinition de hashCode
	 *@return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id_jeu, nbPoints, nom);
	}

	/** Redefinition de la methode equals
	 *@return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Equipe)) {
			return false;
		}
		Equipe other = (Equipe) obj;
		return id_jeu == other.id_jeu && nbPoints == other.nbPoints
				&& Objects.equals(nom, other.nom);
	}

	/** Renvoie tous les noms d'equipes contenus dans la base de donnees appartenant a une ecurie
	 * @return un tableau de string
	 * @throws ErreurBD
	 */
	public static String[] getNomEquipe(int IdEcurie) throws ErreurBD {
		String[] r = null;
		try {
			DataSource bd = new ConnexionBD();
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rese = st.executeQuery("select nom from equipe where id_ecurie = " + IdEcurie + " order by nom");
			List <String> nom = new ArrayList<String>();
			while(rese.next()) {
				nom.add(rese.getString(1));
			}
			r = Arrays.copyOf(nom.toArray(), nom.toArray().length, String[].class);

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
		return r;
	}
	
	@Override
    public String toString() {
        return nom;
    }

}