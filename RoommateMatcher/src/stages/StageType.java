
<<<<<<< HEAD
package stages;

import character.Player;
import character.Player2;
import fieldElements.PowerUp;
import fieldElements.PowerUpBulletSpeed;
import fieldElements.PowerUpFireRate;
import fieldElements.PowerUpHealth;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

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

		inGame = (this instanceof Library || this instanceof OutsideField || this instanceof LivingRoom);

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

	public static void resetPlayers() {
		// DrawingSurface.p1 = new Player(DrawingSurface.user, p, 0,
		// (int) ((int) (p.height -
		// DrawingSurface.background.getStageType().getGround())
		// - DrawingSurface.p1.getHeight()),
		// DrawingSurface.p1.getPlayerState());
		//
		// DrawingSurface.p2 = new Player(DrawingSurface.user, p, (int) (p.width -
		// DrawingSurface.p2.getWidth() * 2),
		// (int) ((int) (p.height -
		// DrawingSurface.background.getStageType().getGround())
		// - DrawingSurface.p1.getHeight()),
		// DrawingSurface.p2.getPlayerState());

		DrawingSurface.p1 = DrawingSurface.p1.getPlayerType();
		DrawingSurface.p2 = DrawingSurface.p2.getPlayerType();

	}

}
=======
import character.Player;
import character.Player2;
import fieldElements.PowerUp;
import fieldElements.PowerUpBulletSpeed;
import fieldElements.PowerUpFireRate;
import fieldElements.PowerUpHealth;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

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

		inGame = (this instanceof Library || this instanceof OutsideField || this instanceof LivingRoom);

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

	public static void resetPlayers() {
		// DrawingSurface.p1 = new Player(DrawingSurface.user, p, 0,
		// (int) ((int) (p.height -
		// DrawingSurface.background.getStageType().getGround())
		// - DrawingSurface.p1.getHeight()),
		// DrawingSurface.p1.getPlayerState());
		//
		// DrawingSurface.p2 = new Player(DrawingSurface.user, p, (int) (p.width -
		// DrawingSurface.p2.getWidth() * 2),
		// (int) ((int) (p.height -
		// DrawingSurface.background.getStageType().getGround())
		// - DrawingSurface.p1.getHeight()),
		// DrawingSurface.p2.getPlayerState());

		DrawingSurface.p1 = DrawingSurface.p1.getPlayerType();
		DrawingSurface.p2 = DrawingSurface.p2.getPlayerType();

	}

}
>>>>>>> parent of ce9b71e... Bullets do damage now
