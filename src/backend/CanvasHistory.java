package backend;

import java.util.LinkedList;

public class CanvasHistory {
    private LinkedList<CanvasState> history = new LinkedList<>();
    private int lastOperation = -1;

    public void addHistory(CanvasState state) {
        if(lastOperation <= history.size()-1) {
            resizeList();
        }
        history.addLast(state);
        lastOperation++;

    }

    public CanvasState getPreviousState() {
        if(!canUndo())
            return history.get(lastOperation);
        return history.get(--lastOperation).getClone();
    }
    public CanvasState getNextState() {
        if(!canRedo())
            return history.get(lastOperation);
        return history.get(++lastOperation).getClone();
    }

    public boolean canUndo() {
        return lastOperation >= 1;
    }
    public boolean canRedo() {
        return lastOperation < history.size()-1;
    }

    private void resizeList() {
        history.subList(lastOperation+1, history.size()).clear();

    }
}
