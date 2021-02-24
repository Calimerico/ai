package com.romania;

import com.CityDB;
import com.ai.Action;
import com.ai.State;
import lombok.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@ToString
public class RomaniaState implements State {

    private final String state;


    @Override
    public Set<Action> getActions() {
        return CityDB.fromCityWithDistances(state).entrySet().stream().map(s -> new RomaniaAction(s.getValue(),s.getKey())).collect(Collectors.toSet());
    }

    @Override
    public State newState(Action action) {
        RomaniaAction romaniaAction = (RomaniaAction) action;
        return new RomaniaState(romaniaAction.getNextCity());
    }

    @Override
    public double getHeuristicFunction() {
        return CityDB.distanceFromBucharest().get(state);
    }

    @Override
    public State deepCopy() {
        return null;//todo
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RomaniaState that = (RomaniaState) o;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
