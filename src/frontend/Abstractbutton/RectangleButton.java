package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;

public class RectangleButton extends AbstractButton{

    public RectangleButton(String name){
        super(name);
    }


    @Override
    public Figure createFigure(Point startpoint, Point endpoint) {
        return new Rectangle(startpoint,endpoint);
    }
}
