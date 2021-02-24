package com.eightqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class EightQueensBoardGenerator {

    public boolean[][] randomBoardWithEightQueens() {
        boolean[][] board = new boolean[8][8];
        List<Integer> numbers = new ArrayList<>();
        IntStream.rangeClosed(0,63).forEach(numbers::add);
        IntStream.rangeClosed(0,7).forEach(value -> {
            Integer randomNum = numbers.remove(ThreadLocalRandom.current().nextInt(0, numbers.size()));
            int file = randomNum / 8;
            int rank = randomNum % 8;
            board[file][rank] = true;
        });
        return board;
    }
}
