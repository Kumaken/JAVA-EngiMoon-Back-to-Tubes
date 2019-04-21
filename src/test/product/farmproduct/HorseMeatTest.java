package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class HorseMeatTest {
    @Test
    public void HorseMeatTest() throws Exception{
        HorseMeat temp = new HorseMeat();
        assertEquals(temp.getHarga(),15000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"HorseMeat");
    }

}