package cells; //package must be lowercase
import cells.facilities.*;
import animals.FarmAnimal;
// Base Class : Cell
/* Kelas Cell adalah class yang merepresentasikan
   setiap objek-objek yang akan muncul di map, seperti
   semua objek Facility, objek Land, objek Farm Animal,
   dan Player. Kelas ini memiliki atribut symbol
   bertipe char yang unik bagi setiap objek di map dan
   method getter untuk simbol tersebut.*/

/**
 * Parent class to represent all object in map (Facility objects,
 * Land objects, Animal objects, and Player)
 */
public class Cell{
//Attributes:
    // Innate attributes of class:
    // Unique symbol for each objects
    protected char Symbol;
    protected char OverrideSymbol;
    protected boolean isInRest;
    protected int restCountdown;
    protected FarmAnimal Animalref;
    protected Facility Facilityref;
    protected int x,y;

//Abstract Methods:
    // Make grass grow:
    public void growGrass(){};

    // Ungrow the grass:
    public void ungrowGrass(){};

    //updatefacil:
    public void facilityUpdate(){};

    //invalidate facility:
    public void invalidateFacility(){};

//Constructors:   
    // Constructor 
    public Cell(){
        // Initialize:
        this.Symbol = '\0';
        // Make this cell unoccupied:
        this.OverrideSymbol = '\0';
        this.Animalref = null;
        this.Facilityref = null;
    }
//Getter & Setters:
    //Getters:
        // Getter for Unique symbol of every types of landtypes/facillities in cell
        public char showSymbol() {
            return this.Symbol;
        }

        // Getter for Override Symbol:
        public char getOverrideSymbol() {
            return this.OverrideSymbol;
        }

        // Getter for AnimalPtr:
        public FarmAnimal getAnimalRef() {
            return this.Animalref;
        }

        // Getter for FacilityPtr:
        public Facility getFacilityRef() {
            return this.Facilityref;
        }

        //getX
        public int getX(){
            return this.x;
        }

        //getY
        public int getY(){
            return this.y;
        }

    //Setters:
        // Animal occupies cell
        public void animalOccupy(FarmAnimal _animalref){
            this.Animalref = _animalref;
            this.OverrideSymbol = Animalref.showSimbol();
        }

        // Player occupies cell
        public void playerOccupy(){
            this.OverrideSymbol = 'P';
        }
        // Make cell unoccupied
        public void makeUnoccupied(){
            this.OverrideSymbol = '\0';
        }

        // Set Facility ptr
        public void setFacilityRef(Facility _facilityref){
            this.Facilityref = _facilityref;
        }

        // Setpos
        public void setPos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

