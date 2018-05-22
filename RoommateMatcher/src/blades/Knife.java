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
	private float length;
	private PImage knifePic;
	private PApplet drawer;
	private Hitbox hitbox;

	public Knife(float x, float y, float length, float angle, PApplet drawer, Player player) {
		super(x, y, length, angle, drawer, player);
		// this.angle = angle;
		this.length = length;
		knifePic = drawer.loadImage("weaponImages" + Stage.fileSeparator + "Knife.png");
		knifePic.resize((int) length, (int) length / 2);
		hitbox = new Hitbox(this, drawer);
	}

	public void draw() {
		super.draw();
		hitbox.updateCoordinates();
		this.drawer = drawer;
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.image(knifePic, x1 - length, y1 - length);
		// drawer.translate(x1, y1-length/2);
		//// drawer.translate(length, length/2);
		System.out.println(">" + angle);
		//// System.out.println(angle < Math.PI/101);
		// drawer.rotate((float) (angle));
		// drawer.image(knifePic, 0, 0);
		//// drawer.rotate(-angle);
		// drawer.translate(-length, -length/2);
		drawer.popStyle();
		drawer.popMatrix();
		if (drawer.keyPressed && drawer.key == '1') {
			System.out.println("1 called");
			count -= 100;
			// while (count < 100) {
			// System.out.println("swinging...");
			//// System.out.println(">" + (angle));
			// this.swing();
			// }
			this.swing();
		}
		// if (drawer.keyPressed) {
		// if (drawer.key == 'r') drawer.rotate((float) (Math.PI/4));
		// if (drawer.key == 'R') drawer.rotate((float) -Math.PI/4);
		// drawer.line(100, 100, 200, 200);
		// }
	}

	public void swing() {
		System.out.println("swung");
		super.swing();
		drawer.pushStyle();
		// drawer.pushMatrix();
		// drawer.image(knifePic, x1-length, y1-length);
		drawer.translate(x1, y1 - length / 2);
		// drawer.translate(length/2, 0);
		this.angle = super.angle;
		drawer.rotate((float) (this.angle));
		drawer.image(knifePic, 0, -length / 2);
		// System.out.println(">" + (angle));
		drawer.delay(0);
		drawer.translate(-x1, -y1 + length / 2);
		// drawer.translate(-length/4, -length/8);
		// this.angle = super.angle;
		while (count < 100) {
			swing();
		}
		// drawer.popMatrix();
		drawer.popStyle();
	}

}
