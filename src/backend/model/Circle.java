package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Figure {

    private final Point centerPoint;
    private final double radius;

    public Circle(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void changePosition(double diffX, double diffY) {
        centerPoint.setY(centerPoint.getY() + diffY);
        centerPoint.setX(centerPoint.getX() + diffX);
    }

    @Override
    public boolean hasPoint(Point point) {
        return Math.hypot(centerPoint.getX() - point.getX(), centerPoint.getY() - point.getY()) < radius;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        double diameter = radius * 2;
        gc.fillOval(centerPoint.getX() - radius, centerPoint.getY() - radius, diameter, diameter);
        gc.strokeOval(centerPoint.getX() - radius, centerPoint.getY() - radius, diameter, diameter);
    }
}
