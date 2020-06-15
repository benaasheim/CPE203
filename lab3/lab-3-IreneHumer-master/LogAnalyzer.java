import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Integer.*;

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
   private static void processStartEntry(final String[] words, final Map<String, List<String>> sessionsFromCustomer, List<String> cusind)
   {
      if (words.length != START_NUM_FIELDS)
      {
         return;
      }

         //check if there already is a list entry in the map
         //for this customer, if not create one
      List<String> sessions = sessionsFromCustomer.get(words[START_CUSTOMER_ID]);
      if (sessions == null)
      {
         sessions = new LinkedList<>();
         sessionsFromCustomer.put(words[START_CUSTOMER_ID], sessions);
         cusind.add(words[START_CUSTOMER_ID]);
      }

         //now that we know there is a list, add the current session
      sessions.add(words[START_SESSION_ID]);
   }

      //similar to processStartEntry, should store relevant view
      //data in a map - model on processStartEntry, but store
      //your data to represent a view in the map (not a list of strings)
   private static void processViewEntry(final String[] words,
      /* add parameters as needed */
      final Map<String, Session> seslis, final List<String> sesind)
   {
      if (words.length != VIEW_NUM_FIELDS)
      {
         return;
      }

      //check if there already is a list entry in the map
      //for this customer, if not create one
      Session session = seslis.get(words[VIEW_SESSION_ID]);
      if (session == null)
      {
         session = new Session();
         seslis.put(words[VIEW_SESSION_ID], session);
         sesind.add(words[VIEW_SESSION_ID]);
      }

      //now that we know there is a list, add the current session
      session.newView(Integer.parseInt(words[VIEW_PRICE]), words[VIEW_PRODUCT_ID]);
   }

      //similar to processStartEntry, should store relevant purchases
      //data in a map - model on processStartEntry, but store
      //your data to represent a purchase in the map (not a list of strings)
   private static void processBuyEntry(
      final String[] words,
      /* add parameters as needed */
      final  Map<String, Session> seslis, final List<String> sesind)
   {
      if (words.length != BUY_NUM_FIELDS)
      {
         return;
      }
   //check if there already is a list entry in the map
   //for this customer, if not create one
   Session session = seslis.get(words[BUY_SESSION_ID]);
   if (session == null)
      {
      session = new Session();
      seslis.put(words[BUY_SESSION_ID], session);
      sesind.add(words[BUY_SESSION_ID]);
      }

   session.newBuy(Integer.parseInt(words[BUY_PRICE]), words[BUY_PRODUCT_ID]);
   }

   private static void processEndEntry(final String[] words, final Map<String, Session> seslis)
   {
      if (words.length != END_NUM_FIELDS)
      {
         return;
      }
      Session session = seslis.get(words[END_SESSION_ID]);
      session.writeDiffs();
   }

      //this is called by processFile below - its main purpose is
      //to process the data using the methods you write above
   private static void processLine(
      final String line,
      final Map<String, List<String>> sessionsFromCustomer,
      /* add parameters as needed */
      final List<String> cusind, final Map<String, Session> seslis, final List<String> sesind)
   {
      final String[] words = line.split("\\h");

      if (words.length == 0)
      {
         return;
      }

      switch (words[0])
      {
         case START_TAG:
            processStartEntry(words, sessionsFromCustomer, cusind);
            break;
         case VIEW_TAG:
            processViewEntry(words, /* add arguments as needed */ seslis, sesind);
            break;
         case BUY_TAG:
            processBuyEntry(words, /* add arguments as needed */ seslis, sesind);
            break;
         case END_TAG:
            processEndEntry(words, /* add arguments as needed */ seslis);
            break;
      }
   }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printSessionPriceDifference(
      /* add parameters as needed */
           final Map<String, Session> seslis, final List<String> sesind)
   {
      System.out.println("Price Difference for Purchased Product by Session");

      /* add printing */
      for (String pnn : sesind){
         Session session = seslis.get(pnn);
         System.out.print(pnn);
         for (String npn : session.getBoughtnam()){
            System.out.println("     " + npn + " " + session.getPricediffs().get(npn));
         }
      }
   }

      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printCustomerItemViewsForPurchase(
      /* add parameters as needed */
           final Map<String, List<String>> sessionsFromCustomer, List<String> cusind, final Map<String, Session> seslis, final List<String> sesind) {
      System.out.println("Number of Views for Purchased Product by Customer");

      /* add printing */
      for (String pnn : cusind) {

         List<String> prodlis = new LinkedList<>();
         for (String dmt : sessionsFromCustomer.get(pnn)) {
            Session session = seslis.get(dmt);
            if (session.getBoughtnam().size() > 0) {
               for (String zzm : session.getBoughtnam()) {
                  prodlis.add(zzm);
               }
            }
         }
         List<String> cleanlis = NoDubs(prodlis);
         List<Integer> cleannums = new ArrayList<>();
         for (int a = 0; a < cleanlis.size(); a++){
            cleannums.add(0);
         }
         for (String jre : sessionsFromCustomer.get(pnn)) {
            Session sesh = seslis.get(jre);
            for (int i = 0; i < cleanlis.size(); i++){
               for (String zz : sesh.getViewedProducts()){
                  if (cleanlis.get(i) == zz){
                     cleannums.set(i, (cleannums.get(i)+1));
                  }
               }
            }
         }
         if (prodlis.size() > 0){
            System.out.println(pnn);
            for (Integer z = 0; z < cleanlis.size(); z++){
               System.out.println("   " + cleanlis.get(z) + " " + cleannums.get(z));
            }
         }
      }
   }

   private static List<String> NoDubs(List<String> prodlis){
      List<String> memlis = new LinkedList<>();
      List<String> newlis = new LinkedList<>();
      for (String ss : prodlis){
         boolean beenthere = false;
         if (memlis.size() > 0){
            for (String sb : memlis){
               if (sb == ss){
                  beenthere = true;
               }
            }
         }
         else {
            memlis.add(ss);
            newlis.add(ss);
         }
      }
      return newlis;
   }
      //write this after you have figured out how to store your data
      //make sure that you understand the problem
   private static void printStatistics(
      /* add parameters as needed */
           final Map<String, List<String>> sessionsFromCustomer, List<String> cusind, final Map<String, Session> seslis, final List<String> sesind) {
      printSessionPriceDifference( /*add arguments as needed */seslis, sesind);
      printCustomerItemViewsForPurchase( /*add arguments as needed */sessionsFromCustomer, cusind, seslis, sesind);

      /* This is commented out as it will not work until you read
         in your data to appropriate data structures, but is included
         to help guide your work - it is an example of printing the
         data once propogated */
         printOutExample(sessionsFromCustomer, seslis);

   }

   /* provided as an example of a method that might traverse your
      collections of data once they are written 
      commented out as the classes do not exist yet - write them! */

   private static void printOutExample(

      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, Session> viewsFromSession)
   {
      //for each customer, get their sessions
      //for each session compute views
      for(Map.Entry<String, List<String>> entry: 
         sessionsFromCustomer.entrySet()) 
      {
         System.out.println(entry.getKey());
         List<String> sessions = entry.getValue();
         for(String sessionID : sessions)
         {
            System.out.println("\tin " + sessionID);
            List<String> theViews = viewsFromSession.get(sessionID).getViewedProducts();
            for (String thisView: theViews)
            {
               System.out.println("\t\tviewed " + thisView);
            }
         }
      }
   }


      //called in populateDataStructures
   private static void processFile(
      final Scanner input,
      final Map<String, List<String>> sessionsFromCustomer,
      /* add parameters as needed */
      final List<String> cusind, final Map<String, Session> seslis, final List<String> sesind)
   {
      while (input.hasNextLine())
      {
         processLine(input.nextLine(), sessionsFromCustomer, cusind,
            /* add arguments as needed */ seslis, sesind);
      }
   }

      //called from main - mostly just pass through important data structures
   private static void populateDataStructures(
      final String filename,
      final Map<String, List<String>> sessionsFromCustomer,
      /* add parameters as needed */
      final List<String> cusind, final Map<String, Session> seslis, final List<String> sesind)
      throws FileNotFoundException
   {
      try (Scanner input = new Scanner(new File(filename)))
      {
         processFile(input, sessionsFromCustomer, cusind,
            /* add arguments as needed */ seslis, sesind);
      }
   }

   private static String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }

   public static void main(String[] args)
   {
      /* Map from a customer id to a list of session ids associated with
       * that customer.
       */
      final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
      final Map<String, Session> seslis = new HashMap<>();
      final List<String> sesind = new LinkedList<>();
      final List<String> cusind = new LinkedList<>();
      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */

      final String filename = getFilename(args);

      try
      {
         populateDataStructures(filename, sessionsFromCustomer,
            /* add parameters as needed */
            cusind, seslis, sesind);
         printStatistics(
            /* add parameters as needed */
            sessionsFromCustomer, cusind, seslis, sesind);
      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }
   }
}
