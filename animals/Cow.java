package animals;

import java.util.*;

class Cow extends FarmAnimal implements MilkProducing, MeatProducing{
    public Cow(int x, int y, boolean l){
        super(x,y,l);
        if (lapar) simbol = 's';
        else simbol = 'S';
        threshold = 10;
    }
    
    public void move(){
        int row, col;
        List<Integer> arr = Arrays.asList(1,2,3,4);
        Collections.shuffle(arr, new Random());
        for (int x=0; x<4; x++){
            if (arr.get(x)==1){
                row = getX();
                setX(row+1);
                if (getX()>=gamemap.size() || gamemap[getX()][getY()]->getOverrideSymbol()!='\0' || (gamemap[getX()][getY()]->showSymbol()!='.' && gamemap[getX()][getY()]->showSymbol()!='#')){
                    setX(row);
                }
                else break;
            }
            else if (arr.get(x)==2){
                col = getY();
                setY(col+1);
                if (getY()>=gamemap[0].size() || gamemap[getX()][getY()]->getOverrideSymbol()!='\0' || (gamemap[getX()][getY()]->showSymbol()!='.' && gamemap[getX()][getY()]->showSymbol()!='#')){
                    setY(col);
                }
                else break;
            }
            else if (arr.get(x)==3){
                row = getX();
                setX(row-1);
                if (getX()<0 || gamemap[getX()][getY()]->getOverrideSymbol()!='\0' || (gamemap[getX()][getY()]->showSymbol()!='.' && gamemap[getX()][getY()]->showSymbol()!='#')){
                    setX(row);
                }
                else break;
            }
            else if (arr.get(x)==4){
                col = getY();
                setY(col-1);
                if (getY()<0 || gamemap[getX()][getY()]->getOverrideSymbol()!='\0' || (gamemap[getX()][getY()]->showSymbol()!='.' && gamemap[getX()][getY()]->showSymbol()!='#')){
                    setY(col);
                }
                else break;
            }
        }
    } //implementasi fungsi pure virtual dari parent
    
    public void eat(){
        if (threshold<=0 && !lapar) revLapar();
        if (lapar && gamemap[getX()][getY()]->showSymbol()=='#'){
            gamemap[getX()][getY()]->ungrowGrass();
            revLapar();
            threshold = 10;
        }
    }
    
    public String sound(){
        return "moo";
    }
    
    @Override
    public FarmProduct produceMeat(){
        return CowMeat();
    }
    
    @Override
    public FarmProduct produceMilk(){
        return CowMilk();
    }
}