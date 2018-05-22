package stages;

import fieldElements.Button;
import fieldElements.Tree;
import main.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class OutsideField extends StageType {

	private PImage outsideField;
	private PImage treeImage;
	private Tree tree1, tree2;

	public OutsideField(PApplet p) {
		super(p);
		outsideField = p.loadImage("stageImages" + Stage.fileSeparator + "OutdoorField.jpg");
		outsideField.resize(p.width, p.height);

		treeImage = p.loadImage("stageImages" + Stage.fileSeparator + "Tree.png");

		super.setGround(240);
		super.setStageImage(outsideField);

		tree1 = new Tree(p, 0, (int) (p.height - ground - treeImage.height * 2));
		tree2 = new Tree(p, p.width - treeImage.width * 2, (p.height - ground - treeImage.height * 2));
	}

	public void draw() {
		super.draw();
		// p.translate((float) (-DrawingSurface.p1.getX() + DrawingSurface.p1.getWidth()
		// / 2),
		// (float) -DrawingSurface.p1.getY() + DrawingSurface.p1.getHeight() / 2);

		outsideField.resize(p.width, p.height);
		Button menu = new Button((int) (p.width / 2.65), 0, p, "Menu");
		menu.draw();

		tree1.draw();
		tree2.draw();
	}

	public Tree[] getTrees() {
		return new Tree[] { tree1, tree2 };
	}
}
