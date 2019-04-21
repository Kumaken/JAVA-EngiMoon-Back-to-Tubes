package product.sideproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class MixedCheeseTest {
    @Test
    public void MixedCheeseTest() throws Exception{
        MixedCheese temp = new MixedCheese();
        assertEquals(temp.getHarga(),18000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"MixedCheese");
    }
}