package fieldElements;

import character.Hitbox;
import character.Hitboxable;
import processing.core.PApplet;
import processing.core.PImage;

public class Ladder extends FieldElement implements Hitboxable {

	private Hitbox hitbox;

	public Ladder(PApplet p, PImage ladderPic, float x, float y, int width, int height) {
		super(p, ladderPic, x, y, width, height);
		hitbox = new Hitbox(this, p, (int) x, (int) y, width, height);

	}

	public void draw() {
		super.draw();
		hitbox.draw();
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

}
