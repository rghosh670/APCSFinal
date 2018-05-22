package stages;

import fieldElements.TextBox;
import processing.core.PApplet;
import processing.core.PImage;

public class PlayerMenu extends StageType {
	private PImage playerMenu;
	private TextBox t;

	public PlayerMenu(PApplet p) {
		super(p);
		playerMenu = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		playerMenu.resize(p.width, p.height);
		t = new TextBox((float) p.width / 2, (float) p.height / 2, p.width, p.height / 4, p);
		super.setStageImage(playerMenu);
	}

	public void draw() {
		super.draw();
		t.draw();
	}

}
