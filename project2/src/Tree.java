import bagel.Font;

/** This class implements all the logics for a Tree type object */
public class Tree extends FruitCarrier {

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Tree";

    private Font text = new Font("res/VeraMono.ttf", 25);

    /**
     * This constructor of Tree take in 2 parameters which are its
     * positional coordinates
     *
     * @param x is the first parameter
     * @param y is the second parameter
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        fruit_edit(3);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "text=" + text +
                '}';
    }
}
