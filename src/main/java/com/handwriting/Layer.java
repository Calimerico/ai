package com.handwriting;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    double[] input;
    private List<Neuron> neurons;
    private Layer previousLayer;
    private Layer nextLayer;

    public Layer(int numberOfNeurons, Layer previousLayer, Layer nextLayer) {
        this.neurons = new ArrayList<>(numberOfNeurons);
        for (int i = 0; i < numberOfNeurons; i++) {
//            neurons.add(new Neuron(previousLayer));
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setInput() {
        for (int i = 0 ; i < neurons.size() ; i++) {
            double[] input = {this.input[i]};
            neurons.get(i).setInput(input);
        }
    }

    public double[] getOutput() {
        double[] output = new double[neurons.size()];
        for (int i = 0 ; i < neurons.size() ; i++) {
            output[i] = neurons.get(i).getOutput();
        }
        return output;
    }

    public void setInput(double[] trainingData) {

    }
}
