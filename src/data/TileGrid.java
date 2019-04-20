package data;

import common.Common;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.drawQuadTexture;
import static helpers.Artist.quickLoad;

public class TileGrid {
    public static final int TILEWIDTH = 48, TILEHEIGHT = 48, OBJECTWIDTH = 56, OBJECTHEIGHT = 56;
    public Tile[][] map;

    public TileGrid(){
        //pay attention: map here is constructed with reversed i & j from Common.gamemap!
        map = new Tile[Common.gamemap.get(0).size()][Common.gamemap.size()];
        //System.out.println(Common.gamemap.size() + " >>> " + Common.gamemap.get(0).size());

        for (int i =0; i < map.length; i++){
            for (int j=0; j< map[i].length; j++){
                if ( (Common.gamemap.get(j)).get(i).showSymbol() == '.')
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.GrassTile);
                else if ((Common.gamemap.get(j)).get(i).showSymbol() == '#') {
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.GrassTile);
                    Boot.objectList.add(new Object(quickLoad("grass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else if ((Common.gamemap.get(j)).get(i).showSymbol() == 'x')
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.BarnTile);
                else if ((Common.gamemap.get(j)).get(i).showSymbol() == '@'){
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.BarnTile);
                    Boot.objectList.add(new Object(quickLoad("grass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else if ((Common.gamemap.get(j)).get(i).showSymbol() == 'o')
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.CoopTile);
                else if ((Common.gamemap.get(j)).get(i).showSymbol() == '*') {
                    map[i][j] = new Tile(i * TILEWIDTH, j * TILEHEIGHT, TILEWIDTH, TILEHEIGHT, TileType.CoopTile);
                    Boot.objectList.add(new Object(quickLoad("grass"), map[i][j], OBJECTWIDTH, OBJECTHEIGHT));
                }
                else
                    map[i][j] = new Tile(i*TILEWIDTH, j*TILEHEIGHT, TILEWIDTH,TILEHEIGHT, TileType.GrassTile);
            }
        }
    }

    public void handleOccupiedTiles() {
        for (int i = 0; i < Common.animalList.size(); i++) {
            //System.out.println("x: "+ Common.animalList.get(i).getX() +" | y: "+ Common.animalList.get(i).getY());
            if (Common.animalList.get(i).showSimbol() == 'C')
                Boot.objectList.add(new Object(quickLoad("chicken"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            if (Common.animalList.get(i).showSimbol() == 'c')
                Boot.objectList.add(new Object(quickLoad("chicken off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'B')
                Boot.objectList.add(new Object(quickLoad("pig"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            if (Common.animalList.get(i).showSimbol() == 'b')
                Boot.objectList.add(new Object(quickLoad("pig off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'S')
                Boot.objectList.add(new Object(quickLoad("cow"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            if (Common.animalList.get(i).showSimbol() == 's')
                Boot.objectList.add(new Object(quickLoad("cow off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'H')
                Boot.objectList.add(new Object(quickLoad("horse"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            if (Common.animalList.get(i).showSimbol() == 'h')
                Boot.objectList.add(new Object(quickLoad("horse off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'G')
                Boot.objectList.add(new Object(quickLoad("goat"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.animalList.get(i).showSimbol() == 'g')
                Boot.objectList.add(new Object(quickLoad("goat off"), Boot.grid.getTile(Common.animalList.get(i).getX(), Common.animalList.get(i).getY()), OBJECTWIDTH, OBJECTHEIGHT));
        }

        for (int i = 0; i < Common.facilityList.size(); i++) {
            //System.out.println("x: " + Common.facilityList.get(i).getX() + " | y: " + Common.facilityList.get(i).getY());
            if (Common.facilityList.get(i).showSymbol() == 'W')
                Boot.objectList.add(new Object(quickLoad("well"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'w')
                Boot.objectList.add(new Object(quickLoad("well off"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'T')
                Boot.objectList.add(new Object(quickLoad("truck"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 't')
                Boot.objectList.add(new Object(quickLoad("truck off"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'M')
                Boot.objectList.add(new Object(quickLoad("mixer"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
            else if (Common.facilityList.get(i).showSymbol() == 'm')
                Boot.objectList.add(new Object(quickLoad("mixer off"), Boot.grid.getTile(Common.facilityList.get(i).getY(), Common.facilityList.get(i).getX()), OBJECTWIDTH, OBJECTHEIGHT));
        }
    }

    public void setTile(int x, int y, TileType type){
        map[x][y] = new Tile(x*TILEWIDTH, y*TILEHEIGHT, TILEWIDTH, TILEHEIGHT, type);
    }

    public Tile getTile(int x, int y){
        return map[x][y];
    }

    public void draw(){
        for (int i =0; i < map.length; i++){
            for (int j=0; j< map[i].length; j++){
                Tile temp = map[i][j];
                temp.draw();
            }
        }

    }
}
