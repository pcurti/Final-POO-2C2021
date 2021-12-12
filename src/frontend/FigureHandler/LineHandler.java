package frontend.FigureHandler;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;
import frontend.PaintPane;

public class LineHandler extends FigureHandler{
    public LineHandler(PaintPane pane) {
        super(pane);
    }

    @Override
    public Figure getFigureConstructor(Point startPoint, Point endPoint) {
        return new Line(startPoint,endPoint);
    }
}
