/* This file contains content from the sample solution for Project 1 */

/** This class implements all the logics for a Gatherer type object */
public class Gatherer extends Creature{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Gatherer";

    private int tickCounter = -1;
    private static int actives=0;
    private int dir1,dir2;

    /** This is the consructor for thief.
     * @param x This 1st parameter takes in an int which will be the x coordinate value
     * of the object being made
     * @param y This 2nd parameter takes in an int which will be the y value of the
     * object being made
     * @param direction This is the direction where the object will be moving towards;it
     * takes in an int which points to the direction.
     *  */
    public Gatherer(int x, int y,int direction) {
        super("res/images/gatherer.png", TYPE, x, y);
        this.setDirection(direction);
    }

    //put these meths in the superclass Creature
    /** This method gives the number of active gatherers present
     * @return int This returns the number of gatherers currently active. */
    public static int getActives() {
        return actives;
    }

    /** This is used to update the number of active gatherers.
     * @param i This parameter takes in an int which is used to give
     * the change that will happen in the number of active gatherers. */
    public static int setActives(int i){
        actives += i;
        return actives;
    }

    /** This method gives the number of ticks that have passed for a gatherer
     * @return int This returns an int which is the number of ticks that
     * have passed for a gatherer */
    public int getTickCounter(){
        return tickCounter;
    }

    /**  This method implements the vast logics that a gatherer is meant to follow */
    @Override
    public void update() {
        tickCounter += 1;
        if (tickCounter < ShadowLife.MAX_TICKS) {
            if(getActive()){
                this.setPrev_x(getX());
                this.setPrev_y(getY());
                //System.out.println("px="+getX()+ " py="+getY());
                move(getDirection());
                //System.out.println(("ax="+getX()+ " ay="+getY()));
            }

            for (Actor actor : ShadowLife.actors) {//for every gatherer,check all actors,hence O(n^2)
                if (stand_equality(this.getX(), this.getY(), actor.getX(), actor.getY())) {
                    if (actor instanceof Fence) {
                        setActive(false);
                        actives -= 1;
                        setX(this.getPrev_x());
                        setY(this.getPrev_y());
                    }

                    //mitosis pool
                    else if (actor instanceof MitosisPool) {
                        dir1 = rotateAcw90(getDirection());
                        dir2 = rotateCw90(getDirection());

                        ShadowLife.actors.add(new Gatherer(getX(), getY(), dir1));
                        ShadowLife.gatherers.add(new Gatherer(getX(), getY(), dir1));
                        actives += 1;

                        ShadowLife.actors.add(new Gatherer(getX(), getY(), dir2));
                        ShadowLife.gatherers.add(new Gatherer(getX(), getY(), dir2));
                        actives += 1;

                        int tmp_size1 = ShadowLife.actors.size();
                        ShadowLife.actors.get(tmp_size1 - 2).move(dir1);
                        ShadowLife.actors.get(tmp_size1 - 1).move(dir2);

                        //new
                        int tmp_size2= ShadowLife.gatherers.size();
                        ShadowLife.gatherers.get(tmp_size2-2).move(dir1);
                        ShadowLife.gatherers.get(tmp_size2-1).move(dir2);

                        ShadowLife.actors.remove(this);
                        ShadowLife.gatherers.remove(this);
                        actives -= 1;
                    }

                    //sign
                    else if (actor instanceof Signs) {
                        String sType = actor.type;
                        switch (sType) {
                            case Signs.UP:
                                setDirection(Direction.UP);
                                break;
                            case Signs.DOWN:
                                setDirection(Direction.DOWN);
                                break;
                            case Signs.LEFT:
                                setDirection(Direction.LEFT);
                                break;
                            case Signs.RIGHT:
                                setDirection(Direction.RIGHT);
                                break;
                        }
                    }

                    else if (actor instanceof Tree && !getCarrying())  {
                        if (((Tree) actor).getFruit() > 0) {
                            ((Tree) actor).fruit_edit(-1);
                            setCarrying(true);
                            rotate_180(this, this.getDirection());
                        }
                    }

                    else if (actor instanceof GoldenTree && !getCarrying()){
                        setCarrying(true);
                        rotate_180(this, this.getDirection());
                    }
                    else if (actor instanceof Hoard || actor instanceof StockPile) {
                        if(getCarrying()){
                            setCarrying(false);
                            if (actor instanceof Hoard) {
                                ((Hoard) actor).fruit_edit(1);
                            } else {
                                ((StockPile) actor).fruit_edit(1);
                            }
                        }
                        this.rotate_180(this, this.getDirection());
                    }
                    else {
                    }
                }

            }

        }
        else {
            System.out.println("Timed out");
            System.exit(-1);
        }
    }
}

