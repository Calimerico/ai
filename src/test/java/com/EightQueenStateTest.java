package com;

import com.eightqueens.EightQueensState;
import org.junit.Assert;
import org.junit.Test;

public class EightQueenStateTest {

    @Test
    public void testHeuristic() {
        //given
        boolean[][] board = new boolean[8][8];
        board[0][5] = true;
        board[1][0] = true;
        board[2][2] = true;
        board[3][6] = true;
        board[4][3] = true;
        board[5][4] = true;
        board[6][1] = true;
        board[7][7] = true;
        HillClimbingSearchTest.printBoard(board);
        //when
        double heuristicFunction = new EightQueensState(board).getHeuristicFunction();
        //then
        Assert.assertTrue(heuristicFunction == 12);
    }

//    oooxoooo
//    ooooooxo
//    xooooooo
//    ooooooox
//    ooooxooo
//    oxoooooo
//    oooooxoo
//    ooxooooo This is one of the solutions -> heuristic 0
}
