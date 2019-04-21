package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class PigTest {
    @Test
    public void testPig() throws Exception{
        Pig pig = new Pig(4,4,false);
        assertEquals(pig.getLapar(),false);
        assertEquals(pig.getThreshold(),12);
        assertEquals(pig.getX(),4);
        assertEquals(pig.getY(),4);
        pig.minThreshold();
        assertEquals(pig.getThreshold(),11);
        FarmProduct prod = pig.produceMeat();
        assertEquals(prod.getProductName(),"PigMeat");
        FarmProduct prodx = pig.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(pig.showSimbol(),'B');
        pig.revLapar();
        assertEquals(pig.getLapar(),true);
    }
}