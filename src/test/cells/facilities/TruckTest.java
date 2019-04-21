package cells.facilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckTest {
    @Test
    public void TruckTest() throws Exception{
        Truck temp = new Truck();
        assertEquals(temp.showSymbol(), 'T');
    }

}