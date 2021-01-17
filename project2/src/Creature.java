import java.awt.*;

/** This is an abstract class;implements all the common logics for
 *  its subclass objects */
public abstract class Creature extends Actor{
    private Boolean carrying=false;
    private Boolean active=true;
    private int direction;
    private int prev_x;
    private int prev_y;

    /** This is the constructor for Creature
     * @param filename This is the first parameter which expects a filename
     * as String
     * @param type This is the second parameter which expects a String for the type
     * of Actor
     * @param x This the third parameter which expects an int
     * @param y This is the fourth parameter which expects an int
     * */
    public Creature(String filename, String type,int x, int y){
        super(filename,type,x,y);
    }

    /** This tells whether a creature is carrying fruit or not
     * @return Boolean This returns a Boolean value */
    public Boolean getCarrying() {
        return carrying;
    }

    /** This updates the state whether the creature is carrying or not.
     * @param carrying This is the only parameter and it expects a Boolean
     * value. */
    public void setCarrying(Boolean carrying) {
        this.carrying = carrying;
    }

    /** This method tells whether the creature is active or not at that point of time.
     * @return This returns a Boolean. */
    public Boolean getActive() {
        return active;
    }

    /** This updates the state of the creature whether it is active or not
     * @param active This parameter expects a Boolean value */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /** This gives the current direction of the creature.
     * @return int This returns an int which stands for one of the 4 directions */
    public int getDirection() {
        return direction;
    }

    /** This updates the direction of the creauture.
     * @param direction This parameter expects an int which stands for
     * one of the 4 directions */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /** This gives the previous x value of the position the creature was at
     * @return int This returns an int which stands for one of the
     * four direcions*/
    public int getPrev_x() {
        return prev_x;
    }

    /** This updates the previous x value of the creature
     * @param prev_x This parameter expects an int */
    public void setPrev_x(int prev_x) {
        this.prev_x = prev_x;
    }

    /** This updates the previous y value of the creature
     * @param prev_y This parameter expects an int */
    public void setPrev_y(int prev_y) {
        this.prev_y = prev_y;
    }

    /** This gives the previous y value of the position the creature was at
     * @return int This returns an int which stands for one of the
     * four direcions*/
    public int getPrev_y() {
        return prev_y;
    }

    /** This checks whether the 2 "x" and the 2 "y" values are equal or not
     * @param x1 The x value of  actor1/creature1
     * @param y1 The y value of actor1/creature1
     * @param x2 The x value of actor2/creature2
     * @param y2 The y value of actor2/creature2
     * @return boolean This returns a boolean */
    public boolean stand_equality(int x1, int y1, int x2, int y2){
        if(x1==x2 && y1==y2){
            return true;
        }
        return false;
    }
    /** This method implements the logics that are common to both gatherer
     * and creature
     * @param creature This 1st parameter takes in a Creature object
     * @param actor This 2nd parameter takes an Actor object
     * */
    public void partial_logics(Creature creature,Actor actor){
        if (actor instanceof Fence) {
            creature.active = false;
            if(creature instanceof Gatherer) {
                Gatherer.setActives(-1);
            }
            else {
                Thief.setActives(-1);
            }

            creature.setX(creature.prev_x);
            creature.setY(creature.prev_y);
        }

    }
    /** This method rotates the given Creature object by 180 degree
     * @param g The 1st parameter takes in the Creature object we want to
     * rotate
     * @param direction  The 2nd parameter takes in the current direction of the object
     * */
    public void rotate_180(Creature g, int direction){
        switch (direction){
            case Direction.UP:
                g.direction=Direction.DOWN;
                break;
            case Direction.DOWN:
                g.direction=Direction.UP;
                break;
            case Direction.LEFT:
                g.direction=Direction.RIGHT;
                break;
            case Direction.RIGHT:
                g.direction=Direction.LEFT;
                break;
        }
    }

    /** This method rotates the given Creature object by 90 degree A.C.W
     * @param g The 1st parameter takes in the Creature object we want to
     * rotate
     * @param direction  The 2nd parameter takes in the current direction of the object
     * */
    public void rotate_Acw90(Creature g, int direction){
        switch (direction){
            case Direction.UP:
                g.direction=Direction.LEFT;
                break;
            case Direction.DOWN:
                g.direction=Direction.RIGHT;
                break;
            case Direction.LEFT:
                g.direction=Direction.DOWN;
                break;
            case Direction.RIGHT:
                g.direction=Direction.UP;
                break;
        }
    }

    /** This method rotates the given Creature object by 90 degree C.W.
     * @param g The 1st parameter takes in the Creature object we want to
     * rotate
     * @param direction  The 2nd parameter takes in the current direction of the object
     * */
    public void rotate_Cw90(Creature g, int direction){
        switch (direction){
            case Direction.UP:
                g.direction=Direction.RIGHT;
                break;
            case Direction.DOWN:
                g.direction=Direction.LEFT;
                break;
            case Direction.LEFT:
                g.direction=Direction.UP;
                break;
            case Direction.RIGHT:
                g.direction=Direction.DOWN;
                break;
        }
    }
    /** This method gives the direction we would get after rotating it 90 degree
     * A.C.W starting from a specified direction
     * @param direction  The sole parameter takes in a starting direction
     * @return int The method returns the final direction represented by an int.
     * */
    public int rotateAcw90(int direction){ //try to move this func to creature
        int dir=0;
        switch (direction){
            case Direction.UP:
                dir=Direction.LEFT;
                break;
            case Direction.DOWN:
                dir=Direction.RIGHT;
                break;
            case Direction.LEFT:
                dir=Direction.DOWN;
                break;
            case Direction.RIGHT:
                dir=Direction.UP;
                break;
        }
        return dir;
    }

    /** This method gives the direction we would get after rotating it 90 degree
     * C.W starting from a specified direction.
     * @param direction  The sole parameter takes in a starting direction
     * @return int The method returns the final direction represented by an int.
     * */
    public int rotateCw90(int direction){ //try to move this func to creature
        int dir=0;
        switch (direction){
            case Direction.UP:
                dir=Direction.RIGHT;
                break;
            case Direction.DOWN:
                dir=Direction.LEFT;
                break;
            case Direction.LEFT:
                dir=Direction.UP;
                break;
            case Direction.RIGHT:
                dir=Direction.DOWN;
                break;
        }
        return dir;
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {

    }
}
