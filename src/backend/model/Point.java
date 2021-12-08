package backend.model;

public class Point{

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point o){
        return Math.hypot(o.getX()-getX(),o.getY()-getY());
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Point)){
            return false;
        }
        Point aux = (Point) o;
        return this.getX() == aux.getX() && this.getY() == aux.getY();
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }
}
