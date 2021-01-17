/** This class implements all the logics for a Fence type object */
public class Fence extends Actor{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Fence";

    /** This is the constructor for a Fence object.
     * @param x This is the first parameter to determine the object's
     * location(x coordinate)
     * @param y This is the second parameter to determine the object's
     * location */
    public Fence(int x, int y) {
        super("res/images/fence.png", TYPE, x, y);
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {
    }
}
