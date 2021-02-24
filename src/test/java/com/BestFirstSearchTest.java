package com;

import com.ai.Action;
import com.ai.BestFirstSearch;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BestFirstSearchTest {
    @Test
    public void test() {
        //given
        BestFirstSearch search = new BestFirstSearch();
        //when
        List<Action> actions = search.search(new RomaniaState("Arad"), new RomaniaState("Bucharest"));
        //then
        Assert.assertEquals(((RomaniaAction) actions.get(0)).getNextCity(),CityDB.SIBIU);
        Assert.assertEquals(((RomaniaAction) actions.get(1)).getNextCity(),CityDB.FAGARAS);
        Assert.assertEquals(((RomaniaAction) actions.get(2)).getNextCity(),CityDB.BUCHAREST);
    }
}
