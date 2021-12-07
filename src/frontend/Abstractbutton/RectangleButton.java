package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;

public class RectangleButton extends FigureButton {

    public RectangleButton(){
        super("Rectangulo");
    }


    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {
        if(!validPoints(startpoint, endpoint))
            return null;
        return new Rectangle(startpoint,endpoint);
    }
}
