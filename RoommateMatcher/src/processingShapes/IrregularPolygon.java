package processingShapes;

import java.awt.Color;
import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.Arrays;

import processingShapes.Line;
import processingShapes.Shape;
import processing.core.PApplet; // for Processing
import processing.core.PShape;

/*
 * I like the implementation of each method
 * 
 * 
 */
public class IrregularPolygon extends Shape {
	private ArrayList<Point2D.Double> myPolygon;
	private ArrayList<Line> lines;

	// constructors
	public IrregularPolygon() {
		this(new ArrayList<Point2D.Double>() {
			{
				add(new Point2D.Double(200, 200));
				add(new Point2D.Double(150, 200 - 100 * Math.PI / 3));
				add(new Point2D.Double(100, 200));
				// add(new Point2D.Double(20, 10));
				// add(new Point2D.Double(70, 20));
				// add(new Point2D.Double(50, 50));
				// add(new Point2D.Double(0, 40));
			}
		});
	}

	public IrregularPolygon(ArrayList<Point2D.Double> points) {
		super(points.get(0).getX(), points.get(0).getY());
		myPolygon = points;
		lines = new ArrayList<Line>();
		toLines();
	}

	// public methods
	public void add(Point2D.Double aPoint) {
		myPolygon.add(aPoint);
		toLines();
	}

	public void toLines() {
		untangle();
		for (int i = 0; i < myPolygon.size() - 1; i++) {
			lines.add(new Line(myPolygon.get(i).getX(), myPolygon.get(i).getY(), myPolygon.get(i + 1).getX(),
					myPolygon.get(i + 1).getY()));
		}
		lines.add(new Line(myPolygon.get(myPolygon.size() - 1).getX(), myPolygon.get(myPolygon.size() - 1).getY(),
				myPolygon.get(0).getX(), myPolygon.get(0).getY()));
	}

	public void draw(PApplet marker) {
		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).draw(marker);
		}
		if (lines.size() > 0) {
			for (Line line : lines) {
				line.draw(marker);
			}
		}
	}

	public double calcPerimeter() {
		double perimeter = 0;
		for (Line line : lines) {
			double length = Math.sqrt(Math.pow(Math.abs(line.getX() - line.getPoint2()[0]), 2)
					+ Math.pow(Math.abs(line.getY() - line.getPoint2()[1]), 2));
			perimeter += length;
		}
		return perimeter;
	}

	public double calcArea() {
		double area = 0;
		for (int i = 0; i < myPolygon.size() - 1; i++) {
			area += myPolygon.get(i).getX() * myPolygon.get(i + 1).getY();
			area -= myPolygon.get(i).getY() * myPolygon.get(i + 1).getX();
		}
		area += myPolygon.get(myPolygon.size() - 1).getX() * myPolygon.get(0).getY();
		area -= myPolygon.get(myPolygon.size() - 1).getY() * myPolygon.get(0).getX();
		area /= 2;
		return Math.abs(area);
	}

	public boolean isInside(double x, double y) {
		double intersections = 0;
		boolean onShape = false;
		Line hLine = new Line(x, y, 10 ^ 4, y);
		Line pointLine = new Line(x - 1, y, x + 1, y);
		for (Line line : lines) {
			if (hLine.intersects(line) != null) {
				intersections++;
			}
			if (pointLine.intersects(line) != null) {
				onShape = true;
			}
		}
		if (intersections % 2 == 1 || onShape) {
			return true;
		}
		return false;
	}

	public ArrayList<float[]> calcIntersections() {
		ArrayList<float[]> intersections = new ArrayList<float[]>();
		for (Line line1 : lines) {
			for (Line line2 : lines) {
				if (line1.intersects(line2) != null && Math.abs(line1.intersects(line2)[0] - line1.getX()) > 0.01
						&& Math.abs(line1.intersects(line2)[0] - line2.getX()) > 0.01
						&& Math.abs(line1.intersects(line2)[1] - line1.getY()) > 0.01
						&& Math.abs(line1.intersects(line2)[1] - line2.getY()) > 0.01) {
					intersections.add(line1.intersects(line2));
				}
			}
		}
		ArrayList<float[]> f0 = new ArrayList<float[]>();
		for (int i = 0; i < intersections.size(); i++) {
			float[] point = intersections.get(i);
			boolean duplicate = false;
			for (float[] p2 : f0) {
				if (Arrays.equals(point, p2)) {
					duplicate = true;
				}
			}
			if (duplicate) {
				intersections.remove(point);
				i--;
			} else {
				f0.add(point);
			}
		}
		return intersections;
	}

	public int countIntersections() {
		return calcIntersections().size();
	}

	public Point2D.Double getClosestPoint(Point2D.Double refPoint) {
		Point2D.Double closest = myPolygon.get(0);
		double min = closest.distance(refPoint);
		for (int i = 0; i < myPolygon.size(); i++) {
			if (myPolygon.get(i).distance(refPoint) < min) {
				min = (myPolygon.get(i).distance(refPoint));
				closest = myPolygon.get(i);
			}
		}
		return closest;
	}

	public void untangle() {
		for (Point2D.Double point : myPolygon) {
			double minAngle = 2 * Math.PI;
			Point2D.Double next = point;
			for (Point2D.Double point2 : myPolygon) {
				if (!point.equals(point2)) {
					double angle = Math.atan(point.distance(point2));
					if (angle < minAngle) {
						minAngle = angle;
						next = point2;
					}
				}
			}
			lines.add(new Line(point.getX(), point.getY(), next.getX(), next.getY(), Color.RED, Color.RED, 1.f));
		}

		// CONTAINS ERRORS STILL (as of 23:28 1/11/18)
		ArrayList<float[]> intersections = calcIntersections();
		if (calcIntersections().size() > 0) {
			for (int i = 0; i < intersections.size() - 1; i++) {
				for (int j = 0; j < lines.size() - 1; j++) {
					System.out.println(i + "" + intersections.get(i));
					if (intersections.get(i) != null
							&& lines.get(j).contains(intersections.get(i)[0], intersections.get(i)[1])) {
						lines.remove(j);
						j--;
					}
				}
			}
		}
	}

	public void fillSection(double x, double y) {
		System.out.println(isInside(x, y));
		PShape shape;
	}
}