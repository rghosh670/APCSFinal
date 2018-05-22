package firearms;

import character.Player;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a rifle, which is a type of gun and shoots bullets at a
 * faster rate than a shotgun
 * 
 * @author rghosh293
 *
 */
public class Rifle extends Firearm {

	private PImage originalRiflePic, riflePic;

	public Rifle(float x, float y, int height, PApplet p, Player player) {
		super(x, y, height, p, player);
		fireRateMax = normalFireRateMax;
		width = (int) (1.2 * height);
		originalRiflePic = p.loadImage("weaponImages" + Stage.fileSeparator + "Rifle.png");
		originalRiflePic.resize(width, height);
		super.setDamage(2);
		super.p = p;

		normalBulletSpeed = 20;
		normalFireRateMax = 30;
	}

	public void draw() {
		if (super.left)
			riflePic = originalRiflePic.get(0, height / 2, width, height / 2);
		else
			riflePic = originalRiflePic.get(0, 0, width, height / 2);

		super.setGunImage(riflePic);
		if (!player.getFRIncrease())
			super.setFireRateMax(normalFireRateMax);

		if (!player.getBSIncrease())
			super.setBulletSpeed(normalBulletSpeed);
		super.draw();
	}

}
