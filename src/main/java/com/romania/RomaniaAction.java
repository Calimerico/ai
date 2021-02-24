package com.romania;

import com.ai.Action;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class RomaniaAction implements Action {
    double pathCost;
    String nextCity;

    @Override
    public double pathCost() {
        return pathCost;
    }
}
