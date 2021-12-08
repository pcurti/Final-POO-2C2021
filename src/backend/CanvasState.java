package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }
    public void removeFigure(Figure figure) { list.remove(figure); }
    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}
