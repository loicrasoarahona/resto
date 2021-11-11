package resto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class Categorie {
	int id;
	String nom;
	int mereID;

	public Categorie() {

	}

	public Categorie(int id, String nom, int mereID) {
		this.id = id;
		this.nom = nom;
		this.mereID = mereID;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMereID() {
		return mereID;
	}

	public void setMereID(int mereID) {
		this.mereID = mereID;
	}

	public static Categorie getCategorieByID(int id, Connection con) {
		try {
			ResultSet res = con.createStatement().executeQuery("select * from categorie where id=" + id);
			while (res.next()) {
				int ID = res.getInt("id");
				int MERE = res.getInt("mere");
				String NOM = res.getString("nom");

				return new Categorie(ID, NOM, MERE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Vector<Categorie> getCategorie(int id, String nom, int mereID, Connection con) {
		String requete = "select * from categorie where 1<2";
		if (id > 0) {
			requete += " and id=" + id;
		}
		if (mereID > 0) {
			requete += " and mere=" + mereID;
		}
		if (nom != null && !nom.isEmpty()) {
			requete += " and nom='" + nom + "'";
		}
		Vector<Categorie> retour = new Vector<Categorie>();
		try {
			ResultSet res = con.createStatement().executeQuery(requete);
			while (res.next()) {
				int ID = res.getInt("id");
				int MERE = res.getInt("mere");
				String NOM = res.getString("nom");

				retour.add(new Categorie(ID, NOM, MERE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}
}
