import java.awt.*;
import java.lang.Math;

class Circle implements Shape{

    private double radius;
    private Point center;
    private Color color;

    public Circle(double radius, Point center, Color color){
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public double getArea() {
        return this.radius * this.radius * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public void translate(Point p) {
        int newx = this.center.x - (int)p.getX();
        int newy = this.center.y - (int)p.getY();
        this.center = new Point(newx, newy);
    }

    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean equals(Object other){
        if (other instanceof Circle){
            return (this.radius == ((Circle)other).getRadius()) && (this.center.x == ((Circle)other).getCenter().x) && (this.center.y == ((Circle)other).getCenter().y) && (this.color == ((Circle)other).getColor());
        }
        return false;
    }
}

