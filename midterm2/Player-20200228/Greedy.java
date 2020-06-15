public class Greedy extends Player{
    public String name;
    private String taunt;

    //do not change the argument list of this constructor in the Player class!
    public Greedy(String name, String taunt) {
        super(name);
        this.taunt = taunt;
    }



    public void takeTurn(Pile p) {

        if (p.sticks() > 3)
            sticksTaken=3;
        else
            sticksTaken= (p.sticks());
        p.remove(sticksTaken);

        System.out.print(this.taunt);

    }
}
