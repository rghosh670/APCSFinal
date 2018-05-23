package data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import main.RoommateMatcher;
import stages.Stage;

/**
 * This class models a user and stores their characteristics 
 * 
 * @author ksrinivas788
 *
 */
public class User {

	private String name;
	private ArrayList<String> images, weapons;
	private HashMap<String, Double> values;
	private String id;

	public User(String name, String id) {
		this.name = name;
		this.values = new HashMap<String, Double>();
	}
	
	public User(String name, String id, ArrayList<String> images, ArrayList<String> weapons, HashMap<String, Double> values) {
		this.name = name;
		this.id = id;
		this.images = images;
		this.weapons = weapons;
		this.values = values;
	}
	
	public String toString() {
		return "name: " + name + "\n" + "images: " + images.toString().replace("[", "").replace("]", "") + "\n" + "weapons: " + weapons.toString().replace("[", "").replace("]", "") + "\n" + "values: " + values.toString();
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<String> getImages() {
		return this.images;
	}
	
	public ArrayList<String> getWeapons() {
		return this.weapons;
	}
	
	public void changeDefense(double change) {
		values.put("defense", values.get("defense")+change);
	}
	
	public void changeOffense(double change) {
		values.put("offense", values.get("offense")+change);
	}
	
	public String getId() {
		return this.id;
//		return String.format("%03d", this.id);
	}
	
	public HashMap<String, Double> getValues() {
		return values;
	}
	
	public double compare(User other) {
		double sum = 0;
		for (String key : values.keySet()) {
			sum += Math.abs(values.get(key) - other.values.get(key));
		}
		return (double) (10 - sum / values.keySet().size());
	}
	
	public User findBestMatch() {
		ArrayList<User> users = RoommateMatcher.getUsers();
		User best = users.get(0);
		for (User u : users) {
//			System.out.println(u);
//			System.out.println("-------");
			if (!u.getName().equals(this.getName()) && this.compare(u) > u.compare(best)) best = u;
		}
		return best;
	}
	
	public void writeToFile() {
		FileWriter writer = null;
		try {
			writer = new FileWriter("users" + Stage.fileSeparator + getId() + ".txt");
			writer.write(toString().split("values")[0]);
			for (Entry<String, Double> entry : getValues().entrySet()) {
				writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("written by User");
	}

}
