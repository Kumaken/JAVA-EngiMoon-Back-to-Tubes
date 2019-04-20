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
    public static ArrayList<Cell> facilityList;

    public static ArrayList<ArrayList<Cell>> getGamemap() {
        return gamemap;
    }

    public static ArrayList<FarmAnimal> getAnimalList() {
        return animalList;
    }

    public static ArrayList<Cell> getFacilityList() {
        return facilityList;
    }

    public Common(){
        gamemap = new ArrayList<ArrayList<Cell>>();
        animalList = new ArrayList<FarmAnimal>();
        facilityList = new ArrayList<Cell>();
        loadMap();
    }

    public void classIdentifier(char c, ArrayList<Cell> v, int row, int col){
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
            temp.setPos(row, col);
            v.add(temp);
            facilityList.add(temp);
            v.get(v.size()-1).setFacilityRef(temp);
        } else if(c == 'W'){
            Well temp = new Well();
            temp.setPos(row, col);
            v.add(temp);
            facilityList.add(temp);
            v.get(v.size()-1).setFacilityRef((temp));;
        } else if(c == 'M'){
            Mixer temp = new Mixer();
            temp.setPos(row, col);
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
            int row=0;
            while(read.hasNextLine()){
                ArrayList<Cell> tempv = new ArrayList<Cell>();
                line = read.nextLine();
                for(int i = 0; i < line.length(); i++){
                    classIdentifier(line.charAt(i), tempv, row, i);
                }
                gamemap.add(tempv);
                row++;
            }
            read.close();
        } catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }

//    public void setInMap()

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
}