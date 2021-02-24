package com.ai.cbp;

import java.util.List;

public interface CBPConstraint <T> {
    boolean violated(List<T> domainValues);
    String getSymbol();
}
