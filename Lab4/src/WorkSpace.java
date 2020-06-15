import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WorkSpace {

    private ArrayList<Shape> shapeList;

    public WorkSpace(){
        this.shapeList = new ArrayList<>();
    }

    public void add(Shape shape){
        this.shapeList.add(shape);
    }

    public Shape get(int index){
        return this.shapeList.get(index);
    }

    public int size(){
        return this.shapeList.size();
    }

    public List<Circle> getCircles(){
        List<Circle> circles = new ArrayList<>();
        for (Shape shape : this.shapeList){
            if (shape instanceof Circle){
                circles.add((Circle)shape);
            }
        }
        return circles;
    }

    public List<Rectangle> getRectangles(){
        List<Rectangle> rectangles = new ArrayList<>();
        for (Shape shape : this.shapeList){
            if (shape instanceof Rectangle){
                rectangles.add((Rectangle) shape);
            }
        }
        return rectangles;
    }

    public List<Triangle> getTriangles(){
        List<Triangle> triangles = new ArrayList<>();
        for (Shape shape : this.shapeList){
            if (shape instanceof Triangle){
                triangles.add((Triangle) shape);
            }
        }
        return triangles;
    }

    public List<Shape> getShapesByColor(Color color){
        List<Shape> shapes = new ArrayList<>();
        for (Shape shape : this.shapeList){
            if (shape.getColor() == color){
                shapes.add(shape);
            }
        }
        return shapes;
    }

    public double getAreaOfAllShapes(){
        double totalarea = 0;
        for (Shape shape : this.shapeList){
            totalarea += shape.getArea();
            }
        return totalarea;
    }

    public double getPerimeterOfAllShapes(){
        double totalper = 0;
        for (Shape shape : this.shapeList){
            totalper = totalper + shape.getPerimeter();
        }

        return totalper;
    }

}
