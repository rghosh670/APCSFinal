package fieldElements;

import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

public class PowerUpHealth extends PowerUp {

	private PImage healthImage;

	public PowerUpHealth(PApplet p, float x, float y) {
		super(p, null, x, y, 70, 70);
		float width = 70;
		float height = 70;
		healthImage = p.loadImage("stageImages" + Stage.fileSeparator + "Health.png");
		healthImage.resize((int) width, (int) height);
		super.setImage(healthImage);
	}

	public void draw() {
		super.draw();
		if (getHitbox().intersects(DrawingSurface.p1.getHitbox())) {
			DrawingSurface.p1.addHealth();
			disappear();
		}

		if (getHitbox().intersects(DrawingSurface.p2.getHitbox())) {
			DrawingSurface.p2.addHealth();
			disappear();
		}
	}

}
