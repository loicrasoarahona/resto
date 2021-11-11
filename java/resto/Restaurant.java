package resto;

import java.sql.Connection;
import java.sql.ResultSet;

public class Restaurant {
	int id;
	String nom;
	
	public Restaurant () {
		
	}
	public Restaurant (int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public static Restaurant getRestaurantByID (int id, Connection con) {
		try {
			ResultSet res = con.createStatement().executeQuery("select * from restaurant where id="+id);
			if (res.next()) {
				int ID = res.getInt("id");
				String nom = res.getString("nom");
				
				return new Restaurant(ID, nom);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
