package character;

import blades.Blade;
import fieldElements.Tree;
import firearms.Bullet;
import processing.core.PApplet;

/**
 * This creates a box around each character so that when a weapon hits that area
 * they lose health
 * 
 * @author rghosh293
 *
 */
public class Hitbox {
	private Hitboxable object;
	private PApplet p;
	public float x, y, width, height, angle;

	public Hitbox(Hitboxable object, PApplet p) {
		this.object = object;
		this.p = p;

		if (object instanceof Player) {
			object = ((Player) object);
			width = ((Player) object).getWidth();
			height = ((Player) object).getHeight();
			angle = 0;
		} else if (object instanceof Bullet) {
			object = ((Bullet) object);
			width = ((Bullet) object).getWidth();
			height = ((Bullet) object).getHeight();
			angle = 0;
		} else if (object instanceof Tree) {
			object = ((Tree) object);
			width = ((Tree) object).getWidth();
			height = ((Tree) object).getHeight();
		}
	}

	public Hitbox(Hitboxable object, PApplet p, int x, int y, int width, int height) {
		this.object = object;
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw() {
		updateCoordinates();
//		p.stroke(255, 0, 0);
//		p.rotate(angle);
//		p.rect(x, y, width, height);
	}

	public boolean intersects(Hitbox s) {
		if (s instanceof Hitbox) {
			Hitbox other = (Hitbox) s;

			if (Math.max(other.x, other.x + other.width) >= Math.min(this.x, this.x + width)) {
				if (Math.max(other.y, other.y + other.height) >= Math.min(this.y, this.y + height)) {
					if (Math.min(other.x, other.x + other.width) >= Math.max(this.x, this.x + width)) {
						return false;
					}

					if (Math.min(other.y, other.y + other.height) >= Math.max(this.y, this.y + height)) {
						return false;
					}
					return true;
				}
			}
			return false;
		}
		throw new IllegalArgumentException("Unsupported datatype in s.");
	}

	public void updateCoordinates() {
		if (object instanceof Player) {
			x = ((Player) object).getX();
			y = ((Player) object).getY();
		} else if (object instanceof Bullet) {
			x = ((Bullet) object).getX();
			y = ((Bullet) object).getY();
		}
	}

	public void updateCoordinates(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
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

}