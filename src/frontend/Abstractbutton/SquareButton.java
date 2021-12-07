package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;

public class SquareButton extends AbstractButton{

    public SquareButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {

        return new Square(startpoint, endpoint.getX()- startpoint.getX());
    }

}
