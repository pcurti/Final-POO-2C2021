package backend.model;

import backend.Clone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public abstract class Figure implements DrawingProperties, Clone<Figure> {
        protected Point[] points;
        private Color borderColor;
        private Color fillColor;
        private double borderWidth;
        private boolean selected = false;


        public Figure(Point[] points) {
                this.points = points;
        }

        //ABSTRACT METHODS -> subclasses must implement
        public abstract Figure getClone();
        public abstract boolean hasPoint(Point point);
        protected abstract void draw(GraphicsContext gc);


        //UPDATING INSTANCE METHODS
        public void changePosition(double diffX, double diffY) {
                for(Point point : points) {
                        point.setX(point.getX() + diffX);
                        point.setY(point.getY() + diffY);
                }
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

        public void select() {
                selected = true;
        }

        public void unSelect() {
                selected = false;
        }

        //GET METHODS
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

        public boolean isSelected(){return selected;}


        //FIGURE DRAWING METHOD
        public void drawFigure(GraphicsContext gc) {
                loadDrawingProperties(gc);
                if(selected)
                        gc.setStroke(Color.RED);
                draw(gc);
        }


        //MATH METHOD
        public boolean isContainedIn(Rectangle container) {
                for(Point point : points) {
                        if(!container.hasPoint(point))
                                return false;
                }
                return true;
        }


        //DEEP COPY TOOLS
        public Point[] getClonedPoints() {
                Point[] copy = new Point[points.length];
                int i = 0;
                for (Point point : points) {
                        copy[i++] = point.getClone();
                }
                return copy;
        }

}
