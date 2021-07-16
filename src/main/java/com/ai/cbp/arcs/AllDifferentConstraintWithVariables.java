package com.ai.cbp.arcs;

import com.ai.cbp.CBPVariable;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AllDifferentConstraintWithVariables implements ConstraintWithVariables<Integer> {

    private final List<CBPVariable<Integer>> variables;

    @Override
    public List<CBPArc<Integer>> getArcs() {
        List<CBPArc<Integer>> arcs = new ArrayList<>();
        variables.forEach(variable -> {
            LinkedList<CBPVariable<Integer>> variablesWithoutCurrent = variables
                    .stream()
                    .filter(v -> !variable.equals(v))
                    .collect(Collectors.toCollection(LinkedList::new));
            variablesWithoutCurrent.addFirst(variable);
            arcs.add(new CBPArc<>(variablesWithoutCurrent,new com.ai.cbp.constraints.AllDifferentConstraint<>()));
        });
        return arcs;
    }
}
