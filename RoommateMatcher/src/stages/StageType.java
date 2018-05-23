package stages;

import character.Player;
import fieldElements.PowerUp;
import fieldElements.PowerUpBulletSpeed;
import fieldElements.PowerUpFireRate;
import fieldElements.PowerUpHealth;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class is used as a basis for all the playable stages
 * 
 * @author rohitghosh
 *
 */
public class StageType {
	protected PImage stageImage;
	protected PApplet p;
	protected float ground;

	public static boolean inGame;

	private PowerUp powerUp;

	public StageType(PApplet p) {
		this.p = p;
		powerUp = new PowerUpBulletSpeed(p, -300, getGround());
	}

	public void draw() {

		inGame = (this instanceof Library || this instanceof OutdoorField || this instanceof LivingRoom);

		p.image(stageImage, 0, 0);

		if (inGame) {
			if (powerUp.getDisappeared()) {
				double r = Math.random();
				if (r < .001) {
					powerUp = new PowerUpFireRate(p, (float) (Math.random() * p.width), 0);
					powerUp.setDisappeared(false);
				} else if (r < .002) {
					powerUp = new PowerUpBulletSpeed(p, (float) (Math.random() * p.width), 0);
					powerUp.setDisappeared(false);
				} else if (r < .003) {
					powerUp = new PowerUpHealth(p, (float) (Math.random() * p.width), 0);
					powerUp.setDisappeared(false);
				} else if ((DrawingSurface.p1.getHealth() < 50 || DrawingSurface.p2.getHealth() < 50) && r < .04) {
					powerUp = new PowerUpHealth(p, (float) (Math.random() * p.width), 0);
					powerUp.setDisappeared(false);
				}
			}
		}

		powerUp.draw();

	}

	public void setStageImage(PImage pImage) {
		this.stageImage = pImage;
	}

	public void setGround(float ground) {
		this.ground = ground;
	}

	public float getGround() {
		return ground;
	}

	public void resetPlayers() {
		DrawingSurface.p1 = DrawingSurface.p1.getPlayerType();
		DrawingSurface.p2 = DrawingSurface.p2.getPlayerType();
	}

}
