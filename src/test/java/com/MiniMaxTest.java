package com;

import com.ai.*;
import org.junit.Test;

import java.util.HashSet;

public class MiniMaxTest {


    @Test
    public void chessTest() {
        //todo wtf sta radi ovaj test
        MiniMax miniMax = new MiniMax(new BreakTest(6));
        HashSet<Action> actionList = new HashSet<>();
        actionList.add(new DummyAction(1));
        actionList.add(new DummyAction(-1));
        actionList.add(new DummyAction(2));
        Action action = miniMax.search(new DummyMiniMax(5, true, actionList));
        System.out.println(action);
    }
}
