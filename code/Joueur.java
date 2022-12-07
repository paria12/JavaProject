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


	/** constructueur de la classe Joueur et de nom et pr�nom 
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

	/** constructueur de la classe Joueur et de toutes ces variables
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
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/** renvoie le pr�nom du joueur
	 * @return prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/** renvoie le date de naissance du joueur
	 * @return date de naissance
	 * @throws ErreurBD 
	 */
	public Date getDateNaissance() throws ErreurBD {
		if (this.dateN == null) {
			this.select();
		}
		return this.dateN;
	}

	/** renvoie le sexe du joueur
	 * @return sexe
	 * @throws ErreurBD 
	 */
	public char getSexe() throws ErreurBD {
		if (this.sexe != 'H' && this.sexe != 'F') {
			this.select();
		}
		return this.sexe;
	}

	/**renvoie le num�ro de t�l�phone d'un joueur
	 * @return num�ro de t�l�phone
	 * @throws ErreurBD 
	 */
	public String getTel() throws ErreurBD {
		if (this.numTel == null) {
			this.select();
		} 
		return this.numTel;
	}

	/**renvoie l'email d'un Joueur
	 * @return email
	 * @throws ErreurBD 
	 */
	public String getEmail() throws ErreurBD {
		if (this.email == null) {
			this.select();
		}
		return this.email;
	}
	
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

	/** importation des informations secondaires depuis la base de donn�es
	 * @throws ErreurBD 
	 * 
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

		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requette a la bd");
		}
	}

	/** ins�rer toutes les informations dans la base de donn�es
	 * @throws ErreurBD 
	 * 
	 */
	public void insert(int equipe) throws ErreurBD {
		if (this.dateN != null && (this.sexe == 'H' || this.sexe == 'F') && this.numTel != null && this.email != null && equipe>=0) {
			try {
				DataSource bd = new ConnexionBD();
				
				Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO joueur values(seq_joueur.nextVal,'"+this.nom+"','"+this.prenom+"',to_date('"+this.dateN.toString()+"','YYYY-MM-DD'),'"
						+this.sexe+"','"+this.numTel+"','"+this.email+"',"+equipe+")");

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de reqette a la bd");
			}
		} else {
			throw new IllegalArgumentException("Au moins un des param�tres n'est pas valide/definie");
		}
	}
}
