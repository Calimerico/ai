package com.ai.cbp.arcs;

import com.ai.cbp.CBPConstraint;
import com.ai.cbp.CBPVariable;

import java.util.List;

public interface ConstraintWithVariables<T> {
    List<CBPArc<T>> getArcs();
}
