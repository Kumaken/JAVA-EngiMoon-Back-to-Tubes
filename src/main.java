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
    public void classIdentifier(char c, List<Cell> v){
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
            Common.facilityList.add(temp);

        }
    }
}