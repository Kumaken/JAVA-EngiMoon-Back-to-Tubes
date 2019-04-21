package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChickenEggTest {
    @Test
    public void ChickenEggTest() throws Exception{
        ChickenEgg temp = new ChickenEgg();
        assertEquals(temp.getHarga(),2000); // Sudah termasuk setter
        assertEquals(temp.getProductName(), "ChickenEgg");
    }
}