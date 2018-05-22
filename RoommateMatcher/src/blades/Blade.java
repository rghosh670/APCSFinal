
package blades;

import java.awt.geom.Point2D;

import character.Hitboxable;
import processing.core.PApplet;
/**
 * 
 * This class models a blade which is extended by different types of blades
 * 
 * @author ksrinivas788
 *
 */
public class Blade implements Hitboxable {
	
	public float angle;
	public float length;
	protected float x1, y1, x2, y2;
	private float a, b, c, d;
	protected PApplet drawer;
	public int count = 0;
	
	public Blade(float x, float y, float length, float angle) {
		this.angle = angle;
		this.length = length;
		this.x1 = x;
		this.y1 = y;
		this.x2 = (float) (x + length * Math.cos(angle));
		this.y2 = (float) (y - length * Math.sin(angle));
		a = 100;
		b = 200;
		c = 400; 
		d = 200;
	}
	
	public Point2D.Float intersects(Blade other) {
		float x3 = (float) other.x1;
		float x4 = other.x2;
		float y3 = (float) other.y1;
		float y4 = other.y2;
		float intersectionX = ((((float) x1 * y2 - y1 * x2) * (x3 - x4)) - ((x1 - x2) * (x3 * y4 - y3 * x4)))
				/ (((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4)));
		float intersectionY = ((((float) x1 * y2 - y1 * x2) * (y3 - y4)) - ((y1 - y2) * (x3 * y4 - y3 * x4)))
				/ (((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4)));

		// if intersection point is valid on given line segments
		if (Double.isFinite(intersectionX) && Double.isFinite(intersectionY)) {
			if (((intersectionX <= x2 && intersectionX >= x1) || (intersectionX >= x2 && intersectionX <= x1))
					&& ((intersectionX <= x4 && intersectionX >= x3) || (intersectionX >= x4 && intersectionX <= x3))) {
				if (((intersectionY <= y2 && intersectionY >= y1) || (intersectionY >= y2 && intersectionY <= y1))
						&& ((intersectionY <= y4 && intersectionY >= y3)
								|| (intersectionY >= y4 && intersectionY <= y3))) {
					return new Point2D.Float(intersectionX, intersectionY);
				}
			}
		}
		return null;
	}
	
	public void draw(PApplet drawer) {
		this.drawer = drawer;
//		drawer.stroke(0);
//		drawer.strokeWeight(2f);
//		drawer.line(x1, y1, x2, y2);
		if (drawer.keyPressed && drawer.key == 's') {
			System.out.println("s called");
			swing();
		}
	}
	
//	public void swing() {
//		int frames = 100;
//		for (int count = 0; count < frames; count++) {
//			double delta = count < frames / 2 ? -1 : 1;
//			angle += (delta) * Math.PI / frames;
//			this.x2 = (float) (x1 + length * Math.cos(angle));
//			this.y2 = (float) (y1 - length * Math.sin(angle));
////			drawer.ellipse(x2, y2, 5, 5);
////			drawer.line(x1, y1, x2, y2);
//			drawer.delay(1000 / 24);
//		}
//	}
	
	public void swing() {
		if (count >= 100) count = 0;
		if (count < 100) {
			double delta = count < 100/2 ? -1 : 1;
			angle += (delta)*Math.PI/100;
			this.x2 = (float) (x1 + length*Math.cos(angle));
			this.y2 = (float) (y1 - length*Math.sin(angle));
			count++;
		} 	
//		if (Math.abs(angle) < Math.PI/101) {
//			System.out.println("angle reset");
//			angle = (float) 0.0;
//		}
	}
	
	public void moveTo(float x, float y) {
		x1 = x;
		y1 = y;
		x2 = (float) (x + length * Math.cos(angle));
		y2 = (float) (y - length * Math.sin(angle));
	}

}
