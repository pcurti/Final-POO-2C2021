package frontend.Abstractbutton;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.control.ToggleButton;

public abstract class AbstractButton extends ToggleButton {

    public AbstractButton(String name){
        super(name);
    }

    public abstract Figure createFigure(Point startpoint, Point endpoint);
}
