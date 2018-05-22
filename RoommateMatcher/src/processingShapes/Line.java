package processingShapes;
import java.awt.Color;
import processing.core.PApplet;

/*
 * Evan Su's review
 * 2 compliments:
 * 	- Bounding box checks are quick and efficient.
 *  - Using the Double class to check if the intersection coordinates are finite is pretty cool. Better than what I used.
 * 
 * 2 suggestions for improvements:
 *  - If you want, you could edit the intersection-point-drawing-thingy so that it only draws the point iff the intersection point is valid.
 *  - Might want to add some documentation/comments. TBH I don't think this is a big problem given that your code is concise and often self explanatory,
 * 
 */

/**
 * This class models a Processing-drawable Line.
 * 
 * @author ksrinivas788
 *
 */
public class Line extends Shape {
	
	private float x2, y2;
	private double angle, length;
	
	/**
	 * Creates a new instance of a Line object
	 * 
	 * @param x1 The x coordinate of the first endpoint
	 * @param y1 The y coordinate of the first endpoint
	 * @param x2 The x coordinate of the second endpoint
	 * @param y2 The y coordinate of the second endpoint
	 */
	public Line(double x1, double y1, double x2, double y2) {
		this(x1, y1, x2, y2, Color.BLACK, Color.WHITE, 1.f);
	}
	
	/**
	 * Creates a new Line with starting point (x1, y1) and of specified length and angle
	 * 
	 * @param x1 The x coordinate of the first endpoint
	 * @param y1 The y coordinate of the first endpoint
	 * @param angle The angle of the line relative to the horizontal in radians
	 * @param length The length of the line in pixels
	 */
	public Line(int x1, int y1, double angle, double length) {
		this(x1, y1, (float) (x1 + Math.cos(angle)*length), (float) (y1 - Math.sin(angle)*length), Color.BLACK, Color.WHITE, 1.f);
	}
	
	/**
	 * Creates a new Line with starting point (x1, y1) and of specified length, angle, color, and weight
	 * 
	 * @param x1 The x coordinate of the first endpoint
	 * @param y1 The y coordinate of the first endpoint
	 * @param angle The angle of the line relative to the horizontal in radians
	 * @param length The length of the line in pixels
	 * @param stroke The Color object representing the stroke color
	 * @param fill The Color object representing the fill color
	 * @param strokeWeight The width of the stroke
	 * @param s Parameter to differentiate nearly identical constructors - any value can be passed in
	 */
	public Line(int x1, int y1, double angle, double length, Color stroke, Color fill, float strokeWeight, String s) {
		this(x1, y1, (float) (x1 + Math.cos(angle)*length), (float) (y1 - Math.sin(angle)*length), stroke, fill, strokeWeight);
		this.angle = angle;
		this.length = length;
	}
	
	/**
	 * Creates a new instance of a Line object with specified colors and weights
	 * The ultimate constructor that all other constructors call
	 * 
	 * @param x1 The x coordinate of the first endpoint
	 * @param y1 The y coordinate of the first endpoint
	 * @param x2 The x coordinate of the second endpoint
	 * @param y2 The x coordinate of the second endpoint
	 * @param stroke The Color object representing the drawing color
	 * @param fill The Color object representing the filling color
	 * @param strokeWeight The weight of the drawing stroke
	 */
	public Line(double x1, double y1, double x2, double y2, Color stroke, Color fill, float strokeWeight) {
		super(x1, y1, stroke, fill, strokeWeight);
		this.x2 = (float) x2;
		this.y2 = (float) y2;
	}
	
	/**
	 * Rotates the line by angle delta
	 * 
	 * @param delta The angle in radians to rotate the line. Positive goes clockwise and negative goes counterclockwise
	 */
	public void rotateLine(double delta) {
		angle += delta;
		setPoint2(super.x + Math.cos(angle)*length, super.y - Math.sin(angle)*length);
	}
	
	public void move(double x, double y) {
		setPoint2(x + (this.x2 - super.x), y + (this.y2 - super.y));
		super.x = (float) x;
		super.y = (float) y;
	}
	
	public void moveBy(double x, double y) {
		super.x += x;
		super.y += y;
		setPoint2(this.x2 + x, this.y2 + y);
	}
	
	/**
	 * Sets the first endpoint of the line to (x,y)
	 * 
	 * @param x X coordinate of first endpoint
	 * @param y Y coordinate of first endpoint
	 */
	public void setPoint1(double x, double y) {
		super.x = (float) x;
		super.y = (float) y;
	}
	
	/**
	 * Sets the second endpoint of the line to (x,y)
	 * 
	 * @param x X coordinate of the second endpoint
	 * @param y Y coordinate of the second endpoint
	 */
	public void setPoint2(double x, double y) {
		this.x2 = (float) x;
		this.y2 = (float) y;
	}
	
	public float[] getPoint2() {
		float[] points = new float[2];
		points[0] = this.x2;
		points[1] = this.y2;
		return points;
	}
	
	/**
	 * Calculates the intersection point of current line with another line
	 * 
	 * @param other The line with which this line intersects
	 * @return float[] which contains the x and y coordinates of the intersection point
	 */
	public float[] intersects(Line other) {
		float x3 = (float) other.x;
		float x4 = other.x2;
		float y3 = (float) other.y;
		float y4 = other.y2;
		float intersectionX = ((((float)super.x*y2 - super.y*x2)*(x3 - x4)) - ((super.x - x2)*(x3*y4 - y3*x4))) / (((super.x - x2)*(y3 - y4)) - ((super.y - y2)*(x3 - x4)));
		float intersectionY = ((((float)super.x*y2 - super.y*x2)*(y3 - y4)) - ((super.y - y2)*(x3*y4 - y3*x4))) / (((super.x - x2)*(y3 - y4)) - ((super.y - y2)*(x3 - x4)));
		float[] intersection = null;
		
		// if intersection point is valid on given line segments
		if (Double.isFinite(intersectionX) && Double.isFinite(intersectionY)) {
			if (((intersectionX <= x2 && intersectionX >= super.x) || (intersectionX >= x2 && intersectionX <= super.x)) &&
					((intersectionX <= x4 && intersectionX >= x3) || (intersectionX >= x4 && intersectionX <= x3))) {
				if (((intersectionY <= y2 && intersectionY >= super.y) || (intersectionY >= y2 && intersectionY <= super.y)) &&
						((intersectionY <= y4 && intersectionY >= y3) || (intersectionY >= y4 && intersectionY <= y3))) {
					intersection = new float[] {intersectionX, intersectionY};
					return intersection;
				}
			}
		}
		return intersection;
	}
	
	public boolean contains(double x, double y) {
		double smallSlope = (y - this.getY()) / (x - this.getX());
		double lineSlope = (this.getPoint2()[1] - this.getY()) / (this.getPoint2()[0] - this.getX());
		if (((x < this.getX() && x > this.getPoint2()[0]) ||
				(x > this.getX() && x < this.getPoint2()[0])) &&
				((y < this.getY() && y > this.getPoint2()[1]) ||
				(y > this.getY() && y < this.getPoint2()[1]))) {
			if (equals(smallSlope, lineSlope)) {
				return true;
			}			
		}
		return false;
	}
	
	/**
	 * Draws the line on the PApplet
	 * 
	 * @param marker The PApplet used to draw the line
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.line((float)super.x, (float)super.y, x2, y2);
	}
	
}
