package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;

public class LineButton extends FigureButton {
    public LineButton() {
        super("Linea");
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {
        return new Line(startpoint, endpoint);
    }
}
