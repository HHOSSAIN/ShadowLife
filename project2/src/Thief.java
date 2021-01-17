import bagel.Font;

/** This class implements all the logics for a Thief type object */
public class Thief extends Creature{

    /** This stores a string that is unique to the type of the class*/
    public static final String TYPE = "Thief";

    private Boolean consuming=false;
    private int tickCounter = -1;
    private int dir1,dir2;
    private static int actives=0;
    private Font text=new Font("res/VeraMono.ttf", 25);

    /** This is the consructor for thief.
     * @param x This 1st parameter takes in an int which will be the x coordinate value
     * of the object being made
     * @param y This 2nd parameter takes in an int which will be the y value of the
     * object being made
     * @param direction This is the direction where the object will be moving towards;it
     * takes in an int which points to the direction.
     * */
    public Thief(int x, int y,int direction) {
        super("res/images/thief.png", TYPE, x, y);
        this.setDirection(direction);
    }

    /** This method gives the number of active thieves present
     * @return int This returns the number of thieves currently active. */
    public static int getActives() {
        return actives;
    }

    /** This is used to update the number of active thieves.
     * @param i This parameter takes in an int which is used to give
     * the change that will happen in the number of active thieves. */
    public static void setActives(int i){
        actives += i;
    }

    /** This method implements the vast logics that a thief is meant to follow */
    @Override
    public void update() {
        tickCounter += 1;
        if (tickCounter < ShadowLife.MAX_TICKS) { //do sth to halt otherwise
            if(getActive()){
                this.setPrev_x(getX());
                this.setPrev_y(getY());
                //System.out.println("ptx=" + getX() + " pty=" + getY());
                move((getDirection()));
                //System.out.println(("atx=" + getX() + " aty=" + getY()));
            }
            for (Actor actor : ShadowLife.actors) {
                if (stand_equality(this.getX(), this.getY(), actor.getX(), actor.getY())) {
                    if (actor instanceof Fence) {
                        setActive(false);
                        actives -= 1;
                        setX(getPrev_x());
                        setY(getPrev_y());
                    }
                    else if (actor instanceof MitosisPool) {
                        dir1 = rotateAcw90(getDirection());
                        dir2 = rotateCw90(getDirection());

                        ShadowLife.actors.add(new Thief(getX(), getY(), dir1));
                        ShadowLife.thieves.add(new Thief(getX(),getY(),dir1));
                        actives += 1;

                        ShadowLife.actors.add(new Thief(getX(), getY(), dir2));
                        ShadowLife.thieves.add(new Thief(getX(),getY(),dir2));
                        actives += 1;

                        int tmp_size = ShadowLife.actors.size();
                        ShadowLife.actors.get(tmp_size - 2).move(dir1);
                        ShadowLife.actors.get(tmp_size - 1).move(dir2);

                        //new
                        int tmp_size2= ShadowLife.thieves.size();
                        ShadowLife.thieves.get(tmp_size2-2).move(dir1);
                        ShadowLife.thieves.get(tmp_size2-1).move(dir2);

                        ShadowLife.actors.remove(this);
                        actives -= 1;
                    }
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
                    else if(actor instanceof Pad){
                        consuming=true;
                    }

                    else if(actor instanceof Tree && !getCarrying()){
                        if(((Tree) actor).getFruit() >= 1){
                            ((Tree) actor).fruit_edit(-1);
                            setCarrying(true);
                        }
                    }
                    else if (actor instanceof GoldenTree && !getCarrying()){
                        setCarrying(true);
                    }
                    else if(actor instanceof Hoard){
                        if (consuming){
                            consuming=false;
                            if(!getCarrying()){
                                if(((Hoard) actor).getFruit() >= 1){
                                    setCarrying(true);
                                    ((Hoard) actor).fruit_edit(-1);
                                }
                                else{
                                    this.rotate_Cw90(this,this.getDirection());
                                }
                            }
                        }
                        else if (getCarrying()){
                            setCarrying(false);
                            ((Hoard) actor).fruit_edit(1);
                            this.rotate_Cw90(this,this.getDirection());
                        }
                    }
                    else if(actor instanceof StockPile){
                        if (!getCarrying()){
                            if(((StockPile) actor).getFruit() >= 1){
                                setCarrying(true);
                                consuming=false;
                                ((StockPile) actor).fruit_edit(-1);
                                this.rotate_Cw90(this,this.getDirection());
                            }
                        }
                        else {
                            this.rotate_Cw90(this,this.getDirection());
                        }
                    }
                    else {

                    }
                }
            }
            //new
            for (Gatherer gatherer : ShadowLife.gatherers) {
                if (stand_equality(this.getX(), this.getY(), gatherer.getX(), gatherer.getY())) {
                    //System.out.println("got in");
                    if (((Gatherer) gatherer).getTickCounter() >= tickCounter) {
                        this.rotate_Acw90(this, this.getDirection());
                    }
                }
            }
        }
    }
}
