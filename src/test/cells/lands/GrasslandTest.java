package cells.lands;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrasslandTest {
    @Test
    public void GrasslandTest() throws Exception{
        Grassland temp = new Grassland();
        assertEquals(temp.showSymbol(), '.');
        temp.growGrass();
        assertEquals(temp.showSymbol(), '#');
        temp.ungrowGrass();
        assertEquals(temp.showSymbol(), '.');
    }
}