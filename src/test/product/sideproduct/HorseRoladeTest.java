package product.sideproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class HorseRoladeTest {
    @Test
    public void HorseRoladeTest() throws Exception{
        HorseRolade temp = new HorseRolade();
        assertEquals(temp.getHarga(),25000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"HorseRolade");
    }
}