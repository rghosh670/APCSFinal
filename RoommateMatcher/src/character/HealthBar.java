package character;

import processing.core.PApplet;

/**
 * 
 * This class models the health bar that appears above each player
 * 
 * @author rohitghosh
 *
 */
public class HealthBar {
	private PApplet p;
	private int height, width;
	private Player player;

	public HealthBar(PApplet p, Player player) {
		this.p = p;
		this.player = player;
		height = 25;
		width = 100;
	}

	public void draw() {
		p.pushStyle();
		p.pushMatrix();
		p.fill(255, 0, 0);
		p.rect(player.getX(), player.getY() - (int) (p.height / 45), width, height);
		p.fill(0, 255, 0);
		p.rect(player.getX(), player.getY() - (int) (p.height / 45), player.getHealth(), height);
		p.popMatrix();
		p.popStyle();
	}

}
