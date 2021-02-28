package com.handwriting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
    private List<Double> weights;
    private double bias;

    public Neuron(Layer previousLayer, Layer nextLayer) {
        weights = new ArrayList<>();
        for (int i = 0 ; i < previousLayer.getNeurons().size() ; i++) {
            ThreadLocalRandom.current().nextDouble(0, 2.0);
        }
        bias = ThreadLocalRandom.current().nextDouble(0, 2.0);
    }

    public double getOutput() {
        double output = bias;
//        for(int i = 0 ; i< previousLayer.getNeurons().size() ; i++) {
//            output += previousLayer.getNeurons().get(i).getOutput() * weights.get(i);
//        }
        return output;
    }

    public void setInput(double[] input) {

    }
}
