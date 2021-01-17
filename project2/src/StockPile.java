import bagel.Font;

/** This class implements all the logics for a StockPile type object */
public class StockPile extends FruitCarrier{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Stockpile";

    private int fruit=0;
    private Font text=new Font("res/VeraMono.ttf", 25);

    /** This is the constructor for a StockPile object.
     * @param x This is the first parameter to determine the object's
     * location
     * @param y This is the second parameter to determine the object's
     * location */
    public StockPile(int x, int y) {
        super("res/images/cherries.png", TYPE, x, y);
        fruit_edit(0);
    }
}

