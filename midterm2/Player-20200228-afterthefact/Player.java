/*
You MAY modify this class!
    TO DO:
    a.) Refactor code using the design principles you learned in class

    b.) Override toString , in the format "Player: name, KIND", i.e.: "Player: Max, SMART"
        Important: the kind of the player must be in capital letter as indicated in PlayerKind
 */
import java.util.Objects;
import java.util.Scanner;

public abstract class Player {

    //If you move the variables to other classes, do not change their name!
    public String name;
    public int sticksTaken;


    //do not change the argument list of this constructor in the Player class!
    public Player(String name) {
        this.name = name;
        this.sticksTaken = 0;
    }

    protected abstract void takeTurn(Pile p);





    //IMPORTANT:  Any player should have the option to stop
    public void stop() {
        System.out.println(this + " has left the game.");
    }

    //IMPORTANT:  Any player should have the option to take Sticks
    public boolean takeSticks(int num, Pile p) {
        if(p==null)
            return false;
        if(p.sticks()<num)
            return false;
        p.remove(num);
        return true;
    }
}
