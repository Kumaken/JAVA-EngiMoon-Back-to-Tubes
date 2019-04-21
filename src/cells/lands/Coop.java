package cells.lands;
import cells.Cell;
/*Kelas Coop adalah turunan dari kelas Land dan merepresentasikan lahan pijakan yang di dalam kandang EggProducing Animals.*/

/**
 * Class for coop
 */
public class Coop extends Cell {
    // Constructor Implementation:
    public Coop(){
        this.Symbol = 'o';
    }

    //Method to grow grass chaange symbol into '#':
    public void growGrass(){
        this.Symbol = '*';
    }

    //Method to ungrow grass chaange symbol into 'o':
    public void ungrowGrass(){
        this.Symbol = 'o';
    }
}

