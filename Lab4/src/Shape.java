import java.awt.*;

interface Shape {
    public Color getColor();
    public void setColor(Color c);
    public double getArea();
    public double getPerimeter();
    public void translate(Point p);
}
