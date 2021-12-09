package backend.model;

public class Square extends Rectangle{
    public Square(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado: [%s, %s]", getTopLeft(), getBottomRight());
    }
}
