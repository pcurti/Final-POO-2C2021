package backend.model;

import backend.DrawingProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public abstract class Figure implements DrawingProperties {
        private Color borderColor;
        private Color fillColor;
        private double borderWidth;
        private boolean selected = false;
        public abstract void changePosition(double diffX, double diffY);
        public abstract boolean hasPoint(Point point);
        @Override
        public Color getBorderColor() {
                return borderColor;
        }
        @Override
        public Color getFillColor() {
                return fillColor;
        }
        @Override
        public double getBorderWidth() {
                return borderWidth;
        }

        @Override
        public void setFillColor(Color fillColor) {
                this.fillColor = fillColor;
        }
        @Override
        public void setBorderColor(Color borderColor) {
                this.borderColor = borderColor;
        }

        @Override
        public void setBorderWidth(double borderWidth) {
                this.borderWidth = borderWidth;
        }

        protected abstract void draw(GraphicsContext gc);

        public void drawFigure(GraphicsContext gc) {
                loadDrawingProperties(gc);
                if(selected)
                        gc.setStroke(Color.RED);
                draw(gc);
        }

        public void select() {
                selected = true;
        }
        public void unSelect() {
                selected = false;
        }
}
