import product.*;
import cells.*;
import animals.*;
import common.*;
import java.util.*;

public class Main{
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
            System.out.print("|");
            col=row.next().iterator();
            while (col.hasNext()){
                Cell tempCol = col.next();
                if (tempCol.getOverrideSymbol() == '\0'){
                    System.out.print(tempCol.showSymbol()+" | ");
                }else{
                    System.out.print(tempCol.getOverrideSymbol()+" | ");
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

    public static void printLegend(){
        System.out.println("Keterangan:                  Controls:");
    }

    public static void main(String[] args){
        int tick = 0;
        Common com = new Common();
//        for(ArrayList<Cell> innerList : com.getGamemap()) {
//            for (Cell b : innerList) {
//                System.out.print(b.showSymbol());
//            }
//            System.out.println();
//        }
        //test
        for(int i = 0; i < Common.getGamemap().size(); i++){
            for(int j = 0; j < Common.getGamemap().get(0).size(); j++){
                System.out.print(Common.getGamemap().get(i).get(j).showSymbol());
            }
            System.out.println();
        }
//        printMap(0,0,0);
    }
}