package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.control.ToggleButton;

public abstract class FigureButton extends ToggleButton {

    public FigureButton(String name){
        super(name);
    }

    public abstract Figure createFigure(Point startpoint, Point endpoint);

    protected boolean validPoints(Point startPoint, Point endPoint) {
        return startPoint.getX() < endPoint.getX() && startPoint.getY() <  endPoint.getY();
    }
}
