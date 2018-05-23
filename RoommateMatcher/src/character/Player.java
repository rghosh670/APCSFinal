package character;

import blades.Blade;
import blades.Knife;
import blades.Sword;
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
import stages.StageType;

/**
 * This class models a playable character
 * 
 * @author ksrinivas788
 *
 */
public class Player implements Hitboxable {

	private int N_SPRITES_X;
	private int N_SPRITES_Y;
	private int TOTAL_SPRITES;

	private boolean isFacingLeft, isMoving, isJumping;

	private int current_sprite_right = 0, current_sprite_left = 0;
	private double speed_x = 10;
	protected int x, y, width, height;

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

	private Player opponent, selfResetPoint;
	private Knife knife;
	private Sword sword;
	private boolean isKnife;
	private boolean isGun;

	private int gunSwitchCooldown, bladeSwitchCooldown;
	private boolean gunSwitchCooldownBool, bladeSwitchCooldownBool;

	private PlayerState ps;

	private PImage my_sprite_sheet_right = null, my_sprite_sheet_left = null;

	public Player(PApplet p) {
		this(p, 0, 0, PlayerState.SMURF, null);
	}

	public Player(PApplet p, int xPos, int yPos, PlayerState ps, Player opponent) {
		this.p = p;
		this.x = xPos + 30;
		this.y = yPos;

		this.ps = ps;

		hitbox = new Hitbox(this, p);
		shotgun = new Shotgun(x, y, 80, p, this);
		rifle = new Rifle(x, y, 80, p, this);
		sword = new Sword(p, this);
		knife = new Knife(p, this);
		health = 100;
		healthBar = new HealthBar(p, this);
		alive = true;

		originalBottom = p.height - height - DrawingSurface.background.getStageType().getGround();
		bottom = originalBottom;

		selfResetPoint = this;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public void setOpponent(Player other) {
		opponent = other;
	}

	public Player getOpponent() {
		return opponent;
	}

	public User getUser() {
		return user;
	}

	public Player getPlayerType() {
		return selfResetPoint;
	}

	public void draw() {
		if (my_sprite_sheet_right == null || my_sprite_sheet_left == null) {
			int index = 0;

			if (ps.equals(PlayerState.SMURF)) {
				my_sprite_sheet_right = p.loadImage("playerImages" + Stage.fileSeparator + "SmurfsRight.png");
				my_sprite_sheet_left = p.loadImage("playerImages" + Stage.fileSeparator + "SmurfsLeft.png");
				N_SPRITES_X = 4;
				N_SPRITES_Y = 4;
				TOTAL_SPRITES = N_SPRITES_X * N_SPRITES_Y;
				my_sprites_right = new PImage[TOTAL_SPRITES];
				my_sprites_left = new PImage[TOTAL_SPRITES];
				width = my_sprite_sheet_right.width / N_SPRITES_X;
				height = my_sprite_sheet_right.height / N_SPRITES_Y;

				for (int y = 0; y < N_SPRITES_Y; y++) {
					for (int x = 0; x < N_SPRITES_X; x++) {

						PImage temp = my_sprite_sheet_right.get(x * width, y * height, width, height);
						temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));
						my_sprites_right[index] = temp;

						temp = my_sprite_sheet_left.get(x * width, y * height, width, height);
						temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));
						my_sprites_left[index] = temp;

						index++;
					}
				}

				width = (int) (p.width / 11.25);
				height = (int) (p.height / 7.03125);

			} else if (ps.equals(PlayerState.ANIME)) {
				my_sprite_sheet_right = p.loadImage("playerImages" + Stage.fileSeparator + "OtherGuy.png");
				my_sprite_sheet_left = p.loadImage("playerImages" + Stage.fileSeparator + "OtherGuyLeft.png");

				N_SPRITES_X = 5;
				N_SPRITES_Y = 2;
				TOTAL_SPRITES = N_SPRITES_X * N_SPRITES_Y;
				my_sprites_right = new PImage[TOTAL_SPRITES];
				my_sprites_left = new PImage[TOTAL_SPRITES];

				width = my_sprite_sheet_right.width / N_SPRITES_X;
				height = my_sprite_sheet_right.height / N_SPRITES_Y;

				for (int y = 0; y < N_SPRITES_Y; y++) {
					for (int x = 0; x < N_SPRITES_X; x++) {

						PImage temp = my_sprite_sheet_right.get(x * width, y * height, width, height);
						temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));

						my_sprites_right[index] = temp;

						temp = my_sprite_sheet_left.get(x * width, y * height, width, height);
						temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));
						my_sprites_left[index] = temp;

						index++;

					}
				}

				width = (int) (p.width / 11.25);
				height = (int) (p.height / 7.03125);

			} else {

				my_sprite_sheet_right = p.loadImage("playerImages" + Stage.fileSeparator + "Trump.png");
				my_sprite_sheet_left = p.loadImage("playerImages" + Stage.fileSeparator + "Trump.png");

				N_SPRITES_X = 6;
				N_SPRITES_Y = 4;
				TOTAL_SPRITES = N_SPRITES_X * N_SPRITES_Y;
				my_sprites_right = new PImage[N_SPRITES_X];
				my_sprites_left = new PImage[N_SPRITES_X];
				width = my_sprite_sheet_right.width / N_SPRITES_X;
				height = my_sprite_sheet_right.height / N_SPRITES_Y;

				for (int x = 0; x < N_SPRITES_X; x++) {
					PImage temp = my_sprite_sheet_right.get(x * width, height, width, height);
					temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));
					my_sprites_right[index] = temp;

					temp = my_sprite_sheet_right.get(my_sprite_sheet_right.width - x * width - width, 3 * height, width,
							height);
					temp.resize((int) (p.width / 11.25), (int) (p.height / 7.03125));
					my_sprites_left[index] = temp;
					index++;

				}
				width = (int) (p.width / 11.25);
				height = (int) (p.height / 7.03125);
			}

			hitbox.setWidth(p.width / 16);
			hitbox.setHeight(height);
		}

		originalBottom = p.height - height - DrawingSurface.background.getStageType().getGround();
		alive = (health > 0);

		if (alive) {
			p.pushMatrix();
			p.pushStyle();

			hitbox.draw();

			fall();
			fall();
			fall();
			act();

			hitbox.updateCoordinates(x - (p.width / 65), y);
			display_the_sprite();

			p.frameRate(30);

			rifle.moveTo(getHandPosition()[0], getHandPosition()[1] - height / 3);
			shotgun.moveTo(getHandPosition()[0], getHandPosition()[1] - height / 3);

			knifeSwitchCooldown();
			gunSwitchCooldown();

			if (isGun) {
				if (isRifle)
					rifle.draw();
				else
					shotgun.draw();

			} else {
				if (isKnife)
					knife.draw();
				else
					sword.draw();
			}

			healthBar.draw();

			p.popMatrix();
			p.popStyle();
		} else {
			user.changeDefense(-0.5);
			user.writeToFile();
		}
	}

	public void moveRight() {
		if (!DrawingSurface.gameOver && StageType.inGame) {
			isFacingLeft = false;
			if (onGround)
				current_sprite_right++;

			if (ps.equals(PlayerState.SMURF))
				current_sprite_right %= TOTAL_SPRITES;
			else
				current_sprite_right %= N_SPRITES_X;

			x = (x < p.width - width) ? x += speed_x : x;

			rifle.setLeft(isFacingLeft);
			shotgun.setLeft(isFacingLeft);
		}

	}

	public void moveLeft() {
		if (!DrawingSurface.gameOver && StageType.inGame) {
			isFacingLeft = true;

			rifle.setLeft(isFacingLeft);
			shotgun.setLeft(isFacingLeft);

			if (onGround)
				current_sprite_left++;

			if (ps.equals(PlayerState.SMURF))
				current_sprite_left %= TOTAL_SPRITES;
			else
				current_sprite_left %= N_SPRITES_X;
			x = (x > 0) ? x -= speed_x : x;
		}
	}

	public void moveUp() {
		if (!DrawingSurface.gameOver) {
			y = (y > -300) ? y -= speed_x : y;
		}
	}

	public void moveDown() {
		if (!DrawingSurface.gameOver) {
			y = (y < p.height - DrawingSurface.background.getStageType().getGround() - height) ? y += speed_x : y;
		}
	}

	public void shoot() {
		if (!DrawingSurface.gameOver && isGun) {
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
		return y;
	}

	public float getWidth() {
		return width - 55;
	}

	public float getHeight() {
		return height;
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

	public Firearm getGun() {
		if (isRifle)
			return rifle;
		return shotgun;
	}

	public void increaseSpeed() {
		speed_x += .25;
	}

	public void getShot(Player other) {

		if (!justHit && health > other.getGun().getDamage()) {
			health -= other.getGun().getDamage();
			justHit = true;
		} else if (health <= other.getGun().getDamage()) {

			health = 0;
			alive = false;
			DrawingSurface.gameOver = true;
		}
	}

	public void getCut(Player other) {
		if (StageType.inGame && !other.getIsGun()) {
			if (!justHit && health > other.getBlade().getDamage()) {
				health -= other.getBlade().getDamage();
				justHit = true;
			} else if (health <= other.getBlade().getDamage()) {
				health = 0;
				alive = false;
				DrawingSurface.gameOver = true;
			}
		}
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

	public PlayerState getPlayerState() {
		return ps;
	}

	public void setPlayerState(PlayerState ps) {
		this.ps = ps;
	}

	public boolean getIsAlive() {
		return alive;
	}

	public Blade getBlade() {
		if (isKnife) {
			return knife;
		} else
			return sword;
	}

	public void setIsGun(boolean ig) {
		isGun = ig;
	}

	public boolean getIsGun() {
		return isGun;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	public void toggleIsRifle() {
		if (!gunSwitchCooldownBool) {
			isRifle = !isRifle;
			gunSwitchCooldownBool = true;
			gunSwitchCooldown();
		}
	}

	public void toggleIsKnife() {
		if (!bladeSwitchCooldownBool) {
			isKnife = !isKnife;
			bladeSwitchCooldownBool = true;
			knifeSwitchCooldown();
		}
	}

	private void knifeSwitchCooldown() {
		if (bladeSwitchCooldownBool)
			bladeSwitchCooldown++;

		if (bladeSwitchCooldown > 10) {
			bladeSwitchCooldownBool = false;
			bladeSwitchCooldown = 0;
		}
	}

	private void gunSwitchCooldown() {
		if (gunSwitchCooldownBool) {
			gunSwitchCooldown++;
		}

		if (gunSwitchCooldown > 10) {
			gunSwitchCooldownBool = false;
			gunSwitchCooldown = 0;
		}
	}

}