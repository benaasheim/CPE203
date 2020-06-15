public class Lazy extends Player {
    public String name;

    //do not change the argument list of this constructor in the Player class!
    public Lazy(String name) {
        super(name);
    }


    public void takeTurn(Pile p) {
        p.remove(p.sticks());
        stop();
    }
}
