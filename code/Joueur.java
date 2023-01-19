package code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.sql.DataSource;



public class Joueur {

	
//initialisation des diff�rentes variables	
	private String nom;
	private String prenom;
	private Date dateN;
	private char sexe = ' ';
	private String numTel;
	private String email;


	/** constructueur de la classe Joueur en fonction de son nom et de son prénom 
	 * @param nom
	 * @param prenom
	 * @throws IllegalArgumentException
	 * @exception nom et pr�nom non null
	 */
	public Joueur(String nom, String prenom) throws IllegalArgumentException{
		if (nom == null || prenom == null) {
			throw new IllegalArgumentException("nom et pr�nom ne peuvent pas �tre null");
		}
		this.nom = nom;
		this.prenom = prenom;
	}

	/** constructueur de la classe Joueur en fonction de son nom, son prénom, sa date de naissance, sonn sexe, son numéro de téléphone et de son email
	 * @param nom
	 * @param prenom
	 * @param date
	 * @param sexe
	 * @param tel
	 * @param email
	 * @throws IllegalArgumentException
	 * @exception nom et pr�nom non null
	 */
	public Joueur(String nom, String prenom, Date date, char sexe, String tel, String email) throws IllegalArgumentException{
		if (nom == null || prenom == null) {
			throw new IllegalArgumentException("nom et pr�nom ne peuvent pas �tre null");
		}
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = date;
		this.sexe = sexe;
		this.numTel = tel;
		this.email = email;
	}

	/** renvoie le nom du joueur
	 * @return string : nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** renvoie le pr�nom du joueur
	 * @return string : prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/** renvoie le date de naissance du joueur
	 * @return Date : date de naissance
	 * @throws ErreurBD 
	 */
	public Date getDateNaissance() throws ErreurBD {
		if (this.dateN == null) {
			this.select();
		}
		return this.dateN;
	}

	/** renvoie le sexe du joueur
	 * @return char : sexe
	 * @throws ErreurBD 
	 */
	public char getSexe() throws ErreurBD {
		if (this.sexe != 'M' && this.sexe != 'F' && this.sexe != 'X') {
			this.select();
		}
		return this.sexe;
	}

	/**renvoie le num�ro de t�l�phone d'un joueur
	 * @return string : num�ro de t�l�phone
	 * @throws ErreurBD 
	 */
	public String getTel() throws ErreurBD {
		if (this.numTel == null) {
			this.select();
		} 
		return this.numTel;
	}

	/**renvoie l'email d'un Joueur
	 * @return string : email
	 * @throws ErreurBD 
	 */
	public String getEmail() throws ErreurBD {
		if (this.email == null) {
			this.select();
		}
		return this.email;
	}
	
	/**redéfinition de la méthode equals
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Joueur)) {
			return false;
		}
		Joueur other = (Joueur) obj;
		return Objects.equals(dateN, other.dateN) && Objects.equals(email, other.email)
				&& Objects.equals(nom, other.nom) && Objects.equals(numTel, other.numTel)
				&& Objects.equals(prenom, other.prenom) && sexe == other.sexe;
	}

	/** importation des informations secondaires d'un joueur depuis la base de donn�es
	 * @throws ErreurBD 
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 */
	public void select() throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rese = st.executeQuery("select date_naissance, sexe, numero_de_telephone, email from joueur where nom='"+this.nom+"' and prenom='"+this.prenom+"'");

			rese.next();
			if (this.dateN == null) {
				this.dateN = rese.getDate(1);
			}
			if (this.sexe != 'H' && this.sexe != 'F') {
				this.sexe = rese.getString(2).charAt(0);
			}
			if (this.numTel == null) {
				this.numTel = rese.getString(3);
			}
			if (this.email == null) {
				this.email = rese.getString(4);
			}

			connx.close();
		} catch (SQLException e) {
			switch(e.getErrorCode()) {
            case 1 : 
                throw new ErreurBD("Un enregistrement similaire est déjà présent dans la base de données");
            case 2291:
                throw new ErreurBD("Il manque la clé étrangère");
            case 2292:
                throw new ErreurBD("Impossibilité de supprimer car l'enregistrement est présent dans une autre table");
            case 2290:
                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
            case 1400:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
            case 1407:
                throw new ErreurBD("Une valeur n'a pas été renseigné");
                
            }
            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
                throw new ErreurBD("Transgréssion de l'un des déclencheurs de la base de données");
            }
		}
	}

	/** ins�rer toutes les informations dans la base de donn�es
	 * @throws ErreurBD 
	 * @exception mauvaise connexion � la BD
	 * @exception mauvaise requ�te
	 * @exception un des paramètres n'est pas valide
	 */
	public void insert(int equipe) throws ErreurBD {
		if (this.dateN != null && (this.sexe == 'M' || this.sexe == 'F' || this.sexe == 'X') && this.numTel != null && this.email != null && equipe>=0) {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO joueur values(seq_joueur.nextVal,'"+this.nom+"','"+this.prenom+"',to_date('"+this.dateN.toString()+"','YYYY-MM-DD'),'"
						+this.sexe+"','"+this.numTel+"','"+this.email+"',"+equipe+")");

				connx.close();
			} catch (SQLException e) {
				switch(e.getErrorCode()) {
	            case 1 : 
	                throw new ErreurBD("Un enregistrement similaire est déjà présent dans la base de données");
	            case 2291:
	                throw new ErreurBD("Il manque la clé étrangère");
	            case 2292:
	                throw new ErreurBD("Impossibilité de supprimer car l'enregistrement est présent dans une autre table");
	            case 2290:
	                throw new ErreurBD("Vous ne pouvez pas renseigner cette valeur dans ce champ");
	            case 1400:
	                throw new ErreurBD("Une valeur n'a pas été renseigné");
	            case 1407:
	                throw new ErreurBD("Une valeur n'a pas été renseigné");
	                
	            }
	            if (200000<= e.getErrorCode() && e.getErrorCode() <=20999) {
	                throw new ErreurBD("Transgréssion de l'un des déclencheurs de la base de données");
	            }
			}
		} else {
			throw new IllegalArgumentException("Au moins un des param�tres n'est pas valide/definie");
		}
	}
}
