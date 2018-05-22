package processingShapes;
import java.awt.Color;

import processing.core.PApplet;

/**
 * This class creates a shape using processing methods
 * 
 * @author ksrinivas788
 *
 */
public abstract class Shape {
	/**
	 * Represents the x coordinate of the shape
	 */
	protected float x;
	
	/**
	 * Represents the y coordinate of the shape
	 */
	protected float y;
	private Color strokeColor, fillColor;
	private float strokeWeight;
	
	/**
	 * Constructor of Shape, taking in point (x, y) 
	 *
	 * @param x The x coordinate of the shape
	 * @param y The y coordinate of the shape
	 */
	public Shape(double x, double y) {
		this(x, y, Color.BLACK, Color.WHITE, 1.f);
	}
	
	/**
	 * Constructor of Shape, taking in point (x, y) along with draw and fill color as well as draw weight
	 * 
	 * @param x The x coordinate of the shape
	 * @param y The y coordinate of the shape
	 * @param strokeColor The color of stroke drawing the shape
	 * @param fillColor The color to fill the shape
	 * @param strokeWeight The weight of the stroke
	 */
	public Shape(double x, double y, Color strokeColor, Color fillColor, float strokeWeight) {
		this.x = (float) x;
		this.y = (float) y;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.strokeWeight = strokeWeight;
	}
	
	/**
	 * Accessor method for x
	 * 
	 * @return double x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Accessor method for y
	 * 
	 * @return double y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the stroke color to the Color passed in
	 * 
	 * @param strokeColor The Color object of the new color desired
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	/**
	 * Sets the fill color to the Color passed in
	 * 
	 * @param fillColor The Color object of the new color desired
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	/**
	 * Sets the stroke weight to the float passed in
	 * 
	 * @param strokeWeight The new stroke weight as a float
	 */
	public void setStrokeWeight(float strokeWeight) {
		this.strokeWeight = strokeWeight;
	}
	
	/**
	 * Sets point (x, y) to new point (x, y)
	 * 
	 * @param x The x coordinate of the new point
	 * @param y The y coordinate of the new point
	 */
	public void move(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	/**
	 * Adds to values of (x, y)
	 * 
	 * @param x The amount to change the x coordinate by
	 * @param y The amount to change the y coordinate by
	 */
	public void moveBy(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	//public abstract boolean isIntersecting(Shape s); // passing in Shape is example of polymorphism
	
	/**
	 * Draws a PApplet
	 * 
	 * @param drawer The PApplet on which to draw
	 */
	public void draw(PApplet drawer) {
		drawer.stroke(strokeColor.getRGB());
		drawer.fill(fillColor.getRGB());
		drawer.strokeWeight(strokeWeight);
	}
	
	public boolean equals(float f1, float f2) {
		return this.equals(f1, f2, 0.01f);
	}
	
	public boolean equals(float f1, float f2, float tolerance) {
		if (Math.abs(f1 - f2) <= tolerance) {
			return true;
		}
		return false;
	}
	
	public boolean equals(double d1, double d2) {
		return this.equals(d1, d2, 0.01);
	}
	
	public boolean equals(double d1, double d2, double tolerance) {
		if (Math.abs(d1 - d2) <= tolerance) {
			return true;
		}
		return false;
	}
}
