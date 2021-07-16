package com.ai.cbp;

import java.util.List;

public interface ConstraintWithVariables<T> {
    List<CBPArc<T>> getArcs();
}
