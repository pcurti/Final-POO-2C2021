package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figure{

    private Point a, b;
    private final static double CLICK_HELPER = 2;

    public Line(Point[]points) {
        super(points);
        this.a = points[0];
        this.b = points[1];
    }



    @Override
    public boolean hasPoint(Point point) {
        Point ac = new Point(point.getX() - a.getX(), point.getY() - a.getY());
        Point ab = new Point(b.getX() - a.getX(), b.getY() - a.getY());
        double cross_product = ac.getX() * ab.getY() - ac.getY() * ab.getX();
        if(Double.compare(cross_product,0) == 0) {
            double Kac = ac.getX() * ab.getX() + ac.getY() + ab.getY();
            double Kab = ab.getX() * ab.getX() + ab.getY() * ab.getY();
            return Kac >= CLICK_HELPER && Kac <= Kab + CLICK_HELPER;
        }
        return false;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    @Override
    public boolean isContainedIn(Rectangle container) {
        return container.hasPoint(a) && container.hasPoint(b);
    }

    @Override
    public String toString() {
        return String.format("Line [%s, %s]", a, b);
    }
    @Override
    public Line clone(){
        Line clone = new Line(new Point[points.length]);
        //copying points
        for (int i = 0; i < points.length; i++) {
            clone.points[i] = new Point(points[i].getX(), points[i].getY());
        }
        clone.a=clone.points[0];
        clone.b=clone.points[1];
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
