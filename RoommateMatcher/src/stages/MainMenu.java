package stages;

import fieldElements.Button;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MainMenu extends StageType {

	private PImage mainMenu, logo;

	public MainMenu(PApplet p) {
		super(p);
		mainMenu = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		mainMenu.resize(p.width, p.height);
		super.setStageImage(mainMenu);

		logo = p.loadImage("Logo.png");
		logo.resize(3 * logo.width / 2, 3 * logo.height / 2);

	}

	public void draw() {
		super.draw();
		p.pushMatrix();
		p.pushStyle();
		p.imageMode(PConstants.CENTER);

		Button play = new Button(p.width / 2, p.height / 2 - p.height / 12, p.width / 4, p.height / 10, p, "Play",
				true);
		play.draw();
		Button i = new Button(p.width / 2, p.height / 2 + p.height / 7, p.width / 4, p.height / 10, p, "Instructions",
				true);
		i.draw();
		Button m = new Button(p.width / 2, p.height / 2 + 2 * p.height / 7 + p.height / 12, p.width / 4, p.height / 10,
				p, "FIND MATCH", true);
		m.draw();
		Button q = new Button(0, p.height - p.height / 10, p.width / 6, p.height / 10, p, "Quit");
		q.draw();
		p.image(logo, p.width / 2, p.height / 5);
		p.popStyle();
		p.popMatrix();

	}

}
