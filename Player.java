import java.util.*; //for map, vector, linkedlist, list, etc;

class Player{
    private static final int MAX_WATER = 100;

    private final Vector<Character> EGGPRODUCINGANIMAL = new Vector<Character>(Arrays.asList(new Character[]{'C','D'}));
    private final Vector<Character> MILKPRODUCINGANIMAL = new Vector<Character>(Arrays.asList(new Character[]{'S','G'}));
    private final Vector<Character> ARRAYFACILITY = new Vector<Character>(Arrays.asList(new Character[]{'W','T','M'}));
    private final Map<String, Vector<String>> RECIPE = new HashMap<String, Vector<String>>() {{
        put("BaconOmelette", new Vector<String>(Arrays.asList("PigMeat","ChickenEgg")));
        put("HorseRolade", new Vector<String>(Arrays.asList("HorseMeat", "DuckEgg")));
        put("MixedCheese", new Vector<String>(Arrays.asList("CowMilk", "GoatMilk")));
    }};

    private int row,col,score,pouch;
    private LinkedList<Product> backpack;

    public Player(){
        row = col = score = 0; pouch = 5;
        backpack = new LinkedList<Product>();
    }

    public Player(int _row, int _col){
        row = _row; col = _col; score = 0; pouch = 5;
        backpack = new LinkedList<Product>();
    }

    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int getPouch(){ return pouch; }
    public int getScore(){ return score; }

    public void setRow(int _row){ row = _row; }
    public void setCol(int _col){ col = _col; }
    public void setPouch(int _pouch){ pouch = _pouch; }
    public void setScore(int _score){ pouch = _score; }

    public void talk(char dir){
        List<Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= gamemap[0].size() || trow < 0 || trow >= gamemap.size()) return;

        if (cell.getOverrideSymbol() != '\0'){ //animal
            System.out.println(cell.getAnimalPtr().sound());
        }else{
            System.out.println("There's no animal..");
        }


    }

    public void interact(char dir){
        List<Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= gamemap[0].size() || trow < 0 || trow >= gamemap.size()) return;

        int idxFacility = ARRAYFACILITY.indexOf(Character.toUpperCase(cell.showSymbol()));

        if (cell.getOverrideSymbol() != '\0'){
            int idxMilkProducing = MILKPRODUCINGANIMAL.indexOf(Character.toUpperCase(cell.getOverrideSymbol()));
            int idxEggProducing = EGGPRODUCINGANIMAL.indexOf(Character.toUpperCase(cell.getOverrideSymbol()));
    
            if (Character.isUpperCase(cell.getOverrideSymbol())){
                if (idxMilkProducing != -1){
                    backpack.add(cell.getAnimalPtr().produceMilk());
                }
                if (idxEggProducing != -1){
                    backpack.add(cell.getAnimalPtr().produceEgg());
    
                }
                if (idxMilkProducing != -1 || idxEggProducing != -1){
                    cell.getAnimalPtr().revLapar();
                    cell.animalOccupy(cell.getAnimalPtr());
                    System.out.println("Product is produced");
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
                cell.getFacilityPtr().invalidateFacility();

            }else if (c == 'T' || c == 't'){ //Truck
                if (cell.showSymbol()=='t'){
                    System.out.println("Truck's not available");
                    return;
                }

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

    public void kill(char dir){
        List<Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);

        if (tcol < 0 || tcol >= gamemap[0].size() || trow < 0 || trow >= gamemap.size()) return;

        if (cell.getOverrideSymbol() != '\0'){
            backpack.add(cell.getAnimalPtr().produceMeat());
            System.out.println(cell.getAnimalPtr().sound());
            cell.makeUnoccupied();
            System.out.println("Animal's killed");
    
            //vector<FarmAnimal*>::const_iterator itr = find(animalList.begin(), animalList.end(),cell.getAnimalPtr());
            //if (itr!=animalList.end()) animalList.erase(itr);
        } else{
            System.out.println("There's no animal..");
        }
    }

    public void grow(){ //on the spot
        if (pouch>0){
            gamemap[row][col].growGrass();
            System.out.println("Grass grown");
            pouch--;
        }else{
            System.out.println("Refill the pouch first");
        }
    }

    public void mix(char dir, String menu){
        List<Object> list = getPositionInteract(dir);
        Cell cell = (Cell) list.get(0);
        int trow = (Integer) list.get(1);
        int tcol = (Integer) list.get(2);
        
        if (tcol < 0 || tcol >= gamemap[0].size() || trow < 0 || trow >= gamemap.size()) return;

        if (cell.showSymbol() != 'M'){
            System.out.println("Mixing's failed");
            return;
        }else if (cell.showSymbol() == 'M'){
            Vector<String> ingredients = RECIPE.get(menu);
            if (ingredients==null){
                System.out.println("Menu's not available..");
                return;
            }

            cell.getFacilityPtr().invalidateFacility();
            Iterator<Product> itrB = backpack.iterator();
            List<String> tempBag = new List<String>();

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

                    if (menu=="BaconOmelette"){
                        backpack.add(new BaconOmelette());
                    } else if (menu == "HorseRolade"){
                        backpack.add(new HorseRolade());
                    }else if (menu =="MixedCheese"){
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

    public List<Object> getPositionInteract(char dir){ // return Cell, rowt, colt
        dir = Character.toLowerCase(dir);
        int colt, rowt; //position of the cell that will be interacted
        int maxRow = gamemap.size(), maxCol = gamemap[0].size();

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
            return Arrays.asList(gamemap[rowt][colt], rowt, colt); 
        }else{
            System.out.println("Cell is out of range");
            return new Cell();
        }
    }

    public boolean canPass(int trow, int tcol){
        char overrideSymbol = gamemap[trow][tcol.getOverrideSymbol();
        char baseSymbol = Character.toUpperCase(gamemap[trow][tcol].showSymbol());
        return (overrideSymbol == '\0' && 
                baseSymbol != 'M' && 
                baseSymbol != 'T' && 
                baseSymbol != 'W');
    }

    public void move(char dir){ //move toward dir direction
        dir = Character.toLowerCase(dir);

        if (dir == 'w' && 0<=row-1 && canPass(row-1,col)){
            row--;
        }
        else if (dir == 'd' && col+1<gamemap[0].size() && canPass(row,col+1)){
            col++;
        }
        else if (dir == 's' && row+1<gamemap.size() && canPass(row+1,col)){
            row++;
        }
        else if (dir == 'a' && 0<=col-1 && canPass(row,col-1)){
            col--;
        }
    }

    public void printBackpack(){
        Iterator<Product> itr = backpack.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next().getProductName() + " ");
        }
        System.out.println();
    }
}