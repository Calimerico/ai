package com;

import com.ai.AStarSearch;
import com.ai.Action;
import com.romania.RomaniaAction;
import com.romania.RomaniaState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AStarSearchTest {

    @Test
    public void test() {
        //given
        AStarSearch search = new AStarSearch();
        //when
        List<Action> actions = search.search(new RomaniaState("Arad"), new RomaniaState("Bucharest"));

        //then
        Assert.assertEquals(((RomaniaAction) actions.get(0)).getNextCity(),CityDB.SIBIU);
        Assert.assertEquals(((RomaniaAction) actions.get(0)).getNextCity(),CityDB.RIMNICU_VILCEA);
        Assert.assertEquals(((RomaniaAction) actions.get(0)).getNextCity(),CityDB.PITESTI);
        Assert.assertEquals(((RomaniaAction) actions.get(0)).getNextCity(),CityDB.BUCHAREST);



    }
}
