package backend.model;

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
    public void redraw(double diffX, double diffY) {
        bottomRight.setX(bottomRight.getX() + diffX);
        bottomRight.setY(bottomRight.getY() + diffY);
        topLeft.setX(topLeft.getX() + diffX);
        topLeft.setY(topLeft.getY() + diffY);
    }
}
