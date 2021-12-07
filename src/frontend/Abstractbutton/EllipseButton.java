package frontend.Abstractbutton;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;

public class EllipseButton extends AbstractButton{
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {

        return new Ellipse(startpoint, endpoint);
    }
}
