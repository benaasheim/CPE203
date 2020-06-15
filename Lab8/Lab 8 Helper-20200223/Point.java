final class Point
{
   public final double x;
   public final double y;
   public double z;

   public Point(double x, double y, double z)
   {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public boolean adjacent(Point p1) {
       return (p1.x == this.x && Math.abs(p1.y - this.y) == 1) ||
          (p1.y == this.y && Math.abs(p1.x - this.x) == 1);
    }
   public double distanceSquared(Point p1) {
      double deltaX = p1.x - this.x;
      double deltaY = p1.y - this.y;

      return deltaX * deltaX + deltaY * deltaY;
   }
   public String toString()
   {
      return "(" + x + "," + y + ")";
   }
   public boolean equals(Object other) {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }
   public boolean condition() {
      return this.z < 2;
   }

}
