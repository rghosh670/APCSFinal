package stages;

import fieldElements.Button;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * 
 * This class models the menu in which the users select which stage they wish to
 * play in
 * 
 * @author rohitghosh
 *
 */
public class StageMenu extends StageType {

	private PImage stageMenu, library, livingRoom, outdoorField;
	private Button title;

	public StageMenu(PApplet p) {
		super(p);
		stageMenu = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		stageMenu.resize(p.width, p.height);
		library = p.loadImage("stageImages" + Stage.fileSeparator + "Library.jpg");
		library.resize(p.width / 5, p.height / 5);
		livingRoom = p.loadImage("stageImages" + Stage.fileSeparator + "LivingRoom.jpg");
		livingRoom.resize(p.width / 5, p.height / 5);
		outdoorField = p.loadImage("stageImages" + Stage.fileSeparator + "OutdoorField.jpg");
		outdoorField.resize(p.width / 5, p.height / 5);

		title = new Button(p.width / 2, p.height / 4, 0, 0, p, "Stage Select", true, true, false);
		super.setStageImage(stageMenu);
	}

	public void draw() {
		super.draw();
		p.pushMatrix();
		p.pushStyle();

		title.draw();

		Button of = new Button(p.width / 5, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p, "Outdoor Field",
				true, true);
		of.draw();

		Button of2 = new Button(p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30,
				p.width / 5, p.height / 5, p, "Outdoor Field", false, false, true);
		of2.draw();

		p.image(outdoorField, p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30);

		Button l = new Button(p.width / 2, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p, "Library", true,
				true);
		l.draw();

		Button l2 = new Button(p.width / 2 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30, p.width / 5,
				p.height / 5, p, "Library", false, false, true);
		l2.draw();
		p.image(library, p.width / 2 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30);

		Button lr = new Button(4 * p.width / 5, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p,
				"Living Room", true, true);
		lr.draw();

		Button lr2 = new Button(4 * p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30,
				p.width / 5, p.height / 5, p, "Living Room", false, false, true);
		lr2.draw();

		p.image(livingRoom, 4 * p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30);

		Button m = new Button(0, p.height - p.height / 10, p.width / 6, p.height / 10, p, "Menu");
		m.draw();

		p.popStyle();
		p.popMatrix();
	}

}
