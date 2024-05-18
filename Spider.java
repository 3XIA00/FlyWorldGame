/**
 * Handles display, movement, and fly eating capabalities for Spiders
 */
public class Spider extends Predator
{
    
    protected static final String imgFile = "spider.png";

    public Spider(GridLocation loc, FlyWorld fw) {
        super(loc, fw, imgFile, "Spider");
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a Spider.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Spiders can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has Spider on it<br>
     * GridLocation has a method to help you determine if there is a Spider<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the Spider can move to
     */
    public GridLocation[] generateLegalMoves()
    {
        GridLocation[] legalMoves = new GridLocation[2];
        //a list to store legal moves
        if(location.getRow() < world.getFlyLocation().getRow()){
        //if spider is north to the fly
            if(checkSouth()){
                legalMoves[0] = northLegalMove();
            }
        } else if(location.getRow() > world.getFlyLocation().getRow()){
        //if spider is south to the fly
            if(checkNorth()){
                legalMoves[0] = southLegalMoves();
            }
        }
        if(location.getCol() > world.getFlyLocation().getCol()){
        //if spider is east to the fly
            if(checkWest()){
                legalMoves[1] = eastLegalMoves();
            }
        }else if(location.getCol() < world.getFlyLocation().getCol()){
        //if spider is west to the fly
            if(checkEast()){
                legalMoves[1] = westLegalMoves();
            }
        }
        return legalMoves;
    }

    private GridLocation northLegalMove(){
        GridLocation legalMove = world.world[location.getRow() + 1][location.getCol()];
        return legalMove;
    }

    private GridLocation southLegalMoves(){
        GridLocation legalMove = world.world[location.getRow() - 1][location.getCol()];
        return legalMove;
    }

    private GridLocation eastLegalMoves(){
        GridLocation legalMove = world.world[location.getRow()][location.getCol() - 1];
        return legalMove;
    }

    private GridLocation westLegalMoves(){
        GridLocation legalMove = world.world[location.getRow()][location.getCol() + 1];
        return legalMove;
    }

    private boolean checkNorth(){
        return world.isValidLoc(location.getRow() - 1, location.getCol()) && world.world[location.getRow() - 1][location.getCol()].hasPredator() != true;
    }
    private boolean checkSouth(){
        return world.isValidLoc(location.getRow() + 1, location.getCol()) && world.world[location.getRow() + 1][location.getCol()].hasPredator() != true;
    }
    private boolean checkWest(){
        return world.isValidLoc(location.getRow(), location.getCol() - 1) && world.world[location.getRow()][location.getCol() - 1].hasPredator() != true;
    }
    private boolean checkEast(){
        return world.isValidLoc(location.getRow(), location.getCol() + 1) && world.world[location.getRow()][location.getCol() + 1].hasPredator() != true;
    }

    /**
     * This method helps determine if a Spider is in a location<br>
     * where it can eat a fly or not. A Spider can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        GridLocation flyLocation = world.getFlyLocation();
        return location.equals(flyLocation);
    }   
}
