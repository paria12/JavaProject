package code;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Tournoi {
	private String nom;
	private String adresse;
	private String ville;
	private String pays;
	private String codePostal;
	private Date date;
	private String notoriete;
	private int idjeu;

	private List<Equipe> equipes;
	private Poule[] poules;

	/** Constructeur d'un tournoi a partir de son nom, sa date et son jeu assigne
	 * @param nom
	 * @param date
	 * @param jeu
	 */
	public Tournoi(String nom, Date date, int jeu) {
		this.nom = nom;
		this.adresse = null;
		this.ville = null;
		this.pays = null;
		this.codePostal = null;
		this.date = date;
		this.notoriete = null;
		this.idjeu = jeu;
		this.equipes = new ArrayList<Equipe>();
		this.poules = new Poule[5];
	}
	
	/** Constructeur d'un tournoi a partir de son nom, adresse, ville, pays, codePostal, date, notoriete et jeu associé
	 * @param nom
	 * @param adresse
	 * @param ville
	 * @param pays
	 * @param codePostal
	 * @param date
	 * @param notoriete
	 * @param jeu
	 */
	public Tournoi(String nom, String adresse, String ville, String pays, String codePostal, Date date, String notoriete, int jeu) {
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.codePostal = codePostal;
		this.date = date;
		this.notoriete = notoriete;
		this.idjeu = jeu;
		this.equipes = new ArrayList<Equipe>();
		this.poules = new Poule[5];
	}

	/** Retourne le nom du tournoi
	 * 
	 * @return string : nom
	 */
	public String getNom() {
		return nom;
	}

	/** Retourne l'adresse du tournoi
	 * 
	 * @return string : adresse
	 * @throws ErreurBD lorsque l'adresse est null
	 */
	public String getAdresse() throws ErreurBD {
		if (this.adresse == null) {
			this.select();
		}
		return adresse;
	}

	/**Retourne la ville ou se deroule le tournoi
	 * 
	 * @return string : ville
	 * @throws ErreurBD lorsque la ville est null
	 */
	public String getVille() throws ErreurBD {
		if (this.ville == null) {
			this.select();
		}
		return ville;
	}

	/**Retourne le pays ou se deroule le tournoi
	 * 
	 * @return string : pays
	 * @throws ErreurBD lorsque le pays est null
	 */
	public String getPays() throws ErreurBD {
		if (this.pays == null) {
			this.select();
		}
		return pays;
	}

	/** Retourne le code postal du tournoi
	 * 
	 * @return string : codePostal
	 * @throws ErreurBD lorsque le code postal est null
	 */
	public String getCodePostal() throws ErreurBD {
		if (this.codePostal == null) {
			this.select();
		}
		return codePostal;
	}

	/** Retourne la date du tournoi
	 * 
	 * @return Date : date
	 */
	public Date getDate() {
		return date;
	}

	/** Retourne la notoriete du tournoi
	 * 
	 * @return string : notoriete
	 * @throws ErreurBD
	 */
	public String getNotoriete() throws ErreurBD {
		if (this.notoriete == null) {
			this.select();
		}
		return notoriete;
	}

	/** Retourne l'id du jeu associe au tournoi
	 * 
	 * @return int : idjeu
	 */
	public int getJeu() {
		return idjeu;
	}
	
	/** Retourne l'id du tournoi depuis la base de donnees
	 * 
	 * @return int : retour
	 * @throws ErreurBD lorsqu'une erreur lie a la base de donnée est leve
	 */
	public int getId() throws ErreurBD {
		int retour = 0;
		try {
			ResultSet rs = ConnexionBD.Query("select id_tournoi from tournoi where nomtournoi = '"+this.nom+"'");
			rs.next();
			retour = rs.getInt(1);
		} catch (SQLException ex){ 
			ErreurBD.excSQL(ex);
		}
		return retour;
	}

	/** Rajoute l'equipe donne en parametre au tournoi
	 * 
	 * @param e : Equipe
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee sql est leve
	 */
	public void addEquipe(Equipe e) throws ErreurBD {
		try {
			ConnexionBD.Query("insert into participation values("+e.getID()+","+this.getId()+")");
		} catch (SQLException ex){ 
			ErreurBD.excSQL(ex);
		}
		this.selectEquipe();
		if (this.equipes.size()==16) {
			this.GenererPoule();
		}
	}

	/** Retourne la liste d'equipe deja inscrite au tournoi
	 * 
	 * @return une liste d'equipe : equipe
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public List<Equipe> selectEquipe() throws ErreurBD {
		try {
			ResultSet rs = ConnexionBD.Query("select nom from equipe e, participation p where e.id_equipe=p.id_equipe and p.id_tournoi="+this.getId());
			
			while(rs.next()) {
				 this.equipes.add(new Equipe(rs.getString(1)));
			}
		} catch (SQLException ex){
			ErreurBD.excSQL(ex);
		}
		return this.equipes;
	}
	
	
	/** Genere les poules du tournoi en fonction des points des equipes
	 * 
	 * @throws IllegalArgumentException lorsque la liste d'equipe inscrite a un tournoi est pleine (16)
	 */
	public void GenererPoule() throws IllegalArgumentException{
		if(this.equipes.size()!=16) {
			throw new IllegalArgumentException("Impossible de genere les poule tant que toute les equipe ne sont pas inscrite");
		} else {
			Collections.sort(this.equipes,new Comparator<Equipe>() {
				@Override
				public int compare(Equipe e1, Equipe e2) {
					try {
						if(e1.getNbPoints()>e2.getNbPoints()) {
							return 1;
						} else if(e1.getNbPoints()==e2.getNbPoints()) {
							return 0;
						} else {
							return -1;
						}
					} catch(Exception e) {
						e.printStackTrace();
						return 0;
					}
				}});
			this.poules[0]=new Poule();
			this.poules[1]=new Poule();
			this.poules[2]=new Poule();
			this.poules[3]=new Poule();
			for(int i = 0; i < 16; i++) {
				this.poules[i%4].insererEquipe(this.equipes.get(i));
			}
		}
	}

	/** Genere la poule finale avec les premiers de chaque poule
	 * 
	 * @throws IllegalArgumentException lorsqu'il y a moins de 4 poules
	 * @throws ErreurBD
	 */
	public void GenererPouleFinal() throws IllegalArgumentException, ErreurBD {
		for(int k = 0; k < 4; k++) {
			if (this.poules[k]==null) {
				throw new IllegalArgumentException("Au moins une des poule n'est pas definie");
			}
		}
		try {
			ResultSet rese = ConnexionBD.Query("SELECT COUNT(matchs.id_poule) from Matchs, Poule, Tournoi where matchs.id_poule = poule.id_poule AND poule.id_tournoi = tournoi.id_tournoi AND tournoi.id_tournoi = "+this.getId()+" AND matchs.gagnant is not null");

			rese.next();
			if (rese.getInt(1)==24) {
				this.poules[4] = new Poule();
				for(int i = 0; i < 4; i++) {
					this.poules[4].insererEquipe(new Equipe(this.poules[i].getClassement()[0][0]));
				}
			}
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
	}

	/** Creer un classement des equipes en fonction de leurs scores
	 * 
	 * @return un tableau a deux dimension des equipes trie en fonction de leur point : string
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public String[][] getClassement() throws ErreurBD {
        try {
            this.selectEquipe();
            String[][] classement = new String[2][16];

            int i = 0;
            ResultSet rs = ConnexionBD.Query("SELECT equipe.nom, count(*) from equipe, matchs, poule, tournoi where matchs.gagnant=equipe.id_equipe and matchs.id_poule=poule.id_poule and poule.id_tournoi=tournoi.id_tournoi and tournoi.nomtournoi='"+this.nom+"' group by equipe.nom order by 2 desc");
            while (rs.next()) {
                classement[0][i] = rs.getString(1);
                classement[1][i] = rs.getString(2);
                i++;
            }
            if (i<15) {
                ArrayList<String> tmp = new ArrayList<String>();
                for(String s:classement[0]) {
                    tmp.add(s);
                }
                for(Equipe e:this.equipes) {
                    if(!tmp.contains(e.getNom())) {
                        classement[0][i] = e.getNom();
                        classement[1][i] = "0";
                        tmp.add(e.getNom());
                        i++;
                    }
                }
            }
            return classement;
        } catch (SQLException e){ 
            throw new ErreurBD("Erreur de requete a la bd"+e);
        }
    }

	/** Generation du score cumule par les equipes durant le tournoi
	 * 
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est lave
	 * @throws IllegalArgumentException lorsque la poule finale n'a pas ete faites
	 */
	public void genererScore() throws ErreurBD, IllegalArgumentException {
		if (this.poules[4]==null) {
			throw new IllegalArgumentException("La poule finale n'a pas etait faite");
		}
		int mult=0;
		switch (this.notoriete) {
		case "local":
			mult=1;
			break;
		case "national":
			mult=2;
			break;
		case "international":
			mult=3;
			break;
		default:
			throw new IllegalArgumentException("la notoriete du tournoi n'est pas valide");
		}
		for (int i = 0; i < 4; i++) {
			try {
				ResultSet rese = ConnexionBD.Query("select nb_points from equipe where nom='"+this.poules[4].getClassement()[0][i]+"'");

				rese.next();
				int nbp = rese.getInt(1);

				switch (i) {
				case 0:
					nbp += 100*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				case 1:
					nbp += 60*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				case 2:
					nbp += 30*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				default:
					nbp += 10*mult+Integer.valueOf(this.poules[4].getClassement()[1][i]);
					break;
				}

				ConnexionBD.Query("Update equipe set nb_points="+nbp+" where nom='"+this.poules[4].getClassement()[0][i]+"'");;
			} catch (SQLException e) {
				ErreurBD.excSQL(e);
			}
		}
	}

	/** Selectionne les informations secondaires du tournoi
	 * 
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public void select() throws ErreurBD {
		try {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(this.date);

	        ResultSet rese = ConnexionBD.Query("select adresse, ville, pays, codepostal, notoriete from Tournoi where nomtournoi='"
	        +this.nom+"' and datetournoi='"+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+"'");

			rese.next();
			if (this.adresse == null) {
				this.adresse = rese.getString(1);
			}
			if (this.ville == null) {
				this.ville = rese.getString(2);
			}
			if (this.pays == null) {
				this.pays = rese.getString(3);
			}
			if (this.codePostal == null) {
				this.codePostal = rese.getString(4);
			}
			if (this.notoriete == null) {
				this.notoriete = rese.getString(5);
			}
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
	}

	/** Insert un tournoi dans la base de donnees
	 * 
	 * @param arbitre
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 * @throws IllegalArgumentException lorsque l'un des argument du tournoi n'est pas renseigne
	 */
	public void insert(int arbitre) throws ErreurBD, IllegalArgumentException{
		if (this.adresse == null || this.codePostal == null || this.notoriete == null || this.pays == null || this.ville == null) {
			throw new IllegalArgumentException("Un des arguments n'est pas valide");
		} else {
			try {
				Calendar cal = Calendar.getInstance();
		        cal.setTime(this.date);

		        ConnexionBD.Query("INSERT INTO tournoi values(seq_tournoi.nextVal,'"+this.ville+"','"+this.pays+"','"+this.codePostal+"','"
		        				+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR))+"','"+this.notoriete
		        				+"','"+this.nom+"','"+this.adresse+"',"+arbitre+","+ this.idjeu+")");
			} catch (SQLException e) {
				ErreurBD.excSQL(e);
			}
		}
	}
	
	/** Supprime un tournoi
	 * 
	 * @throws ErreurBD lorsque une erreur lie a la base de donne est leve
	 */
	public void delete() throws ErreurBD{
        try {
        	ConnexionBD.Query("delete matchs where id_poule in (select id_poule from poule where id_tounoi = "+this.getId()+")");
        	ConnexionBD.Query("delete participer where id_poule in (select id_poule from poule where id_tounoi = "+this.getId()+")");
            ConnexionBD.Query("delete poule where id_tournoi = "+this.getId());
            ConnexionBD.Query("delete participation where id_tournoi = "+this.getId());
            ConnexionBD.Query("delete tournoi where nom = '"+this.nom+"'");
        } catch (SQLException e) {
        	ErreurBD.excSQL(e);
        }
    }

	/** Retourne tous les tournois dans la base de donnees
	 * 
	 * @return tableau des tournois : tournoi
	 * @throws ErreurBD lorsque une erreur lie a la base de donne est leve
	 */
	public static Tournoi[] getAll() throws ErreurBD {
		List<Tournoi> l = new ArrayList<Tournoi>();

		try {
			ResultSet rs = ConnexionBD.Query("select nomtournoi, datetournoi, id_jeu from tournoi order by 1");

			while(rs.next()) {
				l.add(new Tournoi(rs.getString(1),rs.getDate(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			ErreurBD.excSQL(e);
		}
		Tournoi[] t = new Tournoi[l.size()];
		t = l.toArray(t);
		return t;
	}

	/** Retourne les equipes non inscrites au tournoi
	 * 
	 * @param e
	 * @return tableau des tournois disponibles : tournoi
	 * @throws ErreurBD lorsque une erreur lie a la base de donnee est leve
	 */
	public static Tournoi[] getAvailableEquipe(Equipe e) throws ErreurBD {
		List<Tournoi> l = new ArrayList<Tournoi>();

		try {
			int id_jeu = e.getIdJeu();
			ResultSet rs = ConnexionBD.Query("select t.nomtournoi, t.datetournoi from tournoi t where id_jeu="+id_jeu+" and "+e.getID()+"not in (select distinct id_equipe from participation) and (SELECT COUNT(*) FROM Participation p WHERE p.id_tournoi = t.id_tournoi)<16");

			while(rs.next()) {
				l.add(new Tournoi(rs.getString(1),rs.getDate(2),id_jeu));
			}
		} catch (SQLException ex) {
			ErreurBD.excSQL(ex);
		}
		Tournoi[] t = new Tournoi[l.size()];
        t = l.toArray(t);
        return t;
	}
	
	/**redefinition hashCode
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(date, idjeu, nom);
	}

	/**redefinition equals
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tournoi)) {
			return false;
		}
		Tournoi other = (Tournoi) obj;
		return date.compareTo(other.date)==0 && idjeu == other.idjeu && Objects.equals(nom, other.nom);
	}
	
	/** Methode toString
	 * 
	 */
	@Override
	public String toString() {
		return this.date.toString()+" - "+this.nom;
	}	
	
}
