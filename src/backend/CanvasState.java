package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void removeFigure(Figure figure) {
        list.remove(figure);
    }

    public void addFigures(Figure[] figures) {
        list.addAll(List.of(figures));
    }

    public void removeFigures(Figure[] figures) {
        list.removeAll(List.of(figures));
    }

    public void moveToBack(Figure figure){ ((LinkedList) list).addFirst(figure);}

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }
    public Iterable<Figure> reverseFigures(){
        Iterator<Figure> it = ((LinkedList)list).descendingIterator();
        List<Figure> toReturn = new ArrayList<>();
        while(it.hasNext()){
            toReturn.add(it.next());
        }
        return toReturn;
    }
}
