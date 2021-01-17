import bagel.Font;

/** This class implements all the logics for a Hoard type object */
public class Hoard extends FruitCarrier{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Hoard";

    private Font text=new Font("res/VeraMono.ttf", 25);

    /** This constructor of Hoard take in 2 parameters.
     * @param x is the first parameter
     * @param y is the second parameter*/
    public Hoard(int x, int y) {
        super("res/images/hoard.png", TYPE, x, y);
        fruit_edit(0);
    }
}