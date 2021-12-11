package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle{
    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado: [%s, %s]", getTopLeft(), getBottomRight());
    }

    @Override
    public Square getClone(){
        Square clone = new Square(getTopLeft().getClone(), getBottomRight().getClone());
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
