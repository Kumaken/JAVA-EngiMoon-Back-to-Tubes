package cells.lands;
import cells.Cell;
/* Kelas Barn adalah turunan dari kelas Land dan merepresentasikan lahan pijakan yang di dalam kandang dan MeatProducing Animals.*/
public class Barn extends Cell {
    // Constructor Implementation:
    public Barn(){
        this.Symbol = 'x';
    }
    //Method to grow grass chaange symbol into '#'':
    public void growGrass(){
        this.Symbol = '@';
    }

    //Method to ungrow grass chaange symbol into 'x'':
    public void ungrowGrass(){
        this.Symbol = 'x';
    }
}
