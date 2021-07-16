package com;

import com.ai.cbp.*;
import com.google.common.collect.Lists;
import com.sudoku.SudokuState;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ac3AlgorithmTest {

    @Test
    public void ABCproblem() {
        //test for this case https://www.youtube.com/watch?v=4cCS8rrYT14&ab_channel=JohnLevine

        //given
        AC3Algorithm<Integer> ac3Algorithm = new AC3Algorithm<>();
        List<Integer> domainValues = new ArrayList<>();
        domainValues.add(1);
        domainValues.add(2);
        domainValues.add(3);

        CBPVariable<Integer> variableA = new CBPVariable<>("A", domainValues);
        CBPVariable<Integer> variableB = new CBPVariable<>("B", domainValues);
        CBPVariable<Integer> variableC = new CBPVariable<>("C", domainValues);

        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();
        // a > b
        constraintWithVariables.add(new IntegerGreaterThenConstraintWithVariables(Lists.newArrayList(variableA, variableB)));
        // b = c
        constraintWithVariables.add(new IntegerEqualsConstraintWithVariables(Lists.newArrayList(variableB, variableC)));

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then
        Assert.assertFalse(variableA.getDomain().contains(1));
        Assert.assertTrue(variableA.getDomain().contains(2));
        Assert.assertTrue(variableA.getDomain().contains(3));

        Assert.assertTrue(variableB.getDomain().contains(1));
        Assert.assertTrue(variableB.getDomain().contains(2));
        Assert.assertFalse(variableB.getDomain().contains(3));

        Assert.assertTrue(variableC.getDomain().contains(1));
        Assert.assertTrue(variableC.getDomain().contains(2));
        Assert.assertFalse(variableC.getDomain().contains(3));

    }

    @Test
    public void ABCDEProblem() {
        //given
        AC3Algorithm<Integer> ac3Algorithm = new AC3Algorithm<>();
        List<Integer> domainValues = new ArrayList<>();
        domainValues.add(1);
        domainValues.add(2);
        domainValues.add(3);
        domainValues.add(4);

        CBPVariable<Integer> variableA = new CBPVariable<>("A", domainValues);
        CBPVariable<Integer> variableB = new CBPVariable<>("B", domainValues);
        CBPVariable<Integer> variableC = new CBPVariable<>("C", domainValues);
        CBPVariable<Integer> variableD = new CBPVariable<>("D", domainValues);
        CBPVariable<Integer> variableE = new CBPVariable<>("E", domainValues);


        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();

        // a < b < c
        constraintWithVariables.add(new IntegerLessThenConstraintWithVariables(Lists.newArrayList(variableA, variableB, variableC)));
        // c = d = e
        constraintWithVariables.add(new IntegerEqualsConstraintWithVariables(Lists.newArrayList(variableC, variableD, variableE)));

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then

        Assert.assertTrue(variableA.getDomain().contains(1));
        Assert.assertTrue(variableA.getDomain().contains(2));
        Assert.assertFalse(variableA.getDomain().contains(3));
        Assert.assertFalse(variableA.getDomain().contains(4));

        Assert.assertTrue(variableB.getDomain().contains(1));
        Assert.assertTrue(variableB.getDomain().contains(2));
        Assert.assertTrue(variableB.getDomain().contains(3));
        Assert.assertTrue(variableB.getDomain().contains(4));

        Assert.assertFalse(variableC.getDomain().contains(1));
        Assert.assertFalse(variableC.getDomain().contains(2));
        Assert.assertTrue(variableC.getDomain().contains(3));
        Assert.assertTrue(variableC.getDomain().contains(4));

        Assert.assertFalse(variableD.getDomain().contains(1));
        Assert.assertFalse(variableD.getDomain().contains(2));
        Assert.assertTrue(variableD.getDomain().contains(3));
        Assert.assertTrue(variableD.getDomain().contains(4));

        Assert.assertFalse(variableE.getDomain().contains(1));
        Assert.assertFalse(variableE.getDomain().contains(2));
        Assert.assertTrue(variableE.getDomain().contains(3));
        Assert.assertTrue(variableE.getDomain().contains(4));

    }

    @Test
    public void sudokuHard() {
        //in resources there is sudokuHard.png picture that represents this sudoku
        List<CBPVariable<Integer>> variables = new ArrayList<>();
        variables.add(new CBPVariable<>("7 1",Lists.newArrayList(4)));

        variables.add(new CBPVariable<>("1 2",Lists.newArrayList(7)));
        variables.add(new CBPVariable<>("4 2",Lists.newArrayList(2)));
        variables.add(new CBPVariable<>("6 2",Lists.newArrayList(3)));

        variables.add(new CBPVariable<>("5 3",Lists.newArrayList(9)));
        variables.add(new CBPVariable<>("6 3",Lists.newArrayList(1)));
        variables.add(new CBPVariable<>("7 3",Lists.newArrayList(6)));

        variables.add(new CBPVariable<>("7 4",Lists.newArrayList(7)));

        variables.add(new CBPVariable<>("2 5",Lists.newArrayList(1)));
        variables.add(new CBPVariable<>("3 5",Lists.newArrayList(6)));
        variables.add(new CBPVariable<>("4 5",Lists.newArrayList(4)));
        variables.add(new CBPVariable<>("5 5",Lists.newArrayList(3)));

        variables.add(new CBPVariable<>("1 6",Lists.newArrayList(9)));
        variables.add(new CBPVariable<>("4 6",Lists.newArrayList(5)));
        variables.add(new CBPVariable<>("9 6",Lists.newArrayList(2)));

        variables.add(new CBPVariable<>("1 7",Lists.newArrayList(5)));
        variables.add(new CBPVariable<>("9 7",Lists.newArrayList(1)));

        variables.add(new CBPVariable<>("2 8",Lists.newArrayList(7)));
        variables.add(new CBPVariable<>("5 8",Lists.newArrayList(5)));
        variables.add(new CBPVariable<>("7 8",Lists.newArrayList(3)));
        variables.add(new CBPVariable<>("9 8",Lists.newArrayList(9)));

        variables.add(new CBPVariable<>("3 9",Lists.newArrayList(9)));
        variables.add(new CBPVariable<>("5 9",Lists.newArrayList(7)));

        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                String variableName = String.valueOf(i) + " " + String.valueOf(j);
                if (!variables.stream().map(CBPVariable::getVariableName).collect(Collectors.toList()).contains(variableName)) {
                    variables.add(new CBPVariable<>(variableName, Lists.newArrayList(1,2,3,4,5,6,7,8,9)));
                }
            }
        }

        addSudokuConstraints(variables, constraintWithVariables);
        AC3Algorithm<Integer> ac3Algorithm = new AC3Algorithm<>();

        //when
        long start = System.currentTimeMillis();
        ac3Algorithm.execute(constraintWithVariables);
        System.out.println("Execution time: " + (System.currentTimeMillis() - start));

        //then
        variables.forEach(variable -> {
            Assert.assertEquals(1,variable.getDomain().size());
        });
        SudokuState sudokuState = new SudokuState(variables);
        sudokuState.print();
    }


        @Test
    public void sudokuEasy() {
        //sudoku primer can be found in AI Modern Approach book on page 234
        List<CBPVariable<Integer>> variables = new ArrayList<>();

        variables.add(new CBPVariable<>("3 1",Lists.newArrayList(3)));
        variables.add(new CBPVariable<>("5 1",Lists.newArrayList(2)));
        variables.add(new CBPVariable<>("7 1",Lists.newArrayList(6)));

        variables.add(new CBPVariable<>("1 2",Lists.newArrayList(9)));
        variables.add(new CBPVariable<>("4 2",Lists.newArrayList(3)));
        variables.add(new CBPVariable<>("6 2",Lists.newArrayList(5)));
        variables.add(new CBPVariable<>("9 2",Lists.newArrayList(1)));

        variables.add(new CBPVariable<>("3 3",Lists.newArrayList(1)));
        variables.add(new CBPVariable<>("4 3",Lists.newArrayList(8)));
        variables.add(new CBPVariable<>("6 3",Lists.newArrayList(6)));
        variables.add(new CBPVariable<>("7 3",Lists.newArrayList(4)));

        variables.add(new CBPVariable<>("3 4",Lists.newArrayList(8)));
        variables.add(new CBPVariable<>("4 4",Lists.newArrayList(1)));
        variables.add(new CBPVariable<>("6 4",Lists.newArrayList(2)));
        variables.add(new CBPVariable<>("7 4",Lists.newArrayList(9)));

        variables.add(new CBPVariable<>("1 5",Lists.newArrayList(7)));
        variables.add(new CBPVariable<>("9 5",Lists.newArrayList(8)));

        variables.add(new CBPVariable<>("3 6",Lists.newArrayList(6)));
        variables.add(new CBPVariable<>("4 6",Lists.newArrayList(7)));
        variables.add(new CBPVariable<>("6 6",Lists.newArrayList(8)));
        variables.add(new CBPVariable<>("7 6",Lists.newArrayList(2)));

        variables.add(new CBPVariable<>("3 7",Lists.newArrayList(2)));
        variables.add(new CBPVariable<>("4 7",Lists.newArrayList(6)));
        variables.add(new CBPVariable<>("6 7",Lists.newArrayList(9)));
        variables.add(new CBPVariable<>("7 7",Lists.newArrayList(5)));

        variables.add(new CBPVariable<>("1 8",Lists.newArrayList(8)));
        variables.add(new CBPVariable<>("4 8",Lists.newArrayList(2)));
        variables.add(new CBPVariable<>("6 8",Lists.newArrayList(3)));
        variables.add(new CBPVariable<>("9 8",Lists.newArrayList(9)));

        variables.add(new CBPVariable<>("3 9",Lists.newArrayList(5)));
        variables.add(new CBPVariable<>("5 9",Lists.newArrayList(1)));
        variables.add(new CBPVariable<>("7 9",Lists.newArrayList(3)));

        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                String variableName = i + " " + j;
                if (!variables.stream().map(CBPVariable::getVariableName).collect(Collectors.toList()).contains(variableName)) {
                    variables.add(new CBPVariable<>(variableName, Lists.newArrayList(1,2,3,4,5,6,7,8,9)));
                }
            }
        }


        addSudokuConstraints(variables, constraintWithVariables);
        AC3Algorithm<Integer> ac3Algorithm = new AC3Algorithm<>();

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then
        variables.forEach(variable -> {
            Assert.assertEquals(1,variable.getDomain().size());
        });
        SudokuState sudokuState = new SudokuState(variables);
        sudokuState.print();
    }

    private void addSudokuConstraints(List<CBPVariable<Integer>> variables, List<ConstraintWithVariables<Integer>> constraintWithVariables) {
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 9")).findAny().orElse(null)
        )));


        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 1")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 2")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 3")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 4")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 5")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 6")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 7")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 8")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 9")).findAny().orElse(null)
        )));


        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 3")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 3")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 3")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 1")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 2")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 3")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 6")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 6")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 6")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 4")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 5")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 6")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("1 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("2 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("3 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("4 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("5 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("6 9")).findAny().orElse(null)
        )));
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(Lists.newArrayList(
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("7 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("8 9")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 7")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 8")).findAny().orElse(null),
                variables.stream().filter(cbpIntegerVariable -> cbpIntegerVariable.getVariableName().equals("9 9")).findAny().orElse(null)
        )));
    }
}
