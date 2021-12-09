package frontend.FigureHandler;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

public class CircleHandler extends FigureHandler{
    public CircleHandler(PaintPane pane) {
        super(pane);
    }

    @Override
    public Figure getFigureConstructor(Point startPoint, Point endPoint) {
        double circleRadius = Math.hypot(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
        return new Circle(new Point[]{startPoint},circleRadius);
    }
}
