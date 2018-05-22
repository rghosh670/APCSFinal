package fieldElements;

import processing.core.PApplet;
import processing.core.PImage;

public class FieldElement {
	protected PImage elementImage;
	protected PApplet p;
	protected float x, y;
	protected int width, height;

	public FieldElement(PApplet p, PImage elementImage, float x, float y, int width, int height) {
		this.p = p;
		this.elementImage = elementImage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.elementImage = elementImage;
		if (elementImage != null)
			this.elementImage.resize(width, height);
	}

	public void draw() {
		if (elementImage != null)
			p.image(elementImage, x, y);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setImage(PImage image) {
		this.elementImage = image;
	}

}
