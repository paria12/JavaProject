package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

public class Ecurie {
    private String nom;
    private String type = null;
    private List<Equipe> equipe = new ArrayList<Equipe> ();
    
    /**Constructeur d'Écurie à partir de son nom
     * @param nom
     */
    public Ecurie(String nom) {
    	if (nom == null) {
    		throw new IllegalArgumentException("Le nom de l'ecurie ne doit pas etre null");
    	}
    	this.nom = nom;
    	this.equipe = new ArrayList<Equipe>();
    }

    /**Constructeur d'Écurie à partir de son nom et de son type
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
    
    /**Ajouter une équipe à une écurie
     * @param e
     */
    public void addEquipe(Equipe e) {
    	this.equipe.add(e);
    }
    
    /**Supprimer une équipe d'une écurie 
     * @param e
     */
    public void removeEquipe(Equipe e) {
    	this.equipe.remove(e);
    }

    /**Renvoie le nom d'une écurie 
     * @return string : nom
     */
    public String getNom() {
    	return this.nom;
    }

    /** renvoie le type d'une écurie
     * @return string : type
     * @throws ErreurBD
     */
    public String getType() throws ErreurBD {
    	if (this.type != null) {
    		return this.type;
    	} else {
    		try {
    			DataSource bd = new ConnexionBD();
    			
    			Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select type from ecurie where nom='"+this.nom+"'");

				rese.next();
				this.type = rese.getString(1);
				
				connx.close();
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requ�te a la bd");
			}
			return this.type;
		}
    	
    }
    
    /** renvoie les équipes appartenant à l'écurie
     * @return une liste d'équipe : equipe
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
    

    /**récupère toutes les équipes appartenant à l'écurie en fonction de son nom
     * @throws ErreurBD
     */
    public void selectEquipe() throws ErreurBD {
    	try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select e.nom, e.nb_points, e.id_jeu from ecurie ee , equipe e where ee.nom ='"+this.nom+"' and e.id_ecurie = ee.id_ecurie");
			while(rs.next()){
				this.equipe.add(new Equipe(rs.getString(1),rs.getInt(2),rs.getInt(3)));
			}
			
			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requette bd");
		}
    }
    
    /** insère une nouvelle écurie dans la base de donnée
     * @param pwd
     * @throws ErreurBD
     */
    public void insert(String pwd) throws ErreurBD {
    	if (this.type != null) {
    		try {
    			DataSource bd = new ConnexionBD();
    			
    			Connection connx = bd.getConnection();

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO ecurie values(seq_ecurie.nextVal,'"+this.nom+"','"+this.type+"','"+Connexion.sta256(pwd)+"')");

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requête bd");
			}
		} else {
			throw new IllegalArgumentException("Le type de l'ecurie doit etre renseigner pour cette op�ration");
		}
    }

	/** redéfinition de hashCode
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nom, type);
	}

	/**redéfinition de la méthode equals
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
	
	/** renvoie l'id d'une écurie en fonction de son nom
	 * @param ec
	 * @return int : retour
	 * @throws ErreurBD
	 */
	public static int getID(Ecurie ec) throws ErreurBD {
		int retour = 0;
		try {
			DataSource bd = new ConnexionBD();
			
			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select id_Ecurie from ecurie where nom='"+ec.getNom()+"'");
			
			while(rs.next()) {
				retour=rs.getInt(1);
			}

			connx.close();
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requéte a la bd : "+e);
		}
		return retour;
	}
}
