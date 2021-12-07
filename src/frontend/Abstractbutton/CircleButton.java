package frontend.Abstractbutton;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;

public class CircleButton extends FigureButton {

    public CircleButton(){
        super("Circulo");
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) {
        double circleRadius = Math.hypot(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
        return new Circle(startPoint,circleRadius);
    }
}
