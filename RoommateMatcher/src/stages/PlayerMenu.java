package stages;

import fieldElements.TextBox;
import processing.core.PApplet;
import processing.core.PImage;

public class PlayerMenu extends StageType {
	private PImage playerMenu;
	private TextBox firstPlayerName;
	private TextBox secondPlayerName;

	public PlayerMenu(PApplet p) {
		super(p);
		playerMenu = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		playerMenu.resize(p.width, p.height);
		firstPlayerName = new TextBox((float) p.width / 2, (float) p.height / 2, p.width, p.height / 4, p);

		super.setStageImage(playerMenu);
	}

	public void draw() {
		super.draw();

		firstPlayerName.draw();

	}

}
