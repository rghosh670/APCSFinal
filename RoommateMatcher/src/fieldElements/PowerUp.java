package fieldElements;

import character.Hitbox;
import character.Hitboxable;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models a power up which falls down randomly 
 * 
 * @author rohitghosh
 *
 */
public class PowerUp implements Hitboxable {
	private PApplet p;
	private PImage powerUpImage;
	private boolean disappeared;
	protected Hitbox hitbox;
	protected float x, y, width, height;

	public PowerUp(PApplet p, PImage image, float x, float y, float width, float height) {
		this.p = p;
		this.powerUpImage = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hitbox = new Hitbox(this, p, (int) x, (int) y, (int) width, (int) height);
		disappeared = true;
	}

	private void fall() {
		y += 5;
	}

	public void draw() {
		p.image(powerUpImage, x, y);
		hitbox.draw();
		hitbox.updateCoordinates(x, y);
		if (y + height < p.height - DrawingSurface.background.getStageType().getGround())
			fall();
	}

	public void setImage(PImage image) {
		this.powerUpImage = image;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void disappear() {
		x = -width;
		disappeared = true;
	}

	public boolean getDisappeared() {
		return disappeared;
	}

	public void setDisappeared(boolean disappeared) {
		this.disappeared = disappeared;

		if (x < 0 || x + width > p.width)
			disappear();

	}
}
