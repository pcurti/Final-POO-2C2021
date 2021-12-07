package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;

public class SquareButton extends FigureButton {

    public SquareButton() {
        super("Cuadrado");
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {
        if(!validPoints(startpoint, endpoint))
            return null;
        return new Square(startpoint, endpoint.getX()- startpoint.getX());
    }

}
