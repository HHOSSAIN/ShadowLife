/** This class implements all the logics for a GoldenTree type object */
public class GoldenTree extends Actor{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "GoldenTree";

    /** This constructor of GoldenTree take in 2 parameters which are its
     * positional coordinates.
     * @param x is the first parameter;expects an int x value
     * @param y is the second parameter;expects an int y value*/
    public GoldenTree(int x, int y) {
        super("res/images/gold-tree.png", TYPE, x, y);
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {

    }
}

