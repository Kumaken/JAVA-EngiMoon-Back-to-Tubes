package animals;

import org.junit.Test;
import static org.junit.Assert.*;
import product.*;

public class HorseTest {
    @Test
    public void testHorse() throws Exception{
        Horse horse = new Horse(8,5,true);
        assertEquals(horse.getLapar(),true);
        assertEquals(horse.getThreshold(),12);
        assertEquals(horse.getX(),8);
        assertEquals(horse.getY(),5);
        horse.minThreshold();
        assertEquals(horse.getThreshold(),11);
        FarmProduct prod = horse.produceMeat();
        assertEquals(prod.getProductName(),"HorseMeat");
        FarmProduct prodx = horse.produceMilk();
        assertEquals(prodx.getProductName(),"");
        assertEquals(horse.showSimbol(),'h');
        horse.revLapar();
        assertEquals(horse.getLapar(),false);
    }
}