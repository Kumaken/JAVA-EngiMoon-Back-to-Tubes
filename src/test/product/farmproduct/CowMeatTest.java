package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class CowMeatTest {
    @Test
    public void CowMeatTest() throws Exception{
        CowMeat temp = new CowMeat();
        assertEquals(temp.getHarga(),15000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"CowMeat");
    }
}