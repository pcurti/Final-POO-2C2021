package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;

public class LineButton extends AbstractButton{
    public LineButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {
        return new Line(startpoint, endpoint);
    }
}
