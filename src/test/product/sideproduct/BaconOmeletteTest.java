package product.sideproduct;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaconOmeletteTest {
    @Test
    public void BaconOmeletteTest() throws Exception{
        BaconOmelette temp = new BaconOmelette();
        assertEquals(temp.getHarga(),18000); // Sudah termasuk setter
        assertEquals(temp.getProductName(),"BaconOmelette");
    }
}