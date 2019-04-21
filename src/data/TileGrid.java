package data;

import common.Common;

import static helpers.Artist.quickLoad;

/**
 * Class to load matrix of game map
 */
public class TileGrid {
    public static final int TILEWIDTH = 44, TILEHEIGHT = 48, OBJECTWIDTH = 56, OBJECTHEIGHT = 56,
            XSIZE = Common.gamemap.size(), YSIZE = Common.gamemap.get(0).size() ;

    public Tile[][] map;

    public TileGrid(){
        //pay attention: map here is constructed with reversed i & j from Common.gamemap!
        map = new Tile[XSIZE][YSIZE];
        //System.out.println(Common.gamemap.size() + " >>> " + Common.gamemap.get(0).size());

        /**
         * Loop to add land and grass
         */
        for (int i =0; i < map.length; i++){
            for (int j=0; j< map[i].length; j++){
                if ( (Common.gamemap.get(i)).get(j).showSymbol() == '.')
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.GrassTile);
                else if ((Common.gamemap.get(i)).get(j).showSymbol() == '#') {
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.GrassTile);
                    Boot.objectList.add(new Object(quickLoad("grass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else if ((Common.gamemap.get(i)).get(j).showSymbol() == 'x')
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.BarnTile);
                else if ((Common.gamemap.get(i)).get(j).showSymbol() == '@'){
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.BarnTile);
                    Boot.objectList.add(new Object(quickLoad("barngrass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else if ((Common.gamemap.get(i)).get(j).showSymbol() == 'o')
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.CoopTile);
                else if ((Common.gamemap.get(i)).get(j).showSymbol() == '*') {
                    map[i][j] = new Tile(j * TILEWIDTH, i * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.CoopTile);
                    Boot.objectList.add(new Object(quickLoad("coopgrass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else
                    map[i][j] = new Tile(j*TILEWIDTH, i*TILEHEIGHT, TILEWIDTH,TILEHEIGHT, TileType.GrassTile);
            }
        }
    }

    /**
     * Method to add occupied tiles
     */
    public void handleOccupiedTiles() {
        //handle player:
        Boot.objectList.add(new Object(quickLoad("player"), Boot.grid.getTile(Boot.mainPlayer.getRow(), Boot.mainPlayer.getCol()), OBJECTWIDTH, OBJECTHEIGHT));

        /**
         * Loop to add animals
         */
        for (int i = 0; i < Common.animalList.size(); i++) {
            //System.out.println("x: "+ Common.animalList.get(i).getX() +" | y: "+ Common.animalList.get(i).getY());
            if (Common.animalList.get(i).showSimbol() == 'C')
                Boot.objectList.add(new Object(quickLoad("chicken"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'c')
                Boot.objectList.add(new Object(quickLoad("chicken off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'B')
                Boot.objectList.add(new Object(quickLoad("pig"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'b')
                Boot.objectList.add(new Object(quickLoad("pig off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'S')
                Boot.objectList.add(new Object(quickLoad("cow"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 's')
                Boot.objectList.add(new Object(quickLoad("cow off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'H')
                Boot.objectList.add(new Object(quickLoad("horse"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'h')
                Boot.objectList.add(new Object(quickLoad("horse off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'G')
                Boot.objectList.add(new Object(quickLoad("goat"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'g')
                Boot.objectList.add(new Object(quickLoad("goat off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'D')
                Boot.objectList.add(new Object(quickLoad("duck"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'd')
                Boot.objectList.add(new Object(quickLoad("duck off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
        }

        /**
         * Loop to add facilities
         */
        for (int i = 0; i < Common.facilityList.size(); i++) {
            //System.out.println("x: " + Common.facilityList.get(i).getX() + " | y: " + Common.facilityList.get(i).getY());
            if (Common.facilityList.get(i).showSymbol() == 'W')
                Boot.objectList.add(new Object(quickLoad("well"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'w')
                Boot.objectList.add(new Object(quickLoad("well off"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'T')
                Boot.objectList.add(new Object(quickLoad("truck"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 't')
                Boot.objectList.add(new Object(quickLoad("truck off"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'M')
                Boot.objectList.add(new Object(quickLoad("mixer"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'm')
                Boot.objectList.add(new Object(quickLoad("mixer off"), Boot.grid.getTile(Common.facilityList.get(i).getX(), Common.facilityList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
        }
    }

    /**
     * Setter for the map tile
     * @param x
     * @param y
     * @param type
     */
    public void setTile(int x, int y, TileType type){
        map[x][y] = new Tile(x*TILEWIDTH, y*TILEHEIGHT, TILEWIDTH, TILEHEIGHT, type);
    }

    /**
     * Getter for the map tile
     * @param x
     * @param y
     * @return the tile in x and y position
     */
    public Tile getTile(int x, int y){
        return map[x][y];
    }

    /**
     * Method to draw the tile
     */
    public void draw(){
        for (int i =0; i < map.length; i++){
            for (int j=0; j< map[i].length; j++){
                Tile temp = map[i][j];
                temp.draw();
            }
        }

    }
}
