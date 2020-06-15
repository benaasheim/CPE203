import java.lang.Math;

class Point
{
    private final double x;
    private final double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getRadius()
    {
        return Math.sqrt((x*x) + (y*y));
    }
    public double getAngle()
    {
        if (x >= 0)
        {
            if (y >= 0)
            {
                double radang = (((Math.atan(y/x) * 180 / Math.PI)) * Math.PI/180);
                return radang;
            }
            else
            {
                double radang = ((360 + (Math.atan(y/x) * 180 / Math.PI)) * Math.PI/180);
                return radang;
            }
        }
        else
        {
            double radang = ((180 + (Math.atan(y/x) * 180 / Math.PI)) * Math.PI/180);
            return radang;
        }

    }
    public Point rotate90()
    {
        double newy = getX() * (-1);
        double newx = getY();
        Point pont = new Point(newx, newy);
        return pont;
    }
}