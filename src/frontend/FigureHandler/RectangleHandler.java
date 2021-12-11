package frontend.FigureHandler;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.PaintPane;

public class RectangleHandler extends FigureHandler{

    public RectangleHandler(PaintPane pane) {
        super(pane);
    }

    @Override
    public Figure getFigureConstructor(Point startPoint, Point endPoint) {
        return new Rectangle(startPoint, endPoint);
    }
}
