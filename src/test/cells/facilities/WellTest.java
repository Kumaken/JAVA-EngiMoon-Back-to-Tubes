package cells.facilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class WellTest {
    @Test
    public void WellTest() throws Exception{
        Well temp = new Well();
        assertEquals(temp.showSymbol(), 'W');
    }
}