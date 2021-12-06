package backend.model;

public abstract class Figure {
        public abstract void changePosition(double diffX, double diffY);
        public abstract double getArea();
        public abstract double getPerimeter();
        public abstract boolean hasPoint(Point point);
}
