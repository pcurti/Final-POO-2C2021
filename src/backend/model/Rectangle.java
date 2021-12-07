package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft= topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void changePosition(double diffX, double diffY) {
        bottomRight.setX(bottomRight.getX() + diffX);
        bottomRight.setY(bottomRight.getY() + diffY);
        topLeft.setX(topLeft.getX() + diffX);
        topLeft.setY(topLeft.getY() + diffY);
    }



    @Override
    public boolean hasPoint(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX() &&
                point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillRect(topLeft.getX(), topLeft.getY(), base(), height());
        gc.strokeRect(topLeft.getX(), topLeft.getY(), base(), height());
    }

    protected double base() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    protected double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }
}
