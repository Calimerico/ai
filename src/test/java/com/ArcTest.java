package com;

import com.ai.cbp.CBPVariable;
import com.ai.cbp.arcs.CBPArc;
import com.ai.cbp.constraints.IntegerGreaterThenConstraint;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class ArcTest {

    @Test
    public void test() {
        //given
        ArrayList<Integer> domainValues = new ArrayList<>();
        domainValues.add(1);
        domainValues.add(2);
        domainValues.add(3);
        CBPVariable<Integer> variableA = new CBPVariable<>("A", domainValues);
        CBPVariable<Integer> variableB = new CBPVariable<>("B", domainValues);
        variableA.removeDomainValue(1);
        variableB.removeDomainValue(3);
        // a > b
        CBPArc<Integer> arc = new CBPArc<>(asList(variableA, variableB), new IntegerGreaterThenConstraint());

        //when
        boolean variableDomainChanged = arc.execute();

        //then
        Assert.assertFalse(variableDomainChanged);
    }
}
