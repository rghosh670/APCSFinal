package stages;

import fieldElements.TextBox;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models the menu in which the users enter their names
 * 
 * @author rohitghosh
 *
 */
public class PlayerMenu extends StageType {
	private PImage playerMenu;
	private TextBox firstPlayerName;
	private TextBox secondPlayerName;

	public PlayerMenu(PApplet p) {
		super(p);
		playerMenu = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		playerMenu.resize(p.width, p.height);
		firstPlayerName = new TextBox((float) p.width / 2, (float) p.height / 3, p.width, p.height / 4, p, 1);
		secondPlayerName = new TextBox((float) p.width / 2, (float) 2 * p.height / 3, p.width, p.height / 4, p, 2);
		super.setStageImage(playerMenu);
	}

	public void draw() {
		super.draw();

		if (!firstPlayerName.done)
			firstPlayerName.draw();
		if (firstPlayerName.done)
			secondPlayerName.draw();

	}

}
