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
import main.Main;

/**
 * This class models a textbox which the user can input text into. The data can
 * be passed to other classes.
 * 
 * @author ksrinivas788
 *
 */
public class TextBox {

	private float x, y, width, height;
	private String text;
	private PApplet p;
	private static boolean lastKey, foundUser;

	public TextBox(float x, float y, float width, float height, PApplet drawer) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.p = drawer;
		text = "Please enter your name: \n\n";
	}

	public void draw() {
		p.pushStyle();

		p.fill(0, 0, 0, 127.5f);

		p.rectMode(PConstants.CENTER);
		p.noStroke();
		p.rect(x, y, width, height);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);

		PFont myFont = p.createFont("BloodWaxItalic.otf", 32);
		p.fill(255);
		p.textFont(myFont);
		p.textSize(40);

		p.text(text, x, y - 37);

		if (p.keyPressed && !lastKey) {
			lastKey = true;
			if (p.key == PConstants.BACKSPACE) {
				if (text.length() > "Please enter your name: \n\n".length()) {
					text = text.substring(0, text.length() - 1);
				}
			} else if (p.key == PConstants.ENTER) {
				User u = Main.selectUser(text.replace("Please enter your name: \n\n", ""));
				System.out.println(u);
				if (u != null) {
					DrawingSurface.background.setMenu(MenuState.MAIN_MENU);
					DrawingSurface.background.setIsStage(false);
				} else
					text = "Please enter your name: \n\n";
			} else if (p.key != PConstants.CONTROL && p.key != PConstants.ALT && p.key != PConstants.SHIFT
					&& p.key >= 32 && p.key <= 122) {
				text += (p.key + "").toUpperCase();
			}
		}

		p.popStyle();
	}

	public static void setLastKey(boolean lk) {
		lastKey = lk;
	}
}
