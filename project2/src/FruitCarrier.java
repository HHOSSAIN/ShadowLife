import bagel.Font;

/** This is an abstract class;implements all the fruit related logics common in its subclasses */
public abstract class FruitCarrier extends Actor{
    private int fruit;
    private Font text=new Font("res/VeraMono.ttf", 25);

    public FruitCarrier(String file, String type, int x, int y){
        super(file,type,x,y);
    }

    /** It updates the fruit count.
     * @param change is the only parameter it has which is the change to be made */
    public void fruit_edit(int change){
        fruit += change;
    }

    /** This is used to get fruit count.
     * @return int This returns the current number of fruits in the object. */
    public int getFruit(){
        return fruit;
    }

    /** This is overrriden from superclass Actor.It additionally draws the count
     * of fruit adjacent to image drawn of the object */
    @Override
    public void render(){
        super.render();
        text.drawString(String.valueOf(fruit),getX(),getY());
    }

    /**Overridng the abstract update() method from Actor class just to adhere to the rules;
     * does nothing extra */
    @Override
    public void update() {

    }
}
