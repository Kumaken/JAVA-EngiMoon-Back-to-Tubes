package cells.facilities;
import cells.Cell;

//Truck Class: A facility for shipping various products from player's farm
public class Truck extends Cell implements Facility{
    //Constructor implementation:
    public Truck(){
        this.Symbol = 'T';
        this.isInRest = false;
        this.restCountdown = 0;
    }

    //Make Truck Invalid:
    public void invalidateFacility(){
        this.Symbol = 't';
        this.isInRest = true;
        this.restCountdown = 8;
    }

    //Status update for truck:
    public void facilityUpdate(){
        if(this.isInRest){
            this.restCountdown--;
            if(this.restCountdown == 0){
                this.isInRest = false;
                this.Symbol = 'T';
            }
        }
    }
}