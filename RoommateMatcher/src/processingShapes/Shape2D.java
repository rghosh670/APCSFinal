package processingShapes;

import java.awt.Color;

import processing.core.PApplet;

public abstract class Shape2D extends Shape {

	public Shape2D(double x, double y) {
		super(x, y);
	}

	public Shape2D(double x, double y, Color strokeColor, Color fillColor, float strokeWeight) {
		super(x, y, strokeColor, fillColor, strokeWeight);
	}

	/**
	 * Calculates area of the shape
	 * 
	 * @return Area of shape as a double
	 */
	public abstract double calcArea();

	/**
	 * Calculates perimeter of the shape
	 * 
	 * @return Perimeter of shape as a double
	 */
	public abstract double calcPerimeter();

	/**
	 * Figures out if the given point is inside the shape
	 * 
	 * @param x
	 *            The x coordinate of the given point
	 * @param y
	 *            The y coordinate of the given point
	 * @return boolean whether the point is inside
	 */
	public abstract boolean isPointInside(double x, double y);

	/**
	 * Draws a PApplet
	 * 
	 * @param drawer
	 *            The PApplet on which to draw
	 */
	public void draw(PApplet drawer) {
		super.draw(drawer);
		/*
		 * drawer.stroke(strokeColor.getRGB()); drawer.fill(fillColor.getRGB());
		 * drawer.strokeWeight(strokeWeight);
		 */
	}

}
