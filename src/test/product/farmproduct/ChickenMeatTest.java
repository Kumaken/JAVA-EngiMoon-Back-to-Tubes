package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChickenMeatTest {
    @Test
    public void ChickenMeatTest() throws Exception{
        ChickenMeat temp = new ChickenMeat();
        assertEquals(temp.getHarga(),10000); // Sudah termasuk setter
        assertEquals(temp.getProductName(), "ChickenMeat");
    }
}