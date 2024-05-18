import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * display and movement of Mosca
 */
public class Fly
{
    protected static final String imgFile = "fly.png";
    
    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    
    /**
     * Creates a new Fly object.<br>
     * The image file for a fly is fly.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the fly is in
     */
    public Fly(GridLocation loc, FlyWorld fw)
    {
        location = loc;
        world = fw;

        try
        {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setFly(this);
    }

    /**
     * @return BufferedImage, the image of the fly
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation, the location of the fly
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always false, Mosca is not a predator
     */
    public boolean isPredator()
    {
    return false;
    }

    /**
    * Returns a string representation of this Fly showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Fly in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }
    
    /**
     * This method <strong>updates</strong> the fly's location in<br>
     * the <strong>world</strong><br>
     * The fly can move in one of the four cardinal (N, S, E, W) directions.<br>
     * You need to make sure that the <strong>updated</strong> location<br>
     * is within the bounds of the world before<br>
     * changing the location of the fly<br>
     *
     * @param direction one of the four cardinal directions<br>
     *        Given as constants in FlyWorldGUI<br><br>
     *        They are:<br>
     *        FlyWorldGUI.NORTH<br>
     *        FlyWorldGUI.SOUTH<br>
     *        FlyWorldGUI.EAST<br>
     *        FlyWorldGUI.WEST<br>
     */
    public void update(int direction)
    {
        if(direction == FlyWorldGUI.NORTH){
            if (world.isValidLoc(this.getLocation().getRow() - 1, this.getLocation().getCol())){
                //getRow-1 because the smaller the row is, the upper the location is
                this.world.getLocation(this.location.getRow(), this.location.getCol()).removeFly();
                //remove fly from original location
                this.location = this.world.getLocation(this.location.getRow() - 1, this.location.getCol());
                //create a new location of the fly to make sure every movement is recorded
                this.world.getLocation(this.location.getRow(), this.location.getCol()).setFly(this);
            }
        } else if(direction == FlyWorldGUI.SOUTH){
            if (world.isValidLoc(this.getLocation().getRow() + 1, this.getLocation().getCol())){
                //getRow+1 because the bigger the row is, the lower the location is
                this.world.getLocation(this.location.getRow(), this.location.getCol()).removeFly();
                //remove fly from original location
                this.location = this.world.getLocation(this.location.getRow() + 1, this.location.getCol());
                //create a new location of the fly to make sure every movement is recorded
                this.world.getLocation(this.location.getRow(), this.location.getCol()).setFly(this);
            }
        } else if(direction == FlyWorldGUI.WEST){
            if (world.isValidLoc(this.getLocation().getRow(), this.getLocation().getCol() - 1)){
                //getCol-1 because the smaller the col is, the lefter the location is
                this.world.getLocation(this.location.getRow(), this.location.getCol()).removeFly();
                //remove fly from original location
                this.location = this.world.getLocation(this.location.getRow(), this.location.getCol() - 1);
                //create a new location of the fly to make sure every movement is recorded
                this.world.getLocation(this.location.getRow(), this.location.getCol()).setFly(this);
            }
        } else if(direction == FlyWorldGUI.EAST){
            if (world.isValidLoc(this.getLocation().getRow(), this.getLocation().getCol() + 1)){
                //getCol+1 because the smaller the col is, the lefter the location is
                this.world.getLocation(this.location.getRow(), this.location.getCol()).removeFly();
                //remove fly from original location
                this.location = this.world.getLocation(this.location.getRow(), this.location.getCol() + 1);
                //create a new location of the fly to make sure every movement is recorded
                this.world.getLocation(this.location.getRow(), this.location.getCol()).setFly(this);
            }
        }
    }
}
