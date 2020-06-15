import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      Comparator<Song> artistComparator = new ArtistComparator();
      assertEquals(artistComparator.compare(songs[2], songs[3]), -6);
      assertEquals(artistComparator.compare(songs[3], songs[7]), 0);
      assertEquals(artistComparator.compare(songs[6], songs[3]), 10);
      assertEquals(artistComparator.compare(songs[6], songs[1]), -1);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> titleComparator = (c1, c2) -> c1.getTitle().compareTo(c2.getTitle());
      assertEquals(titleComparator.compare(songs[6], songs[1]), -10);
      assertEquals(titleComparator.compare(songs[3], songs[5]), 0);
      assertEquals(titleComparator.compare(songs[6], songs[7]), 14);
   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> yearExtractorComparator = Comparator.comparingInt(Song::getYear).reversed();
      assertEquals(yearExtractorComparator.compare(songs[4], songs[1]), -1);
      assertEquals(yearExtractorComparator.compare(songs[0], songs[1]), 0);
      assertEquals(yearExtractorComparator.compare(songs[6], songs[7]), 1);

   }

   @Test
   public void testComposedComparator()
   {
      ComposedComparator composedComparator = new ComposedComparator();
      assertEquals(composedComparator.compare(songs[2], songs[3]), -6);
      assertEquals(composedComparator.compare(songs[3], songs[7]), 20);
      assertEquals(composedComparator.compare(songs[6], songs[3]), 10);
      assertEquals(composedComparator.compare(songs[6], songs[1]), -1);
      assertEquals(composedComparator.compare(songs[6], new Song("Queen", "Bohemian Rhapsody", 1976)), -1);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> titleComparator = (c1, c2) -> c1.getTitle().compareTo(c2.getTitle());
      Comparator<Song> comparator = titleComparator.thenComparing(new ArtistComparator());
      assertEquals(comparator.compare(songs[6], songs[1]), -10);
      assertEquals(comparator.compare(songs[3], songs[5]), 1);
      assertEquals(comparator.compare(songs[6], songs[7]), 14);
      assertEquals(comparator.compare(songs[5], songs[3]), -1);
      assertEquals(comparator.compare(songs[5], songs[5]), 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      Comparator<Song> artistComparator = new ArtistComparator();
      Comparator<Song> titleComparator = artistComparator.thenComparing((c1, c2) -> c1.getTitle().compareTo(c2.getTitle()));

      Comparator<Song> yearExtractorComparator = Comparator.comparingInt(Song::getYear);
      Comparator<Song> comparator = titleComparator.thenComparing(yearExtractorComparator);

      songList.sort(comparator);

      assertEquals(songList, expectedList);
   }
}
