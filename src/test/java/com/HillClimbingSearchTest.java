package com;

import com.ai.HillClimbingSearch;
import com.eightqueens.EightQueensBoardGenerator;
import com.eightqueens.EightQueensState;
import org.junit.Assert;
import org.junit.Test;

public class HillClimbingSearchTest {

    @Test
    public void test() {
        //given
        EightQueensBoardGenerator generator = new EightQueensBoardGenerator();
        boolean[][] board = generator.randomBoardWithEightQueens();
        printBoard(board);
        System.out.println(" ");
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("");
        HillClimbingSearch hillClimbingSearch = new HillClimbingSearch();

        //when
        EightQueensState solution = (EightQueensState) hillClimbingSearch.search(new EightQueensState(board), true);
        Assert.assertEquals(0, solution.getHeuristicFunction(), 0.0);
        printBoard(solution.getBoard());

    }

    public static void printBoard(boolean[][] board) {
        for (int i = 0; i<8;i++) {
            for (int j = 0; j<8;j++) {
                if (board[i][j]) {
                    System.out.print("x");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println(" ");
        }
    }
}
