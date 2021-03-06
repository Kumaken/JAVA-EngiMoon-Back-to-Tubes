package data;

import java.util.*; //for map, ArrayList, linkedlist, list, etc;
import product.*;
import cells.*;
import product.sideproduct.BaconOmelette;
import product.sideproduct.HorseRolade;
import product.sideproduct.MixedCheese;
import common.Common;

/**
 * Class for player
 */
public class Player{
    private static final int MAX_WATER = 10;
    private static final int LENBACKPACK =6;

    private final ArrayList<Character> EGGPRODUCINGANIMAL = new ArrayList<Character>(Arrays.asList(new Character[]{'C','D'}));
    private final ArrayList<Character> MILKPRODUCINGANIMAL = new ArrayList<Character>(Arrays.asList(new Character[]{'S','G'}));
    private final ArrayList<Character> ARRAYFACILITY = new ArrayList<Character>(Arrays.asList(new Character[]{'W','T','M'}));
    private final Map<String, ArrayList<String>> RECIPE = new HashMap<String, ArrayList<String>>() {{
        put("BaconOmelette", new ArrayList<String>(Arrays.asList("PigMeat","ChickenEgg")));
        put("HorseRolade", new ArrayList<String>(Arrays.asList("HorseMeat", "DuckEgg")));
        put("MixedCheese", new ArrayList<String>(Arrays.asList("CowMilk", "GoatMilk")));
    }};

    private int row,col,score,pouch;
    private LinkedList<Product> backpack;

    /**
     * Construct Player without parameter
     */
    public Player(){
        row = col = score = 0; pouch = 10;
        backpack = new LinkedList<Product>();
    }

    /**
     * Construct player with paramenter baris dan kolom posisi awak
     * @param _row
     * @param _col
     */
    public Player(int _row, int _col){
        row = _row; col = _col; score = 0; pouch = 10;
        backpack = new LinkedList<Product>();
    }

    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int getPouch(){ return pouch; }
    public int getScore(){ return score; }

    public void setRow(int _row){ row = _row; }
    public void setCol(int _col){ col = _col; }
    public void setPouch(int _pouch){ pouch = _pouch; }
    public void setScore(int _score){ score = _score; }

    /**
     * menerima parameter berupa arah dan executable jika sekitar player merupakan hewan
     * @param dir
     */
    public void talk(char dir){
        ArrayList<java.lang.Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= Common.gamemap.get(0).size() || trow < 0 || trow >= Common.gamemap.size()) return;

        if (cell.getOverrideSymbol() != '\0'){ //animal
            System.out.println(cell.getAnimalRef().sound());
        }else{
            System.out.println("There's no animal..");
        }


    }

    /**
     * Menerima parameter berupa arah dan executable jika sekitar player merupakan hewan ataupun facility
     * @param dir
     */
    public void interact(char dir){
        ArrayList<java.lang.Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= Common.gamemap.get(0).size() || trow < 0 || trow >= Common.gamemap.size()) return;

        int idxFacility = ARRAYFACILITY.indexOf(Character.toUpperCase(cell.showSymbol()));

        if (cell.getOverrideSymbol() != '\0'){
            int idxMilkProducing = MILKPRODUCINGANIMAL.indexOf(Character.toUpperCase(cell.getOverrideSymbol()));
            int idxEggProducing = EGGPRODUCINGANIMAL.indexOf(Character.toUpperCase(cell.getOverrideSymbol()));
    
            if (Character.isUpperCase(cell.getOverrideSymbol())){
                if ((idxMilkProducing != -1 || idxEggProducing != -1) && backpack.size()<LENBACKPACK){
                    cell.getAnimalRef().revLapar();
                    cell.animalOccupy(cell.getAnimalRef());
                    System.out.println("Product is produced");
                }else if (backpack.size()>=LENBACKPACK){
                    System.out.println("Backpack is full!!");
                }else{
                    System.out.println("Not a producing animal !!");
                }

                if (idxMilkProducing != -1 && backpack.size()<LENBACKPACK){
                    backpack.add(cell.getAnimalRef().produceMilk());
                }
                if (idxEggProducing != -1 && backpack.size()<LENBACKPACK){
                    backpack.add(cell.getAnimalRef().produceEgg());
                }
    
            }else{
                System.out.println("Animal's hungry");
            }

        }else if (idxFacility != -1){
            char c = cell.showSymbol();

            if (c == 'W' || c == 'w'){ //Well
                if (cell.showSymbol()=='w'){
                    System.out.println("Well is unavailable");
                    return;
                }
                pouch = MAX_WATER;
                System.out.println("Water's filled");
                cell.getFacilityRef().invalidateFacility();

            }else if (c == 'T' || c == 't'){ //Truck
                if (cell.showSymbol()=='t'){
                    System.out.println("Truck's not available");
                    return;
                }

                cell.getFacilityRef().invalidateFacility();

                int money=0;
                Iterator<Product> itr = backpack.iterator();
                while (itr.hasNext()){
                    money += itr.next().getHarga();
                }
                backpack.clear();
                System.out.println("Money earned : " + money);
                setScore(score+money);

            }else{
                System.out.println("Cannot interact with the facility.");
            }

        }else{
            System.out.println("Interact Failed.");
        }
    }

    /**
     * menerima parameter arah dimana hewan berada dan akan menambahkan product of meat dari hewan yang di-kill
     * @param dir
     */
    public void kill(char dir){
        ArrayList<java.lang.Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= Common.gamemap.get(0).size() || trow < 0 || trow >= Common.gamemap.size()) return;

        if (cell.getOverrideSymbol() != '\0'){
            if (backpack.size()<LENBACKPACK){
                backpack.add(cell.getAnimalRef().produceMeat());
            }else{
                System.out.println("Backpack is full!!");
            }
            System.out.println(cell.getAnimalRef().sound());
            cell.makeUnoccupied();
            System.out.println("Animal's killed");
            Common.animalList.remove(cell.getAnimalRef());
        } else{
            System.out.println("There's no animal..");
        }
    }

    /**
     * Land dimana player berada akan diganti ke simbol rumput dan pouch dari player akan berkurang satu
     */
    public void grow(){ //on the spot
        if (pouch>0){
            Common.gamemap.get(row).get(col).growGrass();
            System.out.println("Grass grown");
            pouch--;
        }else{
            System.out.println("Refill the pouch first");
        }
    }

    /**
     * mencari menu yang cocok dari daftar dan akan memeriksa apakah backpack mengandung semua bahan tersebut dan akan melakukan delete inventory apabila sesuai
     * @param dir
     * @param menu
     */
    public void mix(char dir, String menu){
        ArrayList<java.lang.Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);
        
        if (tcol < 0 || tcol >= Common.gamemap.get(0).size() || trow < 0 || trow >= Common.gamemap.size()) return;

        if (cell.showSymbol() != 'M'){
            System.out.println("Mixing's failed");
            return;
        }else if (cell.showSymbol() == 'M'){
            ArrayList<String> ingredients = RECIPE.get(menu);
            if (ingredients==null){
                System.out.println("Menu's not available..");
                return;
            }

            cell.getFacilityRef().invalidateFacility();
            Iterator<Product> itrB = backpack.iterator();
            ArrayList<String> tempBag = new ArrayList<String>();

            while (itrB.hasNext()){
                tempBag.add(itrB.next().getProductName());
            }

            if (!tempBag.isEmpty()){
                int i = 0;
                int[] deleteIdx = new int[ingredients.size()];
                while (i<ingredients.size()-1 && tempBag.indexOf(ingredients.get(i)) != -1){
                    int idx = tempBag.indexOf(ingredients.get(i));
                    deleteIdx[i] = idx;
                    tempBag.remove(idx);
                    i++;
                }

                if(tempBag.indexOf(ingredients.get(i)) != -1){ //last element and product's existed in backpack
                    int idx = tempBag.indexOf(ingredients.get(i));
                    deleteIdx[i] = idx;

                    for(int j=0; j<deleteIdx.length; ++j){
                        backpack.remove(deleteIdx[j]);
                    }

                    if (menu.equals("BaconOmelette")){
                        backpack.add(new BaconOmelette());
                    } else if (menu.equals("HorseRolade")){
                        backpack.add(new HorseRolade());
                    }else if (menu.equals("MixedCheese")){
                        backpack.add(new MixedCheese());
                    }else{
                        backpack.add(new Product());
                    }
                    System.out.println("Product's added");

                }else{
                    System.out.println("Ingredients's not complete");
                }
            }else{
                System.out.println("Ingredients's not complete");
            }

        }

    }

    /**
     * Mengembalikan cell yang di-interact dengan arah yang sesuai di parameter
     * @param dir
     * @return
     */
    public ArrayList<java.lang.Object> getPositionInteract(char dir){ // return Cell, rowt, colt
        dir = Character.toLowerCase(dir);
        int colt = 0, rowt = 0; //position of the cell that will be interacted
        int maxRow = Common.gamemap.size(), maxCol = Common.gamemap.get(0).size();

        if (dir == 'w'){
            colt = col; rowt = row-1;
        }else if (dir == 'd'){
            colt = col+1; rowt = row; 
        }else if (dir == 's'){
            colt = col; rowt = row+1; 
        }else if (dir == 'a'){
            colt = col-1; rowt = row; 
        }

        if (0<=rowt && rowt<maxRow && 0<=colt && colt<maxCol){
            return new ArrayList(Arrays.asList(Common.gamemap.get(rowt).get(colt), rowt, colt));
        }else{
            System.out.println("Cell is out of range");
            return new ArrayList(Arrays.asList(new Cell(), rowt, colt));
        }
    }

    /**
     * Memeriksa apakah player dapat melalui cell sesuai posisi baris trow dan kolom tcol
     * @param trow
     * @param tcol
     * @return
     */
    public boolean canPass(int trow, int tcol){
        char overrideSymbol = Common.gamemap.get(trow).get(tcol).getOverrideSymbol();
        char baseSymbol = Character.toUpperCase(Common.gamemap.get(trow).get(tcol).showSymbol());
        return (overrideSymbol == '\0' && 
                baseSymbol != 'M' && 
                baseSymbol != 'T' && 
                baseSymbol != 'W');
    }

    /**
     * Melakukan pergerakan player ke arah dari parameter
     * @param dir
     */
    public void move(char dir){ //move toward dir direction
        dir = Character.toLowerCase(dir);

        if (dir == 'w' && 0<=row-1 && canPass(row-1,col)){
            row--;
        }
        else if (dir == 'd' && col+1<Common.gamemap.get(0).size() && canPass(row,col+1)){
            col++;
        }
        else if (dir == 's' && row+1<Common.gamemap.size() && canPass(row+1,col)){
            row++;
        }
        else if (dir == 'a' && 0<=col-1 && canPass(row,col-1)){
            col--;
        }
    }

    /**
     * Melakukan print dari semua inventories di backpack
     * @return array of string of all products in backpack
     */
    public String[] printBackpack(){
        Iterator<Product> itr = backpack.iterator();
        String[] temp = new String[]{"","","","","",""};
        int i = 0;
        while (itr.hasNext()){
            if(i > 5){
                break;
            }
            temp[i] = itr.next().getProductName();
            i++;
        }
        return temp;
    }
}