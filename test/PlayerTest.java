package test;

import org.junit.Test;
import static org.junit.Assert.*;

import data.Player;


public class PlayerTest{
    @Test
    public void testPlayerPosition() throws Exception{
        Player player1 = new Player();
        assertEquals(player1.getCol(),0);
        assertEquals(player1.getRow(),0);
        assertEquals(player1.getScore(),0);
        assertEquals(player1.getPouch(),5);

        Player player2 = new Player(10,5);
        assertEquals(player2.getCol(),5);
        assertEquals(player2.getRow(),10);
        assertEquals(player2.getScore(),0);
        assertEquals(player2.getPouch(),10);

        
    }
}
