package main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JFrame;

import data.User;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import stages.Stage;

/**
 * This class runs all the components of the program
 * 
 * @author ksrinivas788
 *
 */
public class RoommateMatcher {

	static User u;
	static DrawingSurface drawing;
	static ArrayList<User> users = new ArrayList<User>();

	public static void main(String args[]) {

		Scanner in = null;
		try {
			FileReader reader = new FileReader("users" + Stage.fileSeparator + "users.txt");
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				User u = read("users" + Stage.fileSeparator + line.split(":")[0] + ".txt");
				users.add(u);
			}
		} catch (FileNotFoundException e) {

		} finally {
			if (in != null)
				in.close();
		}

		drawing = new DrawingSurface();
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		// window.setSize(500, 325); //////////////////////////////
		// window.setSize(500, 325); //////////////////////////////
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if (DrawingSurface.user1 != null)
					DrawingSurface.user1.writeToFile();
				if (DrawingSurface.user2 != null)
					DrawingSurface.user2.writeToFile();
				System.exit(0);
			}
		});
		window.setResizable(true);

		window.setVisible(true);

	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static User getUser() {
		return u;
	}

	public static User selectUser(String name, int userNum) {
		Scanner in = null, input = null;
		try {
			FileReader reader = new FileReader("users" + Stage.fileSeparator + "users.txt");
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.toLowerCase().contains(name.toLowerCase())) {
					u = read("users" + Stage.fileSeparator + line.split(":")[0] + ".txt");
					if (userNum == 1) drawing.setUser1(u);
					if (userNum == 2) drawing.setUser2(u);
					break;
				}
			}
		} catch (FileNotFoundException e) {

		} finally {
			if (in != null)
				in.close();
		}
		return u;
	}

	public static User read(String filename) {
		Scanner in = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(filename);
			in = new Scanner(reader);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
		} catch (FileNotFoundException e) {

		} finally {
			if (in != null)
				in.close();
		}
		if (lines.size() > 0) {
			String name = "";
			String id = "0";
			try {
				id = filename.split(".txt")[0].toString().substring(6);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			ArrayList<String> images = new ArrayList<String>();
			ArrayList<String> weapons = new ArrayList<String>();
			HashMap<String, Double> values = new HashMap<String, Double>();
			for (String line : lines) {
				String[] parts = line.split(": ");
				for (int i = 0; i < parts.length; i += 2) {
					String key = parts[i];
					if (key.equals("name"))
						name = parts[i + 1];
					else if (key.equals("images"))
						for (String image : parts[i + 1].split(", "))
							images.add(image);
					else if (key.equals("weapons")) {
						for (String weapon : parts[i + 1].split(", "))
							weapons.add(weapon);
					} else {
						values.put(key, Double.parseDouble(parts[i + 1]));
					}
				}
			}
			return new User(name, id, images, weapons, values);
		}
		return null;
	}
}