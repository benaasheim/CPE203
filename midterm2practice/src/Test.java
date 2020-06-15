import java.util.*;
import java.util.function.Predicate;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.ArrayList;

import org.junit.Before;


public class Test
{
      public static final double DELTA = 0.0000001;
public static void main(String[] args)
{

      Animal rex = new Animal(4);
      Animal bob = new Professor(0, true, "Bob", 0, 2);
      Professor julie = new Professor(0, true, "Julie", 0, 2);
      Person jane = new Student(3.26, 0, "Jane", 0, 2);

      // What is the output of the following:
      System.out.println(rex.greet(bob));
      System.out.println(bob.greet(rex));
      System.out.println(julie.greet(bob));
      System.out.println(jane.greet(julie));
      System.out.println(julie.greet(jane));

      ArrayList<Professor> profs = new ArrayList<>();
      profs.add(new Professor(500000, false, "Julie", 0, 2));
      profs.add(new Professor(300000, false, "Paul", 0, 2));
      profs.add(new Professor(250001, true, "Phil", 0, 2));
      profs.add(new Professor(250000, true, "Hugh", 0, 2));
      profs.add(new Professor(200000, false, "Kurt", 0, 2));

      ArrayList<Professor> profs2 = new ArrayList<>();
      profs2.add(new Professor(250001, true, "Phil", 0, 2));
      profs2.add(new Professor(300000, false, "Paul", 0, 2));
      profs2.add(new Professor(200000, false, "Kurt", 0, 2));
      profs2.add(new Professor(500000, false, "Julie", 0, 2));
      profs2.add(new Professor(250000, true, "Hugh", 0, 2));




      // Write code to sort an ArrayList of Professor objects in:
      // descending alphabetical order,
      // with all of the tenured professors coming first.

      Comparator<Professor> professorComparator1 = Comparator.comparing(Person::getName);
      Comparator<Professor> professorComparator2 = (p1, p2) -> (p2.hasTenure() ? 1 : p1.hasTenure() ? -1 : 0);
      Comparator<Professor> professorComparator = professorComparator1.reversed().thenComparing(professorComparator2);
      profs.sort(professorComparator);

      assertEquals(profs, profs2);

      // Write one line of code to calculate the average mortgage
      // of all professors with tenure.
      Predicate<Professor> professorPredicate = professor -> professor.hasTenure();
      final LinkedList<Professor> tenured = new LinkedList<>();
      int mortagesum = 0;
      int denomcount = 0;
      for (final Professor professor : profs)
      {
            if (professorPredicate.test(professor))
            {
                  mortagesum += professor.getMortgage();
                  denomcount ++;
                  tenured.add(professor);
            }

      }

      double avg = (double)mortagesum/(double)denomcount;

      double avg2 = (profs.stream().filter(p -> p.hasTenure()).mapToDouble(Professor::getMortgage).sum()/profs.stream().filter(p -> p.hasTenure()).count());
      System.out.println(mortagesum + " " + denomcount + " " + avg + " " + avg2);
      assertEquals(avg, avg2, DELTA);
      }
} 