package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

public class Poule {

	private Equipe[] equipes;
	private Match[] matchs;
	private int pointeur;
	private int id;

	/** Constructeur d'une poule
	 * 
	 */
	public Poule (){
		this.equipes = new Equipe[4];
		this.matchs = new Match[6];
		this.pointeur = 0;
	}

	/** Insert une equipe dans la poule
	 * 
	 * @param e 
	 * @throws IllegalArgumentException lorsque la poule compte deja 4 participants
	 */
	public void insererEquipe(Equipe e) throws IllegalArgumentException{
		if (this.pointeur == 4) {
			throw new IllegalArgumentException("La poule est deja pleine");
		} else {
			this.equipes[pointeur] = e;
			this.pointeur++;
		}
	}
	
	/** Retourne toutes les equipes de la poule
	 * 
	 * @return tableau equipe : equipe
	 */
	public Equipe[] getEquipe () {
		return this.equipes;
	}

	/** Genere les matchs de toute la poule
	 * 
	 * @param hdebut
	 * @throws ErreurBD lorsque une erreur lie a la bd est leve sur la methode getTimeFromId
	 */
	public void GenererMatch(Timestamp hdebut) throws ErreurBD {
        int temps = Jeu.getTimeFromID(this.equipes[0].getIdJeu());
        this.matchs[0] = new Match(this.equipes[2],this.equipes[3],hdebut,new Timestamp(hdebut.getTime()+temps*60000));
        this.matchs[1] = new Match(this.equipes[0],this.equipes[1],hdebut,new Timestamp(hdebut.getTime()+temps*60000));
        this.matchs[2] = new Match(this.equipes[0], this.equipes[2],new Timestamp(hdebut.getTime()+temps*60000), new Timestamp(hdebut.getTime()+2*(temps*60000)));
        this.matchs[3] = new Match(this.equipes[1], this.equipes[3], new Timestamp(hdebut.getTime()+temps*60000), new Timestamp(hdebut.getTime()+2*(temps*60000)));
        this.matchs[4] = new Match(this.equipes[0], this.equipes[3],new Timestamp(hdebut.getTime()+2*(temps*60000)) , new Timestamp(hdebut.getTime()+3*(temps*60000)));
        this.matchs[5] = new Match(this.equipes[1], this.equipes[2], new Timestamp(hdebut.getTime()+2*(temps*60000)), new Timestamp(hdebut.getTime()+3*(temps*60000)));
    }

	/** Retourne tous les matchs d'une poule
	 * 
	 * @return liste match : match
	 */
	public Match[] getMatch() {
		return this.matchs;
	}
	
	/** Insert la poule dans le tournoi donne dans la base de donnee
	 * 
	 * @param tournoi
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public void insert(int tournoi) throws ErreurBD {
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			st.executeQuery("Insert into Poule values(seq_poule.nextVal, "+tournoi+")");
			for (Equipe e : this.equipes) {
				st.executeQuery("Insert into Participer values ( "+e.getID()+ ", seq_poule.currval)");
			}
			ResultSet rs = st.executeQuery("select seq_poule.currval from poule");
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			
			for (Match m : this.matchs) { 
				m.insert(this.id);
			}
			
			connx.close();
		}catch (SQLException e){ 
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

	/**
	 * 
	 * @return tableau a deux dimension contenant les equipes et leur nombre de matchs gagnes : string
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public String[][] getClassement() throws ErreurBD {
		String[][] classement = null;
		try {
			classement = new String[2][4];
			
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();
			
			int i = 0;
			ResultSet rs =st.executeQuery("SELECT equipe.nom, count(*) from equipe, matchs where matchs.gagnant=equipe.id_equipe and matchs.id_poule="+this.id+" group by equipe.nom order by 2 desc");
			while (rs.next()) {
				classement[0][i] = rs.getString(1);
				classement[1][i] = rs.getString(2);
				i++;
			}
			
			connx.close();
		} catch (SQLException e){ 
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
		return classement;
	}

}