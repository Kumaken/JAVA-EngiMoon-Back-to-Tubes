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
    import java.util.*;

public class Boot {
    public static ArrayList<Object> objectList = new ArrayList<Object>();
    public static TileGrid grid;

    public Boot() {
        //call helpers: Artist to load screen:
        beginSession();

        //loadmap:
        grid = new TileGrid();

        Scanner scanner = new Scanner(System.in);
        String command = "";
        //GUI loop
            while(!Display.isCloseRequested()){ //while not prompted to close
                if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                    System.out.println("KEY PRESSED! W");
                }
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
                Display.update();
                Display.sync(60);

                /*
                //Ask for command
                System.out.println("COMMAND:");
                command = (scanner.nextLine().toLowerCase());
                System.out.println(command);
                //clear arraylist objects:
                objectList.clear();
                 */
            }
        //End the game:
            Display.destroy();
    }

    public static void main(String[] args){
        Common com = new Common();
        int maxX = Common.gamemap.size();
        int maxY = Common.gamemap.get(0).size();
        //Spawn 5 Pigs
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            int x = 0;
            int y = 0;
            //srand(time(0));
            //Loop until correct position is found
            do{
                x = random.nextInt(maxX);
                y = random.nextInt(maxY);
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Pig(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //Spawn 3 Chickens
        Random rand = new Random();
        for(int i = 0; i < 3; i++){
            int x = 0;
            int y = 0;
            //srand(time(0));
            //Loop until correct position is found
            do{
                x = rand.nextInt(maxX);
                y = rand.nextInt(maxY);
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
            FarmAnimal a = new Chicken(x, y, false);
            //add animal into list of animal
            Common.animalList.add(a);
            //update gamemap
            Common.gamemap.get(x).get(y).animalOccupy(a);
        }

        //boot the GUI:
        new Boot();
    }
}
