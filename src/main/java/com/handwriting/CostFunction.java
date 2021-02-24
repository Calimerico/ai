package com.handwriting;

import org.apache.commons.math3.geometry.Vector;

public interface CostFunction {
    String getName();
    int[] getGradientVector();
    Double getCost();
}
