package cells.facilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MixerTest {
    @Test
    public void MixerTest() throws Exception{
        Mixer temp = new Mixer();
        assertEquals(temp.showSymbol(), 'M');
    }

}