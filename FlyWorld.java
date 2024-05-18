import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Color;


/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{
    protected int numRows;
    protected int numCols;

    protected GridLocation [][] world;

    protected GridLocation start;
    protected GridLocation goal;
    
    protected Fly mosca;

    protected Frog[] frogs;
    protected Spider[] spider;

    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName){
        
        File theFile = new File(fileName);
        Scanner scan = null;
        try{
            scan = new Scanner(theFile);
        }
        catch (FileNotFoundException fnfe){
            System.err.println("File does not exist"); //check if file exist
        }
        String[] rows_cols = scan.nextLine().split(" ");
        numRows = Integer.parseInt(rows_cols[0]);
        numCols = Integer.parseInt(rows_cols[1]);
        String worldStr = "";                             //a string to store world as a string so that I can locate position of frogs using %row and /row
        int numFrogs = 0;
        int numSpiders = 0;
        world = new GridLocation[numRows][numCols];       //incialize a world with numbRows and numCols
        for(int i = 0; i < numRows; i++){
            String temp = scan.nextLine();
            worldStr = worldStr + temp;
            for(int j = 0; j < numCols; j++){
                world[i][j] = new GridLocation(i, j);
                if(temp.charAt(j) == 'f'){
                    numFrogs ++;
                }else if(temp.charAt(j) == 'a'){
                    numSpiders ++;
                }else if(temp.charAt(j) == 'h'){          //finding goal
                    world[i][j].setBackgroundColor(Color.RED);
                    goal = world[i][j];
                }else if(temp.charAt(j) == 's'){          //finding start
                    world[i][j].setBackgroundColor(Color.GREEN);
                    start = world[i][j];
                }
            }
        }
        scan.close();
        mosca = new Fly(start, this);                  //set initial position of fly

        frogs = new Frog[numFrogs];
        int frogTempCounter = 0;
        spider = new Spider[numSpiders];
        int spiderTempCounter = 0;
        System.out.println(numRows + " , " + numCols);
        for(int k = 0; k < worldStr.length(); k++){
            if(worldStr.charAt(k) == 'f'){
                frogs[frogTempCounter++] = new Frog(world[k/numCols][k%numCols],this);  //find and record frog locations
            }else if(worldStr.charAt(k) == 'a'){
                spider[spiderTempCounter++] = new Spider(world[k/numCols][k%numCols],this); //find and record spider locations
            }
        }
    }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows(){
        return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols(){
        return numCols;
    }

    /**
     * Deterimes if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r a row
     * @param c a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c){
        return r >= 0 && r < numRows && c >= 0 && c < numCols;
    }

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r the row
     * @param c the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c){
        return world[r][c];
    }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation(){
        return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction){
        mosca.update(direction);
        if(mosca.getLocation().equals(goal)){
            return FlyWorldGUI.ATHOME;
        } else{
            for(int i = 0; i < frogs.length; i++){
                if(frogs[i].eatsFly()){
                    return FlyWorldGUI.EATEN;
                }
            }
            for(int j = 0; j < spider.length; j++){
                if(spider[j].eatsFly()){
                    return FlyWorldGUI.EATEN;
                }
            }
        }
        return FlyWorldGUI.NOACTION;
    }

    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators(){
        for (int i = 0; i < frogs.length; i++) {
            frogs[i].update();
            if (frogs[i].eatsFly()) {
                return true;
            }
        }
        for (int i = 0; i < spider.length; i++) {
            spider[i].update();
            if (spider[i].eatsFly()) {
                return true;
            }
        }
        return false; 
    }
}
