package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure{

    private Point a, b;
    private final static double CLICK_HELPER = 2;
    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void changePosition(double diffX, double diffY) {
        a.setX(a.getX() + diffX);
        a.setY(a.getY() + diffY);
        b.setX(b.getX() + diffX);
        b.setY(b.getY() + diffY);
    }


    // y = mx + b
    //
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
    public void draw(GraphicsContext gc) {
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    @Override
    public String toString() {
        return String.format("Line [%s, %s]", a, b);
    }
}
