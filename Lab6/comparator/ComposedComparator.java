import java.util.Comparator;

class ComposedComparator implements Comparator<Song>
{


    public int compare(Song c1, Song c2) {
        int DeltaArtist = c1.getArtist().compareTo(c2.getArtist());
        int DeltaYear = c1.getYear() - c2.getYear();

        if (DeltaArtist != 0) {
            return DeltaArtist;
        }
        else {
            return ((DeltaYear == 0) ? -1 : DeltaYear);
        }
    }
}
