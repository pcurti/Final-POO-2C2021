package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figure {

    private Point centerPoint;
    private final double radius;

    public Circle(Point[] point, double radius) {
        super(point);
        this.centerPoint = point[0];
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    @Override
    public boolean hasPoint(Point point) {
        return centerPoint.distance(point) < radius;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        double diameter = radius * 2;
        gc.fillOval(centerPoint.getX() - radius, centerPoint.getY() - radius, diameter, diameter);
        gc.strokeOval(centerPoint.getX() - radius, centerPoint.getY() - radius, diameter, diameter);
    }

    @Override
    public boolean isContainedIn(Rectangle container) {
        Point topLeft = new Point(centerPoint.getX() - radius, centerPoint.getY() - radius);
        Point bottomRight = new Point(centerPoint.getX() + radius, centerPoint.getY() + radius);
        return container.hasPoint(topLeft) && container.hasPoint(bottomRight);
    }

    @Override
    public Circle clone() {
        Circle clone = new Circle(new Point[points.length],radius);
        //copying points
        for (int i = 0; i < points.length; i++) {
            clone.points[i] = new Point(points[i].getX(), points[i].getY());
        }
        clone.centerPoint=clone.points[0];
        //copying drawing properties
        Color border = new Color(getBorderColor().getRed(), getBorderColor().getGreen(), getBorderColor().getBlue(), getBorderColor().getOpacity());
        Color fill = new Color(getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), getFillColor().getOpacity());
        clone.setDrawingProperties(fill, border, getBorderWidth());
        //default to be unselected
        clone.unSelect();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}
