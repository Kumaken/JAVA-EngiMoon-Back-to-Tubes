package data;
//dependencies:
    import common.Common;
    import org.lwjgl.LWJGLException;
    import org.lwjgl.opengl.Display;
    import org.lwjgl.opengl.DisplayMode;
    //for opengl:
    import static org.lwjgl.opengl.GL11.*;
    //import helpers:
    import static helpers.Artist.*;
    //import texture:
    import org.newdawn.slick.Color;
    import org.newdawn.slick.TrueTypeFont;
    import org.newdawn.slick.opengl.Texture;
    //keyboard control:
    import org.lwjgl.input.Keyboard;

//substitute main:
    import product.*;
    import cells.*;
    import animals.*;
    import common.*;

    import java.awt.*;
    import java.io.ByteArrayOutputStream;
    import java.io.IOException;
    import java.io.PrintStream;
    import java.util.*;

public class Boot {
    public static ArrayList<Object> objectList = new ArrayList<Object>();
    public static TileGrid grid;
    public static Player mainPlayer = new Player(4,3);
    public static Common com;
    public static int tick;
    //STDOUT CHECK:
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
//    private final static PrintStream originalErr = System.err;

    public Boot() {
        tick = 0;
        //call helpers: Artist to load screen:
        beginSession();

        //loadmap:
        grid = new TileGrid();
        //drawmap:
        grid.draw();
        //handle objects:
        grid.handleOccupiedTiles();
        //draw objects:
        for(int i =0; i<objectList.size(); ++i){
            objectList.get(i).draw();
        }

        TrueTypeFont font = null;
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);

        String[] inventory_content;

        //GUI loop
        while(!Display.isCloseRequested()){ //while not prompted to close
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            inventory_content = mainPlayer.printBackpack();
            font.drawString(100, 700, "INVENTORY:");
            font.drawString(110, 730, "1. " + inventory_content[0]);
            font.drawString(110, 760, "2. " + inventory_content[1]);
            font.drawString(110, 790, "3. " + inventory_content[2]);
            font.drawString(110, 820, "4. " + inventory_content[3]);
            font.drawString(110, 850, "5. " + inventory_content[4]);
            font.drawString(110, 880, "6. " + inventory_content[5]);
            font.drawString(500, 700, "OUTPUT:");
            font.drawString(500, 730, outContent.toString());
            font.drawString(1000, 700, "TICK     =  " + tick);
            font.drawString(1000, 750, "MONEY    =  " + mainPlayer.getScore());
            font.drawString(1000, 800, "WATER    =  " + mainPlayer.getPouch());
            if (Keyboard.next() && Keyboard.getEventKeyState()) {
                outContent.reset();
                setUpStreams();
                //MIX KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "BaconOmelette");
                    updateMap();
                }
                //INTERACT KEY:
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_UP) ){
                    mainPlayer.interact('w');
                    updateMap();
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ){
                    mainPlayer.interact('d');
                    updateMap();
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_DOWN) ){
                    mainPlayer.interact('s');
                    updateMap();
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_LEFT) ) {
                    mainPlayer.interact('a');
                    updateMap();
                }
                //TALK KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.talk('w');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.talk('d');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.talk('s');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.talk('a');
                    updateMap();
                }

                //MURDER KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.kill('w');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.kill('d');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.kill('s');
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.kill('a');
                    updateMap();
                }
                //GROW KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                    mainPlayer.grow();
                    updateMap();
                }
                //EXIT KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    break;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('w');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('a');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('s');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('d');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                    updateMap();
                }
                if(Common.animalList.size() == 0){
                    break;
                }

                restoreStreams();

            }

            //drawmap:
            grid.draw();
            //handle objects:
            grid.handleOccupiedTiles();
            //draw objects:
            for(int i =0; i<objectList.size(); ++i){
                objectList.get(i).draw();
            }

            Display.update();
            Display.sync(60);
            }

        Display.destroy();
    }

    public static void updateMap(){
        tick++;

        for(int i = 0; i < Common.animalList.size(); i++){
            Common.animalList.get(i).minThreshold();
        }

        //Move all animal every 2 ticks
        if(tick != 0 && tick % 2 == 0){
            com.moveAllAnimals();
        }

        //Erase dead animal and eat if hungry
        for(int i = 0; i < Common.animalList.size(); i++){
            Common.gamemap.get(Common.animalList.get(i).getX()).get(Common.animalList.get(i).getY()).makeUnoccupied();
            Common.animalList.get(i).eat();
            Common.gamemap.get(Common.animalList.get(i).getX()).get(Common.animalList.get(i).getY()).animalOccupy(Common.animalList.get(i));
            if(Common.animalList.get(i).getThreshold() <= -5){
                Common.gamemap.get(Common.animalList.get(i).getX()).get(Common.animalList.get(i).getY()).makeUnoccupied();
                //animalList.erase(animalList.begin() + i);
                Common.animalList.remove(i);
            }
        }

        //Update all facilities:
        com.updateAllFacilities();

        objectList.clear();
        //loadmap:
        grid = new TileGrid();
    }

    //Functions to catch stdout:
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        //System.setErr(new PrintStream(errContent));
    }

    public static void restoreStreams() {
        System.setOut(originalOut);
        //System.setErr(originalErr);
    }

    public static void printLegend(){
        System.out.println("Keterangan:                  Controls:");
        System.out.println("B : Pig                      w : go up");
        System.out.println("C : Chicken                  a : go left");
        System.out.println("D : Duck                     s : go down");
        System.out.println("G : Goat                     d : go right");
        System.out.println("H : Horse");
        System.out.println("S : Cow                      talk (dir)        : talk to animal");
        System.out.println("M : Mixer                    interact (dir)    : interact with things");
        System.out.println("T : Truck                    kill (dir)        : kill animal");
        System.out.println("W : Well                     grow (dir)        : grow grass");
        System.out.println("P : Player                   mix (dir, recipe) : mix ingredients");
        System.out.println("x : Barn                     exit              : exit the game");
        System.out.println("o : Coop");
        System.out.println(". : Grassland");
        System.out.println("*, @, # : Grass");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args){
        com = new Common();
        Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();

        //Bagian Spawn Animal secara random
        //Spawn 3 Chickens
        Random rand = new Random();
        for(int i = 0; i < 3; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Chicken(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 4 Ducks
        for(int i = 0; i < 4; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Duck(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 8 Cows
        for(int i = 0; i < 8; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != '.' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Cow(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 7 Goats
        for(int i = 0; i < 7; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != '.' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Goat(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 5 Pigs
        for(int i = 0; i < 5; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'x' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Pig(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 4 Horses
        for(int i = 0; i < 4; i++){
            int x = 0;
            int y = 0;
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'x' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Horse(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }


        /*
        System.out.println("                 GAME OVER");
        System.out.println("---------------------------------------------");
        System.out.println("Final score: " + mainPlayer.getScore());
        */

        //boot the GUI:
        new Boot();
    }
}
