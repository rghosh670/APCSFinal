
package blades;

import java.awt.geom.Point2D;

import character.Hitbox;
import character.Hitboxable;
import character.Player;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class models a blade which is extended by different types of blades
 * 
 * @author ksrinivas788
 *
 */
public class Blade implements Hitboxable {

	protected PImage originalBladePic, bladePic, leftBladePic, rightBladePic;
	protected float x, y, width, height;
	protected Hitbox hitbox;
	protected PApplet p;
	protected boolean left;
	protected Player player;
	protected int damage;

	public Blade(float x, float y, float width, float height, PApplet p, PImage bladePic, Player player) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.p = p;
		this.bladePic = bladePic;
		this.player = player;
		hitbox = new Hitbox(this, p, (int) x, (int) y, (int) width, (int) height);
		if (this instanceof Knife)
			hitbox.setHeight(-(height - p.height / 6));
	}

	public void draw() {
		moveTo(player.getHandPosition()[0], player.getHandPosition()[1]);

		hitbox.draw();

		if (player.getIsFacingLeft())
			bladePic = leftBladePic;
		else {
			bladePic = rightBladePic;
		}

		if (this instanceof Knife) {
			if (player.getIsFacingLeft()) {
				hitbox.updateCoordinates(x - width / 3, y + p.height / 12);
			} else {
				hitbox.updateCoordinates(x, y + (p.height / 11f));
			}
		} else {
			if (player.getIsFacingLeft()) {
				hitbox.updateCoordinates(x - (p.width / 36), y + (p.height / 22.5f));
			} else
				hitbox.updateCoordinates(x, y + (p.height / 22.5f));

			hitbox.setHeight(.75f * (height / 2 - height / 3));

		}

		if (bladePic != null)
			if (player.getIsFacingLeft())
				p.image(bladePic, x - (p.width / 36), y);
			else
				p.image(bladePic, x, y);

	}

	public void moveTo(float x, float y) {
		if (this instanceof Knife) {
			this.x = x;
			this.y = y - player.getHeight() / 2;
		} else {
			this.x = x;
			this.y = y - player.getHeight() / 2;
		}
	}

	public void setBladePic(PImage image) {
		originalBladePic = image;
		rightBladePic = originalBladePic.get(0, (int) height / 2, (int) width, (int) height / 2);
		leftBladePic = originalBladePic.get(0, 0, (int) width, (int) height / 2);

	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}
}
