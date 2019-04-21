package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class CowTest {
    @Test
    public void testCow() throws Exception{
        Cow cow = new Cow(1,4,true);
        assertEquals(cow.getLapar(),true);
        assertEquals(cow.getThreshold(),10);
        assertEquals(cow.getX(),1);
        assertEquals(cow.getY(),4);
        cow.minThreshold();
        assertEquals(cow.getThreshold(),9);
        FarmProduct prod = cow.produceMilk();
        assertEquals(prod.getProductName(),"CowMilk");
        FarmProduct prodx = cow.produceEgg();
        assertEquals(prodx.getProductName(),"");
        assertEquals(cow.showSimbol(),'s');
        cow.revLapar();
        assertEquals(cow.getLapar(),false);
    }
}