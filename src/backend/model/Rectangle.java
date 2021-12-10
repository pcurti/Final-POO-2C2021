package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    protected Point topLeft, bottomRight;

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
    public Rectangle getClone(){
        Rectangle clone = new Rectangle(new Point[points.length]);
        //copying points
        clone.points = getClonedPoints();
        clone.topLeft=clone.points[0];
        clone.bottomRight=clone.points[1];
        //copying drawing properties
        Color border = cloneColor(getBorderColor());
        Color fill = cloneColor(getFillColor());
        clone.setDrawingProperties(fill, border, getBorderWidth());
        //default to be unselected
        clone.unSelect();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }

    protected double base() {
        return Math.abs(bottomRight.getX() - topLeft.getX());
    }

    protected double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
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
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

}

