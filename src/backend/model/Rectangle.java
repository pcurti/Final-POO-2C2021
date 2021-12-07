package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        if(topLeft.compareTo(bottomRight)<0) {
            this.topLeft= topLeft;
            this.bottomRight = bottomRight;
        }else {
            this.topLeft= bottomRight;
            this.bottomRight = topLeft;
        }
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
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
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

    private double base() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    private double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }
}
