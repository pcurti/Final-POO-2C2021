package frontend.Abstractbutton;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;

public class EllipseButton extends FigureButton {
    public EllipseButton() {
        super("Elipse");
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {

        return new Ellipse(startpoint, endpoint);
    }
}
