package frontend.Abstractbutton;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;

public class EllipseButton extends AbstractButton{
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {

        double sMayorAxis = Math.abs(endpoint.getX() - startpoint.getX());
        double sMinorAxis = Math.abs(endpoint.getY() - startpoint.getY());
        if(startpoint.compareTo(endpoint) > 0) {
            Point holder = startpoint;
            startpoint = endpoint;
            endpoint = holder;
        }

        Point centerPoint = new Point(startpoint.getX() + sMayorAxis/2, startpoint.getY() - sMinorAxis/2);
        return new Ellipse(centerPoint, sMayorAxis, sMinorAxis);
    }
}
