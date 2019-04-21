package cells.lands;
import cells.Cell;
/*Kelas Coop adalah turunan dari kelas Land dan merepresentasikan lahan pijakan yang di dalam kandang EggProducing Animals.*/

/**
 * Class for grassland
 */
public class Grassland extends Cell {
    // Constructor Implementation:
    public Grassland(){
        this.Symbol = '.';
    }
    
    //Method to grow grass chaange symbol into '#'':
    public void growGrass(){
        this.Symbol = '#';
    }
    
    //Method to ungrow grass chaange symbol into '.':
    public void ungrowGrass(){
        this.Symbol = '.';
    }
}

