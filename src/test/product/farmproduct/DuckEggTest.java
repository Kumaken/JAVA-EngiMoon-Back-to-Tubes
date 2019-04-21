package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuckEggTest {
    @Test
    public void DuckEggTest() throws Exception{
        DuckEgg temp = new DuckEgg();
        assertEquals(temp.getHarga(),3500); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"DuckEgg");
    }
}