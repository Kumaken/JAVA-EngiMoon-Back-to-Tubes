package engimoonclass.cells; //package must be lowercase
import engimoonclass.cells.facilities.*;
import engimoonclass.animals.FarmAnimal;
// Base Class : Cell
/* Kelas Cell adalah base class yang merepresentasikan setiap objek-objek yang akan muncul di map, seperti semua objek Facility, objek Land, objek Farm Animal, dan Player. Kelas ini memiliki atribut symbol bertipe char yang unik bagi setiap objek di map dan method getter untuk simbol tersebut.*/

// To solve circular dependencies, include .h files at .cpp files
public abstract class Cell{
//Attributes:
    // Innate attributes of class:
    // Unique symbol for each objects
    protected char Symbol;
    protected char OverrideSymbol;
    protected FarmAnimal Animalref;
    protected Facility Facilityref;

//Abstract Methods:
    // Make grass grow:
    public abstract void growGrass();

    // Ungrow the grass:
    public abstract void ungrowGrass();
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
        char showSymbol() {
            return this.Symbol;
        }

        // Getter for Override Symbol:
        char getOverrideSymbol() {
            return this.OverrideSymbol;
        }

        // Getter for AnimalPtr:
        FarmAnimal getAnimalRef() {
            return this.Animalref;
        }

        // Getter for FacilityPtr:
        Facility getFacilityRef() {
            return this.Facilityref;
        }   

    //Setters:
        // Animal occupies cell
        void animalOccupy(FarmAnimal _animalref){
            this.Animalref = _animalref;
            this.OverrideSymbol = Animalref.showSimbol();
        }

        // Player occupies cell
        void playerOccupy(){
            this.OverrideSymbol = 'P';
        }
        // Make cell unoccupied
        void makeUnoccupied(){
            this.OverrideSymbol = '\0';
        }

        // Set Facility ptr
        void setFacilityRef(Facility _facilityref){
            this.Facilityref = _facilityref;
        }
    }

