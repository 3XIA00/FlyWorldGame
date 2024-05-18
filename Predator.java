import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
public abstract class Predator {

    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    protected String name;

    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Predator(GridLocation loc, FlyWorld fw, String imgFile, String name)
    {
        location = loc;
        world = fw;
        this.name = name;
        try
        {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setPredator(this);
    }
    // I basically used the same code in Fly because Frog and Fly have the same functions. The only difference is that frog moves by itself

    /**
     * @return BufferedImage the image of the frog
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation the location of the frog
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always true
     */
    public boolean isPredator()
    {
    return true;
    }

    public FlyWorld getWorld(){
        return world;
    }

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = name + " in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }

    abstract public GridLocation[] generateLegalMoves();

    public void update(){
        GridLocation[] tempMoves = generateLegalMoves();
        GridLocation[] legalMoves = new GridLocation[4];
        int counter = 0;
        //to count legalMoves
        for(int i = 0; i < tempMoves.length; i++){
            if(tempMoves[i] != null){
                legalMoves[counter++] = tempMoves[i];
            }
        }
        
        if(counter > 0){
        //if spider and fly are neither on same row nor on same col
            Random randNum = new Random();
            int move = randNum.nextInt(counter);
            this.world.getLocation(location.getRow(), location.getCol()).removePredator();
            // remove old forg
            this.location = legalMoves[move];
            // update the location with a new legal move
            this.world.getLocation(location.getRow(), location.getCol()).setPredator(this);
        }
    }


}
