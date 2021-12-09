package backend.model;

import javafx.scene.canvas.GraphicsContext;

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
}
