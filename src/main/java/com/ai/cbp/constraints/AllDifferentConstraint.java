package com.ai.cbp.constraints;

import java.util.HashSet;
import java.util.List;

public class AllDifferentConstraint <T> implements CBPConstraint<T> {


    @Override
    public boolean violated(List<T> domainValues) {
        return new HashSet<>(domainValues).size() != domainValues.size();
    }

    @Override
    public String getSymbol() {
        return "!=";
    }
}
