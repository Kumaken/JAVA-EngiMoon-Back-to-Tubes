package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class PigMeatTest {
    @Test
    public void PigMeatTest() throws Exception{
        PigMeat temp = new PigMeat();
        assertEquals(temp.getHarga(),12000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"PigMeat");
    }
}