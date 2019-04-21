package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class GoatTest {
    @Test
    public void testGoat() throws Exception{
        Goat goat = new Goat(3,7,false);
        assertEquals(goat.getLapar(),false);
        assertEquals(goat.getThreshold(),10);
        assertEquals(goat.getX(),3);
        assertEquals(goat.getY(),7);
        goat.minThreshold();
        assertEquals(goat.getThreshold(),9);
        FarmProduct prod = goat.produceMilk();
        assertEquals(prod.getProductName(),"GoatMilk");
        FarmProduct prodx = goat.produceEgg();
        assertEquals(prodx.getProductName(),"");
        assertEquals(goat.showSimbol(),'G');
        goat.revLapar();
        assertEquals(goat.getLapar(),true);
    }
}