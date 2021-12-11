package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figure {

    private Point centerPoint;
    private final double radius;

    public Circle(Point point, double radius) {
        super(new Point[]{point});
        this.centerPoint = point;
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
    public Circle getClone() {
        Circle clone = new Circle(centerPoint.getClone(),radius);
        //copying drawing properties
        Color border = cloneColor(getBorderColor());
        Color fill = cloneColor(getFillColor());
        clone.setDrawingProperties(fill, border, getBorderWidth());
        //default to be unselected
        clone.unSelect();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}
