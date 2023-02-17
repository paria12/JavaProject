package code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ecurie {
    private String nom;
    private String type = null;
    private List<Equipe> equipe = new ArrayList<Equipe> ();
    
    /**Constructeur d'ecurie a partir de son nom
     * @param nom
     */
    public Ecurie(String nom) {
    	if (nom == null) {
    		throw new IllegalArgumentException("Le nom de l'ecurie ne doit pas etre null");
    	}
    	this.nom = nom;
    	this.equipe = new ArrayList<Equipe>();
    }

    /**Constructeur d'ecurie a partir de son nom et de son type
     * @param nom
     * @param type
     */
    public Ecurie(String nom, String type) {
    	if (nom == null) {
    		throw new IllegalArgumentException("Le nom de l'ecurie ne doit pas etre null");
    	}
    	this.nom = nom;
    	this.type = type;
    	this.equipe = new ArrayList<Equipe>();
    }
    
    /**Ajouter une equipe a une ecurie
     * @param e
     */
    public void addEquipe(Equipe e) {
    	this.equipe.add(e);
    }
    
    /**Supprimer une equipe d'une ecurie 
     * @param e
     */
    public void removeEquipe(Equipe e) {
    	this.equipe.remove(e);
    }

    /**Renvoie le nom d'une ecurie 
     * @return string : nom
     */
    public String getNom() {
    	return this.nom;
    }

    /** Renvoie le type d'une ecurie
     * @return string : type
     * @throws ErreurBD
     */
    public String getType() throws ErreurBD {
    	if (this.type != null) {
    		return this.type;
    	} else {
    		try {
    			ResultSet rese = ConnexionBD.Query("select type from ecurie where nom='"+this.nom+"'");

				rese.next();
				this.type = rese.getString(1);
			} catch (SQLException e) {
				ErreurBD.excSQL(e);
			}
			return this.type;
		}
    	
    }
    
    /** Renvoie les equipes appartenant a l'ecurie
     * @return une liste d'equipe : equipe
     * @throws ErreurBD
     */
    public List<Equipe> getEquipe() throws ErreurBD{
    	if (!this.equipe.isEmpty()) {
    		return this.equipe;
    	} else {
    		this.selectEquipe();
    		return this.equipe;
    	}
    }
    

    /** Recupere toutes les equipes appartenant a l'ecurie en fonction de son nom
     * @throws ErreurBD
     */
    public void selectEquipe() throws ErreurBD {
    	try {
    		ResultSet rs = ConnexionBD.Query("select e.nom, e.nb_points, e.id_jeu from ecurie ee , equipe e where ee.nom ='"+this.nom+"' and e.id_ecurie = ee.id_ecurie");
			
    		while(rs.next()){
				this.equipe.add(new Equipe(rs.getString(1),rs.getInt(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
    }
    
    /** insert une nouvelle ecurie dans la base de donnees
     * @param pwd
     * @throws ErreurBD
     */
    public void insert(String pwd) throws ErreurBD {
    	if (this.type != null) {
    		try {
    			ConnexionBD.Query("INSERT INTO ecurie values(seq_ecurie.nextVal,'"+this.nom+"','"+this.type+"','"+Connexion.sta256(pwd)+"')");
    		} catch (SQLException e) {
    			ErreurBD.excSQL(e);
			}
		} else {
			throw new IllegalArgumentException("Le type de l'ecurie doit etre renseigner pour cette op�ration");
		}
    }

	/** Redefinition de hashCode
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nom, type);
	}

	/** Redefinition de la methode equals
	 *@return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ecurie)) {
			return false;
		}
		Ecurie other = (Ecurie) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(type, other.type);
	}
	
	/** Renvoie l'id d'une ecurie en fonction de son nom
	 * @param ec
	 * @return int : retour
	 * @throws ErreurBD
	 */
	public static int getID(Ecurie ec) throws ErreurBD {
		int retour = 0;
		try {
			ResultSet rs = ConnexionBD.Query("select id_Ecurie from ecurie where nom='"+ec.getNom()+"'");
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
		return retour;
	}
}
