package character;

import data.User;
import enums.PlayerState;
import firearms.Bullet;
import firearms.Firearm;
import firearms.Rifle;
import firearms.Shotgun;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * This class models a surface on which the game is drawn
 * 
 * @author ksrinivas788
 *
 */
public class Player2 extends Player implements Hitboxable {

	private final int N_SPRITES_X = 5;
	private final int N_SPRITES_Y = 2;
	private final int TOTAL_SPRITES = N_SPRITES_X * N_SPRITES_Y;

	private boolean isFacingLeft, isMoving, isJumping;

	private int current_sprite_right = 0, current_sprite_left = 0;
	private double speed_x = 10;
	private int x, y, width, height;

	private Hitbox hitbox;

	private PApplet p;

	private PImage[] my_sprites_right;
	private PImage[] my_sprites_left;

	private Shotgun shotgun;
	private Rifle rifle;
	private boolean isRifle;
	private User user;

	private double yVel;
	private final double grav = .6;
	private boolean onGround;

	private int health;
	private boolean justHit, alive;
	private int healthCooldown;
	private HealthBar healthBar;

	private float originalBottom;
	private float bottom;

	private boolean inTree, onLadder, onCouch;

	private int fireRateIncrease, bulletSpeedIncrease;
	private boolean frIncrease, bsIncrease;

	public Player2(PApplet p) {
		this(null, p, 0, 0);
	}

	public Player2(User u, PApplet p, int xPos, int yPos) {
		super(u, p, xPos, yPos, PlayerState.ANIME);
		this.user = u;
		this.p = p;
		this.x = xPos + 18;
		this.y = yPos + 40;
		my_sprites_right = new PImage[TOTAL_SPRITES];
		my_sprites_left = new PImage[TOTAL_SPRITES];

		// put 1 image in the data s folder
		PImage my_sprite_sheet_right = p.loadImage("playerImages" + Stage.fileSeparator + "OtherGuy.png");
		PImage my_sprite_sheet_left = p.loadImage("playerImages" + Stage.fileSeparator + "OtherGuyLeft.png");

		my_sprite_sheet_right.resize(super.width * N_SPRITES_X, super.height * N_SPRITES_Y);
		my_sprite_sheet_left.resize(super.width * N_SPRITES_X, super.height * N_SPRITES_Y);
		width = my_sprite_sheet_right.width / N_SPRITES_X;
		height = my_sprite_sheet_right.height / N_SPRITES_Y;

		int index = 0;

		for (int y = 0; y < N_SPRITES_Y; y++) {
			for (int x = 0; x < N_SPRITES_X; x++) {
				my_sprites_right[index] = my_sprite_sheet_right.get(x * width, y * height, width, height);
				my_sprites_left[index] = my_sprite_sheet_left.get(x * width, y * height, width, height);
				index++;

			}
		}

		hitbox = new Hitbox(this, p);
		shotgun = new Shotgun(x, y, 80, p, this);
		rifle = new Rifle(x, y, 80, p, this);
		health = 100;
		healthBar = new HealthBar(p, this);
		alive = true;

		originalBottom = p.height - height - DrawingSurface.background.getStageType().getGround();
		bottom = originalBottom;

		try {
			DrawingSurface.p1.setOpponent(DrawingSurface.p2);
			DrawingSurface.p2.setOpponent(DrawingSurface.p1);
		} catch (NullPointerException e) {

		}

	}

	public void draw() {
		originalBottom = p.height - height - DrawingSurface.background.getStageType().getGround();
		alive = (health > 0);
		hitbox.draw();
		p.pushMatrix();
		p.pushStyle();

		fall();
		fall();
		fall();
		act();

		hitbox.updateCoordinates();
		display_the_sprite();

		rifle.moveTo(getHandPosition()[0], getHandPosition()[1] - height / 3);
		shotgun.moveTo(getHandPosition()[0], getHandPosition()[1] - height / 3);

		if (isRifle)
			rifle.draw();
		else
			shotgun.draw();

		healthBar.draw();

		p.popMatrix();
		p.popStyle();

		if (alive) {
			user.changeDefense(-0.5);
			user.writeToFile();
		}
	}

	public void moveRight() {
		if (!DrawingSurface.gameOver) {
			p.pushMatrix();
			p.pushStyle();

			p.frameRate(27);

			isFacingLeft = false;
			if (onGround)
				current_sprite_right++;
			current_sprite_right %= N_SPRITES_X;
			x = (x < p.width - width) ? x += speed_x : x;

			rifle.setLeft(isFacingLeft);
			shotgun.setLeft(isFacingLeft);
			p.popStyle();
			p.popMatrix();
		}

	}

	public void moveLeft() {
		if (!DrawingSurface.gameOver) {
			p.pushMatrix();
			p.pushStyle();

			p.frameRate(27);

			isFacingLeft = true;

			rifle.setLeft(isFacingLeft);
			shotgun.setLeft(isFacingLeft);

			if (onGround)
				current_sprite_left++;

			current_sprite_left %= N_SPRITES_X;
			x = (x > 0) ? x -= speed_x : x;
			p.popStyle();
			p.popMatrix();
		}
	}

	public void moveUp() {
		if (!DrawingSurface.gameOver)
			y = (y > -300) ? y -= speed_x : y;
	}

	public void moveDown() {
		if (!DrawingSurface.gameOver)
			y = (y < p.height - DrawingSurface.background.getStageType().getGround() - height) ? y += speed_x : y;
	}

	public void shoot() {
		if (!DrawingSurface.gameOver) {
			getGun().setBulletIndex(getGun().getBulletIndex() % 20);
			getGun().getBullets()[getGun().getBulletIndex()] = new Bullet(p, this, getGun().getBulletSpeed());
			getGun().setBulletIndex(getGun().getBulletIndex() + 1);
			getGun().getBullets()[getGun().getBulletIndex() - 1].shoot();
			this.getGun().setJustFired(true);
		}
	}

	public void jump() {
		if (!DrawingSurface.gameOver) {
			if (onGround)
				yVel = -12;
		}
	}

	private void act() {
		boolean onGround = false;
		healthCooldown();

		if (this.y >= bottom)
			onGround = true;

		if (onGround) {
			yVel = 0;
			y -= yVel;
			this.onGround = true;
		} else {
			this.onGround = false;
		}

		if (inTree || onLadder)
			yVel = 0;

		if (y > bottom)
			y = (int) (bottom);

		increaseFireRate(true);
		increaseBulletSpeed(true);

	}

	public void display_the_sprite() {
		p.pushMatrix();

		if (isFacingLeft)
			p.image(my_sprites_left[current_sprite_left], x, y);
		else
			p.image(my_sprites_right[current_sprite_right], x, y);

		p.popMatrix();
	}

	private void fall() {
		if (!DrawingSurface.gameOver) {
			if (yVel + grav < 7)
				yVel += grav;

			if (inTree || onLadder)
				yVel = 0;

			y += yVel;
		}
	}

	public boolean getIsMoving() {
		return isMoving;
	}

	public boolean getIsFacingLeft() {
		return isFacingLeft;
	}

	public boolean getIsJumping() {
		return isJumping;
	}

	public int getX() {
		return x + 30;
	}

	public int getY() {
		return y + 20;
	}

	public float getWidth() {
		return width - 55;
	}

	public float getHeight() {
		return height - 20;
	}

	public float[] getHandPosition() {
		float[] pos = new float[2];
		pos[1] = y + 10 * height / 12;

		if (!isFacingLeft)
			pos[0] = x + 8 * width / 12;
		else
			pos[0] = x - 9 * width / 24;

		return pos;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void getHit(Player other) {
		// if (opponent != null) {
		// if (!justHit && health > opponent.getGun().getDamage()) {
		// health -= opponent.getGun().getDamage();
		// justHit = true;
		// opponent.getUser().changeOffense(0.05);
		// this.getUser().changeDefense(-0.05);
		// } else if (health < opponent.getGun().getDamage()) {
		// System.out.println("EEPS");
		// health = 0;
		// alive = false;
		// } else {
		// System.out.println("OPPS");
		// while (opponent != null) {
		// DrawingSurface.p1.setOpponent(DrawingSurface.p2);
		// DrawingSurface.p2.setOpponent(DrawingSurface.p1);
		// System.out.println("I cry");
		// }
		// }
		// }

		if (!justHit && health > other.getGun().getDamage()) {
			health -= other.getGun().getDamage();
			justHit = true;
		} else if (health <= other.getGun().getDamage()) {
			System.out.println("EEPS");
			health = 0;
			alive = false;
			DrawingSurface.gameOver = true;
		}
	}

	public Firearm getGun() {
		if (isRifle)
			return rifle;
		return shotgun;
	}

	public void setIsRifle(boolean ir) {
		isRifle = ir;
	}

	public void increaseSpeed() {
		speed_x += .25;
		System.out.println(speed_x);
	}

	public int getHealth() {
		return health;
	}

	public boolean getIsRifle() {
		return isRifle;
	}

	private void healthCooldown() {
		if (justHit)
			healthCooldown++;

		if (healthCooldown > 20) {
			justHit = false;
			healthCooldown = 0;
		}
	}

	public void setInTree(boolean it) {
		inTree = it;
	}

	public boolean getInTree() {
		return inTree;
	}

	public void setOnLadder(boolean ol) {
		onLadder = ol;
	}

	public boolean getOnLadder() {
		return onLadder;
	}

	public void setOnCouch(boolean oc) {
		onCouch = oc;
	}

	public boolean getOnCouch() {
		return onCouch;
	}

	public void setBottom(float b) {
		bottom = b;
	}

	public float getBottom() {
		return bottom;
	}

	public float getOriginalBottom() {
		return originalBottom;
	}

	public void addHealth() {
		health += 20;
	}

	public boolean getFRIncrease() {
		return frIncrease;
	}

	public void increaseFireRate() {
		frIncrease = true;
	}

	private void increaseFireRate(boolean d) {
		if (frIncrease) {
			fireRateIncrease++;
			getGun().setFireRateMax(getGun().getNormalFireRateMax() / 2);
		} else {
			getGun().setFireRateMax(getGun().getNormalFireRateMax());
		}

		if (fireRateIncrease > 200) {
			frIncrease = false;
			fireRateIncrease = 0;
		}
	}

	public boolean getBSIncrease() {
		return bsIncrease;
	}

	public void increaseBulletSpeed() {
		bsIncrease = true;
	}

	public void setIsFacingLeft(boolean left) {
		isFacingLeft = left;
	}

	private void increaseBulletSpeed(boolean d) {
		if (bsIncrease) {
			bulletSpeedIncrease++;
			getGun().setBulletSpeed(70);
		} else {
			getGun().setFireRateMax(getGun().getNormalBulletSpeed());
		}

		if (bulletSpeedIncrease > 800) {
			bsIncrease = false;
			bulletSpeedIncrease = 0;
		}
	}

	public boolean getIsAlive() {
		return alive;
	}

}