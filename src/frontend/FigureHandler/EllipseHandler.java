package frontend.FigureHandler;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

public class EllipseHandler extends FigureHandler{
    public EllipseHandler(PaintPane pane) {
        super(pane);
    }

    @Override
    public Figure getFigureConstructor(Point startPoint, Point endPoint) {
        return new Ellipse(startPoint, endPoint);
    }
}
