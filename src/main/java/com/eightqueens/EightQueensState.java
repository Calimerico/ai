package com.eightqueens;

import com.Move;
import com.Square;
import com.ai.Action;
import com.ai.State;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@ToString
public class EightQueensState implements State {

    private boolean[][] board;

    @Override
    public Set<Action> getActions() {
        Set<Action> actions = new HashSet<>();
        for (int i =0; i<8;i++) {
            for (int j =0; j<8;j++) {
                if (board[i][j]) {
                    for (int x =0; x<8;x++) {
                        for (int y =0; y<8;y++) {
                            if (!board[x][y]) {
                                actions.add(new EightQueensAction(new Move(Square.calculateSquareFromCoorinates(i,j),Square.calculateSquareFromCoorinates(x,y))));

                            }
                        }
                    }
                }
            }
        }
        return actions;
    }

    @Override
    public State newState(Action action) {
        EightQueensAction a = (EightQueensAction) action;
        boolean[][] newBoard = new boolean[8][8];
        for(int i = 0; i < board.length; i++)
        {
            boolean[] aMatrix = board[i];
            int   aLength = aMatrix.length;
            newBoard[i] = new boolean[aLength];
            System.arraycopy(aMatrix, 0, newBoard[i], 0, aLength);
        }


        newBoard[a.getMove().getStartingSquare().getFile()][a.getMove().getStartingSquare().getRank()] = false;
        newBoard[a.getMove().getEndingSquare().getFile()][a.getMove().getEndingSquare().getRank()] = true;
        return new EightQueensState(newBoard);
    }

    @Override
    public double getHeuristicFunction() {
        int heuristic = 0;
        for (int i =0; i<8;i++) {
            for (int j =0; j<8;j++) {
                if (board[i][j]) {
                    int attackLikeARook = -2;
                    for (int x =0; x<8;x++) {
                        if (board[i][x]) {
                            attackLikeARook++;
                        }
                        if (board[x][j]) {
                            attackLikeARook++;
                        }
                    }
                    int attackLikeABishop = 0;
                    for (int x =0; x<8;x++) {
                        for (int y =0; y<8;y++) {
                            if (x + y == i + j && board[x][y] && x != i && y != j) {
                                attackLikeABishop++;
                            }
                            int diffX = x-i;
                            int diffY = y-j;
                            if (diffX == diffY && board[x][y] && x != i && y != j){
                                attackLikeABishop++;
                            }
                        }
                    }
                    heuristic += attackLikeABishop;
                    heuristic += attackLikeARook;
                }
            }
        }
        return heuristic;
    }

    @Override
    public State deepCopy() {
        return null;//todo
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EightQueensState that = (EightQueensState) o;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (that.getBoard()[i][j] != this.getBoard()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
