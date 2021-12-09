package frontend.canvasHistory;

import frontend.PaintPane;
import frontend.canvasHistory.Operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class canvasHistory {
    private static List<Operation> operations = new ArrayList<>();
    private int lastOperation;
    private PaintPane pane;

    public canvasHistory(PaintPane pane) {
        this.pane = pane;
    }

    public void addOperation(Operation operation) {
        //operations = operations.subList(0, lastOperation);
        operations.add(operation);
    }

    public void undo() {
        if(!canUndo())
            return;
        operations.get(lastOperation).reversedApply(pane);
        lastOperation--;
    }
    public void redo() {
        if(!canRedo())
            return;
        operations.get(lastOperation).apply(pane);
        lastOperation++;
    }
    public boolean canUndo() {
        return lastOperation > -1;
    }
    public boolean canRedo() {
        return lastOperation < operations.size();
    }
}
