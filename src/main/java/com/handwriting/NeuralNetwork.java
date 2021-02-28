package com.handwriting;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private final List<Layer> layers;
    private final CostFunction costFunction;
    private final Optimizer optimizer;

    public NeuralNetwork(List<Integer> numberOfNeuronsInLayer, CostFunction costFunction, Optimizer optimizer) {
        int numberOfLayers = numberOfNeuronsInLayer.size();
        this.layers = new ArrayList<>(numberOfLayers);
        for (int i = 0; i < numberOfLayers; i++) {
            Layer previousLayer;
            if (i == 0) {
                previousLayer = null;
            } else {
                previousLayer = layers.get(layers.size() - 1);
            }
//            layers.add(new Layer(numberOfNeuronsInLayer.get(i), previousLayer));
//            previousLayer.set
        }
        this.costFunction = costFunction;
        this.optimizer = optimizer;
    }

    public void train(double[] trainingData, double[] expected) {
        layers.get(0).setInput(trainingData);
    }

    public int evaluate(int[] data) {
        return 1;
    }
}
