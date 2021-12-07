package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure{


    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }


    @Override
    public void changePosition(double diffX, double diffY) {
        centerPoint.setY(centerPoint.getY() + diffY);
        centerPoint.setX(centerPoint.getX() + diffX);
    }

    @Override
    public double getArea() {
        return Math.PI / 4 * sMayorAxis * sMinorAxis;
    }

    @Override
    public double getPerimeter() {
        return Math.PI / 2 * (sMayorAxis + sMinorAxis);
    }

    @Override
    public boolean hasPoint(Point point) {
        return Math.pow((point.getX() - centerPoint.getX()) / sMayorAxis, 2) + Math.pow((point.getY() - centerPoint.getY()) / sMinorAxis, 2) <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillOval(centerPoint.getX() - sMayorAxis/2, centerPoint.getY() + sMinorAxis / 2, sMayorAxis, sMinorAxis);
        gc.strokeOval(centerPoint.getX() - sMayorAxis / 2, centerPoint.getY() + sMinorAxis / 2, sMayorAxis, sMinorAxis);
    }
}
