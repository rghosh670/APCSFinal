package stages;

import fieldElements.Button;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models the screen which displays the instructions
 * 
 * @author vshirsat573
 *
 */
public class Instructions extends StageType {
	private PImage instructions;

	public Instructions(PApplet p) {
		super(p);
		instructions = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		instructions.resize(p.width, p.height);
		super.setStageImage(instructions);

	}

	public void draw() {
		super.draw();
		p.pushMatrix();
		p.pushStyle();
		p.stroke(255);
		p.fill(255);
		p.textSize(p.height/30);
		p.translate(p.width/2, 0);
		p.textAlign(p.CENTER, p.CENTER);
		Button menu = new Button((int) (-p.width/8), 0, p, "Menu");

		float newLine = p.height / 30;
		float first = p.height / 20 + menu.getHeight();

		p.text("Welcome to the Roommate Matcher!", 0, first);
		p.text("Controls: ", 0, first + newLine);

		p.fill(0);
		p.text("Player One: ", 0, first + 3 * newLine);
		p.fill(255);
		p.textAlign(p.LEFT, p.CENTER);
		p.text("Arrow Keys    -    Move Around", 0, first + 4 * newLine);
		p.text("Period    -    Shoot", 0, first + 5 * newLine);
		p.text("Comma    -    Toggle Gun", 0, first + 6 * newLine);
		p.text("M    -    Toggle Blade", 0, first + 7 * newLine);

		p.textAlign(p.CENTER, p.CENTER);
		p.fill(0);
		p.text("Player Two: ", 0, first + 9 * newLine);
		p.fill(255);
		p.textAlign(p.LEFT, p.CENTER);
		p.text("WASD    -    Move Around", 0, first + 10 * newLine);
		p.text("F    -    Shoot", 0, first + 11 * newLine);
		p.text("Q    -    Toggle Gun", 0, first + 12 * newLine);
		p.text("E    -    Toggle Blade", 0, first + 13 * newLine);
		
		p.text("Gun Power Up increases fire rate", 0, first  + 16 * newLine);
		p.text("Bullet Power Up increases bullet speed", 0, first  + 17 * newLine);
		p.text("Health Power Up increases health", 0, first  + 18 * newLine);
		
		p.text("Rifle fires faster and does 2 damage", 0, first + 20*newLine);
		p.text("Shotgun fires slower and does 10 damage", 0, first + 21*newLine);
		p.text("Knife does 10 damage and Sword does 20 damage", 0, first + 22*newLine);

		menu.draw();
		p.popStyle();
		p.popMatrix();
	}

}
