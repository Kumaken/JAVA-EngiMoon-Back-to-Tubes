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
    import org.newdawn.slick.opengl.Texture;
    //keyboard control:
    import org.lwjgl.input.Keyboard;

//substitute main:
    import product.*;
    import cells.*;
    import animals.*;
    import common.*;

    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;
    import java.util.*;

public class Boot {
    public static ArrayList<Object> objectList = new ArrayList<Object>();
    public static TileGrid grid;
    public static Player mainPlayer = new Player(4,3);

    public Boot(Common com) {
        int tick = 0;
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

        //GUI loop
        while(!Display.isCloseRequested()){ //while not prompted to close
            if (Keyboard.next() && Keyboard.getEventKeyState()) {
//                for(int i = 0; i < Common.animalList.size(); i++){
//                    System.out.println("animal " + (i+1) + " pos: " + Common.animalList.get(i).getX() + " " + Common.animalList.get(i).getY());
//                }
//
//                System.out.println("playerpos: " + mainPlayer.getRow() + " " + mainPlayer.getCol());

                //MIX KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "MixedCheese");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "HorseRolade");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.mix('a', "BaconOmelette");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "MixedCheese");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "HorseRolade");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.mix('s', "BaconOmelette");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "MixedCheese");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "HorseRolade");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.mix('d', "BaconOmelette");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_0) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "MixedCheese");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_1) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "HorseRolade");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_M) && Keyboard.isKeyDown(Keyboard.KEY_2) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.mix('w', "BaconOmelette");
                }
                //INTERACT KEY:
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_UP) ){
                    mainPlayer.interact('w');
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ){
                    mainPlayer.interact('d');
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_DOWN) ){
                    mainPlayer.interact('s');
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_I) && Keyboard.isKeyDown(Keyboard.KEY_LEFT) ) {
                    mainPlayer.interact('a');
                }
                //TALK KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.talk('w');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.talk('d');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.talk('s');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.talk('a');
                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
//                    mainPlayer.talk('w');
//                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
//                    mainPlayer.talk('d');
//                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
//                    mainPlayer.talk('s');
//                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
//                    mainPlayer.talk('a');
//                }
                //MURDER KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    mainPlayer.kill('w');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    mainPlayer.kill('d');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    mainPlayer.kill('s');
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_K) && Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    mainPlayer.kill('a');
                }
                //GROW KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                    mainPlayer.grow();
                }
                //EXIT KEY:
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    break;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('w');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('a');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('s');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                    mainPlayer.move('d');
                    Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
                }

                tick++;

                for(int i = 0; i < Common.animalList.size(); i++){
                    Common.animalList.get(i).minThreshold();
                }

                //Move all animal every 2 ticks
                if(tick != 0 && tick % 2 == 0){
                    com.moveAllAnimals();
                    tick = 0;
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
                //drawmap:
                grid.draw();
                //handle objects:
                grid.handleOccupiedTiles();
                //draw objects:
                for(int i =0; i<objectList.size(); ++i){
                    objectList.get(i).draw();
                }

                if(Common.animalList.size() == 0){
                    break;
                }

            }

            Display.update();
            Display.sync(60);
            }

        Display.destroy();
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
        Common com = new Common();
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


        ByteArrayOutputStream baos = new ByteArrayOutputStream();;
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;


        /*
        while(!command.equals("exit") && Common.animalList.size() > 0){
            // print 'local' content
            //clearscreen()

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

            //print the map
            printMap(mainPlayer.getScore(), mainPlayer.getPouch(), tick);
            //print the legend
            //printLegend();
            //if all animal is dead, break from loop and game over
            if(Common.animalList.size() == 0){
                break;
            }

            System.out.println();
            System.out.print("Inventory: ");
            mainPlayer.printBackpack();
            System.out.println();

            System.out.println("OUTPUT: ");
            // if (baos != null){
            System.out.println(baos.toString());
            // }
            baos = new ByteArrayOutputStream();

            System.out.print("Command: ");
            command = scanner.next();
            command = command.toLowerCase();
            // System.out.println("command: "+command);
            //lowercase the command input
            System.out.println();

            ps = new PrintStream(baos);
            System.setOut(ps);
            String c;
            if(command.equals("talk")){ //talk action
                c = scanner.next(); //direction to talk to
                mainPlayer.talk(c.charAt(0));
            } else if(command.equals("interact")){ //interact action
                c = scanner.next(); //direction to interact to
                mainPlayer.interact(c.charAt(0));
            } else if(command.equals("kill")){ //kill action
                c = scanner.next(); //direction to kill animal
                mainPlayer.kill(c.charAt(0));
            } else if(command.equals("grow")){ //grow action
                mainPlayer.grow();
            } else if(command.equals("mix")){ //mix action
                c = scanner.next();
                String menu;
                menu = scanner.next();
                mainPlayer.mix(c.charAt(0),menu);
            } else if(command.equals("w") || command.equals("a") || command.equals("s") || command.equals("d")){ //move action
                Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                mainPlayer.move(command.charAt(0));
                Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
            } else if(command.equals("cheats")){
                int x,y;
                y = scanner.nextInt();
                x = scanner.nextInt();
                Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).makeUnoccupied();
                System.out.println("cheats ");
                mainPlayer.setCol(x);
                mainPlayer.setRow(y);
                Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
            } else if(command.equals("exit")){
            } else{
                tick--;
                System.out.println("Invalid command");
            }



            tick++;
            //Decrement hunger threshold for all animal
            for(int i = 0; i < Common.animalList.size(); i++){
                Common.animalList.get(i).minThreshold();
            }

            // Put things back
            System.out.flush();
            System.setOut(old);
        }*/

        /*
        System.out.println("                 GAME OVER");
        System.out.println("---------------------------------------------");
        System.out.println("Final score: " + mainPlayer.getScore());
        */

        //boot the GUI:
        new Boot(com);
    }
}
