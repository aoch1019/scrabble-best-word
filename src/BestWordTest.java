import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class BestWordTest {

    @Test
    public void testBusyBoard() {
        Board b = new Board();
        Tile t1 = new Tile('S');
        Tile t2 = new Tile('O');
        Tile t3 = new Tile('P');
        Tile t4 = new Tile('O');
        Tile t5 = new Tile('R');
        Tile t6 = new Tile('U');
        Tile t7 = new Tile('Q');
        Tile t8 = new Tile('T');
        Tile t9 = new Tile('E');
        Tile t10 = new Tile('N');
        Tile t11 = new Tile('S');
        Tile t12 = new Tile('I');
        Tile t13 = new Tile('L');
        Tile t14 = new Tile('S');
        // N for not
        Tile t15 = new Tile('N');
        // CON
        Tile t16 = new Tile('C');
        Tile t17 = new Tile('O');
        Tile t18 = new Tile('N');
        // TORSADE (T***ADE)
        Tile t19 = new Tile('T');
        Tile t20 = new Tile('A');
        Tile t21 = new Tile('D');
        Tile t22 = new Tile('E');
        // SOPOR
        b.placeTile(t1, 7, 7);
        b.placeTile(t2, 7, 8);
        b.placeTile(t3, 7, 9);
        b.placeTile(t4, 7, 10);
        b.placeTile(t5, 7, 11);
        // SUQ
        b.placeTile(t6, 8, 7);
        b.placeTile(t7, 9, 7);
        // UTENSILS
        b.placeTile(t8, 8, 8);
        b.placeTile(t9, 8, 9);
        b.placeTile(t10, 8, 10);
        b.placeTile(t11, 8, 11);
        b.placeTile(t12, 8, 12);
        b.placeTile(t13, 8, 13);
        b.placeTile(t14, 8, 14);
        // NOT
        b.placeTile(t15, 6, 8);
        // CON
        b.placeTile(t16, 6, 10);
        b.placeTile(t17, 6, 11);
        b.placeTile(t18, 6, 12);
        // TORSADE
        b.placeTile(t19, 5, 11);
        b.placeTile(t20, 9, 11);
        b.placeTile(t21, 10, 11);
        b.placeTile(t22, 11, 11);
        BestWord bw = new BestWord(b);
        b.printBoard();
        bw.printIntBoard();
        ArrayList<Entry<Integer, Integer>> validAnchors = bw.getValidAnchors();
        HashMap<Entry<Integer, Integer>, Character> bestMove = bw
            .highestScoringMove(bw.tryAllPlacements(bw.allValidTilePlacements(validAnchors)));
        for (Entry<Integer, Integer> e : bestMove.keySet()) {
          int row = e.getKey();
          int col = e.getValue();
          Tile t = new Tile(bestMove.get(e));
          b.placeTile(t, row, col);
        }
        b.printBoard();
    }
    
    @Test
    public void testBestWordBlankBoard() {
        Board b = new Board();  
        BestWord bw = new BestWord(b);
        Tile t1 = new Tile('S');
        Tile t2 = new Tile('T');
        Tile t3 = new Tile('A');
        Tile t4 = new Tile('P');
        Tile t5 = new Tile('L');
        Tile t6 = new Tile('E');
        Tile t7 = new Tile('R');
        Tile[] newHand = new Tile[7];
        newHand[0] = t1;
        newHand[1] = t2;
        newHand[2] = t3;
        newHand[3] = t4;
        newHand[4] = t5;
        newHand[5] = t6;
        newHand[6] = t7;
        bw.setHand(newHand);
        ArrayList<Entry<Integer, Integer>> validAnchors = bw.getValidAnchors();
        HashMap<Entry<Integer, Integer>, Character> bestMove = bw.highestScoringMove(bw.tryAllPlacements(bw.allValidTilePlacements(validAnchors)));
              System.out.println(bw.getHighestScore());
        for(Entry<Integer, Integer> e : bestMove.keySet()) {
          int row = e.getKey();
          int col = e.getValue();
          Tile t = new Tile(bestMove.get(e));
          b.placeTile(t, row, col);
        }
        b.printBoard();              
        assertEquals(74, bw.getHighestScore());
    }
    
    @Test
    public void testBestWordSmallBoard() {
        Board b = new Board();
        Tile t1 = new Tile('T');
        Tile t2 = new Tile('E');
        Tile t3 = new Tile('S');
        Tile t4 = new Tile('T');
        b.placeTile(t1, 7, 7);
        b.placeTile(t2, 7, 8);
        b.placeTile(t3, 7, 9);
        b.placeTile(t4, 7, 10);
        BestWord bw = new BestWord(b);
        Tile t5 = new Tile('S');
        Tile t6 = new Tile('T');
        Tile t7 = new Tile('A');
        Tile t8 = new Tile('P');
        Tile t9 = new Tile('L');
        Tile t10 = new Tile('E');
        Tile t11 = new Tile('R');
        Tile[] newHand = new Tile[7];
        newHand[0] = t5;
        newHand[1] = t6;
        newHand[2] = t7;
        newHand[3] = t8;
        newHand[4] = t9;
        newHand[5] = t10;
        newHand[6] = t11;
        bw.setHand(newHand);
        ArrayList<Entry<Integer, Integer>> validAnchors = bw.getValidAnchors();
        HashMap<Entry<Integer, Integer>, Character> bestMove = 
              bw.highestScoringMove(bw.tryAllPlacements(bw.allValidTilePlacements(validAnchors)));
        System.out.println(bw.getHighestScore());
        for(Entry<Integer, Integer> e : bestMove.keySet()) {
          int row = e.getKey();
          int col = e.getValue();
          Tile t = new Tile(bestMove.get(e));
          b.placeTile(t, row, col);
        }
        b.printBoard();
        assertEquals(76, bw.getHighestScore());
    }
    
    @Test
    public void testBestWordSmallBoard2() {
        Board b = new Board();
        Tile t1 = new Tile('W');
        Tile t2 = new Tile('O');
        Tile t3 = new Tile('R');
        Tile t4 = new Tile('D');
        b.placeTile(t1, 7, 7);
        b.placeTile(t2, 7, 8);
        b.placeTile(t3, 7, 9);
        b.placeTile(t4, 7, 10);
        BestWord bw = new BestWord(b);
        Tile t5 = new Tile('S');
        Tile t6 = new Tile('T');
        Tile t7 = new Tile('A');
        Tile t8 = new Tile('P');
        Tile t9 = new Tile('L');
        Tile t10 = new Tile('E');
        Tile t11 = new Tile('R');
        Tile[] newHand = new Tile[7];
        newHand[0] = t5;
        newHand[1] = t6;
        newHand[2] = t7;
        newHand[3] = t8;
        newHand[4] = t9;
        newHand[5] = t10;
        newHand[6] = t11;
        bw.setHand(newHand);
        ArrayList<Entry<Integer, Integer>> validAnchors = bw.getValidAnchors();
        HashMap<Entry<Integer, Integer>, Character> bestMove =
                bw.highestScoringMove(bw.tryAllPlacements(bw.allValidTilePlacements(validAnchors)));
        System.out.println(bw.getHighestScore());
        for(Entry<Integer, Integer> e : bestMove.keySet()) {
            int row = e.getKey();
            int col = e.getValue();
            Tile t = new Tile(bestMove.get(e));
            b.placeTile(t, row, col);
        }
        b.printBoard();
        assertEquals(80, bw.getHighestScore());
    }
    
    @Test
    public void testBasic() {
        Board b = new Board();
        b.createRandomBoard();        
        BestWord bw = new BestWord(b);
        b.printBoard();
        bw.printIntBoard();
        ArrayList<Entry<Integer, Integer>> validAnchors = bw.getValidAnchors();
        HashMap<Entry<Integer, Integer>, Character> bestMove = bw.highestScoringMove(bw.tryAllPlacements(bw.allValidTilePlacements(validAnchors)));
        for(Entry<Integer, Integer> e : bestMove.keySet()) {
            int row = e.getKey();
            int col = e.getValue();
            Tile t = new Tile(bestMove.get(e));
            b.placeTile(t, row, col);
        }
        System.out.println(bw.getHighestScore());
        b.printBoard();
        bw.calculateScore2(bestMove);        
    }

}
