package resto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class Produit {
	int id;
	String nom;
	String description;
	Boolean fabriquable;
	int categorieID;
	String categorieNom;
	double prix;
	double prixLounge;
	
	public double getPrix() {
		return prix;
	}
	public double getPrixLounge() {
		return prixLounge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getFabriquable() {
		return fabriquable;
	}
	public void setFabriquable(Boolean fabriquable) {
		this.fabriquable = fabriquable;
	}
	public int getCategorieID() {
		return categorieID;
	}
	public void setCategorieID(int categorieID) {
		this.categorieID = categorieID;
	}
	public String getCategorieNom() {
		return categorieNom;
	}
	public void setCategorieNom(String categorieNom) {
		this.categorieNom = categorieNom;
	}
	
	public Produit () {
		
	}
	
	public Produit (int id, String nom, String description, Boolean fabriquable, int categorieID, String categorieNom, double prix, double prixLounge) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.fabriquable = fabriquable;
		this.categorieID = categorieID;
		this.categorieNom = categorieNom;
		this.prix = prix;
		this.prixLounge = prixLounge;
	}
	
	public static Produit getProduitByID (int id, Connection con) {
		try {
			Statement stmt = con.createStatement();
			ResultSet res = con.createStatement().executeQuery("select * from produit_categorie where id="+id);
			if (res.next()) {
				int ID = res.getInt("id");
				String NOM = res.getString("nom");
				String DESCRIPTION = res.getString("description");
				Boolean FABRIQUABLE = res.getInt("fabriquable") == 1 ? true : false;
				int CATEGORIEID = res.getInt("categorieID");
				String CATEGORIENOM = res.getString("categorie");
				double PRIX = res.getDouble("prix");
				double PRIXLOUNGE = res.getDouble("prix_lounge");
				return new Produit(ID, NOM, DESCRIPTION, FABRIQUABLE, CATEGORIEID, CATEGORIENOM, PRIX, PRIXLOUNGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object[] getProduitParCategorie (int categorie, Connection con) {
		String nomCategorie = "";
		try {
			Vector<Produit> retour = new Vector<Produit>();
			Statement stmt = con.createStatement();
			ResultSet res = con.createStatement().executeQuery("select * from produit_categorie where categorieID="+categorie);
			while (res.next()) {
				int ID = res.getInt("id");
				String NOM = res.getString("nom");
				String DESCRIPTION = res.getString("description");
				Boolean FABRIQUABLE = res.getInt("fabriquable") == 1 ? true : false;
				int CATEGORIEID = res.getInt("categorieID");
				String CATEGORIENOM = res.getString("categorie");
				nomCategorie = CATEGORIENOM;double PRIX = res.getDouble("prix");
				double PRIXLOUNGE = res.getDouble("prix_lounge");
				retour.add(new Produit(ID, NOM, DESCRIPTION, FABRIQUABLE, CATEGORIEID, CATEGORIENOM, PRIX, PRIXLOUNGE));
			}
			return new Object[] {nomCategorie, retour};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static Vector<Object[]> getAllProduitParCategorie (Connection con) {
		Vector<Object[]> retour = new Vector<Object[]>();
		
		try {
			ResultSet res = con.createStatement().executeQuery("select * from categorie");
			while (res.next()) {
				Object[] element = getProduitParCategorie(res.getInt("id"), con);
				if (element != null) {
					retour.add(element);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}
}
