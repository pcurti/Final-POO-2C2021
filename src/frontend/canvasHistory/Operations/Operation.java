package frontend.canvasHistory.Operations;

import frontend.PaintPane;

public interface Operation {
    void apply(PaintPane pane);
    void reversedApply(PaintPane pane);
}
