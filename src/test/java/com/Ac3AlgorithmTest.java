package com;

import com.ai.cbp.*;
import com.ai.cbp.arcs.*;
import com.sudoku.SudokuState;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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
        constraintWithVariables.add(new IntegerGreaterThenConstraintWithVariables(asList(variableA, variableB)));
        // b = c
        constraintWithVariables.add(new IntegerEqualsConstraintWithVariables(asList(variableB, variableC)));

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then
        Assert.assertFalse(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertTrue(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertTrue(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));

        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertFalse(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));

        Assert.assertTrue(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertTrue(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertFalse(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));

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

        CBPVariable<Integer> variableA = new CBPVariable<Integer>("A", domainValues);
        CBPVariable<Integer> variableB = new CBPVariable<Integer>("B", domainValues);
        CBPVariable<Integer> variableC = new CBPVariable<Integer>("C", domainValues);
        CBPVariable<Integer> variableD = new CBPVariable<Integer>("D", domainValues);
        CBPVariable<Integer> variableE = new CBPVariable<Integer>("E", domainValues);


        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();

        // a < b < c
        constraintWithVariables.add(new IntegerLessThenConstraintWithVariables(asList(variableA, variableB, variableC)));
        // c = d = e
        constraintWithVariables.add(new IntegerEqualsConstraintWithVariables(asList(variableC, variableD, variableE)));

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then

        Assert.assertTrue(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertTrue(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertFalse(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));
        Assert.assertFalse(variableA.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(4));

        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));
        Assert.assertTrue(variableB.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(4));

        Assert.assertFalse(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertFalse(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertTrue(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));
        Assert.assertTrue(variableC.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(4));

        Assert.assertFalse(variableD.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertFalse(variableD.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertTrue(variableD.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));
        Assert.assertTrue(variableD.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(4));

        Assert.assertFalse(variableE.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(1));
        Assert.assertFalse(variableE.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(2));
        Assert.assertTrue(variableE.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(3));
        Assert.assertTrue(variableE.getDomain().getDomainValues().stream().map(CBPDomainValue::getValue).collect(Collectors.toList()).contains(4));

    }

    @Test
    public void sudokuHard() {
        //in resources there is sudokuHard.png picture that represents this sudoku
        List<CBPVariable<Integer>> variables = new ArrayList<>();

        variables.add(new CBPVariable<Integer>("7 1",asList(4)));

        variables.add(new CBPVariable<Integer>("1 2",asList(7)));
        variables.add(new CBPVariable<Integer>("4 2",asList(2)));
        variables.add(new CBPVariable<Integer>("6 2",asList(3)));

        variables.add(new CBPVariable<Integer>("5 3",asList(9)));
        variables.add(new CBPVariable<Integer>("6 3",asList(1)));
        variables.add(new CBPVariable<Integer>("7 3",asList(6)));

        variables.add(new CBPVariable<Integer>("7 4",asList(7)));

        variables.add(new CBPVariable<Integer>("2 5",asList(1)));
        variables.add(new CBPVariable<Integer>("3 5",asList(6)));
        variables.add(new CBPVariable<Integer>("4 5",asList(4)));
        variables.add(new CBPVariable<Integer>("5 5",asList(3)));

        variables.add(new CBPVariable<Integer>("1 6",asList(9)));
        variables.add(new CBPVariable<Integer>("4 6",asList(5)));
        variables.add(new CBPVariable<Integer>("9 6",asList(2)));

        variables.add(new CBPVariable<Integer>("1 7",asList(5)));
        variables.add(new CBPVariable<Integer>("9 7",asList(1)));

        variables.add(new CBPVariable<Integer>("2 8",asList(7)));
        variables.add(new CBPVariable<Integer>("5 8",asList(5)));
        variables.add(new CBPVariable<Integer>("7 8",asList(3)));
        variables.add(new CBPVariable<Integer>("9 8",asList(9)));

        variables.add(new CBPVariable<Integer>("3 9",asList(9)));
        variables.add(new CBPVariable<Integer>("5 9",asList(7)));

        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                String variableName = String.valueOf(i) + " " + String.valueOf(j);
                if (!variables.stream().map(CBPVariable::getVariableName).collect(Collectors.toList()).contains(variableName)) {
                    variables.add(new CBPVariable<Integer>(variableName, asList(1,2,3,4,5,6,7,8,9)));
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
            Assert.assertEquals(1,variable.getDomain().getDomainValues().size());
        });
        SudokuState sudokuState = new SudokuState(variables);
        sudokuState.print();
    }


        @Test
    public void sudokuEasy() {
        //sudoku primer can be found in AI Modern Approach book on page 234
        List<CBPVariable<Integer>> variables = new ArrayList<>();

        variables.add(new CBPVariable<Integer>("3 1",asList(3)));
        variables.add(new CBPVariable<Integer>("5 1",asList(2)));
        variables.add(new CBPVariable<Integer>("7 1",asList(6)));

        variables.add(new CBPVariable<Integer>("1 2",asList(9)));
        variables.add(new CBPVariable<Integer>("4 2",asList(3)));
        variables.add(new CBPVariable<Integer>("6 2",asList(5)));
        variables.add(new CBPVariable<Integer>("9 2",asList(1)));

        variables.add(new CBPVariable<Integer>("3 3",asList(1)));
        variables.add(new CBPVariable<Integer>("4 3",asList(8)));
        variables.add(new CBPVariable<Integer>("6 3",asList(6)));
        variables.add(new CBPVariable<Integer>("7 3",asList(4)));

        variables.add(new CBPVariable<Integer>("3 4",asList(8)));
        variables.add(new CBPVariable<Integer>("4 4",asList(1)));
        variables.add(new CBPVariable<Integer>("6 4",asList(2)));
        variables.add(new CBPVariable<Integer>("7 4",asList(9)));

        variables.add(new CBPVariable<Integer>("1 5",asList(7)));
        variables.add(new CBPVariable<Integer>("9 5",asList(8)));

        variables.add(new CBPVariable<Integer>("3 6",asList(6)));
        variables.add(new CBPVariable<Integer>("4 6",asList(7)));
        variables.add(new CBPVariable<Integer>("6 6",asList(8)));
        variables.add(new CBPVariable<Integer>("7 6",asList(2)));

        variables.add(new CBPVariable<Integer>("3 7",asList(2)));
        variables.add(new CBPVariable<Integer>("4 7",asList(6)));
        variables.add(new CBPVariable<Integer>("6 7",asList(9)));
        variables.add(new CBPVariable<Integer>("7 7",asList(5)));

        variables.add(new CBPVariable<Integer>("1 8",asList(8)));
        variables.add(new CBPVariable<Integer>("4 8",asList(2)));
        variables.add(new CBPVariable<Integer>("6 8",asList(3)));
        variables.add(new CBPVariable<Integer>("9 8",asList(9)));

        variables.add(new CBPVariable<Integer>("3 9",asList(5)));
        variables.add(new CBPVariable<Integer>("5 9",asList(1)));
        variables.add(new CBPVariable<Integer>("7 9",asList(3)));

        List<ConstraintWithVariables<Integer>> constraintWithVariables = new ArrayList<>();


        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                String variableName = String.valueOf(i) + " " + String.valueOf(j);
                if (!variables.stream().map(CBPVariable::getVariableName).collect(Collectors.toList()).contains(variableName)) {
                    variables.add(new CBPVariable<Integer>(variableName, asList(1,2,3,4,5,6,7,8,9)));
                }
            }
        }


        addSudokuConstraints(variables, constraintWithVariables);
        AC3Algorithm<Integer> ac3Algorithm = new AC3Algorithm<>();

        //when
        ac3Algorithm.execute(constraintWithVariables);

        //then
        variables.forEach(variable -> {
            Assert.assertEquals(1,variable.getDomain().getDomainValues().size());
        });
        SudokuState sudokuState = new SudokuState(variables);
        sudokuState.print();
    }

    private void addSudokuConstraints(List<CBPVariable<Integer>> variables, List<ConstraintWithVariables<Integer>> constraintWithVariables) {
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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


        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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


        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
        constraintWithVariables.add(new AllDifferentConstraintWithVariables(asList(
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
