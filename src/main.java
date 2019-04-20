import product.*;
import cells.*;
import animals.*;
import common.*;
import java.util.*;
import java.io.*;

public class main{
    public static void printMap(int money, int aqua, int tick){
        Iterator<ArrayList<Cell>> row = Common.getGamemap().iterator();
        Iterator<Cell> col;

        int colMax = Common.getGamemap().get(0).size();
        for (int i = 0; i<colMax*4-15;i++){
            if (i == (colMax*4+1)/2){
                System.out.print("PETA ENGI'S FARM");
            } else{
                System.out.print(" ");
            }
        }
        System.out.println();

        for(int i = 0; i < colMax * 4 + 1; i++){
            System.out.print("_");
        }
      
        System.out.println();

        while (row.hasNext()){
            System.out.print("| ");
            col=row.next().iterator();
            while (col.hasNext()){
                Cell tempCol = col.next();
                if (tempCol.getOverrideSymbol() == '\0'){
                    System.out.print(tempCol.showSymbol()+" | ");
                }else{
                    System.out.print(tempCol.getOverrideSymbol()+" | ");
                }
            }
            System.out.println();
        }

        for(int i = 0; i < colMax * 4 + 1; i++){
            if(i % 4 == 0){
                System.out.print("|");
            } else{
                System.out.print("-");
            }
        }
        System.out.println();

        int moneySpaces = Integer.toString(money).length();

        for(int i = 0; i < colMax * 4; i++){
            if(i == 0){
                System.out.print("| Money: "+money);
            } else if(i >= 9 + moneySpaces){
                System.out.print(" ");
            }
        }
        System.out.println("|");

        for(int i = 0; i < colMax * 4 + 1; i++){
            if(i == 0 || i == colMax * 4){
                System.out.print("|");
            } else{
                System.out.print("_");
            }
        }
        System.out.println();

        //jumlah digit yang ada di pouch
        int waterSpaces = Integer.toString(aqua).length();

        for(int i = 0; i < colMax * 4; i++){
            if(i == 0){
                System.out.print("| Water: " + aqua);
            } else if(i >= 9 + waterSpaces){
                System.out.print(" ");
            }
        }
        System.out.println("|");

        for(int i = 0; i < colMax * 4 + 1; i++){
            if(i == 0 || i == colMax * 4){
                System.out.print("|");
            } else{
                System.out.print("_");
            }
        }
        System.out.println();
        
        //jumlah digit yang ada di timer
        int timerSpaces = Integer.toString(tick).length();
        for(int i = 0; i < colMax * 4; i++){
            if(i == 0){
                System.out.print("| Timer: " + tick);
            } else if(i >= 9 + timerSpaces){
                System.out.print(" ");
            }
        }
        System.out.println("|");

        for(int i = 0; i < colMax * 4 + 1; i++){
            if(i == 0 || i == colMax * 4){
                System.out.print("|");
            } else{
                System.out.print("_");
            }
        }
        System.out.println();
    }

    public static void printLegend(){
        System.out.println("Keterangan:                  Controls:");
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tick = 0;
        //system(CLEAR);

        System.out.println(" _____            _ _      ______                   ");
        System.out.println("|  ___|          (_| )     |  ___|                  ");
        System.out.println("| |__ _ __   __ _ _|/ ___  | |_ __ _ _ __ _ __ ___  ");
        System.out.println("|  __| '_ \\ / _` | | / __| |  _/ _` | '__| '_ ` _ \\ ");
        System.out.println("| |__| | | | (_| | | \\__ \\ | || (_| | |  | | | | | |");
        System.out.println("\\____/_| |_|\\__, |_| |___/ \\_| \\__,_|_|  |_| |_| |_|");
        System.out.println("             __/ |                                  ");
        System.out.println("            |___/                                   ");
        System.out.println("When in game, type exit to quit the game");
        System.out.print("Press enter to start");
        scanner.nextLine();
        String command = "";
    
        Common com = new Common();
        Player mainPlayer = new Player(5,5);

        Common.gamemap.get(mainPlayer.getRow()).get(mainPlayer.getCol()).playerOccupy();
        
        //clearscreen()
        printMap(0,0,0);

        //Bagian Spawn Animal secara random
        //Spawn 3 Chickens
        Random rand = new Random();
        for(int i = 0; i < 3; i++){
            int x = 0;
            int y = 0;
            //srand(time(0));
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
            //srand(time(0));
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
            //srand(time(0));
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
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
            //srand(time(0));
            //Loop until correct position is found
            do{
                x = rand.nextInt(Common.gamemap.size());
                y = rand.nextInt(Common.getGamemap().get(0).size());
            } while(Common.gamemap.get(x).get(y).showSymbol() != 'o' || (Common.gamemap.get(x).get(y).getOverrideSymbol() != '\0'));
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
            //srand(time(0));
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
            //srand(time(0));
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
        

        while(command != "exit" && Common.animalList.size() > 0){
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
            printLegend();
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
                System.out.println("position of player : "+ mainPlayer.getRow() + " " + mainPlayer.getCol());
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
        }

        
        System.out.println("                 GAME OVER");
        System.out.println("---------------------------------------------");
        System.out.println("Final score: " + mainPlayer.getScore());
    }
}