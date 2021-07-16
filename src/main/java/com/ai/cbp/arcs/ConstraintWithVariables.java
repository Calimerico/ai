package com.ai.cbp.arcs;

import java.util.List;

public interface ConstraintWithVariables<T> {
    List<CBPArc<T>> getArcs();
}
