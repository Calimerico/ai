package com.ai.cbp.arcs;

import com.ai.cbp.CBPVariable;
import com.ai.cbp.constraints.IntegerGreaterThenConstraint;
import com.ai.cbp.constraints.IntegerLessThenConstraint;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class IntegerGreaterThenConstraintWithVariables implements ConstraintWithVariables<Integer> {

    private final List<CBPVariable<Integer>> variables;

    @Override
    public List<CBPArc<Integer>> getArcs() {
        LinkedList<CBPVariable<Integer>> variablesReversed = new LinkedList<>();
        variables.forEach(variablesReversed::addFirst);
        CBPArc<Integer> arc1 = new CBPArc<>(variables, new IntegerGreaterThenConstraint());
        CBPArc<Integer> arc2 = new CBPArc<>(variablesReversed, new IntegerLessThenConstraint());
        ArrayList<CBPArc<Integer>> arcs = new ArrayList<>();
        arcs.add(arc1);
        arcs.add(arc2);
        return arcs;
    }
}
