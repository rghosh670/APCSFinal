package firearms;

import character.Player;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a firearm which is extended by the various types of guns
 * available in the game
 * 
 * @author vshirsat573
 *
 */
public class Firearm {

	protected float x, y;
	protected int width, height, fireRate, fireRateMax, reloadTime, bulletCooldown, bulletIndex, damage, bulletSpeed;
	protected boolean justFired;
	protected boolean left, isRifle;
	protected PImage gunImage;
	protected PApplet p;
	protected Bullet[] bullets;
	protected int normalFireRateMax, normalBulletSpeed;
	protected Player player;

	public Firearm(float x, float y, int height, PApplet p, Player player) {
		this.x = x;
		this.y = y - height;
		this.height = height;
		this.p = p;
		this.player = player;
		bullets = new Bullet[20];
	}

	public void draw() {
		p.pushMatrix();
		p.image(gunImage, x, y);
		controlBullets();
		p.popMatrix();
	}

	private void controlBullets() {
		bulletSleep(DrawingSurface.p1);
		bulletSleep(DrawingSurface.p2);
		for (int i = 0; i < 20; i++) {
			if (bullets[i] != null) {
				bullets[i].draw();
			}
		}

	}

	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
	}

	private void bulletSleep(Player p) {
		if (p.getGun().getJustFired())
			p.getGun().setFireRate(p.getGun().getFireRate() + 1);

		if (p.getGun().getFireRate() > p.getGun().getMaxFireRate()) {
			p.getGun().setJustFired(false);
			p.getGun().setFireRate(0);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getFireRate() {
		return fireRate;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public int getBulletCooldown() {
		return bulletCooldown;
	}

	public boolean getJustFired() {
		return justFired;
	}

	public int getMaxFireRate() {
		return fireRateMax;
	}

	public void setFireRate(int fr) {
		fireRate = fr;
	}

	public void setJustFired(boolean jf) {
		justFired = jf;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setGunImage(PImage image) {
		gunImage = image;
	}

	public Bullet[] getBullets() {
		return bullets;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setBulletSpeed(int bs) {
		this.bulletSpeed = bs;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public int getBulletIndex() {
		return bulletIndex;
	}

	public void setBulletIndex(int bi) {
		this.bulletIndex = bi;
	}

	public void setFireRateMax(int fr) {
		this.fireRateMax = fr;
	}

	public int getNormalFireRateMax() {
		return normalFireRateMax;
	}

	public int getNormalBulletSpeed() {
		return normalBulletSpeed;
	}
}