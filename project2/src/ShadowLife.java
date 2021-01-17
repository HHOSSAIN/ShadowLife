/* This file contains content from the sample solution for Project 1 */

import bagel.AbstractGame;

import bagel.Image;
import bagel.Input;
import bagel.Window;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.lang.reflect.Type;

public class ShadowLife extends AbstractGame{

    /** This stores the tile size */
    public static final int TILE_SIZE = 64;

    private long lastTick = 0;

    /** This stores the max ticks allowed by the program */
    public static int MAX_TICKS;

    /** This stores the tick rate of the program */
    public static int TICK_RATE;

    private int counter=-1;

    private final Image background = new Image("res/images/background.png");

    /** This stores all the gatherers of the simulation */
    public static CopyOnWriteArrayList<Gatherer> gatherers= new CopyOnWriteArrayList<>();

    /** This stores all the thieves of the simulation */
    public static CopyOnWriteArrayList<Thief> thieves= new CopyOnWriteArrayList<>();

    /** This stores all the actors of the simulation */
    public static CopyOnWriteArrayList<Actor> actors= new CopyOnWriteArrayList<>();

    /** This method is used to read through provided file and stores all the actors on a
     * CopyOnWriteArrayList called "actors"; there are 2 more CopyOnWriteArrayList(s) called
     * "gatherers" and "thieves" which store all the gatherers and thieves found respectively.
     * @param file This is the only parameter in the method which is the file to be read from.
     */
    public void load_actors(String file){ //throws InvalidLine{
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String text;
            int count = 0;
            while ((text = br.readLine()) != null) {
                count += 1;
                String parts[]=null;
                try {

                    parts = text.split(","); //.split() makes an arr of splitted text
                    if (parts.length != 3) {
                        throw  new InvalidLine(file,count); //directly goes to catch after execution
                                                           //of thro,i.e. making of the exception obj
                    }

                }
                catch (InvalidLine e){
                    e.getMessage();
                    System.exit(-1);
                }

                String type = parts[0];
                int x=0,y=0;
                boolean correctNum=true;

                try {
                    try {
                        //if()
                        x = Integer.parseInt(parts[1]); // x = Double.parseDouble(cells[1]);
                        y = Integer.parseInt(parts[2]);
                    }
                    catch (NumberFormatException e) {
                        correctNum = false;
                        //e.getMessage();
                    }
                    /* even if an exception is caught by the inner try,catch block above, we haven't
                    * done sytem.exit() yet, so uporer try catch er scope er baire jei code ase,that
                    * will be read by the compiler. However, say we threw an InvalidLineException
                    * before the going inside the inner try,catch block, then hoito inner try,catch
                    * block r read korbe na as they are within the scope of the outer try,catch block
                    * and exception jehetu akta paye gese,it will directly go the outer catch block */
                    if(!correctNum){
                        //System.out.println("got in");
                        throw new InvalidLine(file,count);
                    }
                }
                catch (InvalidLine e){
                    e.getMessage();
                    System.exit(-1);
                }

                /* break this below part into a new function later...sth(String type) */
                try {
                    switch (type) {
                        case Tree.TYPE:
                            actors.add(new Tree(x, y));
                            break;
                        case GoldenTree.TYPE:
                            actors.add(new GoldenTree(x, y));
                            break;
                        case Gatherer.TYPE:
                            actors.add((new Gatherer(x, y, Direction.LEFT)));
                            gatherers.add(new Gatherer(x, y, Direction.LEFT));
                            Gatherer.setActives(1);
                            break;
                        case Thief.TYPE:
                            actors.add(new Thief(x, y, Direction.UP));
                            thieves.add(new Thief(x, y, Direction.UP));
                            //Thief.actives += 1;
                            Thief.setActives(1);
                            break;
                        case Fence.TYPE:
                            actors.add((new Fence(x, y)));
                            break;
                        case Pad.TYPE:
                            actors.add((new Pad(x, y)));
                            break;
                        case Signs.UP:
                            //2nd arg is type,1st arg is fname
                            actors.add((new Signs(Signs.iUp, Signs.UP, x, y)));
                            break;
                        case Signs.DOWN:
                            actors.add((new Signs(Signs.iDown, Signs.DOWN, x, y)));
                            break;
                        case Signs.LEFT:
                            actors.add((new Signs(Signs.iLeft, Signs.LEFT, x, y)));
                            break;
                        case Signs.RIGHT:
                            actors.add((new Signs(Signs.iRight, Signs.RIGHT, x, y)));
                            break;
                        case StockPile.TYPE:
                            actors.add((new StockPile(x, y)));
                            break;
                        case Hoard.TYPE:
                            actors.add((new Hoard(x, y)));
                            break;
                        case MitosisPool.TYPE:
                            actors.add(new MitosisPool(x, y));
                            break;
                        default: throw new InvalidLine(file,count);
                    }
                }
                catch (InvalidLine e){
                    e.getMessage();
                    System.exit(-1);
                }

            }
        }
        catch (FileNotFoundException e){
            System.out.println("error: file " + file +" not found");
            System.exit(-1);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        /* done */
    }

    /** This method is used to call the load_actors(String file) method; it
     * is the constructor of ShadowLife.
     *  @param file expects a file as a parameter to read from. */
    public ShadowLife(String file) throws InvalidLine {
        load_actors(file);
    }

    /** This method is used to call on the tick() methods from the respective gatherers and thieves
     * read from the file which in turn calls their update() methods which implements all the
     * gatherer and thief logics; it also calls on render() method for all actors to draw them
     * on their current position.
     * @param input The only parameter in this method; expects an object from Input
     *   class as parameter */
    @Override
    protected void update(Input input) {
        if (System.currentTimeMillis() - lastTick > TICK_RATE) {

            /*updting last tick val*/
            lastTick = System.currentTimeMillis();

            counter += 1;

            if (counter < MAX_TICKS) {

                for (Gatherer gatherer : gatherers) {
                    //if (actor != null) {
                    if (gatherer != null) {
                        //actor.tick();
                        gatherer.tick();
                    }
                }

                for (Thief thief : thieves) {
                    if (thief != null) {
                        thief.tick();
                    }
                }
            }

            else {
                System.out.println("Timed out");
                System.exit(-1);
            }
        }

        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null && !(actor instanceof Gatherer) && !(actor instanceof Thief)) {
                actor.render();
            }
        }
        for (Gatherer gatherer : gatherers) {
            if (gatherer != null) {
                gatherer.render();
            }
        }
        for (Thief thief : thieves) {
            if (thief != null) {
                thief.render();
            }
        }

        if (Gatherer.getActives() == 0 && Thief.getActives() == 0) {
            counter += 1;
            //System.out.println("ticks=" + counter);
            System.out.println(counter + " ticks");
            for (Actor actor : actors) {
                if (actor instanceof StockPile) {
                    //System.out.println("fruit(sp)=" + ((StockPile) actor).getFruit());
                    System.out.println(((StockPile) actor).getFruit());
                } else if (actor instanceof Hoard) {
                    //System.out.println("fruit(h)=" + ((Hoard) actor).getFruit());
                    System.out.println(((Hoard) actor).getFruit());
                }
            }
            Window.close();
        }
    }

    /** This is the main method which makes a ShadowLife object and calls the run()
     * method */
    public static void main(String[] args) throws InvalidLine{
        String file=null;
        if(args.length != 3){
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        else{
            TICK_RATE= Integer.parseInt(args[0]);
            MAX_TICKS= Integer.parseInt(args[1]);
            file = args[2];
            if(TICK_RATE<0 || MAX_TICKS<0){
                System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
                System.exit(-1);
            }
        }
        new ShadowLife(file).run();
        //ShadowLife game = new ShadowLife(file);
        //game.run();
        //Window.close();
    }
}
