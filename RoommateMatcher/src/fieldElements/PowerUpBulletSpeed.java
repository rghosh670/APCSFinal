package fieldElements;

import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * 
 * This class models a power up which makes the player's bullets faster for a short period of time
 * 
 * @author vshirsat573
 *
 */
public class PowerUpBulletSpeed extends PowerUp {
	private PImage bsImage;

	public PowerUpBulletSpeed(PApplet p, float x, float y) {
		super(p, null, x, y, p.loadImage("stageImages" + Stage.fileSeparator + "BulletSpeed.png").width / 7,
				p.loadImage("stageImages" + Stage.fileSeparator + "BulletSpeed.png").height / 7);
		bsImage = p.loadImage("stageImages" + Stage.fileSeparator + "BulletSpeed.png");
		float width = bsImage.width / 7;
		float height = bsImage.height / 7;

		bsImage.resize((int) width, (int) height);
		super.setImage(bsImage);
	}

	public void draw() {
		super.draw();
		if (getHitbox().intersects(DrawingSurface.p1.getHitbox())) {
			DrawingSurface.p1.increaseBulletSpeed();
			disappear();
		}

		if (getHitbox().intersects(DrawingSurface.p2.getHitbox())) {
			DrawingSurface.p2.increaseBulletSpeed();
			disappear();
		}
	}
}
