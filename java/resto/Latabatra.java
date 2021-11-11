package resto;

import java.sql.Connection;
import java.sql.ResultSet;

public class Latabatra {
	int id;
	int num;
	int restaurantID;
	Boolean lounge;
	
	public Latabatra () {
		
	}
	public Latabatra (int id, int num, int restaurantID, Boolean lounge) {
		this.id = id;
		this.num = num;
		this.restaurantID = restaurantID;
		this.lounge = lounge;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public Boolean getLounge() {
		return lounge;
	}
	public void setLounge(Boolean lounge) {
		this.lounge = lounge;
	}
	
	public static Latabatra getLatabatraByID (int id, Connection con) {
		try {
			ResultSet res = con.createStatement().executeQuery("select * from latabatra where id="+id);
			if (res.next()) {
				int ID = res.getInt("id");
				int NUM = res.getInt("num");
				int RESTAURANTID = res.getInt("restaurantID");
				Boolean LOUNGE = res.getInt("lounge") == 0 ? false : true;
				return new Latabatra(ID, NUM, RESTAURANTID, LOUNGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}