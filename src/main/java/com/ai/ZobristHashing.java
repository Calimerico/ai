package com.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZobristHashing {

    private Map<ZobristValue, Integer> zobristValues;
    private Map<Integer, Double> cachedEvaluations = new HashMap<>();

    public Double getCachedValue(MiniMaxState state) {
        return cachedEvaluations.get(hash(state));
    }

    public void saveIntoCache(MiniMaxState state, double evaluation) {
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
        List<Integer> numbers = IntStream.range(0,100000).boxed().collect(Collectors.toList());
        state.getAllZobristValues()
                .forEach(zobristValue -> zobristValues.put(
                        zobristValue,
                        numbers.remove(ThreadLocalRandom.current().nextInt(0, numbers.size()))
                ));
    }
}
