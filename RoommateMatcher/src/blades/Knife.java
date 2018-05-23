package blades;

import character.Hitbox;
import character.Hitboxable;
import character.Player;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a knife which is a type of blade
 * 
 * @author vshirsat573
 *
 */
public class Knife extends Blade implements Hitboxable {

	// private float angle;
	private PImage knifePic;

	public Knife(PApplet p, Player player) {
		super(player.getHandPosition()[0], player.getHandPosition()[1],
				p.loadImage("weaponImages" + Stage.fileSeparator + "Knife.png").width / 6,
				p.loadImage("weaponImages" + Stage.fileSeparator + "Knife.png").height / 6, p, null, player);
		knifePic = p.loadImage("weaponImages" + Stage.fileSeparator + "Knife.png");
		knifePic.resize((int) width, (int) height);
		super.setBladePic(knifePic);
		super.setDamage(10);
	}

}
