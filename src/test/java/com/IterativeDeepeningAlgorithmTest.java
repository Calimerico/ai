package com;

import com.ai.Action;
import com.ai.IterativeDeepeningDepthFirstSearch;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IterativeDeepeningAlgorithmTest {
    @Test
    public void test() {
        //given
        IterativeDeepeningDepthFirstSearch search = new IterativeDeepeningDepthFirstSearch();
        //when
        List<Action> actions = search.search(new RomaniaState("Arad"), new RomaniaState("Bucharest"));
        //then
        Assert.assertNotNull(actions);//todo
    }
}
