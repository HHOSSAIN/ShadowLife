import java.awt.*;

/** This class implements all the logics for any Signs type object */
public class Signs extends Actor{

    /** This stores a string that is unique to the type of the sign*/
    public static final String UP = "SignUp";

    /** This stores a string that is unique to the type of the sign*/
    public static final String DOWN = "SignDown";

    /** This stores a string that is unique to the type of the sign*/
    public static final String LEFT = "SignLeft";

    /** This stores a string that is unique to the type of the sign*/
    public static final String RIGHT = "SignRight";

    /** This stores a string that stores the path of an image
     * unique to the type of the sign*/
    public static final String iUp="res/images/up.png";

    /** This stores a string that stores the path of an image
     * unique to the type of the sign*/
    public static final String iDown="res/images/down.png";

    /** This stores a string that stores the path of an image
     * unique to the type of the sign*/
    public static final String iLeft="res/images/left.png";

    /** This stores a string that stores the path of an image
     * unique to the type of the sign*/
    public static final String iRight="res/images/right.png";
    //up=0, down=1, left=2, right=3

    /** This is the constructor for a Signs object.
     * @param filename This takes the image path as String for the object
     * @param type This takes 1 of the 4 possible sign types
     * @param x This is the 3rd parameter to determine the object's
     * location(x coordinate)
     * @param y This is the 4th parameter to determine the object's
     * location */
    public Signs(String filename, String type, int x, int y) {
        super(filename, type, x, y);
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {
    }

}
