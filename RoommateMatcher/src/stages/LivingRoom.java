package stages;

import fieldElements.Button;
import fieldElements.Couch;
import fieldElements.Ladder;
import fieldElements.LivingRoomLadder;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models the Living Room stage
 * 
 * @author vshirsat573
 *
 */
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

		couch = new Couch(p, null, (int) (p.width / 4.51612903226), (int) (p.height / 1.83673469388),
				(int) (p.width / 1.65680473373), (int) (p.height / 4));
		l1 = new LivingRoomLadder(p, (int) (p.width / 6.66666666667), (int) (p.height / 1.83673469388),
				(int) (p.width / 14), (int) (p.height / 3), false);
		l2 = new LivingRoomLadder(p, (int) (p.width / 1.21739130435), (int) (p.height / 1.83673469388),
				(int) (p.width / 14), (int) (p.height / 3), true);
	}

	public void draw() {
		super.draw();
		if (!DrawingSurface.gameOver) {
			Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
			menu.draw();
		}
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
