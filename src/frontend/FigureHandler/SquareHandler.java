package frontend.FigureHandler;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;
import frontend.PaintPane;

public class SquareHandler extends FigureHandler{
    public SquareHandler(PaintPane pane) {
        super(pane);
    }

    @Override
    public Figure getFigureConstructor(Point startPoint, Point endPoint) {
        if(!validPoints(startPoint, endPoint))
            return null;
        return new Square(startPoint, endPoint.getX()- startPoint.getX());
    }
}
