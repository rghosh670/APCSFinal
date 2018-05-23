package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import blades.Blade;
import blades.Knife;
import character.Player;
import character.Player2;
import character.Player3;
import data.User;
import enums.PlayerState;
import enums.StageState;
import fieldElements.Button;
import fieldElements.Ladder;
import fieldElements.TextBox;
import firearms.Bullet;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Library;
import stages.LivingRoom;
import stages.OutsideField;
import stages.Stage;
import stages.StageType;

/**
 * This class models a surface on which the game is drawn
 * 
 * @author ksrinivas788
 *
 */
public class DrawingSurface extends PApplet {

	public static Stage background;
	public static Player p1;
	public static Player p2;
	public static User user1, user2;

	public static boolean gameOver;

	private PImage deathScreen;

	private ArrayList<Integer> keys;
	Knife knife;
	Blade blade;

	public void setup() {
		background = new Stage(this);
		deathScreen = loadImage("stageImages" + Stage.fileSeparator + "BlackScreen.jpg");
		deathScreen.resize(width, height);
		p1 = new Player(this, 0, height - (int) background.getStageType().getGround() - 40, PlayerState.ANIME, null);
		p2 = new Player(this, (int) (width * .8), height - (int) background.getStageType().getGround() - 40, PlayerState.ANIME, null);
		p1.setOpponent(p2);
		p2.setOpponent(p1);

		knife = new Knife(p1.getHandPosition()[0], p1.getHandPosition()[1], p1.getHeight() / 2, (float) (Math.PI / 3),
				this);
		blade = new Blade(p1.getHandPosition()[0], p1.getHandPosition()[1], p1.getHeight() / 2, (float) (Math.PI / 3));

		keys = new ArrayList<Integer>();

		noFill();
	}

	public void setUser1(User u) {
		p1.setUser(u);
		// this.user1 = u;
	}

	public void setUser2(User u) {
		p2.setUser(u);
		// this.user2 = u;
	}

	public void settings() {
		fullScreen();
	}

	public void draw() {
		
//		if (p1.getOpponent() == null) p1.setOpponent(p2);
//		if (p2.getOpponent() == null) p2.setOpponent(p1);

		background.draw();

		if (StageType.inGame) {
			p1.draw();
			p2.draw();

			// knife.moveTo(p1.getHandPosition()[0], p1.getHandPosition()[1]);
			// knife.draw(this);
			if (knife.intersects(p2.getHitbox()) != null) {
				ellipse(knife.intersects(p2.getHitbox())[0], knife.intersects(p2.getHitbox())[1], 5, 5);
				System.out.println("hit");
			}
		}

		setFieldElements();
		controlPlayers();
		winLose();

	}

	private void controlPlayers() {
		if (isPressed(KeyEvent.VK_LEFT))
			p1.moveLeft();
		if (isPressed(KeyEvent.VK_RIGHT))
			p1.moveRight();
		if (isPressed(KeyEvent.VK_UP)) {
			if (p1.getInTree())
				p1.moveUp();
			else
				p1.jump();

			if (p1.getOnLadder())
				p1.moveUp();
			else
				p1.jump();
		}
		if (isPressed(KeyEvent.VK_SLASH))
			p1.setIsRifle(false);
		if (isPressed(KeyEvent.VK_PERIOD))
			p1.setIsRifle(true);
		if (isPressed(KeyEvent.VK_M) && !p1.getGun().getJustFired()) {
			p1.shoot();
			p1.getGun().setJustFired(true);
		}
		if (isPressed(KeyEvent.VK_DOWN) && (p1.getInTree() || p1.getOnLadder())) {
			p1.moveDown();
		}

		////////////////////////////////////////
		if (isPressed(KeyEvent.VK_D))
			p2.moveRight();
		if (isPressed(KeyEvent.VK_A))
			p2.moveLeft();
		if (isPressed(KeyEvent.VK_W)) {
			if (p2.getInTree())
				p2.moveUp();
			else
				p2.jump();

			if (p2.getOnLadder())
				p2.moveUp();
			else
				p2.jump();
		}
		if (isPressed(KeyEvent.VK_Q))
			p2.setIsRifle(false);
		if (isPressed(KeyEvent.VK_E))
			p2.setIsRifle(true);
		if (isPressed(KeyEvent.VK_F) && !p2.getGun().getJustFired()) {
			p2.shoot();
			p2.getGun().setJustFired(true);
		}
		if (isPressed(KeyEvent.VK_S) && (p2.getInTree() || p2.getOnLadder())) {
			p2.moveDown();
		}

		for (Bullet b : p1.getGun().getBullets()) {
			if (b != null)
				if (b.getHitbox().intersects(p2.getHitbox())) {
					p2.getHit();
					b.disappear();
				}
		}

		for (Bullet b : p2.getGun().getBullets()) {
			if (b != null)
				if (b.getHitbox().intersects(p1.getHitbox())) {
					p1.getHit();
					b.disappear();
				}
		}

	}

	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
		TextBox.setLastKey(false);

	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	private void setFieldElements() {
		if (background.getStageType() instanceof OutsideField) {
			OutsideField of = (OutsideField) (background.getStageType());
			if (p1.getHitbox().intersects(of.getTrees()[0].getHitboxes()[0])
					|| p1.getHitbox().intersects(of.getTrees()[1].getHitboxes()[0])) {
				p1.setInTree(true);
			} else
				p1.setInTree(false);

			if (p2.getHitbox().intersects(of.getTrees()[0].getHitboxes()[0])
					|| p2.getHitbox().intersects(of.getTrees()[1].getHitboxes()[0])) {
				p2.setInTree(true);
			} else
				p2.setInTree(false);
		} else {
			p1.setInTree(false);
			p2.setInTree(false);
		}

		if (background.getStageType() instanceof Library) {
			Library l = (Library) (background.getStageType());
			for (Ladder ladder : l.getLadders()) {
				if (p1.getHitbox().intersects(ladder.getHitbox())) {
					p1.setOnLadder(true);
					break;
				} else
					p1.setOnLadder(false);
			}
		} else if (!(background.getStageType() instanceof LivingRoom)) {
			p1.setOnLadder(false);
			p2.setOnLadder(false);
		}

		if (background.getStageType() instanceof Library) {
			Library l = (Library) (background.getStageType());
			for (Ladder ladder : l.getLadders()) {
				if (p2.getHitbox().intersects(ladder.getHitbox())) {
					p2.setOnLadder(true);
					break;
				} else
					p2.setOnLadder(false);
			}
		} else if (!(background.getStageType() instanceof LivingRoom)) {
			p1.setOnLadder(false);
			p2.setOnLadder(false);
		}

		if (background.getStageType() instanceof LivingRoom) {
			LivingRoom l = (LivingRoom) (background.getStageType());
			for (Ladder ladder : l.getLadders()) {
				if (p1.getHitbox().intersects(ladder.getHitbox())) {
					p1.setOnLadder(true);
					break;
				} else
					p1.setOnLadder(false);
			}
		} else if (!(background.getStageType() instanceof Library)) {
			p1.setOnLadder(false);
			p2.setOnLadder(false);
		}

		if (background.getStageType() instanceof LivingRoom) {
			LivingRoom l = (LivingRoom) (background.getStageType());
			for (Ladder ladder : l.getLadders()) {
				if (p2.getHitbox().intersects(ladder.getHitbox())) {
					p2.setOnLadder(true);
					break;
				} else
					p2.setOnLadder(false);
			}
		} else if (!(background.getStageType() instanceof Library)) {
			p1.setOnLadder(false);
			p2.setOnLadder(false);
		}

		if (background.getStageType() instanceof LivingRoom) {
			LivingRoom l = (LivingRoom) (background.getStageType());

			if (p1.getX() + p1.getWidth() > l.getCouch().getHitboxes()[0].getX()
					&& p1.getX() < l.getCouch().getHitboxes()[0].getX() + l.getCouch().getHitboxes()[0].getWidth()) {
				if (p1.getY() + p1.getHeight() / 2 <= l.getCouch().getHitboxes()[0].getY()) {
					p1.setBottom(l.getCouch().getHitboxes()[0].getY() - l.getCouch().getHitboxes()[0].getHeight() / 2);
				}

				else {
					p1.setBottom(p1.getOriginalBottom());
				}
			}

			else if (p1.getX() + p1.getWidth() > l.getCouch().getHitboxes()[2].getX()
					&& p1.getX() < l.getCouch().getHitboxes()[2].getX() + l.getCouch().getHitboxes()[2].getWidth()) {
				if (p1.getY() + p1.getHeight() / 2 <= l.getCouch().getHitboxes()[2].getY()) {
					p1.setBottom(l.getCouch().getHitboxes()[2].getY() - l.getCouch().getHitboxes()[2].getHeight() / 2);
				}

				else {
					p1.setBottom(p1.getOriginalBottom());
				}
			}

			else if (p1.getX() + p1.getWidth() > l.getCouch().getHitboxes()[1].getX()
					&& p1.getX() < l.getCouch().getHitboxes()[1].getX() + l.getCouch().getHitboxes()[1].getWidth()) {
				if (p1.getY() + p1.getHeight() <= l.getCouch().getHitboxes()[1].getY()) {
					p1.setBottom(l.getCouch().getHitboxes()[1].getY() - l.getCouch().getHitboxes()[1].getHeight());
				}
			} else {
				p1.setBottom(p1.getOriginalBottom());
			}

			if (p2.getX() + p2.getWidth() > l.getCouch().getHitboxes()[0].getX()
					&& p2.getX() < l.getCouch().getHitboxes()[0].getX() + l.getCouch().getHitboxes()[0].getWidth()) {
				if (p2.getY() + p2.getHeight() / 2 <= l.getCouch().getHitboxes()[0].getY()) {
					p2.setBottom(l.getCouch().getHitboxes()[0].getY() - l.getCouch().getHitboxes()[0].getHeight() / 2);
				}

				else {
					p2.setBottom(p1.getOriginalBottom());
				}
			}

			else if (p2.getX() + p2.getWidth() > l.getCouch().getHitboxes()[2].getX()
					&& p2.getX() < l.getCouch().getHitboxes()[2].getX() + l.getCouch().getHitboxes()[2].getWidth()) {
				if (p2.getY() + p2.getHeight() / 2 <= l.getCouch().getHitboxes()[2].getY()) {
					p2.setBottom(l.getCouch().getHitboxes()[2].getY() - l.getCouch().getHitboxes()[2].getHeight() / 2);
				}

				else {
					p2.setBottom(p1.getOriginalBottom());
				}
			}

			else if (p2.getX() + p2.getWidth() > l.getCouch().getHitboxes()[1].getX()
					&& p2.getX() < l.getCouch().getHitboxes()[1].getX() + l.getCouch().getHitboxes()[1].getWidth()) {
				if (p2.getY() + p2.getHeight() <= l.getCouch().getHitboxes()[1].getY()) {
					p2.setBottom(l.getCouch().getHitboxes()[1].getY() - l.getCouch().getHitboxes()[1].getHeight());
				}
			} else {
				p2.setBottom(p2.getOriginalBottom());
			}
		} else {
			p1.setBottom(p1.getOriginalBottom());
			p2.setBottom(p2.getOriginalBottom());
		}
	}

	public void setGameOver(boolean go) {
		gameOver = go;

	}

	public void winLose() {
		pushMatrix();

		if (gameOver) {
			image(deathScreen, 0, 0);
			if (!p1.getIsAlive()) {
				Button p2Wins = new Button(width / 2, height / 8 + height / 20, width / 5, height / 10, this,
						"Player Two Wins!", true, true);
				p2Wins.draw();
			} else {
				Button p1Wins = new Button(width / 2, height / 8 + height / 20, width / 5, height / 10, this,
						"Player One Wins!", true, true);
				p1Wins.draw();
			}

			Button quit = new Button(width / 2, height / 20 + height / 20, width / 4, height / 10, this, "Quit", true);
			quit.draw();
		}

		popMatrix();

	}
}