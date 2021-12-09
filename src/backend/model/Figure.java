package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure implements DrawingProperties, Cloneable {
        protected Point[] points;
        private Color borderColor;
        private Color fillColor;
        private double borderWidth;
        private boolean selected = false;

        public Figure(Point[] points) {
                this.points = points;
        }

        public Point[] getPoints() {
                return points.clone();
        }
        public void changePosition(double diffX, double diffY) {
                for(Point point : points) {
                        point.setX(point.getX() + diffX);
                        point.setY(point.getY() + diffY);
                }
        }
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
        public abstract boolean isContainedIn(Rectangle container);
        public void select() {
                selected = true;
        }
        public void unSelect() {
                selected = false;
        }

        //tal vez falle?
        @Override
        public Figure clone() {
                try {
                        Figure clone = (Figure) super.clone();
                        //copying points
                        clone.points = new Point[points.length];
                        for(int i = 0; i < points.length; i++) {
                             clone.points[i] = new Point(points[i].getX(), points[i].getY());
                        }
                        //copying drawing properties
                        Color border = new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), borderColor.getOpacity());
                        Color fill = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), fillColor.getOpacity());
                        clone.setDrawingProperties(fill, border, borderWidth);
                        //default to be unselected
                        clone.unSelect();
                        // TODO: copy mutable state here, so the clone can't change the internals of the original
                        return clone;
                } catch (CloneNotSupportedException e) {
                        throw new AssertionError();
                }
        }
}
