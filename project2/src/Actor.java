import bagel.Image;
import java.awt.*;

/** This is an abstract class;implements the logics common in its subclasses */
public abstract class Actor {
    private int x;
    private int y;

    private final Image image;
    public final String type;

    /** This is the constructor of Actor
     * @param filename This expects the path that leads to the image of
     * the respective actor in form of String
     * @param type This expects the type of the object which is unique for
     * the respective Actor subclasses
     * @param x This is the 3rd parameter;expects an int x value
     * @param y This is the 4th parameter;expects an int y value*/
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /** This gives the x value
     * @return int returns value of x */
    public int getX() {
        return x;
    }

    /** This updates the value of x
     * @param x This parameter takes in an int which will be
     * the new value of x*/
    public void setX(int x) {
        this.x = x;
    }

    /** This gives the x value
     * @return int returns value of x */
    public int getY() {
        return y;
    }

    /** This updates the value of x
     * @param y This parameter takes in an int which will be
     * the new value of y*/
    public void setY(int y) {
        this.y = y;
    }

    /** This method calls the update() method */
    public final void tick() {
        update();
    }

    /** This method draws the image of the object that calls it */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /** This method is used to update an object's position to the specified
     * direction.
     * @param direction This parameter expects an int which represents
     * one of the 4 directions and the method will eventually update the
     * coordinates of the object in the given direction */
    public void move(int direction){
        if(direction==0){
            y -= ShadowLife.TILE_SIZE; //up
        }
        else if(direction==1){ //down
            y += ShadowLife.TILE_SIZE;
        }
        else if(direction==2){ //left
            x -= ShadowLife.TILE_SIZE;
        }
        else{ //right
            x += ShadowLife.TILE_SIZE;
        }
    }

    /** Abstract method;does nothing;subclasses will override it and implement logics*/
    public abstract void update();
}
