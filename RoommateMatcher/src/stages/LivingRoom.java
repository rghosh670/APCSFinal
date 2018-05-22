
<<<<<<< HEAD
package stages;

import fieldElements.Button;
import fieldElements.Couch;
import fieldElements.Ladder;
import fieldElements.LivingRoomLadder;
import processing.core.PApplet;
import processing.core.PImage;

public class LivingRoom extends StageType {

	private PImage livingRoom;
	private LivingRoomLadder l1, l2;
	private Couch couch;

	public LivingRoom(PApplet p) {
		super(p);
		livingRoom = p.loadImage("stageImages" + Stage.fileSeparator + "LivingRoom.jpg");
		livingRoom.resize(p.width, p.height);
		super.setGround(60);
		super.setStageImage(livingRoom);
		couch = new Couch(p, null, 310, 490, 845, 225);
		l1 = new LivingRoomLadder(p, 210, 490, 100, 300, false);
		l2 = new LivingRoomLadder(p, 1150, 490, 100, 300, true);
	}

	public void draw() {
		super.draw();
		Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
		menu.draw();
		couch.draw();
		l1.draw();
		l2.draw();
	}

	public Ladder[] getLadders() {
		return new Ladder[] { l1, l2 };
	}

	public Couch getCouch() {
		return couch;
	}
}
=======
import fieldElements.Button;
import fieldElements.Couch;
import fieldElements.Ladder;
import fieldElements.LivingRoomLadder;
import processing.core.PApplet;
import processing.core.PImage;

public class LivingRoom extends StageType {

	private PImage livingRoom;
	private LivingRoomLadder l1, l2;
	private Couch couch;

	public LivingRoom(PApplet p) {
		super(p);
		livingRoom = p.loadImage("stageImages" + Stage.fileSeparator + "LivingRoom.jpg");
		livingRoom.resize(p.width, p.height);
		super.setGround(60);
		super.setStageImage(livingRoom);
		couch = new Couch(p, null, 310, 490, 845, 225);
		l1 = new LivingRoomLadder(p, 210, 490, 100, 300, false);
		l2 = new LivingRoomLadder(p, 1150, 490, 100, 300, true);
	}

	public void draw() {
		super.draw();
		Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
		menu.draw();
		couch.draw();
		l1.draw();
		l2.draw();
	}

	public Ladder[] getLadders() {
		return new Ladder[] { l1, l2 };
	}

	public Couch getCouch() {
		return couch;
	}
}
>>>>>>> parent of ce9b71e... Bullets do damage now
