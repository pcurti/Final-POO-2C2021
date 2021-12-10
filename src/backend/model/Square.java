package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle{
    public Square(Point[] points) {
        super(points);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado: [%s, %s]", getTopLeft(), getBottomRight());
    }

    @Override
    public Square clone(){
        Square clone = new Square(new Point[points.length]);
        //copying points
        for (int i = 0; i < points.length; i++) {
            clone.points[i] = new Point(points[i].getX(), points[i].getY());
        }
        clone.topLeft=clone.points[0];
        clone.bottomRight=clone.points[1];
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
