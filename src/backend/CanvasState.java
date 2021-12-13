package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CanvasState implements  Clone<CanvasState>{

    private final LinkedList<Figure> list = new LinkedList<>();


    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void removeFigure(Figure figure) {
        list.remove(figure);
    }

    public void moveToBack(Figure figure){
        removeFigure(figure);
        list.addFirst(figure);
    }

    public void moveToFront(Figure figure){
        removeFigure(figure);
        list.addLast(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    public Iterable<Figure> reverseFigures(){
        Iterator<Figure> it = list.descendingIterator();
        List<Figure> toReturn = new ArrayList<>();
        while(it.hasNext()){
            toReturn.add(it.next());
        }
        return toReturn;
    }

    public CanvasState getClone() {
        CanvasState clone = new CanvasState();
        clone.list.clear();
        for (Figure figure : figures()) {
            clone.list.add(figure.getClone());
        }
        return clone;
    }
}
