package com;

import com.ai.Action;
import com.ai.DepthFirstSearch;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepthFirstSearchTest {

    @Test
    public void test() {
        //given
        DepthFirstSearch search = new DepthFirstSearch();
        //when
        List<Action> actions = search.search(new RomaniaState("Arad"), new RomaniaState("Bucharest"));
        //then
        Assert.assertNotNull(actions);//todo

    }
}
