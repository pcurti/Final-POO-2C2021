package frontend.canvasHistory.Operations;

import backend.model.Figure;
import frontend.PaintPane;

import java.util.ArrayList;
import java.util.List;

public class createFigureOperation implements Operation{
    List<Figure> list = new ArrayList<>();
    boolean inverted = false;

    public createFigureOperation(Figure[] figures, boolean inverted) {
        this.inverted = inverted;
        list.addAll(List.of(figures));
    }

    public createFigureOperation(Figure figure, boolean inverted) {
        this.inverted = inverted;
        list.add(figure);
    }

    @Override
    public void apply(PaintPane pane) {
        if(inverted) {
            reversedApply(pane);
            return;
        }
        for(Figure figure : list) {
           pane.addFigure(figure);
        }
    }

    @Override
    public void reversedApply(PaintPane pane) {
        if(inverted) {
            reversedApply(pane);
            return;
        }

        for(Figure figure : list) {
            pane.removeFigure(figure);
        }
    }
}
