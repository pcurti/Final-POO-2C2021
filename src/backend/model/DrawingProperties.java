package backend.model;

import backend.Clone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public  interface DrawingProperties {

     void drawFigure(GraphicsContext gc);

     void setFillColor(Color color);
     void setBorderColor(Color color);
     void setBorderWidth(double width);

    Color getBorderColor();

    Color getFillColor();

    double getBorderWidth();

     default void setDrawingProperties(Color fillColor, Color borderColor, double width) {
         setFillColor(fillColor);
         setBorderColor(borderColor);
         setBorderWidth(width);
     }

    default Color cloneColor(Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }

    default void loadDrawingProperties(GraphicsContext gc) {
        gc.setStroke(getBorderColor());
        gc.setLineWidth(getBorderWidth());
        gc.setFill(getFillColor());
    }

}
