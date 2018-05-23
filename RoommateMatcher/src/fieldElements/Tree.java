package fieldElements;

import character.Hitbox;
import character.Hitboxable;
import processing.core.PApplet;
import processing.core.PImage;
import stages.Stage;

/**
 * 
 * This class models a tree which is present in the Outdoor Field stage
 * 
 * @author vshirsat573
 *
 */
public class Tree extends FieldElement implements Hitboxable {
	private PImage treePic;
	private Hitbox trunkHitbox, branchHitbox;

	public Tree(PApplet p, float x, float y) {
		super(p, p.loadImage("stageImages" + Stage.fileSeparator + "Tree.png"), x, y,
				p.loadImage("stageImages" + Stage.fileSeparator + "Tree.png").width * 2,
				p.loadImage("stageImages" + Stage.fileSeparator + "Tree.png").height * 2);

		treePic = p.loadImage("stageImages" + Stage.fileSeparator + "Tree.png");

		width = treePic.width * 2;
		height = treePic.height * 2;

		treePic.resize(width, height);
		branchHitbox = new Hitbox(this, p, (int) (x + 50), (int) y, 350, 320);
		trunkHitbox = new Hitbox(this, p, (int) (x + 450 / 4), (int) (y + 320), 200, 280);
	}

	public Hitbox[] getHitboxes() {
		return new Hitbox[] { trunkHitbox, branchHitbox };
	}

	public void draw() {
		super.draw();
		branchHitbox.draw();
		trunkHitbox.draw();
	}
}
