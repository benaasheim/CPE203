import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.lang.Integer.*;
import java.util.stream.Collectors;

public class LogAnalyzer
{
    //constants to be used when pulling data out of input
    //leave these here and refer to them to pull out values
    private static final String START_TAG = "START";
    private static final int START_NUM_FIELDS = 3;
    private static final int START_SESSION_ID = 1;
    private static final int START_CUSTOMER_ID = 2;
    private static final String BUY_TAG = "BUY";
    private static final int BUY_NUM_FIELDS = 5;
    private static final int BUY_SESSION_ID = 1;
    private static final int BUY_PRODUCT_ID = 2;
    private static final int BUY_PRICE = 3;
    private static final int BUY_QUANTITY = 4;
    private static final String VIEW_TAG = "VIEW";
    private static final int VIEW_NUM_FIELDS = 4;
    private static final int VIEW_SESSION_ID = 1;
    private static final int VIEW_PRODUCT_ID = 2;
    private static final int VIEW_PRICE = 3;
    private static final String END_TAG = "END";
    private static final int END_NUM_FIELDS = 2;
    private static final int END_SESSION_ID = 1;

    //a good example of what you will need to do next
    //creates a map of sessions to customer ids


    private static String getFilename(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Log file not specified.");
            System.exit(1);
        }

        return args[0];
    }

    private static List<Point> FiletoList(String filename) throws FileNotFoundException{
        Scanner in = loadLines(filename);
        return load(in);
    }

    private static Scanner loadLines(String filename) throws FileNotFoundException{
        try
        {
            Scanner in = new Scanner(new File(filename));
            return in;
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }
    private static List<Point> load(Scanner in) {
        long lineNumber = 0;
        List<Point> LoP = new LinkedList<>();
        while (in.hasNextLine())
        {
            String x = in.nextLine();
            try
            {
                if (!processLine(x, LoP))
                {
                    System.err.println(String.format("AA wrong entry on line %d",
                            lineNumber));
                }
            }
            catch (NumberFormatException e)
            {
                System.err.println(String.format("BB invalid entry on line %d",
                        lineNumber, e.getMessage()));
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(x);
                System.err.println(String.format("CC issue on line %d: %s",
                        lineNumber, e.getMessage()));
            }
            lineNumber++;
        }
        return LoP;
    }

    private static boolean processLine(String line, List<Point> LoP) {
        String[] properties = line.split("\\s");
        if (properties.length == 3) {
            double x = Double.parseDouble(properties[0].replaceAll(",", ""));
            double y = Double.parseDouble(properties[1].replaceAll(",", ""));
            double z = Double.parseDouble(properties[2]);
            Point p = new Point(x, y, z);
            LoP.add(p);
        }
        return (properties.length == 3);
    }

    private static List<Point> EditList(List<Point> LoP) {
        List<Point> flis = LoP.stream().filter(point -> point.z <= 2).collect(Collectors.toList());
        List<Point> slis = flis.stream().map(point -> new Point((point.x*0.5), (point.y*0.5), (point.z * 0.5))).collect(Collectors.toList());
        List<Point> tlis = slis.stream().map(point -> new Point((point.x-150), (point.y-37), point.z)).collect(Collectors.toList());
        return LoP;
    }

    private static void WritetoNew(List<Point> LoE)  {
        try {
            List<String> lines = ListtoLines(LoE);
            Path file = Paths.get("drawMe.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
        catch (java.io.IOException e)
        {
            System.err.println(e.getMessage());
        }

    }
    private static List<String> ListtoLines(List<Point> LoE) {
        List<String> lines = new LinkedList<>();
        for (Point point : LoE) {
            String line = pointToLine(point);
            lines.add(line);
        }
        return lines;
    }

    private static String pointToLine(Point point) {
        String ptA = Double.toString(point.x);
        String ptB = Double.toString(point.y);
        String pcC = Double.toString(point.z);
        return ptA + ", " + ptB + ", " + pcC;
    }

    public static void main(String[] args)
    {
        /* Map from a customer id to a list of session ids associated with
         * that customer.
         */
        final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
        final List<String> sesind = new LinkedList<>();
        final List<String> cusind = new LinkedList<>();
        /* create additional data structures to hold relevant information */
        /* they will most likely be maps to important data in the logs */

//        final String filename = getFilename(args);
            final String filename = "positions.txt";
        try
        {
            List<Point> LoP = FiletoList(filename);
            List<Point> LoE = EditList(LoP);
            WritetoNew(LoE);

        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
