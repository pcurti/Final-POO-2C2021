package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figure{

    private Point a, b;
    private final static double CLICK_HELPER = 2;

    public Line(Point a,Point b) {
        super(new Point[]{a,b});
        this.a = a;
        this.b = b;
    }

    @Override
    public Line getClone(){
        Line clone = new Line(a.getClone(),b.getClone());
        //copying drawing properties
        Color border = cloneColor(getBorderColor());
        Color fill = cloneColor(getFillColor());
        clone.setDrawingProperties(fill, border, getBorderWidth());
        //default to be unselected
        clone.unSelect();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
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
    public String toString() {
        return String.format("Line [%s, %s]", a, b);
    }
}
