package com;

import com.ai.Action;
import com.ai.BreadthFirstSearch;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BreadthFirstSearchTest {

    @Test
    public void test() {
        //given
        BreadthFirstSearch search = new BreadthFirstSearch();
        //when
        List<Action> actions = search.search(new RomaniaState("Arad"), new RomaniaState("Bucharest"));
        //then
        Assert.assertNotNull(actions);//todo
    }
}
