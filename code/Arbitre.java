package code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import javax.sql.DataSource;

public class Arbitre {
	private String login;
	
	public Arbitre(String log) {
		this.login = log;
	}
	
	public Match[] getMatch() throws ErreurBD {
		ArrayList<Match> result = new ArrayList<Match>();
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select e1.nom,e2.nom,heuredebut,heurefin from matchs, equipe e1, equipe e2, poule, tournoi, arbitre "
					+ "where e1.id_equipe=matchs.id_equipe and e2.id_equipe=matchs.id_equipe1 "
					+ "and matchs.id_poule=poule.id_poule and poule.id_tournoi=tournoi.id_tournoi "
					+ "and tournoi.id_arbitre=arbitre.id_arbitre and arbitre.login='"+this.login+"'");
			
			while(rs.next()) {
				result.add(new Match(new Equipe(rs.getString(1)),new Equipe(rs.getString(2)),rs.getTimestamp(3),rs.getTimestamp(4)));
			}
			
			connx.close();
			
			Match[] t = new Match[result.size()];
			t = result.toArray(t);
			return t;
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requ�te a la bd");
		}
		
	}
	
	public static String[] getAll() throws ErreurBD {
		ArrayList<String> result = new ArrayList<String>();
		try {
			DataSource bd = new ConnexionBD();

			Connection connx = bd.getConnection();

			Statement st = connx.createStatement();

			ResultSet rs = st.executeQuery("select login from arbitre order by 1");
			
			while(rs.next()) {
				result.add(rs.getString(1));
			}
			
			connx.close();
			
			String[] t = new String[result.size()];
			t = result.toArray(t);
			return t;
		} catch (SQLException e) {
			throw new ErreurBD("Erreur de requ�te a la bd");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Arbitre)) {
			return false;
		}
		Arbitre other = (Arbitre) obj;
		return Objects.equals(login, other.login);
	}
}
