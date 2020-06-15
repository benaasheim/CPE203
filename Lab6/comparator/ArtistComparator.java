//import java.util.Comparator;
//
//public class ArtistComparator implements Comparator<Song> {
//    public ArtistComparator() {
//    }
//    public int compare(Song c1, Song c2) {
//        Comparator<Song> artistComparator = Comparator.comparing(Song::getArtist);
//        return artistComparator.compare(c1, c2);
//    }
//}

import java.util.Comparator;

class ArtistComparator implements Comparator<Song>
{
    public int compare(Song c1, Song c2) {
        return c1.getArtist().compareTo(c2.getArtist());
    }
}

