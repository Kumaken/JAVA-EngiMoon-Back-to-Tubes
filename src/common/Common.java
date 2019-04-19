package common;

import animals.FarmAnimal;
import cells.Cell;
import cells.facilities.Facility;
import cells.facilities.Mixer;
import cells.facilities.Truck;
import cells.facilities.Well;
import cells.lands.Barn;
import cells.lands.Coop;
import cells.lands.Grassland;

import java.util.*;
import java.io.*;

public class Common{
    public static ArrayList<ArrayList<Cell>> gamemap;
    public static ArrayList<FarmAnimal> animalList;
    public static ArrayList<Facility> facilityList;

    public Common(){
        gamemap = new ArrayList<ArrayList<Cell>>();
        animalList = new ArrayList<FarmAnimal>();
        facilityList = new ArrayList<Facility>();
        loadMap();
    }

    public void classIdentifier(char c, ArrayList<Cell> v){
        if(c == '.'){
            v.add(new Grassland());
        } else if(c == '#'){
            Grassland temp = new Grassland();
            temp.growGrass();
            v.add(temp);
        } else if(c == '@'){
            Barn temp = new Barn();
            temp.growGrass();
            v.add(temp);
        } else if(c == '*'){
            Coop temp = new Coop();
            temp.growGrass();
            v.add(temp);
        } else if(c == 'o'){
            v.add(new Coop());
        } else if(c == 'x'){
            v.add(new Barn());
        } else if(c == 'T'){
            Truck temp = new Truck();
            v.add(temp);
            facilityList.add(temp);
            v.get(v.size()-1).setFacilityRef(temp);
        } else if(c == 'W'){
            Well temp = new Well();
            v.add(temp);
            facilityList.add(temp);
            v.get(v.size()-1).setFacilityRef((temp));;
        } else if(c == 'M'){
            Mixer temp = new Mixer();
            v.add(temp);
            facilityList.add(temp);
            v.get(v.size()-1).setFacilityRef(temp);
        }
    }

    public void loadMap(){
        try{
            String filename = "mapschema.txt";
            File f = new File(filename);
            Scanner read = new Scanner(f);
            String line = "";
            ArrayList<Cell> tempv = new ArrayList<Cell>();
            while(read.hasNextLine()){
                line = read.nextLine();
                for(int i = 0; i < line.length(); i++){
                    classIdentifier(line.charAt(i), tempv);
                }
                gamemap.add(tempv);
                tempv.clear();
            }
            read.close();
        } catch(Exception e){
            System.out.println("File read error");
            System.exit(0);
        }
    }

    public void moveAllAnimals(){
        for(int i = 0; i < animalList.size(); i++){
            gamemap.get(animalList.get(i).getX()).get(animalList.get(i).getY()).makeUnoccupied();
            animalList.get(i).move();
            gamemap.get(animalList.get(i).getX()).get(animalList.get(i).getY()).animalOccupy(animalList.get(i));
        }
    }

    public void updateAllFacilities(){
        for(int i = 0; i < facilityList.size(); i++){
            facilityList.get(i).facilityUpdate();
        }
    }

//    public static void main(String[] args){
//        Common a = new Common();
//        for(ArrayList<Cell> innerList : gamemap) {
//            for (Cell b : innerList) {
//                System.out.print(b.showSymbol());
//            }
//            System.out.println();
//        }
//    }
}