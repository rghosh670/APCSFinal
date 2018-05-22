
package stages;

import data.User;
import enums.MenuState;
import fieldElements.Button;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PConstants;

public class PlayerTwoSelect extends PlayerOneSelect {
	
	private User u;
	private boolean smurf, anime, trump;

	public PlayerTwoSelect(PApplet p) {
		super(p);
	}

	public void draw() {		
		if (u == null && DrawingSurface.p1.getUser() != null) {
			u = DrawingSurface.p1.getUser();
			smurf = u.getImages().contains("Smurf");
			anime = u.getImages().contains("Anime");
			trump = u.getImages().contains("Trump");
		}
		System.out.println("2: " + smurf + ", " + anime + ", " + trump);
		
		if (smurf) drawSmurf();		
		if (anime) drawAnime();		
		if (trump) drawTrump();
		
		title = new Button(p.width / 2, p.height / 4, 0, 0, p, "Player Two Select", true, true, false);
		super.draw();

	}

}
