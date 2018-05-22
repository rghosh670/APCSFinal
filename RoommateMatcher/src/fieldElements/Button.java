package fieldElements;

import enums.MenuState;
import enums.PlayerState;
import enums.StageState;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PConstants;
import stages.StageType;

/**
 * This class models a button which is used to switch between stages and select
 * characters and weapons
 * 
 * @author rghosh293
 *
 */
public class Button {
	private int x, y, width, height;
	private String text;
	private PApplet p;
	private boolean center, white, transparent;

	public Button(int x, int y, PApplet p, String text) {
		this(x, y, p.width / 4, p.height / 10, p, text);
	}

	public Button(int x, int y, int width, int height, PApplet p, String text) {
		this(x, y, width, height, p, text, false);
	}

	public Button(int x, int y, int width, int height, PApplet p, String text, boolean center) {
		this(x, y, width, height, p, text, center, false);
	}

	public Button(int x, int y, int width, int height, PApplet p, String text, boolean center, boolean white) {
		this(x, y, width, height, p, text, center, white, false);

	}

	public Button(int x, int y, int width, int height, PApplet p, String text, boolean center, boolean white,
			boolean transparent) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.text = text;
		this.width = width;
		this.height = height;
		this.center = center;

		if (center)
			p.rectMode(PConstants.CENTER);
		else
			p.rectMode(PConstants.CORNER);

		this.white = white;
		this.transparent = transparent;
	}

	public void draw() {
		p.pushStyle();
		if (buttonClicked()) {
			if (text.equals("Outdoor Field")) {
				DrawingSurface.background.setStage(StageState.OUTDOOR_FIELD);
				DrawingSurface.background.setIsStage(false);
				DrawingSurface.background.setMenu(MenuState.PLAYER_ONE_SELECT);
				StageType.resetPlayers();
				p.delay(100);
			} else if (text.equals("Library")) {
				DrawingSurface.background.setStage(StageState.LIBRARY);
				DrawingSurface.background.setIsStage(false);
				DrawingSurface.background.setMenu(MenuState.PLAYER_ONE_SELECT);
				StageType.resetPlayers();
				p.delay(100);
			} else if (text.equals("Living Room")) {
				DrawingSurface.background.setStage(StageState.LIVING_ROOM);
				DrawingSurface.background.setIsStage(false);
				DrawingSurface.background.setMenu(MenuState.PLAYER_ONE_SELECT);
				StageType.resetPlayers();
				p.delay(100);
			} else if (text.equals("Menu")) {
				DrawingSurface.background.setMenu(MenuState.MAIN_MENU);
				DrawingSurface.background.setIsStage(false);
				p.delay(100);
			} else if (text.equals("Instructions")) {
				DrawingSurface.background.setMenu(MenuState.INSTRUCTIONS);
				DrawingSurface.background.setIsStage(false);
			} else if (text.equals("FIND MATCH")) {
				DrawingSurface.background.setMenu(MenuState.MATCH_MAKER);
				DrawingSurface.background.setIsStage(false);
			} else if (text.equals("Play")) {
				DrawingSurface.background.setMenu(MenuState.STAGE_MENU);
				DrawingSurface.background.setIsStage(false);
				p.delay(100);
			} 
			
			else if (text.equals("Choose Player One")) {
				DrawingSurface.background.setMenu(MenuState.PLAYER_ONE_SELECT);
				DrawingSurface.background.setIsStage(false);
				p.delay(100);
			} 
			
			
			
			else if (text.equals("Choose Player Two")) {
				DrawingSurface.background.setMenu(MenuState.PLAYER_TWO_SELECT);
				DrawingSurface.background.setIsStage(false);
				p.delay(100);
			}
			
			
			
			else if (text.equals("Smurf")) {
				if (DrawingSurface.background.getMenu().equals(MenuState.PLAYER_ONE_SELECT)) {
					DrawingSurface.p1.setPlayerState(PlayerState.SMURF);
					DrawingSurface.background.setMenu(MenuState.PLAYER_TWO_SELECT);
					DrawingSurface.changePlayers(DrawingSurface.p1, PlayerState.SMURF);
					DrawingSurface.background.setIsStage(false);
					p.delay(100);
				} else {
					DrawingSurface.p2.setPlayerState(PlayerState.SMURF);
					DrawingSurface.background.setIsStage(true);
					DrawingSurface.changePlayers(DrawingSurface.p2, PlayerState.SMURF);
				}
			}
			
			
			
			else if (text.equals("Anime?")) {
				if (DrawingSurface.background.getMenu().equals(MenuState.PLAYER_ONE_SELECT)) {
					DrawingSurface.p1.setPlayerState(PlayerState.ANIME);
					DrawingSurface.background.setMenu(MenuState.PLAYER_TWO_SELECT);
					DrawingSurface.background.setIsStage(false);
					DrawingSurface.changePlayers(DrawingSurface.p1, PlayerState.ANIME);
					p.delay(100);
				} else {
					DrawingSurface.p2.setPlayerState(PlayerState.ANIME);
					DrawingSurface.background.setIsStage(true);
					DrawingSurface.changePlayers(DrawingSurface.p2, PlayerState.ANIME);
				}
			} 
			
			
			
			else if (text.equals("Trump")) {
				if (DrawingSurface.background.getMenu().equals(MenuState.PLAYER_ONE_SELECT)) {
					DrawingSurface.p1.setPlayerState(PlayerState.TRUMP);
					DrawingSurface.changePlayers(DrawingSurface.p1, PlayerState.TRUMP);
					DrawingSurface.background.setMenu(MenuState.PLAYER_TWO_SELECT);
					DrawingSurface.background.setIsStage(false);
					p.delay(100);
				} else {
					DrawingSurface.p2.setPlayerState(PlayerState.TRUMP);
					DrawingSurface.changePlayers(DrawingSurface.p2, PlayerState.TRUMP);
					DrawingSurface.background.setIsStage(true);
				}
			} 
			
			
			else if (text.equals("FIGHT!")) {
				DrawingSurface.background.setIsStage(true);
			} else if (text.equals("Quit")) {
				System.exit(0);
			}
		}

		if (!(text.equals("Play") || text.equalsIgnoreCase("Next")))
			p.fill(255, 255, 255, 180);
		else
			p.fill(255, 255, 255);

		if (white) {
			p.noFill();
			p.noStroke();
		}

		p.rect(x, y, width, height);
		if (!white)
			p.fill(0);
		else
			p.fill(255);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize((p.height + p.width) / 80);

		if (transparent) {
			p.noFill();
			p.noStroke();
			text = "";
		}

		if (center)
			p.text(text, x, y);
		else
			p.text(text, x + width / 2, y + height / 2);

		p.popStyle();

	}

	public boolean buttonClicked() {
		boolean result = false;
		if (!center)
			result = (p.mouseX >= x && p.mouseX <= x + width && p.mouseY >= y && p.mouseY <= y + height);
		else
			result = (p.mouseX >= x - width / 2 && p.mouseX <= x + width / 2 && p.mouseY >= y - height / 2
					&& p.mouseY <= y + height / 2);
		return (p.mousePressed && result);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
