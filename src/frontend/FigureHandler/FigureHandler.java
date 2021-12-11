package frontend.FigureHandler;

import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

import javafx.scene.paint.Color;

public abstract class FigureHandler {
    PaintPane pane;

    public FigureHandler(PaintPane pane) {
        this.pane = pane;
    }

    public Figure createFigure(Point startPoint, Point endPoint) {

        try {
            Figure figure = getFigureConstructor(startPoint, endPoint);
            figure.setDrawingProperties((Color) pane.getContext().getFill(), (Color) pane.getContext().getStroke(), pane.getContext().getLineWidth());
            return figure;
        }catch(Exception e) {
            pane.getStatusPane().updateStatus(e.getMessage());
            return null;
        }

    }
    public abstract Figure getFigureConstructor(Point startPoint, Point endPoint);

}
