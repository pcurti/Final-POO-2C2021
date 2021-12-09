package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Figure {

    private final Point centerPoint;
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

}
