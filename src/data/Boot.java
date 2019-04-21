package data;
//dependencies:
    import common.Common;
    import org.lwjgl.opengl.Display;

    //for opengl:
    import static org.lwjgl.opengl.GL11.*;

    //import helpers:
    import static helpers.Artist.*;

    //import texture:
    import org.newdawn.slick.TrueTypeFont;

    //keyboard control:
    import org.lwjgl.input.Keyboard;

//substitute main:
    import animals.*;

    import java.awt.*;
    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;
    import java.util.*;

/**
 * The main part of program
 */
public class Boot {
    public static ArrayList<Object> objectList = new ArrayList<Object>();
    public static TileGrid grid;
    public static Player mainPlayer = new Player(4,3);
    public static Common com;
    public static int tick;
    public static String playerPic = "playerM";
    public static String playerName = "MoonBoy";
    //STDOUT CHECK:
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
//    private final static PrintStream originalErr = System.err;

    public Boot() {
        tick = 0;
        //call helpers: Artist to load screen:
        beginSession();

        /**
         * Load and draw the map
         */
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

        /**
         * Initialize string writer for GUI
         */
        TrueTypeFont font = null;
        Font awtFont = new Font("Times New Roman", Font.BOLD, 20);
        Font awtFont2 = new Font("Times New Roman", Font.BOLD, 14);
        Font awtFont3 = new Font("Calibri", Font.BOLD, 34);
        font = new TrueTypeFont(awtFont, false);
        TrueTypeFont font2 = new TrueTypeFont(awtFont2, false);
        TrueTypeFont font3 = new TrueTypeFont(awtFont3, false);

        String[] inventory_content;
        int pass = 0;

        /**
         * GUI Loop
         */
        while(!Display.isCloseRequested()){ //while not prompted to close
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            font3.drawString(47, 690, "CONTROLS", Color.yellow);
            font2.drawString(50, 730, "Move     = w/a/s/d", Color.orange);
            font2.drawString(50, 755, "Grow     = g", Color.orange );
            font2.drawString(50, 780, "Talk       = t + <arrow_key>",Color.orange);
            font2.drawString(50, 805, "Kill         = k + <arrow_key>",Color.orange);
            font2.drawString(50, 830, "Interact = i + <arrow_key>",Color.orange);
            font2.drawString(50, 855, "Mix        = m + <arrow_key> + <recipe_code>",Color.orange);
            font2.drawString(50, 880, "Exit        = ESC_KEY",Color.orange);
            font3.drawString(345, 690, "RECIPE CODE", Color.green);
            font2.drawString(350, 730, "Mixed Cheese    = 0", Color.cyan);
            font2.drawString(350, 755, "(Cow Milk + Goat Milk)", Color.cyan);
            font2.drawString(350, 785, "Horse Rolade     = 1", Color.cyan);
            font2.drawString(350, 810, "(Horse Meat + Duck Egg)", Color.cyan);
            font2.drawString(350, 840, "Bacon Omelette = 2", Color.cyan);
            font2.drawString(350, 865, "(Pig Meat + Chicken Egg)", Color.cyan);

            inventory_content = mainPlayer.printBackpack();
            int ystep = 25;
            font3.drawString(560, 690, "INVENTORY:");
            for(int i=0; i<6; ++i){
                font2.drawString(560, 730+(ystep*i), (i+1)+". " + inventory_content[i]);
                if(inventory_content[i] != "") {
                    if (inventory_content[i] == "MixedCheese")
                        drawQuadTexture(quickLoad("mixedcheese"), 700, 730+(ystep*i), 30, 25);
                    else if (inventory_content[i] == "BaconOmelette")
                        drawQuadTexture(quickLoad("baconomelette"), 700, 730+(ystep*i), 30, 25);
                    else if (inventory_content[i] == "HorseRolade")
                        drawQuadTexture(quickLoad("horserolade"), 700, 730+(ystep*i), 30, 25);
                }
            }

            /*
            if (inventory_content[0] != ""){
                drawQuadTexture(quickLoad("MixedCheese"), 600, 730, 50, 50);
            }*/

            font.drawString(900, 700, "OUTPUT:");

            if(outContent.toString().contains("Animal's killed")){
                String[] str_piece = outContent.toString().split("A", 2);
                font.drawString(900, 730, "It cries : " + str_piece[0]);
                font.drawString(900, 755, "A" + str_piece[1]);
                if(Common.animalList.size() == 0){
                    font.drawString(900, 780, "---------------");
                    font.drawString(900, 805, "Game Over");
                    font.drawString(900, 830, "Final Score: " + mainPlayer.getScore());
                }
            } else if(Common.animalList.size() == 0){
                font.drawString(900, 730, "Game Over");
                font.drawString(900, 755, "Final Score: " + mainPlayer.getScore());
            } else{
                font.drawString(900, 730, outContent.toString());
            }
            font.drawString(1150, 700, "TICK         =  " + tick);
            font.drawString(1150, 730, "MONEY    =  " + mainPlayer.getScore(), Color.yellow);
            font.drawString(1150, 760, "WATER    =  " + mainPlayer.getPouch(), Color.blue);
            font.drawString(1150, 900, "");
            drawQuadTexture(quickLoad(playerPic), 1150, 790, 150, 150);
            font.drawString(1150, 900, playerName);
            if((tick % 20) > 10){
                font.drawString(1150, 760, "", Color.blue); // set to malam
            }
            if (Keyboard.next() && Keyboard.getEventKeyState()) {
                outContent.reset();
                setUpStreams();
                //CHANGE PLAYER PIC:
                if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
                    if (playerPic == "playerM") {
                        playerPic = "playerF";
                        playerName = "MoonGurl";
                    }
                    else{
                        playerPic = "playerM";
                        playerName = "MoonBoy";
                    }
                }
				
                //MIX KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('w', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('w', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('w', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('d', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('d', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('d', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('s', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('s', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('s', "BaconOmelette");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('a', "MixedCheese");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('a', "HorseRolade");
                    updateMap();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('a', "BaconOmelette");
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

                //KILL KEY:
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

                //MOVE KEY:
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
            if(Common.animalList.size() == 0){
                pass++;
                if(pass > 5){
                    try{Thread.sleep(3000);}catch(InterruptedException e){break;}
                    break;
                }
            }
        }

        /**
         * Close the GUI Window after Game Over or ESC KEY pressed
         */
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

    /**
     * Method to catch STDOUT
     */
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        //System.setErr(new PrintStream(errContent));
    }

    /**
     * Method to clear the setOut
     */
    public static void restoreStreams() {
        System.setOut(originalOut);
        //System.setErr(originalErr);
    }

    /**
     * Entry point of program.
     * Initialize map reader, spawn animals, and create GUI window
     * @param args
     */
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
