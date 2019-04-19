package animals;

import java.util.*;

import product.FarmProduct;
import product.farmproduct.ChickenEgg;
import product.farmproduct.ChickenMeat;
import common.Common;

public class Chicken extends FarmAnimal implements EggProducing, MeatProducing{
    public Chicken(int x, int y, boolean l){
        super(x,y,l);
        if (lapar) simbol = 'c';
        else simbol = 'C';
        threshold = 8;
    }
    
    public void move(){
        int row, col;
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
        Collections.shuffle(arr, new Random());
        for (int x=0; x<4; x++){
            if (arr.get(x)==1){
                row = getX();
                setX(row+1);
                if (getX()>=Common.gamemap.size() || Common.gamemap.get(getX()).get(getY()).getOverrideSymbol()!='\0' || (Common.gamemap.get(getX()).get(getY()).showSymbol()!='o' && Common.gamemap.get(getX()).get(getY()).showSymbol()!='*')){
                    setX(row);
                }
                else break;
            }
            else if (arr.get(x)==2){
                col = getY();
                setY(col+1);
                if (getY()>=Common.gamemap.get(0).size() || Common.gamemap.get(getX()).get(getY()).getOverrideSymbol()!='\0' || (Common.gamemap.get(getX()).get(getY()).showSymbol()!='o' && Common.gamemap.get(getX()).get(getY()).showSymbol()!='*')){
                    setY(col);
                }
                else break;
            }
            else if (arr.get(x)==3){
                row = getX();
                setX(row-1);
                if (getX()<0 || Common.gamemap.get(getX()).get(getY()).getOverrideSymbol()!='\0' || (Common.gamemap.get(getX()).get(getY()).showSymbol()!='o' && Common.gamemap.get(getX()).get(getY()).showSymbol()!='*')){
                    setX(row);
                }
                else break;
            }
            else if (arr.get(x)==4){
                col = getY();
                setY(col-1);
                if (getY()<0 || Common.gamemap.get(getX()).get(getY()).getOverrideSymbol()!='\0' || (Common.gamemap.get(getX()).get(getY()).showSymbol()!='o' && Common.gamemap.get(getX()).get(getY()).showSymbol()!='*')){
                    setY(col);
                }
                else break;
            }
        }
    } //implementasi fungsi pure virtual dari parent
    
    public void eat(){
        if (threshold<=0 && !lapar) revLapar();
        if (lapar && Common.gamemap.get(getX()).get(getY()).showSymbol()=='*'){
            Common.gamemap.get(getX()).get(getY()).ungrowGrass();
            revLapar();
            threshold = 8;
        }
    }
    
    public String sound(){
        return "petok";
    } //suara 'petok'
    
    @Override
    public FarmProduct produceEgg(){
        return new ChickenEgg();
    }
    
    @Override
    public FarmProduct produceMeat(){
        return new ChickenMeat();
    }

    @Override
    public FarmProduct produceMilk(){
        return new FarmProduct();
    }
}