package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class CowMilkTest {
    @Test
    public void CowMilkTest() throws Exception{
        CowMilk temp = new CowMilk();
        assertEquals(temp.getHarga(),6000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"CowMilk");
    }
}