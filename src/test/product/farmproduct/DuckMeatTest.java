package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuckMeatTest {
    @Test
    public void DuckMeatTest() throws Exception{
        DuckMeat temp = new DuckMeat();
        assertEquals(temp.getHarga(),13000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"DuckMeat");
    }
}