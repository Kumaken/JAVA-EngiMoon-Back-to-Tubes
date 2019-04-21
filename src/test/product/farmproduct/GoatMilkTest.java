package product.farmproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoatMilkTest {
    @Test
    public void GoatMilkTest() throws Exception{
        GoatMilk temp = new GoatMilk();
        assertEquals(temp.getHarga(),8000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"GoatMilk");
    }
}