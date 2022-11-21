package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ecurie {
    private String nom;
    private String type = null;
    private List<Equipe> equipe = new ArrayList<Equipe> ();
    
    public Ecurie(String nom) {
    	if (nom == null) {
    		throw new IllegalArgumentException("Le nom de l'ecurie ne doit pas etre null");
    	}
    	this.nom = nom;
    	this.equipe = new ArrayList<Equipe>();
    }

    public Ecurie(String nom, String type) {
    	if (nom == null) {
    		throw new IllegalArgumentException("Le nom de l'ecurie ne doit pas etre null");
    	}
    	this.nom = nom;
    	this.type = type;
    	this.equipe = new ArrayList<Equipe>();
    }
    
    public void addEquipe(Equipe e) {
    	this.equipe.add(e);
    }
    
    public void removeEquipe(Equipe e) {
    	this.equipe.remove(e);
    }

    public String getNom() {
    	return this.nom;
    }

    public String getType() throws ErreurBD {
    	if (this.type != null) {
    		return this.type;
    	} else {
			String loginBD = "ndf4080a";
			String mdpBD = "fatime31";
			String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de connexion a la bd");
			}

			try {
				Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

				Statement st = connx.createStatement();

				ResultSet rese = st.executeQuery("select type from ecurie where nom='"+this.nom+"'");

				rese.next();
				this.type = rese.getString(1);

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requête a la bd");
			}
			return this.type;
		}
    	
    }
    
    public List<Equipe> getEquipe() throws ErreurBD{
    	if (!this.equipe.isEmpty()) {
    		return this.equipe;
    	} else {
    		this.selectEquipe();
    		return this.equipe;
    	}
    }
    

    public void selectEquipe() throws ErreurBD {
    	String loginBD = "ndf4080a";
		String mdpBD = "fatime31";
		String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de connexion a la bd");
		}

		try {
			Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select e.nom, e.nb_points, e.id_jeu from ecurie ee , equipe e where ee.nom ='"+this.nom+"' and e.id_ecurie = ee.id_ecurie");
			while(rs.next()){
				this.equipe.add(new Equipe(rs.getString(1),rs.getInt(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requette bd");
		}
    }
    
    public void insert(String pwd) throws ErreurBD {
    	if (this.type != null) {
			String loginBD = "ndf4080a";
			String mdpBD = "fatime31";
			String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre";

			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e) {
				throw new ErreurBD("Erreur de connexion a la bd");
			}

			try {
				Connection connx = DriverManager.getConnection(connectString, loginBD, mdpBD);

				Statement st = connx.createStatement();

				st.executeQuery("INSERT INTO ecurie values(seq_ecurie.nextVal,'"+this.nom+"','"+this.type+"','"+Connexion.sta256(pwd)+"')");

			} catch (SQLException e) {
				throw new ErreurBD("Erreur de requette bd");
			}
		} else {
			throw new IllegalArgumentException("Le type de l'ecurie doit etre renseigner pour cette opération");
		}
    }

	@Override
	public int hashCode() {
		return Objects.hash(nom, type);
	}

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
    
    

}
