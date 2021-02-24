package com.ai.cbp;

import com.ai.Algorithm;
import com.ai.cbp.arcs.CBPArc;
import com.ai.cbp.arcs.ConstraintWithVariables;

import java.util.ArrayList;
import java.util.List;

public class AC3Algorithm <T> implements Algorithm {

    public void execute(List<ConstraintWithVariables<T>> constraintWithVariables) {
        List<CBPArc<T>> arcs = new ArrayList<>();
        constraintWithVariables.forEach(cwv -> arcs.addAll(cwv.getArcs()));
        List<CBPArc<T>> agenda = new ArrayList<>(arcs);
        while (!agenda.isEmpty()) {
            CBPArc<T> currentAgenda = agenda.get(0);
            boolean variableDomainChanged = currentAgenda.execute();
            if (variableDomainChanged) {
                for (CBPArc<T> arc : arcs) {
                    if (arc.variableOnTheRightSide(currentAgenda.getFirstVariableName()) && !agenda.contains(arc)) {
                        agenda.add(arc);
                    }
                }
            }
            agenda.remove(0);
        }
    }
}
