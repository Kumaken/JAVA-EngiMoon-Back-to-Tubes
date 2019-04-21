package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoatMeatTest {
    @Test
    public void GoatMeatTest() throws Exception{
        GoatMeat temp = new GoatMeat();
        assertEquals(temp.getHarga(),11000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"GoatMeat");
    }

}