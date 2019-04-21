package cells.lands;

import org.junit.Test;

import static org.junit.Assert.*;

public class BarnTest {
    @Test
    public void BarnTest() throws Exception{
        Barn temp = new Barn();
        assertEquals(temp.showSymbol(), 'x');
        temp.growGrass();
        assertEquals(temp.showSymbol(), '@');
        temp.ungrowGrass();
        assertEquals(temp.showSymbol(), 'x');
    }

}