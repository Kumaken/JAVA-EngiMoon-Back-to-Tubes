package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class ChickenTest {
    @Test
    public void testChicken() throws Exception{
        Chicken chick = new Chicken(2,5,true);
        assertEquals(chick.getLapar(),true);
        assertEquals(chick.getThreshold(),8);
        assertEquals(chick.getX(),2);
        assertEquals(chick.getY(),5);
        chick.minThreshold();
        assertEquals(chick.getThreshold(),7);
        FarmProduct prod = chick.produceEgg();
        assertEquals(prod.getProductName(),"ChickenEgg");
        FarmProduct prodx = chick.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(chick.showSimbol(),'c');
        chick.revLapar();
        assertEquals(chick.getLapar(),false);
    }
}