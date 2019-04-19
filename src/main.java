import cells.facilities.Truck;
import cells.lands.Barn;
import cells.lands.Coop;
import cells.lands.Grassland;
import product.*;
import cells.*;
import animals.*;
import common.*;
import java.util.*;

public class main{
    public static void printMap(int money, int aqua, int tick){
        Iterator< Vector<Cell> > row = common.gamemap.iterator();
        Iterator<Cell> col = row.iterator();

        int colMax = common.gamemap[0].size();
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
            System.out.print("|");
            row.next();
            while (col.hasNext()){
                Iterator<Cell> tempCol = col.next();
                if (col.getOverrideSymbol() == '\0'){
                    System.out.print(col.showSymbol()+" | ");
                }else{
                    System.out.print(col.getOverrideSymbol()+" | ");
                }
                System.out.println();
            }
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
}