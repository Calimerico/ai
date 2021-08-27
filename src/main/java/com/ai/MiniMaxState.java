package com.ai;

import java.util.List;

public interface MiniMaxState extends State {
    boolean maxPlayer();
    List<ZobristValue> getAllZobristValues();
    List<ZobristValue> getCurrentZobristValues();
}
