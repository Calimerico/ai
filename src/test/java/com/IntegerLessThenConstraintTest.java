package com;

import com.ai.cbp.constraints.IntegerLessThenConstraint;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class IntegerLessThenConstraintTest {

    @Test
    public void test() {
        //given
        IntegerLessThenConstraint constraint = new IntegerLessThenConstraint();
        //when
        boolean violated = constraint.violated(Arrays.asList(3, 4, 4));

        //then
        Assert.assertFalse(violated);
    }
}
