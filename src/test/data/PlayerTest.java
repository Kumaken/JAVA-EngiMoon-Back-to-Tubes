package data;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testPlayerPosition() throws Exception{
        Player player1 = new Player();
        assertEquals(player1.getCol(),0);
        assertEquals(player1.getRow(),0);
        assertEquals(player1.getScore(),0);
        assertEquals(player1.getPouch(),10);

        Player player2 = new Player(10,5);
        assertEquals(player2.getCol(),5);
        assertEquals(player2.getRow(),10);
        assertEquals(player2.getScore(),0);
        assertEquals(player2.getPouch(),10);

        player1.setCol(10);
        player1.setRow(2);
        player1.setPouch(2);
        player1.setScore(100);
        assertEquals(player1.getCol(),10);
        assertEquals(player1.getRow(),2);
        assertEquals(player1.getScore(),100);
        assertEquals(player1.getPouch(),2);
    }
}