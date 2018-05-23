package stages;

import enums.MenuState;
import enums.StageState;
import main.DrawingSurface;
import main.RoommateMatcher;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is used to control the stage backdrop being displayed
 * 
 * @author rghosh293
 *
 */
public class Stage {

	public static String fileSeparator = System.getProperty("file.separator");

	private StageState ss;
	private MenuState ms;
	private PApplet p;
	private boolean isStage;
	private StageType livingRoom, library, outdoorField, stageMenu, playerMenu, instructions, matchMaker,
			playerOneSelect, playerTwoSelect, mainMenu;

	public Stage(PApplet p) {
		this.p = p;
		ss = StageState.OUTDOOR_FIELD;
		ms = MenuState.PLAYER_MENU;
		livingRoom = new LivingRoom(p);
		library = new Library(p);
		outdoorField = new OutsideField(p);
		stageMenu = new StageMenu(p);
		playerMenu = new PlayerMenu(p);
		instructions = new Instructions(p);
		matchMaker = new MatchFinder(p);
		mainMenu = new MainMenu(p);
		playerOneSelect = new PlayerOneSelect(p);
		playerTwoSelect = new PlayerTwoSelect(p);
	}

	public void draw() {
		if (isStage) {
			switch (ss) {
			case LIBRARY:
				library.draw();
				break;
			case LIVING_ROOM:
				livingRoom.draw();
				break;
			case OUTDOOR_FIELD:
				outdoorField.draw();
				break;

			default:
			}
		} else {
			switch (ms) {
			case PLAYER_MENU:
				playerMenu.draw();
				break;
			case MAIN_MENU:
				mainMenu.draw();
				break;
			case STAGE_MENU:
				stageMenu.draw();
				break;
			case INSTRUCTIONS:
				instructions.draw();
				break;
			case MATCH_MAKER:
				((MatchFinder) matchMaker).setup(RoommateMatcher.getUser());
				matchMaker.draw();
				break;
			case PLAYER_ONE_SELECT:
				playerOneSelect.draw();
				break;
			case PLAYER_TWO_SELECT:
				playerTwoSelect.draw();
				break;
			default:
			}
		}
	}

	public StageType getStageType() {

		switch (ss) {

		case LIBRARY:
			return library;

		case LIVING_ROOM:
			return livingRoom;

		case OUTDOOR_FIELD:
			return outdoorField;

		default:
			return library;
		}

	}

	public void setStage(StageState ss) {
		if (DrawingSurface.p1 != null && DrawingSurface.p2 != null && StageType.inGame) {
			DrawingSurface.background.getStageType().resetPlayers();
		}
		this.ss = ss;
	}

	public StageState getStage() {
		return ss;
	}

	public void setMenu(MenuState ms) {
		this.ms = ms;
	}

	public MenuState getMenu() {
		return ms;
	}

	public boolean getIsStage() {
		return isStage;
	}

	public void setIsStage(boolean is) {
		isStage = is;
	}
}
