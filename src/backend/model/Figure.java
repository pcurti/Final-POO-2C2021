package backend.model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Figure {
        public abstract void changePosition(double diffX, double diffY);
        public abstract double getArea();
        public abstract double getPerimeter();
        public abstract boolean hasPoint(Point point);
        public abstract void draw(GraphicsContext gc);
}
