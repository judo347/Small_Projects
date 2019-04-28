import java.awt.*;
import java.util.ArrayList;

public class AreaHandler {

    //Data

    public int totalNumberOfTiles;
    public int numberOfWaterTiles = 0;
    public boolean canHaveMoreWaterTiles = true;
    public int maxTilesForOneBodyOfWater;


    //-------------------


    private TileType[][] area;
    private int width;
    private int height;

    public enum TileType{
        ROAD(Color.GRAY), GRASS(Color.GREEN), WATER(Color.BLUE), STRUCTURES(Color.ORANGE);

        private Color coler;

        TileType(Color coler) {
            this.coler = coler;
        }

        public Color getColer() {
            return coler;
        }
    }

    public AreaHandler(int width, int height) {
        this.area = new TileType[width][height];
        this.width = width;
        this.height = height;
        this.totalNumberOfTiles = width * height;
        this.maxTilesForOneBodyOfWater = (int)((double)totalNumberOfTiles / Rules.maxWaterBodyCoveragePercent);
    }

    /** @return a 3x3 array of the tiles surrounding the given coordinate. */
    public TileType[][] getSurroundingArea3x3(int x, int y){

        TileType[][] surrounding = new TileType[3][3];

        //Top row
        surrounding[0][0] = getTile(x-1, y-1);
        surrounding[1][0] = getTile(x, y-1);
        surrounding[2][0] = getTile(x+1, y-1);

        //Middle row
        surrounding[0][1] = getTile(x-1, y);
        surrounding[1][1] = getTile(x, y);
        surrounding[2][1] = getTile(x+1, y);

        //Bottom row
        surrounding[0][2] = getTile(x-1, y+1);
        surrounding[1][2] = getTile(x, y+1);
        surrounding[2][2] = getTile(x+1, y+1);

        return surrounding;
    }

    /**@return the tiletype of the given coordinate. Might return null. */
    private TileType getTile(int x, int y){

        if (!isCoordOutOfBounds(x, y))
                return area[x][y];

        return null;
    }

    private boolean isCoordOutOfBounds(int x, int y){
        if(x > 0 && x < width-1)
            if(y > 0 && y < height-1)
                return false;

        return true;
    }

    public void setTile(int x, int y, TileType type){
        this.area[x][y] = type;

        //Count number of water tiles and check if the percentage gets too high
        if(type == TileType.WATER){
            numberOfWaterTiles++;
            double percentWaterCoverage = ((double)numberOfWaterTiles / (double)totalNumberOfTiles);
            //System.out.println(totalNumberOfTiles + " " + numberOfWaterTiles + " " + percentWaterCoverage);
            if(percentWaterCoverage >= Rules.maxWaterCoveragePercent)
                canHaveMoreWaterTiles = false;
        }
    }

    /** Takes a coordinate, and takes that type and location and
     * returns the number of all connected tiles of that type. */
    public int getSizeOfBodyFromType(int x, int y){

        ArrayList<int[]> foundTiles = new ArrayList<>();

        foundTiles = recursivelyFindTilesOfType(x, y, getTile(x, y), foundTiles);

        if(getTile(x, y) != null)
            System.out.println("Size of type: " + foundTiles.size());

        return foundTiles.size();
    }

    public ArrayList<int[]> recursivelyFindTilesOfType(int x, int y, TileType type, ArrayList<int[]> foundTiles){

        //Out of bounce check
        if(isCoordOutOfBounds(x, y))
            return foundTiles;

        //if the tile of the right type?
        if(getTile(x, y) == type){

            //Is the tile already added to the list?
            boolean isTileInList = false;

            for (int[] coord : foundTiles){
                if(coord[0] == x && coord[1] == y)
                    isTileInList = true;
            }

            //Has this tile not been checked?
            if(!isTileInList){

                foundTiles.add(new int[]{x, y});

                //Call this method on all surrounding tiles
                foundTiles = recursivelyFindTilesOfType(x-1, y-1, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x, y-1, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x+1, y-1, type, foundTiles);

                foundTiles = recursivelyFindTilesOfType(x-1, y, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x, y, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x+1, y, type, foundTiles);

                foundTiles = recursivelyFindTilesOfType(x-1, y+1, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x, y+1, type, foundTiles);
                foundTiles = recursivelyFindTilesOfType(x+1, y+1, type, foundTiles);
            }

        }

        return foundTiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileType[][] getArea() {
        return area;
    }
}
