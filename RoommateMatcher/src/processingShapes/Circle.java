package processingShapes;

import java.awt.Color;

import processing.core.PApplet;

/**
 * This class models a Processing-drawable Circle.
 * 
 * @author ksrinivas788
 *
 */
public class Circle extends Shape2D {
	private double r;

	/**
	 * Creates a default instance of a Circle object with all dimensions set to
	 * zero.
	 */
	public Circle() {
		this(0, 0, 0);
	}

	/**
	 * Creates a new instance of a Circle object
	 * 
	 * @param x
	 *            The x coordinate of the center
	 * @param y
	 *            The y coordinate of the center
	 * @param radius
	 *            The radius of the circle
	 */
	public Circle(double x, double y, double radius) {
		this(x, y, radius, Color.BLACK, new Color(255, 255, 255, 0), 1.f);
	}

	/**
	 * Creates a new instance of a Circle object with specified stroke and fill
	 * color and stroke weight
	 * 
	 * @param x1
	 *            The x coordinate of the center
	 * @param y1
	 *            The y coordinate of the center
	 * @param radius
	 *            The radius of the circle
	 * @param stroke
	 *            The Color object representing the stroke color
	 * @param fill
	 *            The Color object representing the fill color
	 * @param strokeWeight
	 *            The width of the stroke
	 */
	public Circle(double x1, double y1, double radius, Color stroke, Color fill, float strokeWeight) {
		super(x1, y1, stroke, fill, strokeWeight);
		this.r = radius;
	}

	/**
	 * Calculates and returns the circumference of the circle
	 * 
	 * @return double perimeter of the circle
	 */
	public double calcPerimeter() {
		return 2 * r * Math.PI;
	}

	/**
	 * Calculates and returns the area of the circle
	 * 
	 * @return double area of the circle
	 */
	public double calcArea() {
		return Math.PI * (Math.pow(r, 2));
	}

	/**
	 * Determines whether the point x,y is contained inside this circle
	 * 
	 * @param x
	 *            The x coordinate of given point
	 * @param y
	 *            The y coordinate of given point
	 * @return boolean whether the point (x,y) is inside the circle
	 */
	public boolean isPointInside(double x, double y) {
		return Math.pow((super.x - x), 2) + Math.pow((super.y - y), 2) <= Math.pow(r, 2);
		// marker.dist((float)x, (float)y, (float)this.x, (float)this.y) < r;
	}

	/**
	 * Draws a line from the center of the circle to the point (x,y)
	 * 
	 * @param x1
	 *            The x coordinate of given point
	 * @param y1
	 *            The y coordinate of given point
	 * @param marker
	 *            The PApplet used to draw the line
	 */
	public void lineFromCenter(double x1, double y1, PApplet marker) {
		marker.line((float) x, (float) y, (float) x1, (float) y1);
	}

	/**
	 * Mirrors the point (x,y) across the circumference of the circle
	 * 
	 * @param x1
	 *            The x coordinate of given point
	 * @param y1
	 *            The y coordinate of given point
	 * @param marker
	 *            The PApplet used to draw the ellipse
	 */
	public void mirrorPoint(double x1, double y1, PApplet marker) {
		marker.ellipse((float) this.x, (float) this.y, (float) (Math.abs(x1 - this.x) + r),
				(float) (Math.abs(y1 - this.y) + r));
	}

	/**
	 * Determines whether the current circle intersects another
	 * 
	 * @param other
	 *            Circle to check intersection with
	 * @return whether or not the circles are touching
	 */
	public boolean intersects(Circle other) {
		double distance = Math
				.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2));
		return distance < (this.getR() + other.getR());
	}

	/**
	 * Getter for the radius field
	 * 
	 * @return the radius as a double
	 */
	public double getR() {
		return this.r;
	}

	/**
	 * Draws a new instance of a Circle object with the center at (this.x, this.y)
	 * and a radius of this.r
	 * 
	 * @param marker
	 *            The PApplet used to draw the circle
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.ellipse((float) this.x, (float) this.y, (float) this.r, (float) this.r);
	}
}