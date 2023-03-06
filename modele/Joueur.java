package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;



public class Joueur {

	
// Initialisation des differentes variables	
	private String nom;
	private String prenom;
	private Date dateN;
	private char sexe = ' ';
	private String numTel;
	private String email;


	/** Constructueur de la classe Joueur en fonction de son nom et de son prenom 
	 * @param nom
	 * @param prenom
	 * @throws IllegalArgumentException
	 * @exception IllegalArgumentException nom et prenom non null
	 */
	public Joueur(String nom, String prenom) throws IllegalArgumentException{
		if (nom == null || prenom == null) {
			throw new IllegalArgumentException("nom et prenom ne peuvent pas etre null");
		}
		this.nom = nom;
		this.prenom = prenom;
	}

	/** Constructueur de la classe Joueur en fonction de son nom, son prenom, sa date de naissance, son sexe, son numero de telephone et de son email
	 * @param nom
	 * @param prenom
	 * @param date
	 * @param sexe
	 * @param tel
	 * @param email
	 * @throws IllegalArgumentException
	 * @exception IllegalArgumentException nom et prenom non null
	 */
	public Joueur(String nom, String prenom, Date date, char sexe, String tel, String email) throws IllegalArgumentException{
		if (nom == null || prenom == null) {
			throw new IllegalArgumentException("nom et prenom ne peuvent pas etre null");
		}
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = date;
		this.sexe = sexe;
		this.numTel = tel;
		this.email = email;
	}

	/** Renvoie le nom du joueur
	 * @return string : nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** Renvoie le prenom du joueur
	 * @return string : prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/** Renvoie le date de naissance du joueur
	 * @return Date : date de naissance
	 * @throws ErreurBD 
	 */
	public Date getDateNaissance() throws ErreurBD {
		if (this.dateN == null) {
			this.select();
		}
		return this.dateN;
	}

	/** Renvoie le sexe du joueur
	 * @return char : sexe
	 * @throws ErreurBD 
	 */
	public char getSexe() throws ErreurBD {
		if (this.sexe != 'M' && this.sexe != 'F' && this.sexe != 'X') {
			this.select();
		}
		return this.sexe;
	}

	/** Renvoie le numero de telephone d'un joueur
	 * @return string : numero de telephone
	 * @throws ErreurBD 
	 */
	public String getTel() throws ErreurBD {
		if (this.numTel == null) {
			this.select();
		} 
		return this.numTel;
	}

	/** Renvoie l'email d'un Joueur
	 * @return string : email
	 * @throws ErreurBD 
	 */
	public String getEmail() throws ErreurBD {
		if (this.email == null) {
			this.select();
		}
		return this.email;
	}
	
	/** Redefinition de la methode equals
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

	/** Importation des informations secondaires d'un joueur depuis la base de donnees
	 * @throws ErreurBD lorsque une erreur lie a la base de donne est leve
	 */
	public void select() throws ErreurBD {
		try {
			ResultSet rese = ConnexionBD.Query("select date_naissance, sexe, numero_de_telephone, email from joueur where nom='"+this.nom+"' and prenom='"+this.prenom+"'");

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
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
	}

	/** Inserer toutes les informations du joueur dans la base de donnees pour une equipe donnee
	 * @param equipe
	 * @throws ErreurBD lorsque une erreur lie a la base de donnees est leve
	 */
	public void insert(int equipe) throws ErreurBD {
		if (this.dateN != null && (this.sexe == 'M' || this.sexe == 'F' || this.sexe == 'X') && this.numTel != null && this.email != null && equipe>=0) {
			try {
				ConnexionBD.Query("INSERT INTO joueur values(seq_joueur.nextVal,'"+this.nom+"','"+this.prenom+"',to_date('"+this.dateN.toString()+"','YYYY-MM-DD'),'"
						+this.sexe+"','"+this.numTel+"','"+this.email+"',"+equipe+")");
			} catch (SQLException e) {
				ErreurBD.excSQL(e);
			}
		} else {
			throw new IllegalArgumentException("Au moins un des parametres n'est pas valide/defini");
		}
	}
}
