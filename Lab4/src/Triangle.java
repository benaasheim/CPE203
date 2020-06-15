import java.awt.*;
import java.util.ArrayList;

public class Triangle implements Shape{

    private Point a;
    private Point b;
    private Point c;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color){
        this.a = a;
        this.b = b;
        this.c = c;
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
        double z = 2;
        return (((double)this.a.x * (double)this.b.y + (double)this.b.x * (double)this.c.y + (double)this.c.x * (double)this.a.y) - ((double)this.a.y * (double)this.b.x + (double)this.b.y * (double)this.c.x + (double)this.c.y * (double)this.a.x)) / z;
    }

    @Override
    public double getPerimeter(){
        /*
        System.out.println(this.a.distance(this.b));
        System.out.println(this.b.distance(this.c));
                System.out.println(this.a.distance(this.c));*/
        return this.a.distance(this.b) + this.b.distance(this.c) + this.a.distance(this.c);
    }

    @Override
    public void translate(Point p){
        int shiftx = p.x - a.x;
        int shifty = p.y - a.y;
        Point pb = new Point(b.x + shiftx, b.y + shifty);
        Point pc = new Point(c.x + shiftx, c.y + shifty);
        this.a = p;
        this.b = pb;
        this.c = pc;
    }

    public Point getVertexA(){
        return this.a;
    };

    public Point getVertexB() {
        return this.b;
    }

    public Point getVertexC() {
        return this.c;
    }

    public boolean equals(Object other){
        if (other instanceof Triangle){
            return (this.a.x == ((Triangle)other).getVertexA().x) &&
                    (this.a.y == ((Triangle)other).getVertexA().y) &&
                    (this.b.x == ((Triangle)other).getVertexB().x) &&
                    (this.b.y == ((Triangle)other).getVertexB().y) &&
                    (this.c.x == ((Triangle)other).getVertexC().x) &&
                    (this.c.y == ((Triangle)other).getVertexC().y) &&(this.color == ((Triangle)other).getColor());
        }
        return false;
    }

}
