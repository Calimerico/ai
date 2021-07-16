package com.ai.cbp.constraints;

import java.util.List;

public interface CBPConstraint <T> {
    boolean violated(List<T> domainValues);
    String getSymbol();
}
