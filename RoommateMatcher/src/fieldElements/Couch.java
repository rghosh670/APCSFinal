package fieldElements;

import character.Hitbox;
import character.Hitboxable;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class draws hitboxes around the couch in the living room map
 * 
 * @author rohitghosh
 *
 */
public class Couch extends FieldElement implements Hitboxable {

	private Hitbox mainSection, side1, side2;

	public Couch(PApplet p, PImage elementImage, float x, float y, int width, int height) {
		super(p, elementImage, x, y, width, height);

		side1 = new Hitbox(this, p, (int) x, (int) y + 10, width / 10, height);

		mainSection = new Hitbox(this, p, (int) x + width / 10, (int) y + height / 2 + 20, width - width / 5 - 20,
				height / 2);
		side2 = new Hitbox(this, p, (int) x + width - width / 10 - 20, (int) y + 10, width / 10, height);
	}

	public void draw() {
		super.draw();

		side1.draw();
		mainSection.draw();

		side2.draw();
	}

	public Hitbox[] getHitboxes() {
		return new Hitbox[] { side1, mainSection, side2 };
	}

}
