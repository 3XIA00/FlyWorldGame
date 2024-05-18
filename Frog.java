/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog extends Predator
{
    protected static final String imgFile = "frog.png";

    public Frog(GridLocation loc, FlyWorld fw) {
        super(loc, fw, imgFile, "Frog");
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    public GridLocation[] generateLegalMoves()
    {
        GridLocation[] legalMoves = new GridLocation[4];
        //a list to store legal moves
        GridLocation frogLoc = location;
        //record location of the frog
        if(world.isValidLoc(frogLoc.getRow() - 1, frogLoc.getCol()) && !world.world[frogLoc.getRow() - 1][frogLoc.getCol()].hasPredator()){
        //check north
            legalMoves[0] = world.getLocation(frogLoc.getRow() - 1, frogLoc.getCol());
        }
        if(world.isValidLoc(frogLoc.getRow() + 1, frogLoc.getCol()) && !world.world[frogLoc.getRow() + 1][frogLoc.getCol()].hasPredator()){
        //check south
            legalMoves[1] = world.getLocation(frogLoc.getRow() + 1, frogLoc.getCol());
        }
        if(world.isValidLoc(frogLoc.getRow(), frogLoc.getCol() - 1) && !world.world[frogLoc.getRow()][frogLoc.getCol() - 1].hasPredator()){
        //check west
            legalMoves[2] = world.getLocation(frogLoc.getRow(), frogLoc.getCol() - 1);
        }
        if(world.isValidLoc(frogLoc.getRow(), frogLoc.getCol() + 1) && !world.world[frogLoc.getRow()][frogLoc.getCol() + 1].hasPredator()){
        //check east
            legalMoves[3] = world.getLocation(frogLoc.getRow(), frogLoc.getCol() + 1);
        }
        return legalMoves; 
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        GridLocation north = new GridLocation(location.getRow() - 1, location.getCol());
        GridLocation south = new GridLocation(location.getRow() + 1, location.getCol());
        GridLocation west = new GridLocation(location.getRow(), location.getCol() - 1);
        GridLocation east = new GridLocation(location.getRow(), location.getCol() + 1);
        GridLocation flyLocation = world.getFlyLocation();
        return location.equals(flyLocation) || north.equals(flyLocation) || south.equals(flyLocation) || west.equals(flyLocation) || east.equals(flyLocation); 
    }   
}
