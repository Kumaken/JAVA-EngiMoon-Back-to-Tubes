package engimoonclass.animals;

public abstract class FarmAnimal {
    protected char simbol; //menyatakan representasi objek dari kelas ini di map
    protected int threshold; //menyatakan waktu 'lapar'nya objek dari class ini
    protected boolean lapar; //menyatakan status lapar
    protected int x; //posisi
    protected int y; //posisi

    public FarmAnimal(int _x, int _y, boolean _l){
        x = _x;
        y = _y;
        lapar = _l;
        simbol = '\0';
        threshold = -999;
    }
    abstract public void move(); //bergerak secara acak sebesar 1 satuan ke kiri, kanan, atas, maupun bawah
    abstract public String sound(); //method pure virtual, mengeluarkan suara
    abstract public void eat(); //makan bila lapar

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