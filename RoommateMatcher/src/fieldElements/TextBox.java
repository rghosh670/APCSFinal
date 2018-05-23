package fieldElements;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShapeSVG.Font;

import java.util.Scanner;

import data.User;
import enums.MenuState;
import enums.StageState;
import main.DrawingSurface;
import main.RoommateMatcher;

/**
 * This class models a textbox which the user can input text into. The data can
 * be passed to other classes.
 * 
 * @author ksrinivas788
 *
 */
public class TextBox {

	private float x, y, width, height;
	private String text, promptText;
	private PApplet p;
	private static boolean lastKey;
	public boolean done;
	private int userNumber;

	public TextBox(float x, float y, float width, float height, PApplet drawer, int user) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.p = drawer;
		this.userNumber = user;
		if (user == 1)
			text = "Please enter your name: \n\n";
		if (user == 2)
			text = "Please enter your the name of your opponent: \n\n";
		promptText = text;
	}

	public void draw() {
		p.pushStyle();
		p.pushMatrix();

		p.fill(0, 0, 0, 127.5f);

		p.rectMode(PConstants.CENTER);
		p.noStroke();
		p.rect(x, y, width, height);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);

		PFont myFont = p.createFont("BloodWaxItalic.otf", 32);
		p.fill(255);
		p.textFont(myFont);
		p.textSize(40);

		p.text(text, x, y - (int) (p.height / 24.3243243243));

		if (p.keyPressed && !lastKey) {
			lastKey = true;
			if (p.key == PConstants.BACKSPACE) {
				if (text.length() > promptText.length()) {
					text = text.substring(0, text.length() - 1);
				}
			} else if (p.key == PConstants.ENTER) {
				User u = RoommateMatcher.selectUser(text.replace(promptText, ""), userNumber);
				if (u != null) {
					done = true;
					text = promptText;
					if (userNumber == 1) {
						return;
					}
					if (userNumber == 2) {
						DrawingSurface.background.setMenu(MenuState.MAIN_MENU);
						DrawingSurface.background.setIsStage(false);
					}
				} else
					text = promptText;
			} else if (p.key != PConstants.CONTROL && p.key != PConstants.ALT && p.key != PConstants.SHIFT
					&& p.key >= 32 && p.key <= 122) {
				text += (p.key + "").toUpperCase();
			}
		}

		p.popStyle();
		p.popMatrix();
	}

	public static void setLastKey(boolean lk) {
		lastKey = lk;
	}
}
