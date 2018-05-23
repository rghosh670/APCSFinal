package firearms;

import character.Player;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a shotgun which shoots bullets at a slower rate than a
 * rifle
 * 
 * @author rghosh293
 *
 */
public class Shotgun extends Firearm {
	private PImage originalShotgunPic, shotgunPic;

	public Shotgun(float x, float y, int height, PApplet p, Player player) {
		super(x, y, height, p, player);
		fireRateMax = normalFireRateMax;
		width = (int) (1.2 * height);
		originalShotgunPic = p.loadImage("weaponImages" + Stage.fileSeparator + "Shotgun.png");
		originalShotgunPic.resize(width, height);
		super.setDamage(10);
		super.p = p;

		normalBulletSpeed = 12;
		normalFireRateMax = 100;
	}

	public void draw() {
		if (super.left)
			shotgunPic = originalShotgunPic.get(0, height / 2, width, height / 2);
		else
			shotgunPic = originalShotgunPic.get(0, 0, width, height / 2);

		super.setGunImage(shotgunPic);
		super.setDamage(10);

		if (!player.getFRIncrease())
			super.setFireRateMax(normalFireRateMax);

		if (!player.getBSIncrease())
			super.setBulletSpeed(normalBulletSpeed);
		super.draw();
	}

}
