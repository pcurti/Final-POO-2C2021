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
        double side = endPoint.getX() - startPoint.getX();
        Point aux= new Point(startPoint.getX() + side, startPoint.getY() + side);
        return new Square(new Point[]{startPoint, aux});
    }
}
