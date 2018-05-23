package blades;

import character.Player;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a sword which is a type of blade
 * 
 * @author vshirsat573
 *
 */
public class Sword extends Blade {

	private PImage swordPic;

	public Sword(PApplet p, Player player) {
		super(player.getHandPosition()[0], player.getHandPosition()[1],
				p.loadImage("weaponImages" + Stage.fileSeparator + "Sword.png").width / 3,
				p.loadImage("weaponImages" + Stage.fileSeparator + "Sword.png").height / 3, p, null, player);
		swordPic = p.loadImage("weaponImages" + Stage.fileSeparator + "Sword.png");
		swordPic.resize((int) width, (int) height);

		super.setBladePic(swordPic);
		super.setDamage(30);
	}

}
