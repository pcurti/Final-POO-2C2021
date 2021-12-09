package backend.model;

import javafx.scene.canvas.GraphicsContext;

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
}
