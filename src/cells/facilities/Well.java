package cells.facilities;
import cells.Cell;

//Well Class: A facility that supplies water for player's farm

/**
 * Class for well
 */
public class Well extends Cell implements Facility{   
    //Constructor implementation:
    public Well(){
        this.Symbol = 'W';
        this.isInRest = false;
        this.restCountdown = 0;
    }

    //Make Well Invalid:
    public void invalidateFacility(){
        this.Symbol = 'w';
        this.isInRest = true;
        this.restCountdown = 5;
    }

    //Status update for Well:
    public void facilityUpdate(){
        if(this.isInRest){
            this.restCountdown--;
            if(this.restCountdown == 0){
                this.isInRest = false;
                this.Symbol = 'W';
            }
        }
    }
}