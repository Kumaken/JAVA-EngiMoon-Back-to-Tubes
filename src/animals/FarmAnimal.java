package animals;

import product.FarmProduct;

/**
 * Abstract class as parent class of all animals
 */
public abstract class FarmAnimal {
    /**
     * Symbol of animal
     */
    protected char simbol; //menyatakan representasi objek dari kelas ini di map
    /**
     * Attribute for hungriness of animal
     */
    protected int threshold; //menyatakan waktu 'lapar'nya objek dari class ini
    /**
     * Attribute to represent whether animal is hungry or not
     */
    protected boolean lapar; //menyatakan status lapar
    /**
     * x and y position of animal
     */
    protected int x; //posisi
    protected int y; //posisi

    public FarmAnimal(int _x, int _y, boolean _l){
        x = _x;
        y = _y;
        lapar = _l;
        simbol = '\0';
        threshold = -999;
    }

    /**
     * Move method will move the animal into valid tile if possible by checking tiles' current occupant
     */
    abstract public void move(); //bergerak secara acak sebesar 1 satuan ke kiri, kanan, atas, maupun bawah
    /**
     * Method to return the string of animal
     * @return sound of animal
     */
    abstract public String sound(); //method pure virtual, mengeluarkan suara
    /**
     * Method for animal to eat grass if hungry. Will change hunger status
     */
    abstract public void eat(); //makan bila lapar
    abstract public FarmProduct produceMeat();
    abstract public FarmProduct produceEgg();
    abstract public FarmProduct produceMilk();

    //Getter setter
    public char showSimbol(){
        return simbol;
    } //mengembalikan nilai simbol
    public void revSimbol(){
        if (Character.isLowerCase(simbol)) simbol = Character.toUpperCase(simbol);
        else simbol = Character.toLowerCase(simbol);
    } //mengubah nilai simbol dari objek yang akan ditunjukkan di map
    public int getThreshold(){
        return threshold;
    } //mengembalikan nilai threshold dari objek
    public void minThreshold(){
        threshold--;
    } //mengurangi nilai threshold dengan 1
    public boolean getLapar(){
        return lapar;
    } //mengecek apakah objek dari kelas ini lapar atau tidak
    public void revLapar(){
        lapar = !lapar;
        revSimbol();
    } //mengubah nilai lapar dari objek
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int a){
        x = a;
    }
    public void setY(int a){
        y = a;
    }
}