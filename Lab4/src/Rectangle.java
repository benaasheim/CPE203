import java.awt.*;

public class Rectangle implements Shape{

    private double width;
    private double height;
    private Point topleft;
    private Color color;

    public Rectangle(double width, double height, Point topleft, Color color) {
        this.width = width;
        this.height = height;
        this.topleft = topleft;
        this.color = color;
    }

    @Override
    public Color getColor(){
        return this.color;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public double getArea(){
        return (this.height * this.width);
    }

    @Override
    public double getPerimeter(){
        return 2 * (this.height + this.width);
    }

    @Override
    public void translate(Point p){
        this.topleft = p;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double w) {
        this.width = w;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public Point getTopLeft() {
        return topleft;
    }


    public boolean equals(Object other){
        if (other instanceof Rectangle){
            return (this.width == ((Rectangle)other).getWidth()) && (this.height == ((Rectangle)other).getHeight()) && (this.topleft.x == ((Rectangle)other).getTopLeft().x) && (this.topleft.y == ((Rectangle)other).getTopLeft().y) && (this.color == ((Rectangle)other).getColor());
        }
        return false;
    }
}
