package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class DuckTest {
    @Test
    public void testDuck() throws Exception{
        Duck duck = new Duck(10,8,false);
        assertEquals(duck.getLapar(),false);
        assertEquals(duck.getThreshold(),8);
        assertEquals(duck.getX(),10);
        assertEquals(duck.getY(),8);
        duck.minThreshold();
        assertEquals(duck.getThreshold(),7);
        FarmProduct prod = duck.produceEgg();
        assertEquals(prod.getProductName(),"DuckEgg");
        FarmProduct prodx = duck.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(duck.showSimbol(),'D');
        duck.revLapar();
        assertEquals(duck.getLapar(),true);
    }
}