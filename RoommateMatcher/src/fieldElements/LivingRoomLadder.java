package fieldElements;

import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;


/**
 * 
 * This class models a ladder present in the living room stage
 * 
 * @author rohitghosh
 *
 */
public class LivingRoomLadder extends Ladder {

	private PImage originalLadderPic, ladderPic;

	public LivingRoomLadder(PApplet p, float x, float y, int width, int height, boolean left) {
		super(p, null, x, y, width, height);

		originalLadderPic = p.loadImage("stageImages" + Stage.fileSeparator + "Ladder.png");

		if (left)
			ladderPic = originalLadderPic.get(0, originalLadderPic.height / 2, originalLadderPic.width,
					originalLadderPic.height / 2);
		else
			ladderPic = originalLadderPic.get(0, 0, originalLadderPic.width, originalLadderPic.height / 2);

		ladderPic.resize(width, height);

		super.setImage(ladderPic);
	}

}
