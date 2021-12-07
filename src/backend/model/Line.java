package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure{

    @Override
    public void changePosition(double diffX, double diffY) {

    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public boolean hasPoint(Point point) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
