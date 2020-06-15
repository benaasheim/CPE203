import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /* some sample tests but you must write more! see lab write up */
// Circle
   @Test
   public void testCircleGetRadius()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(5.678, c.getRadius(), DELTA);
   }
   @Test
   public void testCircleGetCenter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(2, c.getCenter().x, DELTA);
      assertEquals(3, c.getCenter().y, DELTA);
      assertEquals(true, (c.getCenter() instanceof Point));
   }
   @Test
   public void testCircleSetCenter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(5.678, c.getRadius(), DELTA);

      c.setRadius(4.479);

      assertEquals(4.479, c.getRadius(), DELTA);

      c.setRadius(6);

      assertEquals(6, c.getRadius(), DELTA);
   }
// Rectangle
   @Test
   public void testRectangleGetWidth()
   {
      Rectangle c = new Rectangle(3, 3, new Point(2, 3), Color.BLACK);

      assertEquals(3, c.getWidth(), DELTA);
   }
   @Test
   public void testRectangleGetTopLeft()
   {
      Rectangle c = new Rectangle(3, 3, new Point(2, 3), Color.BLACK);

      assertEquals(2, c.getTopLeft().x, DELTA);
      assertEquals(3, c.getTopLeft().y, DELTA);
      assertEquals(true, (c.getTopLeft() instanceof Point));
   }
   @Test
   public void testRectangleSetWidth()
   {
      Rectangle c = new Rectangle(3, 3, new Point(2, 3), Color.BLACK);

      assertEquals(3, c.getWidth(), DELTA);

      c.setWidth(6);

      assertEquals(6, c.getWidth(), DELTA);
   }
   @Test
   public void testRectangleSetHeight()
   {
      Rectangle c = new Rectangle(3, 3, new Point(2, 3), Color.BLACK);

      assertEquals(3, c.getWidth(), DELTA);

      c.setHeight(6);

      assertEquals(6, c.getHeight(), DELTA);
   }

// Area - Shape
   @Test
   public void testCircleGetArea()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);

      Circle d = new Circle(0, new Point(2, 3), Color.BLACK);

      assertEquals(0, d.getArea(), DELTA);
   }
   @Test
   public void testRectangleGetArea()
   {
      Rectangle c = new Rectangle(3, 3, new Point(0, 3), Color.BLACK);

      assertEquals(9, c.getArea(), DELTA);
   }
   @Test
   public void testTriangleGetArea()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(2, 4), Color.BLACK);

      assertEquals(8, c.getArea(), DELTA);
   }
   @Test
   public void testTriangleGetArea2()
   {
      Triangle c = new Triangle(new Point(2, 5), new Point(-4, 3), new Point(5, 1), Color.BLACK);

      assertEquals(15, c.getArea(), DELTA);
   }

// Perimeter - Shape
   @Test
   public void testCircleGetPerimeter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }
   @Test
   public void testRectangleGetPerimeter()
   {
      Rectangle c = new Rectangle(4, 5, new Point(2, 3), Color.BLACK);

      assertEquals(18, c.getPerimeter(), DELTA);
   }
   @Test
   public void testTriangleGetPerimeter()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);

      assertEquals(12, c.getPerimeter(), DELTA);
   }

// Equals - Shape
   @Test
   public void testCircleEquals()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle b = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(true, b.equals(c));
   }
   @Test
   public void testRectangleEquals()
   {
      Rectangle c = new Rectangle(4, 5, new Point(2, 3), Color.BLACK);
      Rectangle b = new Rectangle(4, 5, new Point(2, 3), Color.BLACK);

      assertEquals(true, b.equals(c));
   }
   @Test
   public void testTriangleEquals()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);
      Triangle b = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);

      assertEquals(true, b.equals(c));
   }

// getColor - Shape
   @Test
   public void testCircleGetColor()
   {
   Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

   assertEquals(Color.BLACK, c.getColor());
}
   @Test
   public void testRectangleGetColor()
   {
      Rectangle c = new Rectangle(5.678, 9, new Point(2, 3), Color.RED);

      assertNotEquals(Color.BLACK, c.getColor());
   }
   @Test
   public void testTriangleGetColor()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);

      assertEquals(Color.BLACK, c.getColor());
   }

// setColor - Shape
   @Test
   public void testCircleSetColor()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());
      c.setColor(Color.RED);
      assertEquals(Color.RED, c.getColor());
      assertNotEquals(Color.BLACK, c.getColor());
   }
   @Test
   public void testRectangleSetColor()
   {
      Rectangle c = new Rectangle(5.678, 9, new Point(2, 3), Color.GRAY);
      assertNotEquals(Color.RED, c.getColor());
      c.setColor(Color.RED);
      assertEquals(Color.RED, c.getColor());
      assertNotEquals(Color.BLACK, c.getColor());
   }
   @Test
   public void testTriangleSetColor()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());

   }

// Translate - Shape
   @Test
   public void testCircleTranslate()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Point expected = new Point(2, 3);
      double a = c.getArea();
      assertEquals(expected, c.getCenter());
      assertEquals(2, c.getCenter().x, DELTA);
      assertEquals(3, c.getCenter().y, DELTA);
      assertEquals(true, (c.getCenter() instanceof Point));
      Point p = new Point(6, 8);
      c.translate(new Point(4,5));
      double b = c.getArea();
      assertNotEquals(expected, c.getCenter());
      assertEquals(p, c.getCenter());
      assertEquals(a, b, DELTA);
      assertEquals(4, c.getCenter().x, DELTA);
      assertEquals(5, c.getCenter().y, DELTA);
      assertEquals(true, (c.getCenter() instanceof Point));

   }
   @Test
   public void testRectangleTranslate()
   {
      Rectangle c = new Rectangle(5.678, 9, new Point(2, 3), Color.GRAY);
      assertNotEquals(Color.RED, c.getColor());
      c.setColor(Color.RED);
      assertEquals(Color.RED, c.getColor());
      assertNotEquals(Color.BLACK, c.getColor());
   }
   @Test
   public void testTriangleTranslate()
   {
      Triangle c = new Triangle(new Point(0, 0), new Point(4, 0), new Point(4, 3), Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());

   }

   @Test
   public void testWorkSpaceAreaOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), 
                 Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
   }
   @Test
   public void testWorkSpacePerimeterOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(5, 6, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(3,0), new Point(3, 4),
              Color.BLACK));

      assertEquals(65.41592653589794, ws.getPerimeterOfAllShapes(), DELTA);
   }
   @Test
   public void testWorkSpacePerimeterOfAllShapes2()
   {
      WorkSpace ws = new WorkSpace();

      assertEquals(0, ws.getPerimeterOfAllShapes(), DELTA);
   }
   @Test
   public void testWorkSpaceGetCircles()
   {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getCircles());
   }
   @Test
   public void testWorkSpaceGetRectangles()
   {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Rectangle c1 = new Rectangle(3, 6, new Point(2, 3), Color.BLACK);
      Rectangle c2 = new Rectangle(7, 2, new Point(-5, -3), Color.RED);

      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
              Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getRectangles());
   }
   @Test
   public void testWorkSpaceGetTriangles()
   {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Triangle c2 = new Triangle(new Point(2, 3), new Point(-5, -2), new Point(1,-4), Color.RED);
      Triangle c1 = new Triangle(new Point(5, 5), new Point(4, 0), new Point(6,0), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Circle(7, new Point(2,-4),
              Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getTriangles());
   }
   @Test
   public void testWorkSpaceGet()
   {
      WorkSpace ws = new WorkSpace();
      Triangle expected = new Triangle(new Point(2, 3), new Point(-5, -2), new Point(1,-4), Color.RED);

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Triangle c2 = new Triangle(new Point(2, 3), new Point(-5, -2), new Point(1,-4), Color.RED);
      Triangle c1 = new Triangle(new Point(5, 5), new Point(4, 0), new Point(6,0), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Circle(7, new Point(2,-4),
              Color.BLACK));
      ws.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.get(3));
   }
   @Test
   public void testWorkSpaceSize()
   {
      WorkSpace ws = new WorkSpace();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Triangle c2 = new Triangle(new Point(2, 3), new Point(-5, -2), new Point(1,-4), Color.RED);
      Triangle c1 = new Triangle(new Point(5, 5), new Point(4, 0), new Point(6,0), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Circle(7, new Point(2,-4),
              Color.BLACK));
      ws.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(4, ws.size());
   }
   @Test
   public void testWorkSpaceGetShapesByColor()
   {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Triangle c2 = new Triangle(new Point(2, 3), new Point(-5, -2), new Point(1,-4), Color.RED);
      Triangle c1 = new Triangle(new Point(5, 5), new Point(4, 0), new Point(6,0), Color.RED);
      Rectangle c3 = new Rectangle(7, 2, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Circle(7, new Point(2,-4),
              Color.BLACK));
      ws.add(c2);
      ws.add(c3);

      expected.add(c1);
      expected.add(c2);
      expected.add(c3);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getShapesByColor(Color.RED));
   }

// ImplSpecifics
   @Test
   public void testCircleImplSpecifics() throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }
   @Test
   public void testRectangleImplSpecifics() throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, 
         new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }
   @Test
   public void testTriangleImplSpecifics() throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }
   private static void verifyImplSpecifics(final Class<?> clazz, final List<String> expectedMethodNames, final List<Class> expectedMethodReturns, final List<Class[]> expectedMethodParameters) throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
