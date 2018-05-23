package stages;

import java.util.Map.Entry;

import data.User;
import fieldElements.Button;
import main.RoommateMatcher;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models the screen that displays the closest match to the current user
 * 
 * @author rohitghosh
 *
 */
public class MatchFinder extends StageType {

	private PImage matchFinder;
	private User match;
	private PApplet p;
	private boolean foundMatch;
	private String text;

	public MatchFinder(PApplet p) {
		super(p);
		this.p = p;
		matchFinder = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		matchFinder.resize(p.width, p.height);
		super.setStageImage(matchFinder);
		text = "";
	}

	public void setup(User u) {
		match = u.findBestMatch();
		for (Entry<String, Double> e : match.getValues().entrySet()) {
			if (!e.getKey().equals("attack time") && !text.contains(e.getKey()))
				text += e.getKey() + ": " + e.getValue() + "\n";
		}
	}

	public void draw() {
		super.draw();
		Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
		menu.draw();
		p.pushMatrix();
		p.pushStyle();
		p.fill(255);
		p.textAlign(p.CENTER, p.CENTER);
		p.textSize(p.width / 30);
		p.text(match.getName() + "\n\n", p.width / 2, p.height / 2);
		p.textSize(p.width / 40);
		p.text(text, p.width / 2, 2 * p.height / 3 + p.width / 10);
		p.popStyle();
		p.popMatrix();
	}

}
