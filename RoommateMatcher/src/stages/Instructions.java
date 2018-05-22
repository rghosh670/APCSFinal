package stages;

import fieldElements.Button;
import processing.core.PApplet;
import processing.core.PImage;

public class Instructions extends StageType {
	private PImage instructions;

	public Instructions(PApplet p) {
		super(p);
		instructions = p.loadImage("stageImages" + Stage.fileSeparator + "Instructions.jpg");
		instructions.resize(p.width, p.height);
		super.setStageImage(instructions);

	}

	public void draw() {
		super.draw();
		p.image(instructions, 0, 0);
		Button menu = new Button((int) (p.width / 2.8), 0, p, "Menu");
		p.textSize(p.width / 50);		
		p.text("Welcome to the Roommate Matcher!\n\n"
				+ "In this game you can speed through the process of finding a roommate by playing a game! "
				+ "Instead of a boring questionnaire where you craft responses to look like an angel, "
				+ "let out your inner gamer and show your true qualities off! With every step of the game, "
				+ "you fight with another character, and will be matched using our current databases to another character who fits your qualities!\n\n"
				+ "Click the MENU button to leave the instructions screen!"
				+ "Pick the arena you would like to fight in\n"
				+ "Pick the user you would like to fight with\n"
				+ "---------------------------------------------------\n"
				+ "Controls:\n"
				+ "Player 1 controls: Arrowkeys to move, down arrow to shoot and shift to use melee weapon\n"
				+ "Player 2 controls: WAD to move, S to shoot and F to use melee weapon", p.width/50, (float) (p.height / 8.125), p.width, p.height);
		menu.draw();
	}

}
