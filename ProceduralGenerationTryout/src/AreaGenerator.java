import java.util.ArrayList;
import java.util.Random;

public class AreaGenerator {

    /** Has to be procedural. Start in the middle and move in a spiral to fill in tiles. */

    private Random rand = new Random();

    private AreaHandler area;

    public AreaGenerator(int width, int height) {
        this.area = new AreaHandler(width, height);
        //generateArea01();
        generateArea02();
    }

    /** Tile by tile, from one end to the other. */
    private void generateArea01(){

        for(int x = 0; x < area.getWidth(); x++){
            for(int y = 0; y < area.getHeight(); y++){

                //Get the surrounding area of the current coordinate. And generate a tile based on that.
                AreaHandler.TileType tile = generateTileType(area.getSurroundingArea3x3(x, y));

                //Set the generated tile.
                area.setTile(x, y, tile);
            }
        }
    }

    private AreaHandler.TileType generateTileType(AreaHandler.TileType[][] surroundingArea){

        /** Water should have a max size. A maksimum % of the map can be covered in water
         * Roads should try to be connected, and have a change to fork.
         * Structures should have a little space them and other tile types. */

        double waterTileChance;
        double roadTileChance;
        double structureChance;
        double grassChance;


        //WATER SECTION
        //Does the map already have max water?
        if(area.canHaveMoreWaterTiles){

            //Is there water nearby?
            ArrayList<int[]> nearbyWater = getNumberOfTileTypes(surroundingArea, AreaHandler.TileType.WATER);

            System.out.println("NearbyWater: " + nearbyWater.size());

            if(nearbyWater.size() > 0){

                //How big are those bodies of water?
                ArrayList<Integer> waterBodySizes = new ArrayList<Integer>();

                for(int[] coord : nearbyWater){
                    waterBodySizes.add(area.getSizeOfBodyFromType(coord[0], coord[1]));
                }

                //Is any of them too big?
                boolean isAnyOneWaterBodyTooBig = false;

                for(Integer num : waterBodySizes){
                    if(num >= area.maxTilesForOneBodyOfWater)
                        isAnyOneWaterBodyTooBig = true;
                }

                if(isAnyOneWaterBodyTooBig){
                    waterTileChance = 0;
                }else{

                    //How many water tiles are nearby?


                    if( nearbyWater.size() == 1)
                        waterTileChance = 0.2;
                    else if(nearbyWater.size() == 2)
                        waterTileChance = 0.5;
                    else if(nearbyWater.size() == 3)
                        waterTileChance = 0.7;
                    else
                        waterTileChance = 1;
                }


                //TODO //and how big is the water connected to them.
            }else
                waterTileChance = 1;
        }else
            waterTileChance = 0;


        //CALCULATE CHANGE OF TILE BEING A WATER TILE

        //Is there a road tile nearby?

            //If yes, should we expand it?

            //If no, should we roll for placing another?

        //CALCULATE CHANGE OF TILE BEING A ROAD TILE

        //Is there another structure?

            //Yes: Is there space between the tile and all other tile types beside grass?
                 //Yes: Can we expand the structure by one?

            //NO: Is there space between the tile and all other tile types beside grass?

                //If yes, should we spawn a structure?

        //CALCULATE CHANGE OF TILE BEING A STRCUTURE TILE

        //CALCULATE CHANGE OF TILE BEING A GRASS TILE

        //PICK THE TILE WITH THE HIGHEST CHANCE

        if(waterTileChance == 1)
            return AreaHandler.TileType.WATER;
        else if(waterTileChance >= 0.5){
            if(rand.nextInt(2) == 0)
                return AreaHandler.TileType.WATER;
            else
                return AreaHandler.TileType.GRASS;
        }
        else
            return AreaHandler.TileType.GRASS;


        /** BELOW IS NOT NEEDED ANY MORE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

        /*
        if(area.canHaveMoreWaterTiles)
            return AreaHandler.TileType.WATER;
        else
            return AreaHandler.TileType.ROAD;*/

        /** STRUCTURE*/

        //Is there water nearby?

        //If yes, how many water tiles are within the 3x3

        //and how big is the water connected to them.


        //CALCULATE CHANGE OF TILE BEING A WATER TILE

        //Is there a road tile nearby?

        //If yes, should we expand it?

        //If no, should we roll for placing another?

        //CALCULATE CHANGE OF TILE BEING A ROAD TILE

        //Is there another structure?

        //Yes: Is there space between the tile and all other tile types beside grass?
        //Yes: Can we expand the structure by one?

        //NO: Is there space between the tile and all other tile types beside grass?

        //If yes, should we spawn a structure?

        //CALCULATE CHANGE OF TILE BEING A STRCUTURE TILE

        //CALCULATE CHANGE OF TILE BEING A GRASS TILE

        //PICK THE TILE WITH THE HIGHEST CHANCE
    }

    /** Does it it more step by step. */
    private void generateArea02(){

        //Set all tiles to be grass
        for(int x = 0; x < area.getWidth(); x++){
            for(int y = 0; y < area.getHeight(); y++){
                area.setTile(x, y, AreaHandler.TileType.GRASS);
            }
        }

        //Pick some spots for seas
        int numberOfLakes = rand.nextInt(6) + 1; //1-6

        ArrayList<int[]> lakeStartPoints = getLakeStartPoints(numberOfLakes);

        //TODO TEMP
        for(int[] coord : lakeStartPoints)
            area.setTile(coord[0], coord[1], AreaHandler.TileType.WATER);

            //When spawning lakes... make sure there is a specific number of tiles between others


        //Create roads?

        //Create structures...
            //Get all possible areas.. rolls for spawn
    }

    private void createLakes(ArrayList<int[]> startPoints){

    }

    /** @return the given number of random coordinates on the map. */
    private ArrayList<int[]> getLakeStartPoints(int numberOfStartPoints){

        ArrayList<int[]> startPoints = new ArrayList<>();

        for(int i = 0; i < numberOfStartPoints; i++){
            startPoints.add(new int[]{rand.nextInt(area.getWidth()), rand.nextInt(area.getHeight())});
        }

        return startPoints;
    }

    /** Takes a 3x3 tileTypes and a tileType, searches for the given tileType and returns the coordinates of them. (-1;1 , -1; 1) */
    public ArrayList<int[]> getNumberOfTileTypes(AreaHandler.TileType[][] area3x3, AreaHandler.TileType type){

        ArrayList<int[]> foundTiles = new ArrayList<>();

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){

                if(area3x3[x][y] != null){
                    if(area3x3[x][y] == type){
                        foundTiles.add(new int[]{x, y});
                    }
                }
            }
        }

        return foundTiles;
    }

    public AreaHandler.TileType[][] getArea() {
        return area.getArea();
    }

    private AreaHandler.TileType getRandomTile(){
        switch (rand.nextInt(4)){
            case 0 : return AreaHandler.TileType.GRASS;
            case 1 : return AreaHandler.TileType.STRUCTURES;
            case 2 : return AreaHandler.TileType.WATER;
            case 3 : return AreaHandler.TileType.ROAD;
        }

        throw new IllegalArgumentException();
    }
}
