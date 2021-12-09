package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point[]points) {
        super(points);
        topLeft = points[0];
        bottomRight = points[1];
    }

    protected Point getTopLeft() {
        return topLeft;
    }

    protected Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean hasPoint(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX() &&
                point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.fillRect(topLeft.getX(), topLeft.getY(), base(), height());
        gc.strokeRect(topLeft.getX(), topLeft.getY(), base(), height());
    }

    @Override
    public boolean isContainedIn(Rectangle container) {
        return container.hasPoint(topLeft) && container.hasPoint(bottomRight);
    }

    protected double base() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    protected double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }
}
