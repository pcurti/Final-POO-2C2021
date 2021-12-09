package backend.canvasHistory;

import backend.CanvasState;
import frontend.PaintPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanvasHistory {
    private List<CanvasState> history = new ArrayList<>();
    private int lastOperation = -1;

    public void addHistory(CanvasState state) {
        if(lastOperation != history.size() -1)
            resizeList(lastOperation);
        history.add(state);
        lastOperation++;
        System.out.println(String.format("Operation n�%d", lastOperation + 1));
    }

    public CanvasState getPreviousState() {
        if(!canUndo())
            return history.get(lastOperation);
        return history.get(--lastOperation);
    }
    public CanvasState getNextState() {
        if(!canRedo())
            return history.get(lastOperation);
        return history.get(++lastOperation);
    }

    public boolean canUndo() {
        return lastOperation  - 1> -1;
    }
    public boolean canRedo() {
        return lastOperation + 1 < history.size();
    }

    private void resizeList(int lastOperation) {
        int size = history.size();
        for(int i = size -1 ; i > lastOperation; i--) {
            history.remove(i);
        }
    }
}
