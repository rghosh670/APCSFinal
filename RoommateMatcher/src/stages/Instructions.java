package stages;

import fieldElements.Button;
import processing.core.PApplet;
import processing.core.PImage;

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
		Button menu = new Button((int) (p.width / 2.8), 0, p, "Menu");
		// p.textSize(p.width / 50);
		// p.text("Welcome to the Roommate Matcher!\n\n"
		// + "In this game you can speed through the process of finding a roommate by
		// playing a game! "
		// + "Instead of a boring questionnaire where you craft responses to look like
		// an angel, "
		// + "let out your inner gamer and show your true qualities off! With every step
		// of the game, "
		// + "you fight with another character, and will be matched using our current
		// databases to another character who fits your qualities!\n\n"
		// + "Click the MENU button to leave the instructions screen!"
		// + "Pick the arena you would like to fight in\n" + "Pick the user you would
		// like to fight with\n"
		// + "---------------------------------------------------\n" + "Controls:\n"
		// + "Player 1 controls: Arrowkeys to move, down arrow to shoot and shift to use
		// melee weapon\n"
		// + "Player 2 controls: WAD to move, S to shoot and F to use melee weapon",
		// p.width / 50,

		// p.text("Welcome to the Roommate Matcher!\n\n"
		// + "In this game you can speed through the process of finding a roommate by
		// playing a game! + \n"
		// + "Instead of a boring questionnaire where you craft responses to look like
		// an angel, "
		// + "let out your inner gamer and show your true qualities off! With every step
		// of the game, "
		// + "let out your inner gamer and show your true qualities off! With every
		// step\n"
		// + " of the game, you fight with another character, and will be matched using
		// our current\n"
		// + " databases to another character who fits your qualities!\n\n", (float)
		// (p.height / 8.125),
		// p.width, p.height);
		//
		// p.text("Click the MENU button to leave the instructions screen!" + "Pick the
		// arena you would like to fight in\n"
		// + "Pick the user you would like to fight with\n"
		// + "---------------------------------------------------\n" + "Controls:\n"
		// + "Player 1 controls: Arrowkeys to move, down arrow to shoot and shift to use
		// melee weapon\n"
		// + "Player 2 controls: WAD to move, S to shoot and F to use melee weapon",
		// p.width / 50, 300);

		float newLine = p.height / 23;
		float first = p.height / 20 + menu.getHeight();

		p.text("Welcome to the Roommate Matcher!", 0, first);
		p.text("Controls: ", 0, first + newLine);

		p.fill(0);
		p.text("Player One: ", 0, first + 3 * newLine);
		p.fill(255);
		p.text("Arrow Keys     ->      Move Around", 0, first + 4 * newLine);
		p.text("Period            ->      Shoot", 0, first + 5 * newLine);
		p.text("Comma            ->      Toggle Gun", 0, first + 6 * newLine);
		p.text("M                  ->      Toggle Blade", 0, first + 7 * newLine);

		p.fill(0);
		p.text("Player Two: ", 0, first + 9 * newLine);
		p.fill(255);
		p.text("WASD              ->      Move Around", 0, first + 10 * newLine);
		p.text("F                   ->      Shoot", 0, first + 11 * newLine);
		p.text("Q                   ->     Toggle Gun", 0, first + 12 * newLine);
		p.text("E                   ->     Toggle Blade", 0, first + 13 * newLine);
		
		p.text("Gun Power Up increases fire rate", 0, first  + 16 * newLine);
		p.text("Bullet Power Up increases bullet speed", 0, first  + 17 * newLine);
		p.text("Health Power Up increases health", 0, first  + 18 * newLine);

		menu.draw();
		p.popStyle();
		p.popMatrix();
	}

}
