package fieldElements;

import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * 
 * This class models a power up which makes the player shoot at a faster rate for a period of time
 * 
 * @author ksrinivas788
 *
 */
public class PowerUpFireRate extends PowerUp {
	private PImage frImage;

	public PowerUpFireRate(PApplet p, float x, float y) {
		super(p, null, x, y, p.loadImage("stageImages" + Stage.fileSeparator + "FireRate.png").width / 10,
				p.loadImage("stageImages" + Stage.fileSeparator + "FireRate.png").height / 10);
		frImage = p.loadImage("stageImages" + Stage.fileSeparator + "FireRate.png");
		float width = frImage.width / 10;
		float height = frImage.height / 10;

		frImage.resize((int) width, (int) height);
		super.setImage(frImage);
	}

	public void draw() {
		super.draw();
		if (getHitbox().intersects(DrawingSurface.p1.getHitbox())) {
			DrawingSurface.p1.increaseFireRate();
			disappear();
		}

		if (getHitbox().intersects(DrawingSurface.p2.getHitbox())) {
			DrawingSurface.p2.increaseFireRate();
			disappear();
		}
	}
}
