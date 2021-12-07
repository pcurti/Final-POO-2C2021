package backend.model;

public class Square extends Rectangle{
    public Square(Point topLeft, double side) {
        super(topLeft, new Point(topLeft.getX() + side, topLeft.getY() - side));
    }
}
