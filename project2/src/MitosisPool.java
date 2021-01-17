/** This class implements all the logics for a MitosisPool type object */
public class MitosisPool extends Actor{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Pool";

    /** This constructor of MitosisPool take in 2 parameters
     * @param x is the first parameter
     * @param y is the second parameter*/
    public MitosisPool(int x, int y) {
        super("res/images/pool.png", TYPE, x, y);
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {

    }
}


