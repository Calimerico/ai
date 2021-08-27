package com.ai;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ZobristHashing {

    private Map<ZobristValue, Integer> zobristValues;
    private Map<Integer, Float> cachedEvaluations = new HashMap<>();

    public Float getCachedValue(MiniMaxState state) {
        return cachedEvaluations.get(hash(state));
    }

    public void saveIntoCache(MiniMaxState state, float evaluation) {
        cachedEvaluations.put(
                hash(state),
                evaluation
        );
    }

    private Integer hash(MiniMaxState state) {
        return state.getCurrentZobristValues()
                .stream()
                .map(zobristValue -> zobristValues.get(zobristValue))
                .reduce((integer, integer2) -> integer ^ integer2)
                .get();

    }

    public void initializeZobristValues(MiniMaxState state) {
        zobristValues = new HashMap<>();
        state.getAllZobristValues()
                .forEach(zobristValue -> zobristValues.put(
                        zobristValue,
                        ThreadLocalRandom.current().nextInt(1_000_000_000, 2_100_000_000)
                ));
    }
}
