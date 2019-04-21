package cells.lands;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoopTest {
    @Test
    public void CoopTest() throws Exception{
        Coop temp = new Coop();
        assertEquals(temp.showSymbol(), 'o');
        temp.growGrass();
        assertEquals(temp.showSymbol(), '*');
        temp.ungrowGrass();
        assertEquals(temp.showSymbol(), 'o');
    }
}