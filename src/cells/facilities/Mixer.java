package cells.facilities;
import cells.Cell;
//Mixer Class: A facility for mixing and creating various side-products for profit
public class Mixer extends Cell implements Facility{
    //Constructor implementation:
    public Mixer(){
        this.Symbol = 'M';
        this.isInRest = false;
        this.restCountdown = 0;
    }

    //Make Mixer Invalid:
    public void invalidateFacility(){
        this.Symbol = 'm';
        this.isInRest = true;
        this.restCountdown = 3;
    }

    //Status update for truck:
    public void facilityUpdate(){
        if(this.isInRest){
            this.restCountdown--;
            if(this.restCountdown == 0){
                this.isInRest = false;
                this.Symbol = 'M';
            }
        }
    }
}