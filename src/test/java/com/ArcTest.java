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
        ArrayList<Integer> domainValueA = new ArrayList<>();
        domainValueA.add(2);
        domainValueA.add(3);
        ArrayList<Integer> domainValueB = new ArrayList<>();
        domainValueB.add(1);
        domainValueB.add(2);
        CBPVariable<Integer> variableA = new CBPVariable<>("A", domainValueA);
        CBPVariable<Integer> variableB = new CBPVariable<>("B", domainValueB);
        // a > b
        CBPArc<Integer> arc = new CBPArc<>(asList(variableA, variableB), new IntegerGreaterThenConstraint());

        //when
        boolean variableDomainChanged = arc.execute();

        //then
        Assert.assertFalse(variableDomainChanged);
    }
}
