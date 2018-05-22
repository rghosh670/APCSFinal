package firearms;

import character.Hitbox;
import character.Hitboxable;
import character.Player;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a bullet which does damage to the opposing player when shot
 * 
 * @author rghosh293
 *
 */
public class Bullet implements Hitboxable {
	private PImage originalShotgunBulletPic, shotgunBulletPic, originalRifleBulletPic, rifleBulletPic, bulletPic;
	private Hitbox hitbox;
	private Player player;
	private PApplet p;
	private boolean isShooting;
	private float x, y, xVel;
	private int width, height;

	public Bullet(PApplet p, Player player, int bulletSpeed) {
		this.p = p;
		originalShotgunBulletPic = p.loadImage("weaponImages" + Stage.fileSeparator + "ShotgunBullet.png");
		originalRifleBulletPic = p.loadImage("weaponImages" + Stage.fileSeparator + "RifleBullet.png");

		width = player.getIsRifle() ? originalRifleBulletPic.width / 10 : originalShotgunBulletPic.width / 10;
		height = player.getIsRifle() ? originalRifleBulletPic.height / 10 : originalShotgunBulletPic.height / 10;

		originalShotgunBulletPic.resize(width, height);
		originalRifleBulletPic.resize(width, height);

		hitbox = new Hitbox(this, p);
		xVel = player.getIsFacingLeft() ? -bulletSpeed : bulletSpeed;
		this.player = player;

		shotgunBulletPic = player.getIsFacingLeft() ? originalShotgunBulletPic.get(0, height / 2, width, height / 2)
				: originalShotgunBulletPic.get(0, 0, width, height / 2);

		rifleBulletPic = player.getIsFacingLeft() ? originalRifleBulletPic.get(0, height / 2, width, height / 2)
				: originalRifleBulletPic.get(0, 0, width, height / 2);

		bulletPic = player.getIsRifle() ? rifleBulletPic : shotgunBulletPic;

	}

	public void draw() {
		if (isShooting)
			x += xVel;

		p.image(bulletPic, x, y);
		 hitbox.updateCoordinates();
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
		return height / 2;
	}

	public void shoot() {
		x = player.getHandPosition()[0];
		y = player.getHandPosition()[1] - 20;
		isShooting = true;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public Player getPlayer() {
		return player;
	}

	public void disappear() {
		x = -width;
		y = -height;
	}

}
