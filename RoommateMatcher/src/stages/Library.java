package stages;

import fieldElements.Button;
import fieldElements.Ladder;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * 
 * This class models the library stage
 * 
 * @author ksrinivas788
 *
 */
public class Library extends StageType {

	private PImage library;
	private Ladder[] ladders;

	public Library(PApplet p) {
		super(p);
		library = p.loadImage("stageImages" + Stage.fileSeparator + "Library.jpg");
		library.resize(p.width, p.height);
		ladders = new Ladder[5];

		ladders[0] = new Ladder(p, null, (int) (p.width / 14.4), (int) (p.height / 1.95652173913),
				(int) (p.width / 14.4), (int) (p.height / 3.21428571429));
		ladders[1] = new Ladder(p, null, (int) (p.width / 4.5), (int) (p.height / 2.95081967213),
				(int) (p.width / 11.0769230769), (int) (p.height / 1.85567010309));
		ladders[2] = new Ladder(p, null, (int) (p.width / 2.34146341463), (int) (p.height / 1.60714285714),
				(int) (p.width / 24), (int) (p.height / 4.73684210526));

		ladders[3] = new Ladder(p, null, (int) (p.width / 1.30909090909), (int) (p.height / 2.19512195122),
				(int) (p.width / 16), (int) (p.height / 2.72727272727));

		ladders[4] = new Ladder(p, null, (int) (p.width / 1.05882352941), (int) (p.height / 1.45161290323),
				(int) (p.width / 24), (int) (p.height / 6.92307692308));

		super.setGround(50);
		super.setStageImage(library);
	}

	public void draw() {
		super.draw();
		if (!DrawingSurface.gameOver) {
			Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
			menu.draw();
		}

		for (Ladder l : ladders)
			l.draw();
	}

	public Ladder[] getLadders() {
		return ladders;
	}
}
