package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Rectangle{

    public Ellipse(Point[]points) {
        super(points);
    }


    @Override
    public boolean hasPoint(Point point) {
        double diffX = point.getX() - centerPoint().getX();
        double diffY = point.getY() - centerPoint().getY();
        double xCalculation = Math.pow(diffX, 2) / Math.pow(base() / 2, 2);
        double yCalculation = Math.pow(diffY, 2) / Math.pow(height() / 2, 2);
        return Double.compare(xCalculation + yCalculation, 1) <= 0;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.fillOval(getTopLeft().getX(), getTopLeft().getY(), base(), height());
        gc.strokeOval(getTopLeft().getX(), getTopLeft().getY(), base(), height());
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, semi-eje Mayor: %.2f, semi-eje Menor: %.2f]", centerPoint(), base(), height());
    }
    private Point centerPoint() {
        return new Point(base()/2 + getTopLeft().getX(), height()/2 + getTopLeft().getY());
    }

    @Override
    public Ellipse clone(){
        Ellipse clone = new Ellipse(new Point[points.length]);
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
