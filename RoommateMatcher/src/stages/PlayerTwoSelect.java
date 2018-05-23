
package stages;

import data.User;
import enums.MenuState;
import fieldElements.Button;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class PlayerTwoSelect extends StageType {

	private PImage playerMenuBackground, smurfPic, animePic, trumpPic;
	protected Button title;
	private boolean smurf, anime, trump;
	private User u;

	public PlayerTwoSelect(PApplet p) {
		super(p);
		
		playerMenuBackground = p.loadImage("stageImages" + Stage.fileSeparator + "MenuBackground.jpg");
		playerMenuBackground.resize(p.width, p.height);

		smurfPic = p.loadImage("playerImages" + Stage.fileSeparator + "SmurfsRight.png");
		smurfPic = smurfPic.get(0, 0, smurfPic.width / 4, smurfPic.height / 4);
		smurfPic.resize((int) (smurfPic.width * 1.5), (int) (smurfPic.height * 1.5));

		animePic = p.loadImage("playerImages" + Stage.fileSeparator + "OtherGuy.png");
		animePic = animePic.get(0, 0, animePic.width / 5, animePic.height / 2);

		trumpPic = p.loadImage("playerImages" + Stage.fileSeparator + "Trump.png");
		trumpPic = trumpPic.get(0, 0, trumpPic.width / 6, trumpPic.height / 4);
		trumpPic.resize((int) (trumpPic.width * 2.25), (int) (trumpPic.height * 2.25));

		title = new Button(p.width / 2, p.height / 4, 0, 0, p, "Player Two Select", true, true, false);

		super.setStageImage(playerMenuBackground);
	}

	public void draw() {
		super.draw();
		p.pushMatrix();
		p.pushStyle();

		if (u == null && DrawingSurface.p1.getUser() != null) {
			u = DrawingSurface.p2.getUser();
			System.out.println("2: " + u.getName() + " " + u.getImages().toString());
			smurf = u.getImages().contains("Smurf");
			anime = u.getImages().contains("Anime");
			trump = u.getImages().contains("Trump");
		}
//		System.out.println("1: " + smurf + ", " + anime + ", " + trump);

		title.draw();

		p.imageMode(PConstants.CENTER);

		if (smurf) drawSmurf();		
		if (anime) drawAnime();		
		if (trump) drawTrump();
		
		Button m = new Button(0, p.height - p.height / 10, p.width / 6, p.height / 10, p, "Menu");
		m.draw();

		p.popStyle();
		p.popMatrix();
	}
	
	public void drawSmurf() {
		Button smurf = new Button(p.width / 5, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p, "Smurf",
				true, true);
		smurf.draw();
		

		Button smurf2 = new Button(p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30,
				p.width / 5, p.height / 5, p, "Smurf", false, true, true);
		smurf2.draw();
		p.image(smurfPic, smurf2.getX() + p.width / 10, (smurf2.getY() + smurf2.getHeight() / 2));
	}
	
	public void drawAnime() {
		Button anime = new Button(p.width / 2, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p, "Anime?",
				true, true);
		anime.draw();

		Button anime2 = new Button(p.width / 2 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30,
				p.width / 5, p.height / 5, p, "Anime?", false, true, true);
		anime2.draw();
		p.image(animePic, anime2.getX() + p.width / 10, (anime2.getY() + anime2.getHeight() / 2));
	}
	
	public void drawTrump() {
		Button trump = new Button(4 * p.width / 5, p.height / 2 + p.height / 7, p.width / 5, p.height / 10, p, "Trump",
				true, true);
		trump.draw();

		Button trump2 = new Button(4 * p.width / 5 - p.width / 10, p.height / 2 + p.height / 8 - p.height / 5 - 30,
				p.width / 5, p.height / 5, p, "Trump", false, true, true);
		trump2.draw();
		p.image(trumpPic, trump2.getX() + p.width / 10, (trump2.getY() + trump2.getHeight() / 2));		
	}

}
